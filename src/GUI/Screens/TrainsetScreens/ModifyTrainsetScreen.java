package GUI.Screens.TrainsetScreens;

import GUI.Screen;
import GUI.Screens.CarScreens.AddCarScreen;
import GUI.Screens.CarScreens.DeleteCarScreen;
import GUI.Screens.CarScreens.ModifyCarScreen;


public class ModifyTrainsetScreen extends Screen {
    public ModifyTrainsetScreen(){
        title = "Modify trainset";
        super.menu.add(1, "Add car", new AddCarScreen());
        super.menu.add(2, "Delete car", new DeleteCarScreen());
        super.menu.add(3, "Modify car", new ModifyCarScreen());
        super.menu.add(4, "Back", null);
    }
}
