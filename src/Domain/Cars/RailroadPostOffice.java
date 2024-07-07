package Domain.Cars;

public class RailroadPostOffice extends Car{
    private int mailCapacity;
    private int mailLoad;
    public RailroadPostOffice(int mailLoad){
        type = "Post office";
        gridConnected = true;
        weight = 40;
        this.mailLoad = mailLoad;
    }
    public RailroadPostOffice(){
        type = "Post office";
        gridConnected = true;
        weight = 40;
        mailCapacity = 100;
        this.mailLoad = 0;
    }
}
