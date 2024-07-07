package Simulation;

import Domain.Connection;
import Domain.Trainset;

import java.util.LinkedList;

public class Coordinator {
    private Connection connection;
    private final LinkedList<Trainset> waiting = new LinkedList<>();
    public Coordinator(Connection con){
        connection = con;
    }

    public void waitToGo(Trainset trainset) throws InterruptedException {
        if(connection.isOccupied()){
            synchronized (waiting) {
                waiting.add(trainset);
            }
            synchronized(trainset) {
                trainset.wait();
            }
        }
    }

    public void allowToGo() {
        synchronized (waiting) {
            if (waiting.isEmpty()) return;
            var trainset = waiting.getFirst();
            synchronized (trainset) {
                trainset.notify();
            }
            waiting.pop();
        }
    }
}
