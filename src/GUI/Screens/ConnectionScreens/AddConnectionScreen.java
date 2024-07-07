package GUI.Screens.ConnectionScreens;

import Domain.RailwayStation;
import Exceptions.NoSuchObjectException;
import Exceptions.ObjectAlreadyExistsException;
import GUI.Screen;
import GUI.ConfirmationDialog;

import java.util.Scanner;

public class AddConnectionScreen extends Screen {
    public AddConnectionScreen(){
        title = "Add connection";
    }
    public void run(){
        System.out.println("=== " + title + " ===");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                RailwayStation first = null;
                RailwayStation second = null;
                while (true) {
                    System.out.println("Enter the first station: ");
                    try {
                        first = railwayGrid.getStation(scanner.nextLine());
                        break;
                    } catch (NoSuchObjectException e) {
                        System.out.println(e.getMessage());
                    }
                }
                while (true) {
                    System.out.println("Enter the second station: ");
                    try {
                        second = railwayGrid.getStation(scanner.nextLine());
                        if(first == second) System.out.println("Enter different stations.");
                        else break;
                    } catch (NoSuchObjectException e) {
                        System.out.println(e.getMessage());
                    }
                }
                    while (true) {
                    System.out.println("Enter length: ");
                    try {
                        railwayGrid.connect(first, second, scanner.nextInt());
                        scanner.nextLine();
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            catch (ObjectAlreadyExistsException e){
                System.out.println(e.getMessage());
                scanner.nextLine();
                continue;
            }
            System.out.println("Connection successfully added.\n");
            if (ConfirmationDialog.display("Create another connection?")==ConfirmationDialog.NO)
                break;
        }
    }
}
