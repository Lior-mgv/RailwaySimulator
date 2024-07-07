package GUI.Components;

import Domain.RailwayGrid;

public class StationListComponent {
    public StationListComponent() {
    }
    public void display(RailwayGrid railwayGrid) {
        int num = 1;
        for (var st : railwayGrid.getStations()) {
            System.out.println(num++ + ". " + st + " (" +
                    (st.getConnections().isEmpty() ? "No connections" : "Connections: " + st.getConnections()) + ")");
        }
    }
}
