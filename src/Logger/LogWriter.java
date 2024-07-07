package Logger;

import Domain.Cars.Car;
import Domain.Trainset;
import Simulation.TrainRide;

import java.io.BufferedWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LogWriter extends Thread {
    private final ArrayList<TrainRide> trainSimulations;
    public LogWriter(ArrayList<TrainRide> trainRides) {
        trainSimulations = trainRides;
    }

    @Override
    public void run() {
        try(BufferedWriter bw = Files.newBufferedWriter(Paths.get("AppState.txt"))) {
            while (true) {
                ArrayList<TrainRide> trainSimulationsCopy = new ArrayList<>(trainSimulations);
                while(trainSimulationsCopy.size() > 1){
                    TrainRide longest = trainSimulationsCopy.get(0);
                    for (int j = 1; j < trainSimulationsCopy.size(); j++) {
                        var cur = trainSimulationsCopy.get(j);
                        if(cur.compareTo(longest) > 0) longest = cur;
                    }
                    bw.write(longest + "\n");
                    writeCars(longest.getTrainset(), bw);
                    trainSimulationsCopy.remove(longest);
                }
                bw.write(trainSimulationsCopy.get(0).toString() + "\n");
                writeCars(trainSimulationsCopy.get(0).getTrainset(), bw);
                bw.write("\n\n");
                Thread.sleep(5000);
                bw.flush();
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void writeCars(Trainset trainset, BufferedWriter bw) throws IOException {
        if(trainset.getCars().isEmpty()) return;
        bw.write("(");
        Car heaviest = null;
        var carsCopy = new ArrayList<>(trainset.getCars());
        while(carsCopy.size() > 1){
            heaviest = carsCopy.get(0);
            for (int i = 1; i < carsCopy.size(); i++) {
                if(heaviest.compareTo(carsCopy.get(i)) < 0) heaviest = carsCopy.get(i);
            }
            bw.write(heaviest.toString() + ",");
            carsCopy.remove(heaviest);
        }
        bw.write(carsCopy.get(0).toString() + ")\n");
    }
}
