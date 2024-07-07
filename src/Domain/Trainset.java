package Domain;

import Domain.Cars.*;
import Exceptions.ElectricGridException;
import Exceptions.OverweightException;
import Exceptions.RailroadHazard;

import java.util.ArrayList;
import java.util.Random;

public class Trainset {
    private String name;
    private Locomotive locomotive;
    private ArrayList<Car> cars;
    private RailwayStation start;
    private RailwayStation dest;
    private double speed;

    public Trainset(String name, Locomotive loc, RailwayStation start, RailwayStation dest) {
        this.name = name;
        locomotive = loc;
        cars = new ArrayList<>();
        this.start = start;
        this.dest = dest;
        speed = locomotive.getSpeed();
    }

    public Trainset(Locomotive loc) {
        locomotive = loc;
        name = loc.getName();
        cars = new ArrayList<>();
        speed = locomotive.getSpeed();
    }

    public String getName() {
        return name;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public RailwayStation getStart() {
        return start;
    }

    public RailwayStation getDest() {
        return dest;
    }
    private int getTotalWeight(){
        int weight = 0;
        for (var c : cars) {
            weight += c.getWeight();
        }
        return weight;
    }
    private int getGridConnected(){
        int num = 0;
        for (var c : cars) {
            if(c.isGridConnected()) num++;
        }
        return num;
    }

    public Car addCar(Car car) throws ElectricGridException, OverweightException {
        if(getTotalWeight() + car.getWeight() > locomotive.getMaxWeight())
            throw new OverweightException("Total weight exceeds the limit.");
        if(car.isGridConnected() && getGridConnected() + 1 > locomotive.getMaxGridConnected())
            throw new ElectricGridException("No more cars can be connected to electric grid");
        cars.add(car);
        return car;
    }

    public void addRandomCar() throws ElectricGridException, OverweightException {
        Random rnd = new Random();
        switch (rnd.nextInt(1, 13)) {
            case 1 -> {
                addCar(new BaggageMailCar());
            }
            case 2 -> {
                addCar(new BasicFreightCar());
            }
            case 3 -> {
                addCar(new ExplosivesCar());
            }
            case 4 -> {
                addCar(new GaseousMaterialsCar());
            }
            case 5 -> {
                addCar(new HeavyFreightCar());
            }
            case 6 -> {
                addCar(new LiquidMaterialsCar());
            }
            case 7 -> {
                addCar(new LiquidToxicMaterialsCar());
            }
            case 8 -> {
                addCar(new PassengerCar());
            }
            case 9 -> {
                addCar(new RailroadPostOffice());
            }
            case 10 -> {
                addCar(new RefrigeratorCar());
            }
            case 11 -> {
                addCar(new RestaurantCar());
            }
            case 12 -> {
                addCar(new ToxicMaterialsCar());
            }
        }
    }
    public Car removeCar(Car car){
        cars.remove(car);
        return car;
    }
    public void changeSpeed(){
        Random rnd = new Random();
        var oldSpeed = speed;
        switch (rnd.nextInt(2)){
            case 0 -> speed-=speed/100*3;
            case 1 -> speed += speed/100*3;
        }
        if(speed > 200 && oldSpeed < 200) throw new RailroadHazard(this);
    }
    public String toString(){return name;
    }
    public void listCars(){
        for (var car : cars) {
            System.out.print(car);
            if(car instanceof FreightCar) System.out.println(" (" + ((FreightCar) car).getCargoWeight() + " tons of " +
                    "cargo)");
            else if(car instanceof PeopleCarryingCar) System.out.println(" (" + ((PeopleCarryingCar) car).getNumOfPeople() + " passengers)");
            else System.out.println();
        }
    }

    public void setInitialSpeed() {
        speed = locomotive.getSpeed();
    }
}
