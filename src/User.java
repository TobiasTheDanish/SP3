import java.util.ArrayList;

public class User extends Stream {

    private String username;
    private String password;
    private ArrayList<IMedia> watchedMedia;
    private ArrayList<IMedia> savedMedia;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ArrayList<IMedia> getWatchedMedia() {
        return watchedMedia;
    }

    public ArrayList<IMedia> getSavedMedia() {
        return savedMedia;
    }

    public void addToWatchedMedia (IMedia media){
        //Add the selected movie to: ArrayList<IMedia> getWatchedMedia()
    }

    public void addToSavedMedia (IMedia media){
        //Add the selected movie to: ArrayList<IMedia> getSavedMedia()
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
