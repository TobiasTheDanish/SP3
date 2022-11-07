import java.util.ArrayList;

public class Movie extends AMedia
    {
    public Movie(String name, String publishingYear, ArrayList<String> categories, float rating) {
        super(name, publishingYear, categories, rating);
    }

    public void play(){
        TextUI.displayMessage(name + " from " + publishingYear + " is now playing...");
        pause();
    }

        public String getName()
    {
        return name;
    }
    public String getPublishingYear()
    {
        return publishingYear;
    }
    public ArrayList<String> getCategories()
    {
        return categories;
    }
    public float getRating()
    {
        return rating;
    }
}
