package GUI.Screens;

import Domain.RailwayGrid;
import Domain.Trainset;
import GUI.Components.TrainsetListComponent;
import GUI.ConfirmationDialog;
import GUI.Screen;
import Logger.LogWriter;
import Simulation.TrainRide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class SimulationScreen extends Screen {
    public SimulationScreen(){
    }
    @Override
    public void run() {
        ArrayList<TrainRide> simulations = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Use railway grid created by user.\n2. Use automatically generated railway grid.");
        if(scanner.nextInt() == 2){
            try{
                var newGrid = new RailwayGrid();
                newGrid.generateStations(100);
                newGrid.generateTrains(25);
                railwayGrid = newGrid;
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        ArrayList<Trainset> toRemove = new ArrayList<>();
        for (var trainset : railwayGrid.getTrainsets()) {
            var path = RailwayGrid.findPath(trainset.getStart(), trainset.getDest());
            if(path.getPath().size() == 0){
                System.out.println("Train " + trainset.getName() + " cannot reach its destination.");
                System.out.println("1. Continue without train " + trainset.getName() + ".\n2. Return to main menu.");
                if(scanner.nextInt() == 1){
                    toRemove.add(trainset);
                    continue;
                }
                else return;
            }
            simulations.add(new TrainRide(path, trainset));
        }
        toRemove.forEach((t) -> {railwayGrid.removeTrainset(t);});
        System.out.println("Simulation started.\n");
        simulations.forEach(TrainRide::start);
        new LogWriter(simulations).start();
        while(true){
            System.out.println("Existing trains: ");
            new TrainsetListComponent().display(railwayGrid);
            int input = -1;
            while (true) {
                System.out.println("Enter the number of the train to see the information: ");
                input = scanner.nextInt();
                if (input <= railwayGrid.getTrainsets().size() && input >= 1) break;
                else System.out.println("Invalid number");
            }
            do {
                simulations.get(input - 1).printState();
                simulations.get(input-1).getTrainset().listCars();
                System.out.println();
            } while (ConfirmationDialog.display("Continue tracking") != ConfirmationDialog.NO);
        }
    }
}
