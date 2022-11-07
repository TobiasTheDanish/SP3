import java.util.ArrayList;

public class MainMenu {

    public static IMedia Search(String mediaName){
        if (mediaName.equalsIgnoreCase("null"))
        {
            return null;
        }

        return new Movie("Test", "1", new ArrayList<>(), 12);
    }

    public static ArrayList<IMedia> searchCategory(String category){
        ArrayList<IMedia> test = new ArrayList<>();
        test.add(new Movie("Test", "1", new ArrayList<>(), 9.5f));
        return test;
    }

    public static ArrayList<IMedia> getUsersWatchedMedia(User user){
        ArrayList<IMedia> test = new ArrayList<>();
        test.add(new Movie("Test", "1", new ArrayList<>(), 9.5f));
        return test;
    }

    public static ArrayList<IMedia> getUsersSavedMedia(User user){
        ArrayList<IMedia> test = new ArrayList<>();
        test.add(new Movie("Test", "1", new ArrayList<>(), 9.5f));
        return test;
    }
}
