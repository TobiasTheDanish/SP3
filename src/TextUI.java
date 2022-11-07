import java.util.Scanner;

public class TextUI {

    private static Scanner scn = new Scanner(System.in);

    public static void displayMessage(String msg){
        System.out.println(msg);
    }
    public static String getInput(String msg){
        displayMessage(msg);
        return scn.nextLine();
    }
}
