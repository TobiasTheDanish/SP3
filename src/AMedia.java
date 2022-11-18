import java.util.ArrayList;

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
    //A function for pausing and un-pausing the media.
    public void pause() {
        //The for loop is infinite, therefore you can never pause too many times.
        for (int i = 1; i > 0; i++) {
            String pause = TextUI.getInput("Press 'P' to pause "+ name + "\n" + "Press 'Q' to quit playing");
            TextUI.clearConsole();
            if (pause.equalsIgnoreCase("p")) {
                /*
                There is also a while loop to make sure if the wrong input is entered,
                it does not jump out of the if statement.
                */
                while (true) {
                    TextUI.clearConsole();
                    String unpause = TextUI.getInput(name+ " is paused. Press 'P' to un-pause." + "\n" + "Press 'Q' to quit playing");
                    if (unpause.equalsIgnoreCase("p")) {
                        TextUI.clearConsole();
                        TextUI.displayMessage(name+" is now playing..." + "\n");
                        break;

                    } else if (unpause.equalsIgnoreCase("q")) {
                        TextUI.displayMessage("Exiting play");
                        return;
                    }
                }
            }
                //Due to the for loop being infinite there is also a break function, to exit the loop.
                else if (pause.equalsIgnoreCase("q")) {
                    TextUI.displayMessage("Exiting play");
                    break;
                }
            }
        }
    }

