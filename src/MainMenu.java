import java.util.ArrayList;

public class MainMenu {

    public IMedia Search(String mediaName){
        return new Movie("1", "1", new ArrayList<>(), 12);
    }

    public ArrayList<IMedia> searchCategory(String category){
        return new ArrayList<>();
    }

    public ArrayList<IMedia> getUsersWatchedMedia(User user){
        return new ArrayList<>();
    }

    public ArrayList<IMedia> getUsersSavedMedia(User user){
        return new ArrayList<>();
    }
}
