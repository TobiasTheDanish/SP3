import java.util.ArrayList;

public interface IDataIO {
    void addUserData(User user);
    ArrayList<IMedia> getMediaData(String path, String type);
    ArrayList<IMedia> getSingleUserMediaData(String username, String type);
    ArrayList<String> readUserData();
    void writeUserData(User user);
    void removeMediaFromSavedMedia(User user, IMedia media);
}
