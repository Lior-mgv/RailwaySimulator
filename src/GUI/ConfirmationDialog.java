package GUI;

import java.util.Scanner;

public class ConfirmationDialog {
    public static int YES = 1;
    public static int NO = 2;

    public static int display(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("\n%s\n1. Yes\n2. No", prompt));
        if (scanner.nextInt()==2) {
            return NO;
        }
        else {
            scanner.nextLine();
            return YES;
        }
    }
}
