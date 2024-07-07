package Domain.Cars;

import Exceptions.OverweightException;

public abstract class FreightCar extends Car{
    protected int maxWeight;
    protected int cargoWeight;
    public void load(int weight)throws OverweightException{
        if(weight > getAvailableWeight())
            throw new OverweightException("Cargo is too heavy. " + getAvailableWeight() + " available.");
        else cargoWeight += weight;
        this.weight += weight;
    }
    public void unload(int weight){
        if(weight >= cargoWeight) cargoWeight = 0;
        else cargoWeight -= weight;
        this.weight -= weight;
    }
    public int getAvailableWeight(){
        return maxWeight - cargoWeight;
    }
    public int getCargoWeight(){
        return cargoWeight;
    }
}
