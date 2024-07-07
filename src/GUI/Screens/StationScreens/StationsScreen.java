package GUI.Screens.StationScreens;

import GUI.Screen;

public class StationsScreen extends Screen {
    public StationsScreen() {
        title = "Stations";
        super.menu.add(1, "Add", new AddStationScreen());
        super.menu.add(2, "Delete", new DeleteStationScreen());
        super.menu.add(3, "Back", null);
    }
    protected void showSpecificContent(){
        System.out.println("Existing stations:");
        int num = 1;
        for (var st : railwayGrid.getStations()) {
            System.out.println(num++ + ". " + st + " (" +
                    (st.getConnections().isEmpty() ? "No connections" : "Connections: " + st.getConnections()) + ")");
        }
        System.out.println("\nOptions:");
    }
}
