package GUI.Screens.TrainsetScreens;

import GUI.Components.TrainsetListComponent;
import GUI.Screen;

public class TrainsetsScreen extends Screen {
    public TrainsetsScreen(){
        title = "Train sets";
        super.menu.add(1, "Add", new AddTrainsetScreen());
        super.menu.add(2, "Delete", new DeleteTrainsetScreen());
        super.menu.add(3, "Modify", new ModifyTrainsetScreen());
        super.menu.add(4, "Back", null);
    }

    @Override
    protected void showSpecificContent() {
        System.out.println("Existing train sets:");
        new TrainsetListComponent().display(railwayGrid);
        System.out.println("\nOptions:");
    }
}
