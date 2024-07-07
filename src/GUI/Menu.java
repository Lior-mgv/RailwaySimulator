package GUI;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<MenuItem> items = new ArrayList<>();

    public MenuItem add(int num, String title, Screen screen) {
        var menuItem = new MenuItem(num, title, screen);
        items.add(menuItem);
        return menuItem;
    }

    public void display() {
        items.forEach(MenuItem::display);
    }

    public MenuItem selectItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option: ");
        int input = scanner.nextInt();
        scanner.nextLine();
        for (var mi : items) {
            if(mi.getNum() == input) return mi;
        }
        return null;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
