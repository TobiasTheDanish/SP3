import java.util.Scanner;

public class TextUI {

    private static Scanner scn = new Scanner(System.in);

    public static void displayMessage(String msg) {
        System.out.println(msg);
    }
    public static String getInput(String msg) {
        displayMessage(msg);
        return scn.nextLine();
    }

    public static void clearConsole() {
        for (int i = 0; i < 100; i++) {
            //Print 100 empty lines to make the console look like it has been cleared.
            displayMessage("");
        }
    }
}
