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

    public void pause(){
        //Print out option for pausing the movie/episode
        //Make an input available (for example "p") for pausing the movie/episode
        //Once it is paused, print out a message to make the user aware
        //Make an input available once again (for example "p" again, for easy use), to unpause the movie/episode
    }
}
