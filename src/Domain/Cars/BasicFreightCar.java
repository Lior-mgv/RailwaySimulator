package Domain.Cars;

public class BasicFreightCar extends FreightCar {
    public BasicFreightCar(){
        type = "Basic freight";
        weight = 50;
        cargoWeight = 0;
        maxWeight = 100;
    }
}
