package Domain.Cars;

import java.util.ArrayList;

public class RestaurantCar extends PeopleCarryingCar {
    private int numOfPeople;
    private int maxNumOfPeople;
    private ArrayList<String> menu;
    public RestaurantCar(int numOfStaff, ArrayList<String> menu){
        type = "Restaurant";
        gridConnected = true;
        weight = 40;
        this.numOfPeople = numOfStaff;
        this.menu = menu;
    }

    public RestaurantCar() {
        type = "Restaurant";
        gridConnected = true;
        weight = 40;
        this.numOfPeople = 0;
        maxNumOfPeople = 50;
        this.menu = new ArrayList<>();
    }
}
