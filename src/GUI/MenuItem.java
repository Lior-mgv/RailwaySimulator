package GUI;

public class MenuItem {
    private int num;
    private String title;
    private final Screen targetScreen;

    public MenuItem(int num, String title, Screen screen) {
        this.num = num;
        this.title = title;
        targetScreen = screen;
    }
    public Screen getScreen() {
        return targetScreen;
    }

    public void run() {
        if (targetScreen!=null)
            targetScreen.run();
    }

    public void display() {
        System.out.printf("%d. %s%n", num, title);
    }

    public int getNum() {
        return num;
    }

    public String getTitle() {
        return title;
    }

    public Screen getTargetScreen() {
        return targetScreen;
    }
}
