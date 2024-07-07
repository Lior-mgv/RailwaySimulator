package Tests;

import Domain.RailwayGrid;
import Domain.RailwayStation;
import Exceptions.ObjectAlreadyExistsException;
import Simulation.RailwayPath;
import Simulation.TrainLocation;

public class FindPathTest {
    public static void main(String[] args) throws ObjectAlreadyExistsException {
        RailwayGrid grid = new RailwayGrid();
        RailwayStation a = grid.addRailwayStation("a");
        RailwayStation b = grid.addRailwayStation("b");
        RailwayStation c = grid.addRailwayStation("c");
        RailwayStation d = grid.addRailwayStation("d");
        RailwayStation e = grid.addRailwayStation("e");
        grid.connect(a, b, 14);
        grid.connect(a, c, 14);
        grid.connect(c, d, 14);
        grid.connect(d, b, 14);
        grid.connect(d, e, 14);
        grid.connect(b, e, 14);
        RailwayPath path = RailwayGrid.findPath(a,e);
        System.out.println(path.getNextStation(b));
        TrainLocation trainLocation = new TrainLocation(path.getPath().get(2), 3);
        System.out.println(path.getDistanceFromStart(trainLocation));
    }
}
