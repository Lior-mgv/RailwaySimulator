package Simulation;

import Domain.DirectedConnection;
import Domain.RailwayStation;

public class TrainLocation {
    private DirectedConnection connection;
    private double passed;
    public TrainLocation(DirectedConnection con, double passed){
        connection = con;
        this.passed = passed;
    }

    public DirectedConnection getConnection() {
        return connection;
    }
    public RailwayStation getStation(){
        if(isAtStation()){
            return connection.getStart();
        }
        return null;
    }

    public void setConnection(DirectedConnection connection) {
        this.connection = connection;
    }

    public double getPassed() {
        return passed;
    }

    public boolean isAtStation(){
        return passed == 0;
    }
    public boolean isAtStation(RailwayStation rs){
        return isAtStation() && getStation() == rs;
    }
    public String toString(){
        return connection + "(" + passed + ")";
    }

    public double getDistanceToNextStation() {
        return connection.getLength()-passed;
    }

    public void moveForward(double distance) {
        passed += distance;
    }

    public TrainLocation projectForward(double forward) {
        return new TrainLocation(connection, passed+forward);
    }

    public boolean nextStationReached() {
        return passed>=connection.getLength();
    }

    public RailwayStation nextStation() {
        return connection.getEnd();
    }
}
