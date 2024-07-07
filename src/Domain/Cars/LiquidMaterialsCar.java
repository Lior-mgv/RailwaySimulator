package Domain.Cars;

public class LiquidMaterialsCar extends LiquidOrGasFreightCar {
    public LiquidMaterialsCar(){
        type = "Liquid materials";
        weight = 65;
        maxWeight = 100;
        maxVolume = 150;
        filledVolume = 0;
    }
}
