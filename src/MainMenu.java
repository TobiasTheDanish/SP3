import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    //Making a function where we are able to search by name in our arraylist
    //Does not make any difference whether it's a movie or a series
    public static IMedia Search(String mediaName) {
        //Using a foreach loop to be able to search in our arraylist
        // will return the name of the movie if it's in the list
        for (IMedia m: Application.movies) {
             if(m.getName().equalsIgnoreCase(mediaName)) {
                 return m;
             }

        }
        //Using a foreach loop to be able to search in our arraylist
        // will return the name of the movie if it's in the list
        for (IMedia s : Application.series) {
            if(s.getName().equalsIgnoreCase(mediaName)) {
                return s;
            }
        }
        return null;
    }


    public static ArrayList<IMedia> searchCategory(String category)
    {
        ArrayList<IMedia> searchResults = new ArrayList<>();
        for (IMedia m : Application.movies)
        {
            for (String cat : m.getCategories())
            {
                if (cat.equalsIgnoreCase(category))
                {
                    searchResults.add(m);

                }
            }
        }

        for (IMedia s : Application.series)
        {
            for (String cat : s.getCategories())
            {
                if (cat.equalsIgnoreCase(category))
                {
                    searchResults.add(s);
                }
            }
        }
        return searchResults;
    }


    public static ArrayList<IMedia> getUsersWatchedMedia(User user)
    {
        return user.getWatchedMedia();

    }

    //Add to a
    public static ArrayList<IMedia> getUsersSavedMedia(User user)
    {
        return user.getSavedMedia();

    }

}
