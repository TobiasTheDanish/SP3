import java.sql.*;
import java.util.ArrayList;

public class MediaDB implements IDataIO
{

    @Override
    public void addUserData(User user) {
        //Add a new user to the user table with username and password
    }

    @Override
    public ArrayList<IMedia> getMediaData(String path, String type)
    {
        //Retrieve all media data based on the type(series or movie) from the db and return it as an Arraylist

        return null;
    }

    //TODO: Refactor this together with User's initWatchedMedia and initSavedMedia functions
    @Override
    public ArrayList<IMedia> getSingleUserMediaData(String username, String type)
    {
        return null;
    }

    @Override
    public ArrayList<String> readData(String path)
    {
        //Read all the data from db based on the path given in the parameter
        return null;
    }

    @Override
    public void writeUserData(User user) {
        //Save the data given in the parameter in the correct tables
    }
}
