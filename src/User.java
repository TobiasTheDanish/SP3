import java.util.ArrayList;

public class User{

    private String username;
    private String password;
    private ArrayList<IMedia> watchedMedia;
    private ArrayList<IMedia> savedMedia;

    private IDataIO dataIO;

    public User(String username, String password, IDataIO dataIO) {
        this.username = username;
        this.password = password;
        this.dataIO = dataIO;
        watchedMedia = initWatchedMedia(username);
        savedMedia = initSavedMedia(username);
    }

    private ArrayList<IMedia> initWatchedMedia(String username) {
        //Returns the users watched media data based on the entered username
        //if the user does not have any watched media connected this will return an empty arraylist
        return this.dataIO.getSingleUserMediaData(username, "watched");
    }

    private ArrayList<IMedia> initSavedMedia(String username) {
        //Returns the users watched media data based on the entered username
        //if the user does not have any watched media connected this will return an empty arraylist
        return this.dataIO.getSingleUserMediaData(username, "saved");
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
            this.dataIO.writeUserData(this);
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
            this.dataIO.writeUserData(this);
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
               this.dataIO.writeUserData(this);
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
