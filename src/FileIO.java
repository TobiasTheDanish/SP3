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
                data.add(input.nextLine());//“Egon: 30000”
            }

        } catch (FileNotFoundException e) {
            data = null;
            System.out.println("Something went wrong");
        }
        return data;
    }


    //NAVNET ER MISVISENDE --- RENAME ---
    public static void writeToFile(User currentUser) {
        ArrayList<String> data = readFile("data/userdata.csv");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).split(",")[0].equals(currentUser.getUsername())) {
                data.set(i, currentUser.getUsername() + "," + currentUser.getPassword());
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
            writer.write(user.getUsername() + "," + user.getPassword());

            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}//class end
