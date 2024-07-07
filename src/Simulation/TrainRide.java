package Simulation;

import Domain.RailwayGrid;
import Domain.Trainset;
import Exceptions.RailroadHazard;

import java.time.Duration;
import java.time.LocalTime;

public class TrainRide extends Thread implements Comparable<TrainRide>{
    @Override
    public int compareTo(TrainRide second) {
        return Integer.compare(this.getPath().getTotalDistance(), second.getPath().getTotalDistance());
    }

    private RailwayPath path;
    private Trainset trainset;
    private TrainLocation location;
    private TrainState state;
    private SimulationTime simulationTime;
    private boolean cancel = false;

    public TrainRide(RailwayPath path, Trainset trainset){
        this.path = path;
        this.trainset = trainset;
        state = TrainState.INITIAL;
    }
    public void run(){
        simulationTime = new SimulationTime();
        try {
            while (!rideIsCancelled()) {
                if (state == TrainState.INITIAL) {
                    location = new TrainLocation(path.getConnection(0), 0);
                    state = TrainState.IN_QUEUE;
                }
                else if (state == TrainState.IN_QUEUE) {
                    // wait for permission to go
                    var coordinator = location.getConnection().getCoordinator();
                    coordinator.waitToGo(trainset);

                    // start moving
                    location.getConnection().setOccupied(true);
                    trainset.setInitialSpeed();
                    simulationTime.updateFromSystemClock();
                    state = TrainState.ON_WAY;
                }
                else if (state==TrainState.ON_WAY){
                    // figure out time and distance until the next change of speed, or the next station
                    Duration time = TimeTools.toVirtualTime(Duration.ofMillis(SimulationSettings.waitAfterSpeedChange));
                    double distance = TimeTools.toHours(time) * trainset.getSpeed();
                    if (location.getDistanceToNextStation() <= distance) {
                        // next station will be reached before the change of speed
                        distance = location.getDistanceToNextStation();
                        time = TimeTools.ofHours(distance / trainset.getSpeed());
                    }

                    // wait until the next event
                    waitUntil(simulationTime.projectTo(time));

                    // update the simulation
                    location.moveForward(distance);
                    simulationTime.increment(time);
                    if (location.nextStationReached()) {
                        state = TrainState.AT_STATION;
                    }
                    else {
                        try {
                            trainset.changeSpeed();
                        }catch (RailroadHazard e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
                else if (state==TrainState.AT_STATION) {
                    location.getConnection().setOccupied(false);

                    Duration waitDuration;
                    if(location.nextStation()==path.getEnd()) {
                        // final station reached
                        path = RailwayGrid.findPath(path.getEnd(), path.getStart());
                        location = new TrainLocation(path.getConnection(0), 0);
                        waitDuration = TimeTools.toVirtualTime(SimulationSettings.waitAtDestination);
                    }
                    else {
                        // intermediate stop
                        location = new TrainLocation(path.getNextConnection(location.getConnection()), 0);
                        waitDuration = TimeTools.toVirtualTime(SimulationSettings.waitAtStation);
                    }

                    waitUntil(simulationTime.projectTo(waitDuration));
                    simulationTime.increment(waitDuration);
                    state = TrainState.IN_QUEUE;
                }
            }
        }
        catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    private void waitUntil(SimulationTime time) throws InterruptedException {
        Duration realtimeDuration = Duration.between(LocalTime.now(), time.toRealTime());
        if (realtimeDuration.isNegative())
            return;
        Thread.sleep(realtimeDuration.toMillis());
    }

    private TrainLocation getRealtimeLocation(){
        Duration timeSinceLastUpdate = Duration.between(simulationTime.toRealTime(), LocalTime.now());
        double passedSinceLastUpdate = TimeTools.toHours(TimeTools.toVirtualTime(timeSinceLastUpdate)) * trainset.getSpeed();
        return location.projectForward(passedSinceLastUpdate);
    }

    public void printState(){
        switch (getTrainState()) {
            case INITIAL -> System.out.println(trainset + " - initializing in process.");
            case ON_WAY -> {
                var realLocation = getRealtimeLocation();
                System.out.println(trainset + " is on its way from " + getLocation().getConnection().getStart() + " to "
                        + getLocation().getConnection().getEnd() + " (" + (int) (realLocation.getPassed() / (realLocation.getConnection().getLength() / 100))
                        + "%). Total distance completed for " + (int) ((path.getDistanceFromStart(realLocation) / (path.getTotalDistance() / 100.)))
                        + "%.");
            }
            case AT_STATION ->
                    System.out.println(trainset + " is at station " + location.getStation() + ". Total distance completed for "
                            + (int) ((path.getDistanceFromStart(location) / (path.getTotalDistance() / 100.))) + "%.");
            case IN_QUEUE -> System.out.println(trainset + " is waiting at station " + location.getStation()
                    + " for " + getWaitingDuraionSec() + " sec."
                    + " Total distance completed for "
                    + (int) ((path.getDistanceFromStart(location) / (path.getTotalDistance() / 100.))) + "%.");
        }
    }
        
    public long getWaitingDuraionSec() {
        if (state!=TrainState.IN_QUEUE)
            return 0;
        return Duration.between(simulationTime.toRealTime(), LocalTime.now()).toSeconds();
    }

    public RailwayPath getPath() {
        return path;
    }

    public TrainLocation getLocation() {
        return location;
    }

    public Trainset getTrainset() {
        return trainset;
    }

    public TrainState getTrainState() {
        return state;
    }
    @Override
    public String toString() {
        return trainset.toString() + " (Path length: " + path.getTotalDistance() + " km)";
    }
    public synchronized void cancelRide(){
        cancel = true;
    }
    public synchronized boolean rideIsCancelled(){
        return cancel;
    }
}
