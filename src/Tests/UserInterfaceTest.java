package Tests;

import Domain.Locomotive;
import Domain.RailwayGrid;
import Domain.Trainset;
import Exceptions.ObjectAlreadyExistsException;
import GUI.Screens.InitialScreen;


public class UserInterfaceTest {
    public static void main(String[] args) throws ObjectAlreadyExistsException {
        RailwayGrid railwayGrid = new RailwayGrid();
        var a = railwayGrid.addRailwayStation("A");
        var b = railwayGrid.addRailwayStation("B");
        railwayGrid.connect(a, b, 15);
        railwayGrid.addTrainset(new Trainset("", new Locomotive(""), a, b));
        var screen = new InitialScreen();
        screen.setRailwayGrid(railwayGrid);
        screen.run();
    }
}
