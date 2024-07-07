package Exceptions;

public class ObjectAlreadyExistsException extends Exception{
    public ObjectAlreadyExistsException(String object){
        super("Such " + object + " already exists.");
    }
}
