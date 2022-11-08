import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
private User user;

    public MainMenu(User user)
    {
        this.user = user;
    }

    public static IMedia Search() throws IOException {
        String searchWord;
        String str;

        File input = new File("data/moviedata.csv");
        FileReader reader = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a keyword you would like to search for: ");
        searchWord = scanner.nextLine();

        try {
            reader = new FileReader(input);
            BufferedReader br = new BufferedReader(reader);

            while ((str = br.readLine()) != null) {
                if (str.contains(searchWord)) {
                    TextUI.displayMessage(str);
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            reader.close();
        }
        return null;
    }


    public ArrayList<IMedia> searchCategory(String category)
    {
        return new ArrayList<>();
        /*search for a movie/series category with a string
        */

    }


    public ArrayList<IMedia> getUsersWatchedMedia(User user)
    {
        return new ArrayList<>();
        //get users watched media from User user "".
    }

    //Add to a
    public ArrayList<IMedia> getUsersSavedMedia(User user)
    {
        return new ArrayList<>();
        //get users saved media from User user
    }



}
