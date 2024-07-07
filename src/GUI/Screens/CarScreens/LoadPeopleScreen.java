package GUI.Screens.CarScreens;

import Domain.Cars.*;
import Exceptions.OverweightException;
import GUI.ConfirmationDialog;
import GUI.Screen;

import java.util.Scanner;

public class LoadPeopleScreen extends Screen {
    private PeopleCarryingCar car;

    public LoadPeopleScreen(PeopleCarryingCar car) {
        title = "Load people";
        this.car = car;
    }

    @Override
    public void run() {
        System.out.println("=== " + title + " ===");
        Scanner scnr = new Scanner(System.in);
        do {
            while (true) {
                System.out.println("Enter number of people: " );
                int input = scnr.nextInt();
                if(input < 0){
                    System.out.println("Invalid input.");
                    continue;
                }
                try {
                    car.load(input);
                    break;
                }catch (OverweightException e){
                    System.out.println(e.getMessage());
                }
            }
        } while (ConfirmationDialog.display("Passengers loaded. Load more?") != ConfirmationDialog.NO);
    }
}
