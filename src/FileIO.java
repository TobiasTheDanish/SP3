import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileIO implements IDataIO {

    @Override
    public ArrayList<String> readData(String filePath) {
        File file = new File(filePath);
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner input = new Scanner(file);
            input.nextLine();//ignore header

            while (input.hasNextLine()) {
                data.add(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            data = null;
            System.out.println("Something went wrong");
        }
        return data;
    }

    @Override
    public void writeUserData(User currentUser) {
        ArrayList<String> data = readData("data/userdata.csv");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).split(",")[0].equals(currentUser.getUsername())) {
                data.set(i, createUserDataString(currentUser));
            }
        }
        try {
            FileWriter writer = new FileWriter("data/userdata.csv");
            //making a template for the userdata
            writer.write("username, password, watchedMovie1: watchedMovie2, savedMovie1: savedMovie2\n");
            for (String s : data) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void addUserData(User user) {
        ArrayList<String> data = readData("data/userdata.csv");

        try {
            FileWriter writer = new FileWriter("data/userdata.csv");
            //making a template for the userdata
            writer.write("username, password, watchedMovie1: watchedMovie2, savedMovie1: savedMovie2\n");
            for (String s : data) {
                writer.write(s + "\n");
            }
            writer.write(createUserDataString(user));
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public ArrayList<IMedia> getSingleUserMediaData(String username, String type) {
        ArrayList<String> data = readData("data/userdata.csv");
        ArrayList<IMedia> returnList = new ArrayList<>();

        switch (type) {
            case "watched":
                for (String s : data) {
                    if (s.split(",")[0].equalsIgnoreCase(username)) {
                        String watchedMediaStr = s.split(",")[2];
                        //Return an empty array if the stored value was "null".
                        if (watchedMediaStr.equalsIgnoreCase("null")) return new ArrayList<>();

                        //Each media is separated by ":", this way we get the name of each media
                        String[] watchedMedia = watchedMediaStr.split(":");

                        for (String mediaName : watchedMedia) {
                            returnList.add(Application.getMediaByName(mediaName));
                        }
                    }
                }
                break;

            case "saved":
                for (String s : data) {
                    if (s.split(",")[0].equalsIgnoreCase(username)) {
                        String savedMediaStr = s.split(",")[3];
                        //Return an empty array if the stored value was "null".
                        if (savedMediaStr.equalsIgnoreCase("null")) return new ArrayList<>();

                        //Each media is separated by ":", this way we get the name of each media
                        String[] savedMedia = savedMediaStr.split(":");

                        for (String mediaName : savedMedia) {
                            returnList.add(Application.getMediaByName(mediaName));
                        }
                    }
                }
                break;
        }
        return returnList;
    }

    @Override
    public ArrayList<IMedia> getMediaData(String filePath, String type) {
        //The type parameter should tell us whether it's a movie or a series (An enum would be better).
        //Read the file at located at filePath (FileIO.readFile(filePath)) and save it in an Arraylist<String> called data
        //Split each string in data, so that we can create a IMedia type object based on the type (either a movie or series)
        //then add that IMedia to an Arraylist<IMedia>.
        //When we have looped over all the strings in data, return the Arraylist<IMedia>
        ArrayList<IMedia> medias = new ArrayList<>();
        ArrayList<String> data = readData(filePath);

        switch (type) {
            case "movie":
                //Loop over all the date from the file.
                for (String s : data) {
                    //Split each line of data into a String array.
                    String[] movieData = s.split(";");
                    //The first element in the array is the movies name
                    String name = movieData[1].trim();
                    //The second element in the array is the movies publishing year
                    String publishingYear = movieData[2].trim();
                    //The third element in the array is the movies categories
                    List<String> list = Arrays.asList(movieData[3].split(","));
                    list.replaceAll(String::trim);
                    ArrayList<String> categories = new ArrayList<>(list);
                    //The fourth element in the array is the movies rating
                    float rating = Float.parseFloat(movieData[4].replace(',', '.'));
                    //Instantiate new Movie object
                    IMedia m = new Movie(name, publishingYear, categories, rating);
                    //Save the new object to the media arraylist.
                    medias.add(m);
                }
                break;

            case "series":
                //Loop over all the date from the file.
                for (String s : data) {
                    //Split each line of data into a String array.
                    String[] movieData = s.split(";");
                    //The first element in the array is the series' name
                    String name = movieData[1].trim();
                    //The second element in the array is the series' publishing year
                    String publishingYear = movieData[2].trim();
                    //The third element in the array is the series' categories
                    List<String> list = Arrays.asList(movieData[3].split(","));
                    list.replaceAll(String::trim);
                    ArrayList<String> categories = new ArrayList<>(list);
                    //The fourth element in the array is the series' rating
                    float rating = Float.parseFloat(movieData[4].replace(',', '.'));
                    //The fifth element in the array is the seasons and episodes
                    String[] seasonsAndEpisodes = movieData[5].split(",");
                    //The number of seasons is the length of the above array.
                    int seasons = seasonsAndEpisodes.length;
                    int episodes = 0;
                    for (String str : seasonsAndEpisodes) {
                        //Adds together all the episodes from the seasons end episodes array.
                        episodes += Integer.parseInt(str.split("-")[1]);
                    }
                    //Instantiate new Series object
                    IMedia m = new Series(name, publishingYear, categories, rating, seasons, episodes);
                    //Save the new object to the media arraylist.
                    medias.add(m);
                }
                break;

            default:
                //If something else is passed in we return an empty arraylist.
                return new ArrayList<>();
        }
        //Return the media arraylist
        return medias;
    }

    private static String createUserDataString(User user) {
        String userData = user.getUsername() + "," + user.getPassword() + ",";

        ArrayList<IMedia> watchedMedia = user.getWatchedMedia();
        if (watchedMedia.size() > 0) {
            for (int j = 0; j < watchedMedia.size() - 1; j++) {
                IMedia m = watchedMedia.get(j);
                userData += m.getName() + ":";
            }
            IMedia m = watchedMedia.get(watchedMedia.size()-1);
            userData += m.getName() + ",";
        } else {
            userData += "null,";
        }

        ArrayList<IMedia> savedMedia = user.getSavedMedia();
        if (savedMedia.size() > 0) {
            for (int j = 0; j < savedMedia.size() - 1; j++) {
                IMedia m = savedMedia.get(j);
                userData += m.getName() + ":";
            }
            IMedia m = savedMedia.get(savedMedia.size()-1);
            userData += m.getName();
        } else {
            userData += "null";
        }
        return userData;
    }
}
