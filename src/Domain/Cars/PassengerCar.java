package Domain.Cars;

public class PassengerCar extends PeopleCarryingCar {
    private boolean sleepingCar;

    public PassengerCar(int numOfSeats, boolean sleepingCar) {
        type = "Passenger";
        gridConnected = true;
        weight = 30;
        maxNumOfPeople = numOfSeats;
        this.sleepingCar = sleepingCar;
    }

    public PassengerCar() {
        type = "Passenger";
        gridConnected = true;
        weight = 30;
        maxNumOfPeople = 100;
        sleepingCar = true;
    }

    public int filledPercentage() {
        return (int) (numOfPeople / (maxNumOfPeople / 100.));
    }
}
