package Domain.Cars;

import Exceptions.OverweightException;

public abstract class PeopleCarryingCar extends Car{
    protected int numOfPeople;
    protected int maxNumOfPeople;
    public void load(int number) throws OverweightException{
        if (numOfPeople + number > maxNumOfPeople)
            throw new OverweightException("There are too many passengers. " + (maxNumOfPeople - numOfPeople) + " available.");
        else numOfPeople += number;
    }

    public int getNumOfPeople(){
        return numOfPeople;
    }
}
