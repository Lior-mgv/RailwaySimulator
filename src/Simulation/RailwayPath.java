package Simulation;

import Domain.DirectedConnection;
import Domain.RailwayGrid;
import Domain.RailwayStation;

import java.util.ArrayList;
import java.util.Collections;

public class RailwayPath {
    private ArrayList<DirectedConnection> path;
    public RailwayPath(ArrayList<DirectedConnection> path){
        this.path = path;
    }

    public ArrayList<DirectedConnection> getPath() {
        return path;
    }
    public RailwayStation getStart(){
        return path.get(0).getStart();
    }
    public RailwayStation getEnd(){
        return path.get(path.size()-1).getEnd();
    }
    public RailwayStation getNextStation(RailwayStation current){
        if(current == path.get(0).getStart()) return path.get(0).getEnd();
        for (int i = 0; i < path.size(); i++){
            if(path.get(i).getEnd() == current){
                if(i!= path.size()-1) {
                    return path.get(i+1).getEnd();
                }
                else return null;
            }
        }
        return null;
    }
    public DirectedConnection getNextConnection(DirectedConnection current){
        if(!path.contains(current)) return null;
        else if(path.indexOf(current) == path.size()-1) return new DirectedConnection(current, current.getEnd(), current.getStart());
        else return path.get(path.indexOf(current) + 1);
    }
    public double getDistanceFromStart(TrainLocation location){
        double distance = 0;
        for (var c : path) {
            if(c != location.getConnection()) distance += c.getLength();
            else {
                distance += location.getPassed();
                break;
            }
        }
        return distance;
    }
    public int getTotalDistance(){
        int distance = 0;
        for (var con : path) {
            distance += con.getLength();
        }
        return distance;
    }

    public DirectedConnection getConnection(int connectionNum) {
        return path.get(connectionNum);
    }
}
