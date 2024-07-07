package GUI.Screens.CarScreens;

import Domain.Trainset;
import Exceptions.ElectricGridException;
import Exceptions.OverweightException;
import GUI.Components.CarTypeChooseComponent;
import GUI.Components.TrainsetListComponent;
import GUI.ConfirmationDialog;
import GUI.Screen;

import java.util.Scanner;

public class AddCarScreen extends Screen {
    public AddCarScreen() {
        title = "Add car";
    }

    @Override
    public void run() {
        System.out.println("=== " + title + " ===");
        System.out.println("Enter the number of the train set.");
        Scanner scanner = new Scanner(System.in);
        new TrainsetListComponent().display(railwayGrid);
        Trainset trainset;
        while (true) {
            int input = scanner.nextInt();
            if (input <= railwayGrid.getTrainsets().size() && input >= 1) {
                trainset = railwayGrid.getTrainsets().get(input - 1);
                break;
            } else System.out.println("Enter a valid number.");
        }
        do {
            try {
                trainset.addCar(new CarTypeChooseComponent().display());
            } catch (ElectricGridException | OverweightException e) {
                System.out.println(e.getMessage());
                if (ConfirmationDialog.display("Would you like to create another car?") == ConfirmationDialog.NO)
                    break;
                else continue;
            }
            System.out.println("Car successfully added.");
        } while (ConfirmationDialog.display("Add another one?") != ConfirmationDialog.NO);
    }
}
