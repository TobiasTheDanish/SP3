import java.util.ArrayList;

public class MainMenu {
private User user;

    public MainMenu(User user)
    {
        this.user = user;
    }

    public IMedia Search(String mediaName)
    {

        for (IMedia m: Application.Movies)
        {
         if(m.getName().equalsIgnoreCase(mediaName))
         {
             return m;
         }

        }
        for (IMedia s : Application.Series)
        {
        if(s.getName().equalsIgnoreCase(mediaName))
        {
            return s;

        }

        }

        return null;

    }


    public ArrayList<IMedia> searchCategory(String category)
    {
        ArrayList<IMedia> tom = new ArrayList<>();
        for (IMedia m : Application.Movies)
        {
            for (String cat : m.getCategories())
            {
                if (cat.equalsIgnoreCase(category))
                {
                    tom.add(m);

                }
            }

            for (IMedia s : Application.Series)
            {
                for (String cat : s.getCategories())
                {
                    if (cat.equalsIgnoreCase(category))
                    {
                        tom.add(s);
                    }
                }
            }


        }
        return tom;
    }


    public ArrayList<IMedia> getUsersWatchedMedia(User user)
    {
        return user.getWatchedMedia();

    }

    //Add to a
    public ArrayList<IMedia> getUsersSavedMedia(User user)
    {
        return user.getSavedMedia();

    }

}
