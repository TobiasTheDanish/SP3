import java.util.ArrayList;

public class Application
{

    private User currentUser;
    private StartMenu startMenu;
    private MainMenu mainMenu;
    public static ArrayList <IMedia> Movies;
    public static ArrayList <IMedia> Series;
   public Application() {
       //Init startMenu to new StartMenu object

       //Set the lists of movies, according to our data file (getMediaData("data/moviedata.csv", "movie")
       //Set the lists of series, according to our data file (getMediaData("data/seriesdata.csv", "series")
   }
    public static void run() {
       StartMenu.logIn();
        //currentUser = startMenu.logIn() //Get the logged in user from the startMenu.

        /*The following might need a loop, so the user can access multiple media

        //Init mainMenu to new MainMenu object with currentUser as a parameter in the constructor
        //watch(mainMenu.display()); //calls the watch function and expects mainMenu.display() to return an IMedia type
        */
    }
    public void watch(IMedia media) {
        //media.play(); //Play the passed in IMedia type
        //currentUser.addToWatched(media); //Add the passed in IMedia type to the users watched list
    }

    private ArrayList<IMedia> getMediaData(String filePath, String type){
        //The type parameter should tell us whether it's a movie or a series.

       //Read the file at located at filePath (FileIO.readFile(filePath)) and save it in an Arraylist<String> called data
       //Split each string in data, so that we can create a IMedia type object based on the type (either a movie or series)
       //then add that IMedia to an Arraylist<IMedia>.

       //When we have looped over all the strings in data, return the Arraylist<IMedia>
       return new ArrayList<>();
    }
}
