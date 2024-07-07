package Exceptions;

import Domain.Trainset;

public class RailroadHazard extends RuntimeException{
    public RailroadHazard(Trainset trainset) {
        super("Warning! Train " + trainset + " is going faster than 200 km/h!");
    }
}
