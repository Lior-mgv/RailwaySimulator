package Tests;

import Domain.RailwayGrid;
import GUI.Screen;
import Simulation.TrainRide;
import java.util.ArrayList;

public class MassRideTest {
    public static void main(String[] args) throws Exception {
        var grid = new RailwayGrid();
        var a = grid.addRailwayStation("A");
        var b = grid.addRailwayStation("B");
        var c = grid.addRailwayStation("C");
        var d = grid.addRailwayStation("D");
        var e = grid.addRailwayStation("E");
        var m = grid.addRailwayStation("M");

        int baseLength = 30;
        grid.connect(a,b,baseLength);
        grid.connect(a,c, (int) (baseLength*1.1));
        grid.connect(b,d, (int) (baseLength*1.2));
        grid.connect(c,d,baseLength);
        grid.connect(d,e, (int) (baseLength*0.6));
        grid.connect(d,m, (int) (baseLength*0.8));
        grid.connect(m,a,baseLength*3);

        grid.generateTrains(15);

        var simulations = new ArrayList<TrainRide>();
        for (var trainset : grid.getTrainsets()) {
            var path = RailwayGrid.findPath(trainset.getStart(), trainset.getDest());
            if(path.getPath().size() == 0){
                System.out.println("Train " + trainset.getName() + " cannot reach its destination.");
            }
            simulations.add(new TrainRide(path, trainset));
        }

        System.out.println("Simulation started.\n");
        simulations.forEach(TrainRide::start);

        while(true) {
            Screen.clearConsole();
            for (var sim:simulations) {
                if (sim.getWaitingDuraionSec()>120) {
                    throw new Exception(String.format("Train %s is stuck in the queue", sim.getTrainset().getName()));
                }
                sim.printState();
            }
            System.out.println("======================================================");
            Thread.sleep(2000);
        }
    }
}
