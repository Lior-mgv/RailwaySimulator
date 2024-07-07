package Domain;

import Exceptions.ElectricGridException;
import Exceptions.NoSuchObjectException;
import Exceptions.ObjectAlreadyExistsException;
import Exceptions.OverweightException;
import Simulation.RailwayPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class RailwayGrid {
    public RailwayGrid(){
        connections = new ArrayList<>();
        stations = new ArrayList<>();
        trainsets = new ArrayList<>();
    }
    private ArrayList<Connection> connections;
    private ArrayList<RailwayStation> stations;
    private ArrayList<Trainset> trainsets;

    public ArrayList<RailwayStation> getStations() {
        return stations;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public ArrayList<Trainset> getTrainsets() {
        return trainsets;
    }

    public RailwayStation getStation(String name) throws NoSuchObjectException {
        for (var station : stations) {
            if(station.getName().equals(name)) return station;
        }
        throw new NoSuchObjectException("station");
    }
    public RailwayStation addRailwayStation(String name) throws ObjectAlreadyExistsException {
        RailwayStation station = new RailwayStation(name);
        for (var st : stations) {
            if(station.getName().equals(st.getName()))
                throw new ObjectAlreadyExistsException("station");
        }
        stations.add(station);
        return station;
    }
    public RailwayStation removeRailwayStation(RailwayStation station){
        stations.remove(station);
        ArrayList<Connection> connections = new ArrayList<>(station.getConnections());
        connections.forEach(this::disconnect);
        return station;
    }
    public Connection connect(RailwayStation a, RailwayStation b, int length) throws IllegalArgumentException,
            ObjectAlreadyExistsException {
        if(length <= 0) throw new IllegalArgumentException("Invalid length value.");
        for (var con : connections) {
            if(con.getConnected().contains(a) && con.getConnected().contains(b))
                throw new ObjectAlreadyExistsException("connection");
        }
        Connection connection = new Connection(a,b,length);
        a.addConnection(connection);
        b.addConnection(connection);
        connections.add(connection);
        return connection;
    }
    public Connection disconnect(Connection con){
        connections.remove(con);
        con.getConnected().forEach(st -> st.deleteConnection(con));
        return con;
    }
    public Trainset addTrainset(Trainset trainset) throws ObjectAlreadyExistsException {
        for (var train : trainsets) {
            if(train.getName().equals(trainset.getName())) throw new ObjectAlreadyExistsException("Train set");
        }
        trainsets.add(trainset);
        return trainset;
    }
    public Trainset removeTrainset(Trainset trainset){
        trainsets.remove(trainset);
        return trainset;
    }
    public static RailwayPath findPath(RailwayStation start, RailwayStation end){
        ArrayList<DirectedConnection> path = new ArrayList<>();
        ArrayList<RailwayStation> visited = new ArrayList<>();
        findPathRecursive(start, end, path, visited);
        return new RailwayPath(path);
    }

    private static boolean findPathRecursive(RailwayStation cur, RailwayStation end,
                                             ArrayList<DirectedConnection> path, ArrayList<RailwayStation> visited){
        if(cur == end){
            return true;
        }
        else if(visited.contains(cur)) return false;
        visited.add(cur);
        for (Connection c : cur.getConnections()) {
            var next = c.getConnected().get(0) == cur ? c.getConnected().get(1) : c.getConnected().get(0);
            if(!visited.contains(next)){
                path.add(new DirectedConnection(c, cur, next));
                boolean found = findPathRecursive(next, end, path, visited);
                if (found) {
                    return true;
                }
            }
        }
        if(path.size() > 0) path.remove(path.size()-1);
        return false;
    }
    public void generateStations(int stationsCount) throws IOException {
        Random rnd = new Random();
        try(BufferedReader br = Files.newBufferedReader(Paths.get("Resources\\StationNames.txt"))) {
            for (int i = 0; i < stationsCount; i++) {
                var curStation = new RailwayStation(br.readLine());
                stations.add(curStation);
                int connectionsToGenerate;
                if (i == 0) continue;
                else if (i < 4) connectionsToGenerate = 1;
                else connectionsToGenerate = i / 4;
                for (int j = 0; j < connectionsToGenerate; j++) {
                    try {
                        connect(curStation, stations.get(rnd.nextInt(stations.size())), rnd.nextInt(10,50));
                    } catch (ObjectAlreadyExistsException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public void generateTrains(int trainCount) {
        try {
            Random rnd = new Random();
            for (int i = 0; i < trainCount; i++) {
                RailwayStation start;
                RailwayStation end;
                do {
                    start = stations.get(rnd.nextInt(stations.size()));
                    end = stations.get(rnd.nextInt(stations.size()));
                } while (start == end);
                var curTrainset = new Trainset(("Train " + (i + 1)), new Locomotive(), start, end);
                for (int j = 0; j < rnd.nextInt(6,12); j++) {
                    curTrainset.addRandomCar();
                }
                trainsets.add(curTrainset);
            }
        } catch (ElectricGridException | OverweightException e) {
            System.out.println(e.getMessage());
        }
    }
}
