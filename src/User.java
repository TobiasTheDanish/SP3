import java.util.ArrayList;

public class User
{

    private String username;
    private String password;
    private ArrayList<IMedia> watchedMedia;
    private ArrayList<IMedia> savedMedia;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        watchedMedia = initWatchedMedia(username);
        savedMedia = initSavedMedia(username);
    }

    private ArrayList<IMedia> initWatchedMedia(String username)
    {
        ArrayList<IMedia> returnList = new ArrayList<>();
        String data = FileIO.getSingleUserData(username);
        if (data == null) return new ArrayList<>();

        String watchedMediaStr = data.split(",")[2];
        if (watchedMediaStr.equalsIgnoreCase("null")) return new ArrayList<>();

        String[] watchedMedia = watchedMediaStr.split(":");

        for (String mediaName : watchedMedia)
        {
            returnList.add(MainMenu.Search(mediaName));
        }

        return returnList;
    }

    private ArrayList<IMedia> initSavedMedia(String username)
    {
        ArrayList<IMedia> returnList = new ArrayList<>();

        String data = FileIO.getSingleUserData(username);
        if (data == null) return new ArrayList<>();

        String savedMediaStr = data.split(",")[3];
        if (savedMediaStr.equalsIgnoreCase("null")) return new ArrayList<>();

        String[] savedMedia = savedMediaStr.split(":");

        for (String mediaName : savedMedia)
        {
            returnList.add(MainMenu.Search(mediaName));
        }

        return returnList;
    }

    public ArrayList<IMedia> getWatchedMedia() {
        return watchedMedia;
    }

    public ArrayList<IMedia> getSavedMedia() {
        return savedMedia;
    }

    public void addToWatchedMedia (IMedia media){
        if (!listContainsMedia(watchedMedia, media))
        {
            watchedMedia.add(media);
            TextUI.displayMessage(media.getName() + " has been added to watched media.");
        }
        else
        {
            TextUI.displayMessage(media.getName() + " has already been added to watched media.");
        }
        //Add the selected movie to: ArrayList<IMedia> getWatchedMedia()
    }

    public void addToSavedMedia (IMedia media){
        if (!listContainsMedia(savedMedia, media))
        {
            savedMedia.add(media);
            TextUI.displayMessage(media.getName() + " has been added to saved media.");
        }
        else
        {
            TextUI.displayMessage(media.getName() + " has already been added to saved media.");
        }
        //Add the selected movie to: ArrayList<IMedia> getSavedMedia()
    }

    public void removeFromSavedMedia(IMedia media)
    {
        for (int i = 0; i < savedMedia.size(); i++) {
            IMedia m = savedMedia.get(i);
            if (m.getName().equals(media.getName()))
            {
               savedMedia.remove(i);
               TextUI.displayMessage(media.getName()+" has been removed from saved media.");
               return;
            }
        }
    }

    public boolean listContainsMedia(ArrayList<IMedia> list, IMedia media)
    {
        for (IMedia m : list)
        {
            if (m.getName().equals(media.getName()))
            {
                return true;
            }
        }
        return false;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
