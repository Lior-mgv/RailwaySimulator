package GUI.Screens.StationScreens;

import GUI.Components.StationListComponent;
import GUI.Screen;
import GUI.ConfirmationDialog;

import java.util.Scanner;

public class DeleteStationScreen extends Screen {
    private final StationListComponent stationList;

    public DeleteStationScreen(){
        title = "Delete station";
        stationList = new StationListComponent();
    }
    public void run(){
        System.out.println("=== " + title + " ===");
        Scanner scanner = new Scanner(System.in);
        while(true){
            int input = -1;
            while (true){
                System.out.println("Enter the number of the station you'd like to delete: ");
                input = scanner.nextInt();
                if(input <= railwayGrid.getStations().size() && input >= 1) break;
            }
            railwayGrid.removeRailwayStation(railwayGrid.getStations().get(input - 1));
            System.out.println("Station successfully deleted.\nRemaining stations:");
            stationList.display(railwayGrid);

            if (ConfirmationDialog.display("Delete another station?")==ConfirmationDialog.NO)
                break;
        }
    }
}
