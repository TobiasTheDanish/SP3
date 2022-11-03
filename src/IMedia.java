import java.util.ArrayList;

public interface IMedia {

    String getName();

    String getPublishingYear();

    ArrayList<String> getCategories();

    float getRating();

    void play();

    void pause();
}
