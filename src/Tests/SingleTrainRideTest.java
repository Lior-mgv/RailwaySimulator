package Tests;

import Domain.*;
import Exceptions.ObjectAlreadyExistsException;
import Simulation.*;

public class SingleTrainRideTest {
    public static void main(String[] args) throws InterruptedException, ObjectAlreadyExistsException {
        RailwayGrid grid = new RailwayGrid();
        RailwayStation a = grid.addRailwayStation("a");
        RailwayStation b = grid.addRailwayStation("b");
        RailwayStation c = grid.addRailwayStation("c");
        grid.connect(a, b, 30);
        grid.connect(b, c, 60);
        RailwayPath path = RailwayGrid.findPath(a, c);
        Trainset trainset = new Trainset("Train 1", new Locomotive(" "),a,c);
        TrainRide trainRide = new TrainRide(path, trainset);
        trainRide.start();
        while (true){
            trainRide.printState();
                Thread.sleep(200);
        }
    }
}
