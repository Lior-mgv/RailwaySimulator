package GUI.Screens.TrainsetScreens;

import GUI.Components.TrainsetListComponent;
import GUI.ConfirmationDialog;
import GUI.Screen;

import java.util.Scanner;

public class DeleteTrainsetScreen extends Screen {
    public DeleteTrainsetScreen() {
        title = "Delete trainset";
    }

    public void run() {
        System.out.println("=== " + title + " ===");
        Scanner scanner = new Scanner(System.in);
        new TrainsetListComponent().display(railwayGrid);
        while (true) {
            int input = -1;
            while (true) {
                System.out.println("Enter the number of the train set you'd like to delete: ");
                input = scanner.nextInt();
                if (input <= railwayGrid.getTrainsets().size() && input >= 1) break;
            }
            railwayGrid.removeTrainset(railwayGrid.getTrainsets().get(input-1));
            if(ConfirmationDialog.display("Delete another one?") == ConfirmationDialog.NO) break;
        }
    }
}
