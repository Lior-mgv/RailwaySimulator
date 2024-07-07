package Domain.Cars;


public class GaseousMaterialsCar extends LiquidOrGasFreightCar {
    public GaseousMaterialsCar(){
        type = "Gaseous materials";
        weight = 55;
        maxVolume = 120;
        filledVolume = 0;
    }
}
