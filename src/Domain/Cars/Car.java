package Domain.Cars;

public abstract class Car implements Comparable<Car> {
    protected boolean gridConnected;
    protected int weight;
    protected String type;

    public int getWeight() {
        return weight;
    }

    public boolean isGridConnected() {
        return gridConnected;
    }

    @Override
    public String toString() {
        return type + " (" + weight+ "t)";
    }

    @Override
    public int compareTo(Car other) {
        if(this.weight >= other.weight) return 1;
        else return -1;
    }
}
