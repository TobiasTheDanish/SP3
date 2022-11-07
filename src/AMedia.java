import java.util.ArrayList;
import java.util.Scanner;

public abstract class AMedia implements IMedia {

    public String name;
    public String publishingYear;
    public ArrayList<String> categories;
    public float rating;

    public AMedia(String name, String publishingYear, ArrayList<String> categories, float rating) {
        this.name = name;
        this.publishingYear = publishingYear;
        this.categories = categories;
        this.rating = rating;
    }

    public void pause() {
        Scanner input = new Scanner(System.in);

        for (int i = 1; i > 0; i++) {
            TextUI.displayMessage("Press 'P' to pause the media" + "\n" + "Press 'Q' to exit");
            String pause = input.nextLine();
            if (pause.equalsIgnoreCase("p")) {
                TextUI.displayMessage("The media is paused. Press 'P' to unpause." + "\n" + "Press 'Q' to exit");
                String unpause = input.nextLine();
                if (unpause.equalsIgnoreCase("p")) {
                    Application.clearConsole();
                    TextUI.displayMessage("The media is now playing..." + "\n");
                } else if (unpause.equalsIgnoreCase("q")) {
                    TextUI.displayMessage("Exiting play");
                    break;
                }
            }
                else if (pause.equalsIgnoreCase("q")) {
                TextUI.displayMessage("Exiting play");
                break;
            }
        }
    }
}

