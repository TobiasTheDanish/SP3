import java.util.ArrayList;

public class User{

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

    private ArrayList<IMedia> initWatchedMedia(String username) {
        //Initialize a list to store the users media
        ArrayList<IMedia> returnList = new ArrayList<>();

        //Read the users data based on the given username;
        String data = FileIO.getSingleUserData(username);

        /* Data is null if there is no user with that username
           then there is no media data either. Therefore, return an empty arraylist.
         */
        if (data == null) return new ArrayList<>();

        //we split the user string, and get the index[2] because that is where the watched media list is stored
        String watchedMediaStr = data.split(",")[2];
        //Return an empty array if the stored value was "null".
        if (watchedMediaStr.equalsIgnoreCase("null")) return new ArrayList<>();

        //Each media is separated by ":", this way we get the name of each media
        String[] watchedMedia = watchedMediaStr.split(":");

        for (String mediaName : watchedMedia) {
            returnList.add(Application.getMediaByName(mediaName));
        }
        return returnList;
    }

    private ArrayList<IMedia> initSavedMedia(String username) {
        //Initialize a list to store the users media
        ArrayList<IMedia> returnList = new ArrayList<>();

        //Read the users data based on the given username;
        String data = FileIO.getSingleUserData(username);

        /* Data is null if there is no user with that username
           then there is no media data either. Therefore, return an empty arraylist.
         */
        if (data == null) return new ArrayList<>();

        //we split the user string, and get the index[3] because that is where the saved media list is stored
        String savedMediaStr = data.split(",")[3];
        //Return an empty array if the stored value was "null".
        if (savedMediaStr.equalsIgnoreCase("null")) return new ArrayList<>();

        //Each media is separated by ":", this way we get the name of each media
        String[] savedMedia = savedMediaStr.split(":");

        for (String mediaName : savedMedia) {
            returnList.add(Application.getMediaByName(mediaName));
        }
        return returnList;
    }

    public ArrayList<IMedia> getWatchedMedia() {
        return watchedMedia;
    }

    public ArrayList<IMedia> getSavedMedia() {
        return savedMedia;
    }

    public void addToWatchedMedia (IMedia media) {
        //If the list of watched media, does not contain the watched media, it will be added.
        if (!listContainsMedia(watchedMedia, media)) {
            watchedMedia.add(media);
            FileIO.writeUserDataToFile(this);
            TextUI.displayMessage(media.getName() + " has been added to watched media.");
            //Else it will not be added.
        } else {
            TextUI.displayMessage(media.getName() + " has already been added to watched media.");
        }
    }

    public void addToSavedMedia (IMedia media) {
        //If the list of saved media, does not contain the saved media, it will be added.
        if (!listContainsMedia(savedMedia, media)) {
            savedMedia.add(media);
            FileIO.writeUserDataToFile(this);
            TextUI.displayMessage(media.getName() + " has been added to saved media.");
            //Else it will not be added.
        } else {
            TextUI.displayMessage(media.getName() + " has already been added to saved media.");
        }
    }

    public void removeFromSavedMedia(IMedia media) {
        //A for loop going through the saved media list and giving indexes.
        for (int i = 0; i < savedMedia.size(); i++) {
            IMedia m = savedMedia.get(i);
            //An if statement for removing saved media from the list
            if (m.getName().equals(media.getName())) {
               savedMedia.remove(i);
               FileIO.writeUserDataToFile(this);
               TextUI.displayMessage(media.getName()+" has been removed from saved media.");
               return;
            }
        }
    }

    public boolean listContainsMedia(ArrayList<IMedia> list, IMedia media) {
        if (media == null) {
            return false;
        }
        for (IMedia m : list) {
            if (m.getName().equals(media.getName())) {
                return true;
            }
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
