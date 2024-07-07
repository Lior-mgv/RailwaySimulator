package GUI.Screens.StationScreens;

import Exceptions.ObjectAlreadyExistsException;
import GUI.Screen;
import GUI.ConfirmationDialog;

import java.util.Scanner;

public class AddStationScreen extends Screen {
    public AddStationScreen() {
        title = "Add station";
    }
    public void run(){
        System.out.println("=== " + title + " ===");
        while (true) {
            System.out.println("Enter the name of the station: ");
            Scanner scanner = new Scanner(System.in);
            try {
                railwayGrid.addRailwayStation(scanner.nextLine());
            } catch (ObjectAlreadyExistsException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Station successfully added.\n");
            if (ConfirmationDialog.display("Create another station?")==ConfirmationDialog.NO)
                break;
        }
    }
}
