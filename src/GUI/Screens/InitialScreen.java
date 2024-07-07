package GUI.Screens;

import GUI.Screen;
import GUI.Screens.ConnectionScreens.ConnectionsScreen;
import GUI.Screens.StationScreens.StationsScreen;
import GUI.Screens.TrainsetScreens.TrainsetsScreen;

public class InitialScreen extends Screen {
    public InitialScreen() {
        super.title = "Home";
        super.menu.add(1, "Stations", new StationsScreen());
        super.menu.add(2, "Connections", new ConnectionsScreen());
        super.menu.add(3, "Train sets", new TrainsetsScreen());
        super.menu.add(4, "Start simulation", new SimulationScreen());
    }
}
