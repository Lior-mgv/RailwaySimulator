import Domain.Cars.BasicFreightCar;
import Domain.Cars.LiquidMaterialsCar;
import Domain.Cars.PassengerCar;
import Domain.Locomotive;
import Domain.RailwayGrid;
import Domain.RailwayStation;
import Domain.Trainset;
import Exceptions.ElectricGridException;
import Exceptions.ObjectAlreadyExistsException;
import Exceptions.OverweightException;
import GUI.Screens.InitialScreen;
import Simulation.RailwayPath;
import Simulation.TrainRide;

public class Main {
    public static void main(String[] args) {
        RailwayGrid grid = new RailwayGrid();
        var screen = new InitialScreen();
        screen.setRailwayGrid(grid);
        screen.run();
    }
}
