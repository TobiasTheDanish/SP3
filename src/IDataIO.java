import java.util.ArrayList;

public interface IDataIO {
    void addUserData(User user);
    ArrayList<IMedia> getMediaData(String path, String type);
    String getSingleUserData(String username);
    ArrayList<String> readData(String path);
    void writeUserData(User user);
}
