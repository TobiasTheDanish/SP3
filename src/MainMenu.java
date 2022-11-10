import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {


    public static IMedia Search(String mediaName) {
        for (IMedia m : Application.movies) {
            if (m.getName().equalsIgnoreCase(mediaName.trim())) {
                return m;
            }

        }
        for (IMedia s : Application.series) {
            if (s.getName().equalsIgnoreCase(mediaName.trim())) {
                return s;
            }
        }
        return null;
    }


    // --  A search function that will let you search for keywords --
   /*
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

      */

    public static ArrayList<IMedia> searchCategory(String category)
    {
        ArrayList<IMedia> searchResults = new ArrayList<>();
        for (IMedia m : Application.movies)
        {
            for (String cat : m.getCategories())
            {
                if (cat.equalsIgnoreCase(category))
                {
                    searchResults.add(m);

                }
            }
        }

        for (IMedia s : Application.series)
        {
            for (String cat : s.getCategories())
            {
                if (cat.equalsIgnoreCase(category))
                {
                    searchResults.add(s);
                }
            }
        }
        return searchResults;
    }


    public static ArrayList<IMedia> searchRating(float rating){
        ArrayList<IMedia> searchResults = new ArrayList<>();
        for (IMedia m : Application.movies) {
            if (m.getRating() >= rating){
                searchResults.add(m);
            }
        }

        for (IMedia s : Application.series) {
            if (s.getRating() >= rating){
                searchResults.add(s);
            }
        }

        return searchResults;
    }


    public static ArrayList<IMedia> getUsersWatchedMedia(User user)
    {
        return user.getWatchedMedia();

    }

    //Add to a
    public static ArrayList<IMedia> getUsersSavedMedia(User user)
    {
        return user.getSavedMedia();

    }



}
