import java.util.ArrayList;

public interface IDataIO {
    void addUserData(User user);
    ArrayList<IMedia> getMediaData(String path, String type);
    ArrayList<IMedia> getSingleUserMediaData(String username, String type);
    ArrayList<String> readData(String path);
    void writeUserData(User user);
}
