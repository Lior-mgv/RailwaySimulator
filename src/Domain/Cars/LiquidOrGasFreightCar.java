package Domain.Cars;

import Exceptions.NotEnoughSpaceException;
import Exceptions.OverweightException;

public abstract class LiquidOrGasFreightCar extends FreightCar {
    protected int filledVolume;
    protected int maxVolume;
    public void fill(int volume, double density) throws NotEnoughSpaceException, OverweightException{
        double loadWeight = convertToWeight(volume, density);
        if(filledVolume + volume > maxVolume)
            throw new NotEnoughSpaceException("Not enough space " + (maxVolume - filledVolume) + " available.");
        if(loadWeight > getAvailableWeight())
            throw new OverweightException("Cargo is too heavy. " + getAvailableWeight() + " available.");
        filledVolume += volume;
        cargoWeight += loadWeight;
        weight += loadWeight;
    }
    public double convertToWeight(int volume, double density){
        return volume * density;
    }
}
