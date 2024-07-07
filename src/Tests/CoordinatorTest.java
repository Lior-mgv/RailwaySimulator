package Tests;

import Domain.Connection;
import Domain.Locomotive;
import Domain.RailwayStation;
import Domain.Trainset;
import Simulation.Coordinator;

public class CoordinatorTest {
    public static void main(String[] args) throws InterruptedException {
        var connection = new Connection(new RailwayStation("A"), new RailwayStation("B"), 10);
        connection.setOccupied(true);
        var coord = connection.getCoordinator();

        CreateTrainThread("1", connection);
        CreateTrainThread("2", connection);
        CreateTrainThread("3", connection);
        CreateTrainThread("4", connection);

        Thread.sleep(1000);
        connection.setOccupied(false);
    }

    private static void CreateTrainThread(String trainName, Connection connection) throws InterruptedException {
        Thread.sleep(200);
        var trainset = new Trainset(trainName, new Locomotive(trainName), null, null);
        var thread = new TraininSim(trainset, connection);
        thread.start();
    }

    static class TraininSim extends Thread {
        private final Connection connection;
        private Trainset trainset;
        private Coordinator coordinator;

        public TraininSim(Trainset trainset, Connection connection) {
            this.trainset = trainset;
            this.coordinator = connection.getCoordinator();
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                System.out.println(trainset.toString() + " is awaiting permission to go");
                coordinator.waitToGo(trainset);
                System.out.println(trainset.toString() + " is allowed to go");
                connection.setOccupied(true);
                Thread.sleep(2000);
                connection.setOccupied(false);
                System.out.println(trainset.toString() + " finished its ride");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
