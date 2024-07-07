package Exceptions;

public class NoSuchObjectException extends Exception{
    public NoSuchObjectException(String object){
        super("Such " + object + " doesn't exist.");
    }
}
