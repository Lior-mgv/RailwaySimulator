import Domain.Cars.BaggageMailCar;
import Domain.Cars.GaseousMaterialsCar;
import Domain.Cars.PassengerCar;
import Domain.Cars.RefrigeratorCar;
import Domain.Locomotive;
import Domain.RailwayGrid;
import Domain.RailwayStation;
import Domain.Trainset;
import Exceptions.ElectricGridException;
import Exceptions.ObjectAlreadyExistsException;
import Exceptions.OverweightException;
import Simulation.TrainRide;
import Simulation.TrainState;

import javax.management.InvalidApplicationException;
import java.time.Duration;
import java.time.LocalTime;

public class Presentation {
    public static void main(String[] args) throws InterruptedException, InvalidApplicationException {
        var grid = new RailwayGrid();

        // Adding some stations
        RailwayStation stationA = null, stationB = null;
        try {
            stationA = grid.addRailwayStation("A");
            stationB = grid.addRailwayStation("B");
        } catch(ObjectAlreadyExistsException e) {
            // it's forbidden to add 2 stations with the same name
        }

        // Connecting these stations
        try {
            grid.connect(stationA, stationB, 14);
        }catch (ObjectAlreadyExistsException e) {
            // if a connection between 2 stations already exists, it's forbidden to add another one
        }

        // Creating a train-set to run between the stations
        var locomotive = new Locomotive();
        var trainset = new Trainset("T1243", locomotive, stationA, stationB);
        try {
            trainset.addCar(new PassengerCar());
            trainset.addCar(new BaggageMailCar());
            trainset.addCar(new RefrigeratorCar());

            // a car may also be removed from the train-set
            var extra = trainset.addCar(new GaseousMaterialsCar());
            trainset.removeCar(extra);
        } catch (ElectricGridException e) {
            // if a car being added exceeds the electric grid capacity of the locomotive
        } catch (OverweightException e) {
            // if the car being added exceeds max weight capacity of the locomotive
        }

        // Once everything is in place, we can run a simulation of the train ride
        var trainRide = new TrainRide(RailwayGrid.findPath(stationA, stationB), trainset);
        trainRide.start();

        // After some time, let's run another train-set on this railway grid
        Thread.sleep(5000);
        var trainset2 = new Trainset("Trainset2", new Locomotive(), stationB, stationA);
        var trainRide2 = new TrainRide(RailwayGrid.findPath(stationB, stationA), trainset2);
        trainRide2.start();

        // To demonstrate the collision prevention logic, let's monitor the rides for some time
        // and make sure the trains don't move along the same connection (while one is moving, another one awaits)
        var started = LocalTime.now();
        while(Duration.between(started, LocalTime.now()).toSeconds()<60) {
            if (trainRide.getTrainState()==TrainState.ON_WAY && trainRide2.getTrainState()== TrainState.ON_WAY) {
                throw new InvalidApplicationException("This should never happen");
            }
            Thread.sleep(100);
        }

        // train ride simulation can be terminated when necessary
        trainRide.cancelRide();
        trainRide2.cancelRide();
    }

}
