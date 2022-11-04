import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    public ArrayList<String> readFile() {
        File file = new File("data/userdata.csv");
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


    public void writeToFile(User currentUser) {
       ArrayList<String> data = readFile();
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).split(",")[0].equals(currentUser.getUsername())){
                data.set(i, currentUser.getUsername() + "," + currentUser.getPassword());
            }
        }
        try {
            FileWriter writer = new FileWriter("data/userdata.csv");
            //making a template for the userdata
            writer.write( "username, password, watchedMovie1: watchedMovie2, savedMovie1: savedMovie2\n");
            for (String s : data) {
                writer.write(s + "\n");

            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
