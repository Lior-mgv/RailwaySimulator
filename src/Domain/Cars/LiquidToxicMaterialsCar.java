package Domain.Cars;

public class LiquidToxicMaterialsCar extends LiquidOrGasFreightCar {
    public LiquidToxicMaterialsCar(){
        type = "Liquid toxic materials";
        weight = 65;
        maxVolume = 150;
        filledVolume = 0;
    }
}
