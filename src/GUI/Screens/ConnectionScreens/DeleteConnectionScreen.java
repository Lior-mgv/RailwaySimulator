package GUI.Screens.ConnectionScreens;

import GUI.Screen;
import GUI.ConfirmationDialog;

import java.util.Scanner;

public class DeleteConnectionScreen extends Screen {
    public DeleteConnectionScreen() {
        title = "Delete connection";
    }

    public void run() {
        System.out.println("=== " + title + " ===");
        Scanner scanner = new Scanner(System.in);
        do {
            int input = -1;
            do {
                System.out.println("Enter the number of the connection you'd like to delete: ");
                input = scanner.nextInt();
            } while (input > railwayGrid.getConnections().size() || input < 1);
            railwayGrid.disconnect(railwayGrid.getConnections().get(input - 1));
            System.out.println("Connection successfully deleted. Remaining connections:");
            int num = 1;
            for (var con : railwayGrid.getConnections()) {
                System.out.println(num++ + ". " + con);
            }
        } while (ConfirmationDialog.display("Delete another connection?") != ConfirmationDialog.NO);
    }
}
