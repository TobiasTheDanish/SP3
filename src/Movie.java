import java.util.ArrayList;

public class Movie extends AMedia
    {
    public Movie(String name, String publishingYear, ArrayList<String> categories, float rating) {
        super(name, publishingYear, categories, rating);
    }

    public void play()
    {
        /*
        After pressing play display the movie title
        maybe display a rating og time length

         */
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
