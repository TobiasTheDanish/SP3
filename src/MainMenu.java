import java.util.ArrayList;

public class MainMenu {

    public static IMedia search(String mediaName)
    {
        for (IMedia m: Application.movies)
        {
             if(m.getName().equalsIgnoreCase(mediaName))
             {
                 return m;
             }

        }
        for (IMedia s : Application.series)
        {
            if(s.getName().equalsIgnoreCase(mediaName))
            {
                return s;
            }
        }
        return null;
    }

    public static ArrayList<IMedia> searchName(String mediaName)
    {
        ArrayList<IMedia> resultList = new ArrayList<>();

        for (IMedia m: Application.movies)
        {
            if(m.getName().toLowerCase().contains(mediaName.toLowerCase()))
            {
                resultList.add(m);
            }

        }
        for (IMedia s : Application.series)
        {
            if(s.getName().toLowerCase().contains(mediaName.toLowerCase()))
            {
                resultList.add(s);
            }
        }

        return resultList;
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
