import java.util.ArrayList;

public class MainMenu {
private User user;

    public MainMenu(User user)
    {
        this.user = user;
    }

    public IMedia Search(String mediaName)
    {
        return new Movie("1", "1", new ArrayList<>(), 12);
        /*
        search for a movie/series name in the csv files
         */
    }


    public ArrayList<IMedia> searchCategory(String category)
    {
        return new ArrayList<>();
        /*search for a movie/series category with a string
        */

    }


    public ArrayList<IMedia> getUsersWatchedMedia(User user)
    {
        return new ArrayList<>();
        //get users watched media from User user "".
    }

    //Add to a
    public ArrayList<IMedia> getUsersSavedMedia(User user)
    {
        return new ArrayList<>();
        //get users saved media from User user
    }

}
