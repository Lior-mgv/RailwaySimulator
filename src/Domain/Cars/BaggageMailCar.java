package Domain.Cars;

public class BaggageMailCar extends Car{
    private int baggageCapacity;
    private int mailCapacity;
    private final int baggageLoad;
    private final int mailLoad;
    public BaggageMailCar(int mailLoad, int baggageLoad){
        type = "Baggage & Mail";
        gridConnected = false;
        weight = 40;
        this.mailLoad = mailLoad;
        this.baggageLoad = baggageLoad;
    }
    public BaggageMailCar(){
        type = "Baggage & Mail";
        gridConnected = false;
        weight = 40;
        baggageCapacity = 100;
        mailCapacity = 100;
        this.mailLoad = 0;
        this.baggageLoad = 0;
    }
}
