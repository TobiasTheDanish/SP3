import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    public static ArrayList<String> readFile(String filePath) {
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

    public static void writeUserDataToFile(User currentUser) {
        ArrayList<String> data = readFile("data/userdata.csv");
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

    public static void addToFile(User user) {
        ArrayList<String> data = readFile("data/userdata.csv");

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

    public static String getSingleUserData(String username) {
        ArrayList<String> data = FileIO.readFile("data/userdata.csv");

        for (String s : data) {
            if (s.split(",")[0].equalsIgnoreCase(username)) {
                return s;
            }
        }
        return null;
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
