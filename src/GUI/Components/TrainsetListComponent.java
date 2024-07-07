package GUI.Components;

import Domain.RailwayGrid;

public class TrainsetListComponent {
    public TrainsetListComponent(){
    }
    public void display(RailwayGrid grid){
        int num = 1;
        for (var tr : grid.getTrainsets()) {
            System.out.println(num++ + ". " + tr + " (" +
                    (tr.getCars().isEmpty() ? "No cars" : tr.getCars().size() + " cars") + ")");
        }
    }
}
