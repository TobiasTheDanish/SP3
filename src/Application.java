import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application
{
    private static User currentUser;
    public static ArrayList <IMedia> movies;
    public static ArrayList <IMedia> series;
   public Application() {
   }
    public static void run() {
        movies = getMediaData("data/moviedata.csv", "movie");
        series = getMediaData("data/seriesdata.csv", "series");

        TextUI.displayMessage("------------------------------");
        TextUI.displayMessage("     Welcome to Dataflix.");
        TextUI.displayMessage("------------------------------");
        currentUser = StartMenu.logIn(); //Get the logged in user from the startMenu.

        /*The following might need a loop, so the user can access multiple media

        //Init mainMenu to new MainMenu object with currentUser as a parameter in the constructor
        //watch(mainMenu.display()); //calls the watch function and expects mainMenu.display() to return an IMedia type
        */

        TextUI.displayMessage("------------------------------");
        TextUI.displayMessage("Welcome " + currentUser.getUsername() + ".");
        TextUI.getInput("Press enter to start browsing.");


        displayUserOptions();

        TextUI.displayMessage("---------------------------------------------------------");
        TextUI.displayMessage("     Thank you for using Dataflix. Logging out...");
        TextUI.displayMessage("---------------------------------------------------------");
    }
    public static void watch(IMedia media) {
        media.play(); //Play the passed in IMedia type
        currentUser.addToWatchedMedia(media); //Add the passed in IMedia type to the users watched list
    }

    private static void displayUserOptions()
    {
        String input = "";
        do
        {
            clearConsole();
            //TextUI.getInput("Press enter to view your options.");
            TextUI.displayMessage("---------------------------------------------------------");
            TextUI.displayMessage("What action do you wish to make?");
            TextUI.displayMessage("1)   Search for media by name.\n" +
                                          "2)   Search for media by category.\n" +
                                          "3)   View your saved medias.\n" +
                                          "4)   View your watched medias.\n");
            input = TextUI.getInput("Enter your selection, or press Q to quit:");

            //Add switch case for above input
            switch (input)
            {
                case "q":
                case "Q":
                    break;

                case "1":
                {
                    IMedia media;
                    do
                    {
                        String mediaName = TextUI.getInput("What is the name of the media you want to watch?");
                        media = MainMenu.Search(mediaName);

                        if (media == null)
                        {
                            TextUI.displayMessage("That name was not found, try again.");
                        }
                    } while (media == null);

                    TextUI.displayMessage(media.getName() + " from " + media.getPublishingYear() + " was found.");
                    watch(media);
                    break;
                }
                case "2":
                    String categoryName = TextUI.getInput("Enter the category you would like to filter by:");
                    ArrayList<IMedia> categoryList = MainMenu.searchCategory(categoryName);
                    //Display category list to user
                    TextUI.getInput("There is " + categoryList.size() + " media in " + categoryName + ".\nPress enter to view the list.");
                    for (int i = 0; i < categoryList.size(); i++)
                    {
                        IMedia media = categoryList.get(i);
                        TextUI.displayMessage((i+1) + ")    " + media.getName() + " from " + media.getPublishingYear() + " has a rating of " + media.getRating() + "/10.");
                    }

                    onMediaSelected(selectMedia(), false);
                    break;

                case "3":
                    ArrayList<IMedia> savedMedia = MainMenu.getUsersSavedMedia(currentUser);
                    TextUI.getInput("There is " + savedMedia.size() + " media in your saved media list." + "\nPress enter to view the list.");
                    for (int i = 0; i < savedMedia.size(); i++)
                    {
                        IMedia media = savedMedia.get(i);
                        TextUI.displayMessage((i+1) + ")    " + media.getName() + " from " + media.getPublishingYear() + " has a rating of " + media.getRating() + "/10.");
                    }
                    onMediaSelected(selectMedia(), true);
                    break;

                case "4":
                    ArrayList<IMedia> watchedMedia = MainMenu.getUsersWatchedMedia(currentUser);
                    TextUI.getInput("There is " + watchedMedia.size() + " media in your watched media list." + "\nPress enter to view the list.");
                    for (int i = 0; i < watchedMedia.size(); i++)
                    {
                        IMedia media = watchedMedia.get(i);
                        TextUI.displayMessage((i+1) + ")    " + media.getName() + " from " + media.getPublishingYear() + " has a rating of " + media.getRating() + "/10.");
                    }
                    onMediaSelected(selectMedia(), false);
                    break;

                default:
                    TextUI.getInput("That was not a valid action. Press enter to try again.");
                    clearConsole();
                    break;
            }
        }
        while (!input.equalsIgnoreCase("q"));
    }

    private static IMedia selectMedia()
    {
        return new Movie("Test", "1", new ArrayList<>(), 8.5f);
    }

    private static void onMediaSelected(IMedia selectedMedia, boolean savedMedia)
    {
        String input = "";

        clearConsole();
        TextUI.displayMessage("You selected " + selectedMedia.getName() + " from " + selectedMedia.getPublishingYear() + ".");
        do
        {
            TextUI.displayMessage("What do you wish to do?");
            TextUI.displayMessage("1)   Watch the media.");
            if (!savedMedia)
            {
                TextUI.displayMessage("2)   Save to your saved medias list.");
            }
            input = TextUI.getInput("Enter your selection, or press Q to quit:");

            switch (input)
            {
                case "q":
                case "Q":
                    break;

                case "1":
                    clearConsole();
                    watch(selectedMedia);
                    TextUI.getInput(selectedMedia.getName() + " is done playing.\nPress enter to return to the main menu.");
                    input = "q";
                    clearConsole();
                    break;

                case "2":
                    if (!savedMedia)
                    {
                        currentUser.addToSavedMedia(selectedMedia);
                        savedMedia = true;
                        break;
                    }

                default:
                    TextUI.getInput("That was not a valid action. Press enter to try again.");
                    clearConsole();
                    break;
            }
        } while(!input.equalsIgnoreCase("q"));

        clearConsole();
    }

    private static ArrayList<IMedia> getMediaData(String filePath, String type){
        //The type parameter should tell us whether it's a movie or a series (An enum would be better).

        //Read the file at located at filePath (FileIO.readFile(filePath)) and save it in an Arraylist<String> called data
        //Split each string in data, so that we can create a IMedia type object based on the type (either a movie or series)
        //then add that IMedia to an Arraylist<IMedia>.

        //When we have looped over all the strings in data, return the Arraylist<IMedia>
        ArrayList<IMedia> medias = new ArrayList<>();
        ArrayList<String> data = FileIO.readFile(filePath);

        switch (type) {
            case "movie":
                for (String s : data) {
                    String[] movieData = s.split(";");
                    String name = movieData[0].trim();
                    String publishingYear = movieData[1].trim();
                    List<String> list = Arrays.asList(movieData[2].split(","));
                    ArrayList<String> categories = new ArrayList<>(list);
                    float rating = Float.parseFloat(movieData[3].replace(',', '.'));
                    IMedia m = new Movie(name, publishingYear, categories, rating);

                    medias.add(m);
                }
                break;

            case "series":
                for (String s : data) {
                    String[] movieData = s.split(";");
                    String name = movieData[0].trim();
                    String publishingYear = movieData[1].trim();
                    List<String> list = Arrays.asList(movieData[2].split(","));
                    ArrayList<String> categories = new ArrayList<>(list);
                    float rating = Float.parseFloat(movieData[3].replace(',', '.'));
                    String[] seasonsAndEpisodes = movieData[4].split(",");
                    int seasons = seasonsAndEpisodes.length;
                    int episodes = 0;
                    for (String str : seasonsAndEpisodes)
                    {
                        episodes += Integer.parseInt(str.split("-")[1]);
                    }

                    IMedia m = new Series(name, publishingYear, categories, rating, seasons, episodes);
                    medias.add(m);
                }
                break;

            default:
                return new ArrayList<>();
        }

       return medias;
    }

    public static void clearConsole()
    {
        for (int i = 0; i < 100; i++)
        {
            TextUI.displayMessage("");
        }
    }
}
