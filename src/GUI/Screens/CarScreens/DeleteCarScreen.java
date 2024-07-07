package GUI.Screens.CarScreens;

import Domain.Trainset;
import GUI.Components.TrainsetListComponent;
import GUI.ConfirmationDialog;
import GUI.Screen;

import java.util.Scanner;

public class DeleteCarScreen extends Screen {
    public DeleteCarScreen() {
        title = "Delete car";
    }

    @Override
    public void run() {
        System.out.println("=== " + title + " ===");
        Scanner scanner = new Scanner(System.in);
        Trainset trainset;
        System.out.println("Enter the number of the train set.");
        new TrainsetListComponent().display(railwayGrid);
        while(true){
            int input = scanner.nextInt();
            if (input <= railwayGrid.getTrainsets().size() && input >= 1) {
                trainset = railwayGrid.getTrainsets().get(input - 1);
                break;
            } else System.out.println("Enter a valid number.");
        }
        do {
            int num = 1;
            for (var car : trainset.getCars()) {
                System.out.println(num++ + ". " + car);
            }
            int input;
            do {
                System.out.println("Enter the number of the car you'd like to delete: ");
                input = scanner.nextInt();
            } while (input > trainset.getCars().size() || input < 1);
            trainset.removeCar(trainset.getCars().get(input - 1));
            System.out.println("Car successfully deleted.");
        } while (ConfirmationDialog.display("Delete another one?") != ConfirmationDialog.NO);
    }
}
