import java.util.ArrayList;

public class Series extends AMedia {

    private int seasons;
    private int episodes;

    public Series(String name, String publishingYear, ArrayList<String> categories, float rating, int seasons, int episodes) {
        super(name, publishingYear, categories, rating);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public void play() {
        TextUI.displayMessage(name + " S" + seasons + " : E" + episodes + " is now playing...");
        pause();
    }
    public String getName() {
        return name;
    }
    public String getPublishingYear() {
        return publishingYear;
    }
    public ArrayList<String> getCategories() {
        return categories;
    }
    public float getRating() {
        return rating;
    }
}
