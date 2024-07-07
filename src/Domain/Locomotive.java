package Domain;

public class Locomotive {
    private static int idCount = 1;
    private final int id = idCount++;
    private String name;
    private int maxCars = 15;
    private int maxWeight = 2000;
    private int maxGridConnected = 10;
    private double speed = 100;

    public Locomotive(String name) {
        this.name = name;
    }
    public Locomotive(){
        this.name = Integer.toString(id);
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public int getMaxCars() {
        return maxCars;
    }


    public int getMaxWeight() {
        return maxWeight;
    }


    public int getMaxGridConnected() {
        return maxGridConnected;
    }


    public double getSpeed() {
        return speed;
    }
}
