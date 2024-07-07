package GUI.Screens.CarScreens;

import Domain.Cars.*;
import GUI.ConfirmationDialog;
import GUI.Screen;

import java.util.Scanner;

public class UnloadCargoScreen extends Screen {
    private FreightCar car;

    public UnloadCargoScreen(FreightCar car) {
        title = "Unload cargo";
        this.car = car;
    }

    @Override
    public void run() {
        System.out.println("=== " + title + " ===");
        Scanner scnr = new Scanner(System.in);
        do {
            while (true) {
                System.out.println("Enter cargo weight in tons: " );
                int input = scnr.nextInt();
                if(input < 0){
                    System.out.println("Invalid input.");
                    continue;
                }
                car.unload(input);
                break;
            }
        } while (ConfirmationDialog.display("Cargo unloaded. Unload more?") != ConfirmationDialog.NO);
    }
}
