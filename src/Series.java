import java.util.ArrayList;

public class Series extends AMedia {

    private int seasons;
    private int episodes;

    public Series(String name, String publishingYear, ArrayList<String> categories, float rating, int seasons, int episodes){
        super(name, publishingYear, categories, rating);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public void play(){
        //Use TextUI.displayMessage(msg) to write to the console with the following string being msg:
        //Write to the console that:
            //name + " S" + seasons + ":E" + episodes + " is now playing!";
    }

    public int getSeasons(){
        return seasons;
    }
    public int getEpisodes(){
        return episodes;
    }

    public String getName(){
        return name;
    }
    public String getPublishingYear(){
        return publishingYear;
    }
    public ArrayList<String> getCategories(){
        return categories;
    }
    public float getRating(){
        return rating;
    }

}
