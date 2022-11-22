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
    public ArrayList<IMedia> getMediaData(String path, String type) {
        ArrayList<IMedia> medias = new ArrayList<>();

        establishConnection();
        String query = "SELECT * FROM media WHERE media_type = ?;";

        try
        {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, type);

            ResultSet resultSet = statement.executeQuery();

            switch (type)
            {
                case ("movie"):
                {
                    try {
                        while (resultSet.next())
                        {
                            String title = resultSet.getString("title");
                            String publishing_year = resultSet.getString("publishing_year");
                            String category = resultSet.getString("category");
                            List<String> list = Arrays.asList(category.split(","));
                            list.replaceAll(String::trim);
                            ArrayList<String> categories = new ArrayList<>(list);
                            float rating = resultSet.getFloat("rating");
                            Movie movie = new Movie(title, publishing_year, categories, rating);
                            medias.add(movie);
                        }

                        connection.close();
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case ("series"):
                {
                    try
                    {
                        while (resultSet.next())
                        {
                            String title = resultSet.getString("title");
                            String publishing_year = resultSet.getString("publishing_year");
                            String category = resultSet.getString("category");
                            List<String> list = Arrays.asList(category.split(","));
                            list.replaceAll(String::trim);
                            ArrayList<String> categories = new ArrayList<>(list);
                            float rating = resultSet.getFloat("rating");
                            String episodesStr = resultSet.getString("episodes");
                            String[] seasonsAndEpisodes = episodesStr.split(",");
                            //The number of seasons is the length of the above array.
                            int seasons = seasonsAndEpisodes.length;
                            int episodes = 0;
                            for (String str : seasonsAndEpisodes)
                            {
                                //Adds together all the episodes from the seasons end episodes array.
                                episodes += Integer.parseInt(str.split("-")[1]);
                            }
                            Series series = new Series(title, publishing_year, categories, rating, seasons, episodes);
                            medias.add(series);
                        }

                        connection.close();
                    }
                    catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return medias;
        }

    //TODO: Refactor this together with User's initWatchedMedia and initSavedMedia functions
    @Override
    public ArrayList<IMedia> getSingleUserMediaData(String username, String type)
    {
        establishConnection();
        ArrayList<IMedia> returnList = new ArrayList<>();
        ResultSet resultSet;
        switch (type)
        {
            case "watched": {
                String query = "SELECT m.title, m.media_type FROM users AS u JOIN watched_media AS watched ON u.id = watched.user_id JOIN media AS m ON watched.media_id = m.id WHERE u.username = ?;";

                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, username);

                    resultSet = statement.executeQuery();
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case "saved": {
                String query = "SELECT m.title, m.media_type FROM users AS u JOIN saved_media AS saved ON u.id = saved.user_id JOIN media AS m ON saved.media_id = m.id WHERE u.username = ?;";

                try {
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, username);

                    resultSet = statement.executeQuery();
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            default:
                try {
                    TextUI.displayMessage("Not the correct type, in MediaDB.getSingleUserMediaData()");
                    resultSet = connection.prepareStatement("").executeQuery();
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }

        try {
            while (resultSet.next()) {
                returnList.add(Application.getMediaByName(resultSet.getString("title")));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return returnList;
    }

    @Override
    public ArrayList<String> readUserData()
    {
        //Read all the data from db based on the path given in the parameter
        ArrayList<String> returnList = new ArrayList<>();
        establishConnection();
        String query = "SELECT * FROM users;";

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                returnList.add(resultSet.getString("username") + "," + resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return returnList;
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
