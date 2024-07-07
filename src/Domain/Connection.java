package Domain;

import Simulation.Coordinator;

import java.util.ArrayList;

public class Connection {
    private boolean occupied = false;
    private double length;
    protected Coordinator coordinator = new Coordinator(this);
    public Connection(RailwayStation a, RailwayStation b, double len){
        connected.add(a);
        connected.add(b);
        length = len;
    }
    public double getLength() {
        return length;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public synchronized boolean isOccupied(){
        return occupied;
    }

    public synchronized  void setOccupied(boolean occupied) {
        this.occupied = occupied;
        if(!occupied){
            coordinator.allowToGo();
        }
    }

    private ArrayList<RailwayStation> connected = new ArrayList<>();

    public ArrayList<RailwayStation> getConnected() {
        return connected;
    }
    public String toString(){
        return connected.get(0) + "-" + connected.get(1) + " (" + length + ")";
    }
}
