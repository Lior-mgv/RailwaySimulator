package GUI.Screens.CarScreens;

import Domain.Cars.*;
import Domain.Trainset;
import GUI.Components.TrainsetListComponent;
import GUI.Menu;
import GUI.MenuItem;
import GUI.Screen;

import java.util.Scanner;

public class ModifyCarScreen extends Screen {
    public ModifyCarScreen() {
        title = "Modify car";
    }

    @Override
    public void run() {
        System.out.println("=== " + title + " ===");
        System.out.println("Enter the number of the train set.");
        Scanner scanner = new Scanner(System.in);
        new TrainsetListComponent().display(railwayGrid);
        Trainset trainset;
        while(true){
            int input = scanner.nextInt();
            if (input <= railwayGrid.getTrainsets().size() && input >= 1) {
                trainset = railwayGrid.getTrainsets().get(input - 1);
                break;
            } else System.out.println("Enter a valid number.");
        }
        System.out.println("Enter the number of the car.");
        int carCount = 1;
        for (var car : trainset.getCars()) {
            System.out.println(carCount++ + ". " + car);
        }
        Car carToModify;
        while(true){
            int input = scanner.nextInt();
            if (input <= carCount && input >= 1) {
                carToModify = trainset.getCars().get(input-1);
                break;
            } else System.out.println("Enter a valid number.");
        }
        int optionCount = 1;
        if(carToModify instanceof LiquidOrGasFreightCar){
            menu.add(optionCount++ ,"Fill", new LoadCargoScreen((LiquidOrGasFreightCar) carToModify));
        }
        else if(carToModify instanceof FreightCar){
            menu.add(optionCount++ ,"Load cargo", new LoadCargoScreen((FreightCar) carToModify));
            menu.add(optionCount++ ,"Unload cargo", new UnloadCargoScreen((FreightCar) carToModify));
        }
        else if(carToModify instanceof PeopleCarryingCar){
            menu.add(optionCount++ ,"Load people", new LoadPeopleScreen ((PeopleCarryingCar)carToModify));
        }
        super.menu.add(optionCount++, "Back", null);
        menu.display();
        MenuItem menuItem;
        while(true){
            menuItem = menu.selectItem();
            if(menuItem != null) break;
            else System.out.println("Invalid option.");
        }
        if(menuItem.getTitle().equals("Back")){
            return;
        }
        menu = new Menu();
        menuItem.run();
    }
}
