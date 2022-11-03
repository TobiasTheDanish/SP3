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

    }
}
