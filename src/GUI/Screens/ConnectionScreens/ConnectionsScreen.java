package GUI.Screens.ConnectionScreens;

import GUI.Screen;

public class ConnectionsScreen extends Screen {
    public ConnectionsScreen() {
        title = "Connections";
        super.menu.add(1, "Add", new AddConnectionScreen());
        super.menu.add(2, "Delete", new DeleteConnectionScreen());
        super.menu.add(3, "Back", null);
    }
    protected void showSpecificContent(){
        System.out.println("Existing connections:");
        int num = 1;
        for (var con : railwayGrid.getConnections()) {
            System.out.println(num++ + ". " + con);
        }
        System.out.println();
    }
}
