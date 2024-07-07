package Domain;

import java.util.ArrayList;
import java.util.Collections;

public class RailwayStation {
    public RailwayStation(String name){
        this.name = name;
        connections = new ArrayList<>();
    }
    private String name;

    public String getName() {
        return name;
    }

    private ArrayList<Connection> connections;

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void addConnection(Connection c){
        connections.add(c);
    }
    public void deleteConnection(Connection c){
        connections.remove(c);
    }
    public String toString(){
        return name;
    }
    public void shuffleConnections(){
        Collections.shuffle(connections);
    }
}
