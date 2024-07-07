package GUI.Screens.TrainsetScreens;

import Domain.Locomotive;
import Domain.RailwayStation;
import Domain.Trainset;
import Exceptions.ElectricGridException;
import Exceptions.NoSuchObjectException;
import Exceptions.ObjectAlreadyExistsException;
import Exceptions.OverweightException;
import GUI.Components.CarTypeChooseComponent;
import GUI.ConfirmationDialog;
import GUI.Screen;

import java.util.Scanner;

public class AddTrainsetScreen extends Screen {
    public AddTrainsetScreen() {
        title = "Add train set";
    }
    public void run(){
        System.out.println("=== " + title + " ===");
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the name for locomotive (press enter to assign automatically): ");
            Locomotive locomotive;
            String input = scanner.nextLine();
            if (input.equals("")) locomotive = new Locomotive();
            else {
                locomotive = new Locomotive(input);
            }
            RailwayStation start;
            while (true) {
                System.out.println("Enter the starting station: ");
                try {
                    start = railwayGrid.getStation(scanner.nextLine());
                    break;
                } catch (NoSuchObjectException e) {
                    System.out.println(e.getMessage());
                }
            }
            RailwayStation end;
            while (true) {
                System.out.println("Enter the destination station");
                try {
                    end = railwayGrid.getStation(scanner.nextLine());
                    if (end.getName().equals(start.getName())) {
                        System.out.println("Enter a different station: ");
                        continue;
                    }
                    break;
                } catch (NoSuchObjectException e) {
                    System.out.println(e.getMessage());
                }
            }
            Trainset trainset;
            while (true) {
                System.out.println("Enter the name for the train set (press enter to assign locomotive name): ");
                input = scanner.nextLine();
                if (input.equals("")) trainset = new Trainset(locomotive.getName(), locomotive, start, end);
                else {
                    trainset = new Trainset(input, locomotive, start, end);
                }
                try {
                    railwayGrid.addTrainset(trainset);
                    break;
                } catch (ObjectAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (ConfirmationDialog.display("Would you like to add cars to your train set?") == ConfirmationDialog.YES) {
                while (true) {
                    try {
                        trainset.addCar(new CarTypeChooseComponent().display());
                    } catch (ElectricGridException | OverweightException e) {
                        System.out.println(e.getMessage());
                        if (ConfirmationDialog.display("Would you like to create another car?") == ConfirmationDialog.NO)
                            break;
                        else continue;
                    }
                    System.out.println("Car successfully added.");
                    if (ConfirmationDialog.display("Add another one?") == ConfirmationDialog.NO) break;
                }
            }
            System.out.println("Train set successfully created.");
        } while (ConfirmationDialog.display("Create another one?") != ConfirmationDialog.NO);
    }
}
