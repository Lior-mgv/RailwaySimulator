package GUI.Components;

import Domain.Cars.*;

import java.util.Scanner;

public class CarTypeChooseComponent {
    public CarTypeChooseComponent() {
    }
    public Car display() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose the type of the car: ");
            System.out.println("1. Baggage & mail car\n" +
                    "2. Basic freight car\n" +
                    "3. Explosives car\n" +
                    "4. Gaseous materials car\n" +
                    "5. Heavy freight car\n" +
                    "6. Liquid materials car\n" +
                    "7. Liquid toxic materials car\n" +
                    "8. Passenger car\n" +
                    "9. Railroad post office\n" +
                    "10. Refrigerator car\n" +
                    "11. Restaurant car\n" +
                    "12. Toxic materials car");
            switch (scanner.nextInt()) {
                case 1 -> {
                    return new BaggageMailCar();
                }
                case 2 -> {
                    return new BasicFreightCar();
                }
                case 3 -> {
                    return new ExplosivesCar();
                }
                case 4 -> {
                    return new GaseousMaterialsCar();
                }
                case 5 -> {
                    return new HeavyFreightCar();
                }
                case 6 -> {
                    return new LiquidMaterialsCar();
                }
                case 7 -> {
                    return new LiquidToxicMaterialsCar();
                }
                case 8 -> {
                    return new PassengerCar();
                }
                case 9 -> {
                    return new RailroadPostOffice();
                }
                case 10 -> {
                    return new RefrigeratorCar();
                }
                case 11 -> {
                    return new RestaurantCar();
                }
                case 12 -> {
                    return new ToxicMaterialsCar();
                }
                default -> {
                    System.out.println("Enter a valid number");
                }
            }
        }
    }
}
