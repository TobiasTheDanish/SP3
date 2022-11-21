import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediaDB implements IDataIO
{
    private Connection connection;
    private final String url = "jdbc:mysql://localhost/sp3+?" + "autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false";
    private final String username = "root";
    private final String password = "admin123";
    @Override
    //Add a new user to the user table with username and password
    public void addUserData(User user) {
        establishConnection();
        String query = "INSERT INTO users (username, password) VALUES (?,?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    //Retrieve all media data based on the type(series or movie) from the db and return it as an Arraylist
    public ArrayList<IMedia> getMediaData(String path, String type)
    {
        ArrayList<IMedia> movies = new ArrayList<IMedia>();
        establishConnection();
        String query = "SELECT * FROM movies;";
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String title = resultSet.getString("title");
                String publishing_year = resultSet.getString("publishing_year");
                String category = resultSet.getString("category");
                List<String> list = Arrays.asList(category.split(","));
                list.replaceAll(String::trim);
                ArrayList<String> categories = new ArrayList<>(list);
                float rating = resultSet.getFloat("rating");
                Movie movie = new Movie(title, publishing_year, categories, rating);
                movies.add(movie);
            }
            System.out.println(resultSet);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    //TODO: Refactor this together with User's initWatchedMedia and initSavedMedia functions
    @Override
    public String getSingleUserData(String username)
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

    private void establishConnection(){
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
