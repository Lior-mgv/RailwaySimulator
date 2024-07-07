package GUI.Screens.CarScreens;

import Domain.Cars.*;
import Exceptions.NotEnoughSpaceException;
import Exceptions.OverweightException;
import GUI.ConfirmationDialog;
import GUI.Screen;

import java.util.Scanner;

public class LoadCargoScreen extends Screen {
    private FreightCar car;

    public LoadCargoScreen(FreightCar car) {
        title = "Load cargo";
        this.car = car;
    }

    @Override
    public void run() {
        System.out.println("=== " + title + " ===");
        Scanner scnr = new Scanner(System.in);
        do {
            while (true) {
                if(LiquidOrGasFreightCar.class.isAssignableFrom(car.getClass()))
                    System.out.println("Enter volume in m3: ");
                else System.out.println("Enter cargo weight in tons: " );
                int input = scnr.nextInt();
                if(input < 0){
                    System.out.println("Invalid input.");
                    continue;
                }
                try {
                    if(car instanceof LiquidOrGasFreightCar){
                        System.out.println("Enter density in t/m3: ");
                        ((LiquidOrGasFreightCar) car).fill(input, scnr.nextDouble());
                    }
                    else {
                        car.load(input);
                    }
                    break;
                }catch (OverweightException | NotEnoughSpaceException e){
                    System.out.println(e.getMessage());
                }
            }
        } while (ConfirmationDialog.display("Cargo loaded. Load more?") != ConfirmationDialog.NO);
    }
}
