package Tests;

import Domain.Locomotive;
import Domain.RailwayGrid;
import Domain.RailwayStation;
import Domain.Trainset;
import Exceptions.ObjectAlreadyExistsException;
import Simulation.RailwayPath;
import Simulation.TrainRide;

public class CollisionPreventionTest {
    public static void main(String[] args) throws InterruptedException, ObjectAlreadyExistsException {
        RailwayGrid grid = new RailwayGrid();
        RailwayStation a = grid.addRailwayStation("a");
        RailwayStation b = grid.addRailwayStation("b");
        grid.connect(a,b,50);
        Trainset train1 = new Trainset("Train 1", new Locomotive(""),a,b);
        Trainset train2 = new Trainset("Train 2", new Locomotive(""),a,b);
        RailwayPath path = RailwayGrid.findPath(a, b);
        TrainRide trainRide1 = new TrainRide(path, train1);
        TrainRide trainRide2 = new TrainRide(path, train2);
        trainRide1.start();
        trainRide1.printState();
        Thread.sleep(1000);
        trainRide1.printState();
        Thread.sleep(1000);
        trainRide2.start();
        while(true){
            trainRide1.printState();
            trainRide2.printState();
            System.out.println();
            Thread.sleep(1000);
        }
    }
}
