package GUI;

import Domain.RailwayGrid;

public class Screen {
    protected static RailwayGrid railwayGrid;
    protected String title;
    protected Menu menu = new Menu();

    public Screen() {
    }

    public void setRailwayGrid(RailwayGrid railwayGrid) {
        Screen.railwayGrid = railwayGrid;
    }

    public void run() {
        MenuItem menuItem;
        do {
            clearConsole();
            System.out.println("=== "+ title + " ===");
            showSpecificContent();
            if (menu.isEmpty())
                break;
            menu.display();
            do {
                menuItem = menu.selectItem();
                if(menuItem == null)
                    System.out.println("Invalid option.");
            }
            while(menuItem==null);
            menuItem.run();
        }
        while(menuItem.getScreen()!=null);
    }

    protected void showSpecificContent() {
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
