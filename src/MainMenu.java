import java.util.ArrayList;


public class MainMenu {

    public static ArrayList<IMedia> searchName(String mediaName) {
        ArrayList<IMedia> resultList = new ArrayList<>();

        for (IMedia m: Application.movies) {
            if(m.getName().toLowerCase().contains(mediaName.toLowerCase())) {
                resultList.add(m);
            }
        }
        for (IMedia s : Application.series) {
            if(s.getName().toLowerCase().contains(mediaName.toLowerCase())) {
                resultList.add(s);
            }
        }
        return resultList;
    }


    public static ArrayList<IMedia> searchCategory(String category) {
        //Using foreach loop to search by category in our arraylist
        //First loop through movies by calling the application class
        ArrayList<IMedia> searchResults = new ArrayList<>();
        for (IMedia m : Application.movies) {
            for (String cat : m.getCategories()) {
                if (cat.equalsIgnoreCase(category)) {
                    searchResults.add(m);
                }
            }
        }
        //Using foreach loop to search by category in our arraylist
        //Then loop through series by calling the application class
        for (IMedia s : Application.series) {
            for (String cat : s.getCategories()) {
                if (cat.equalsIgnoreCase(category)) {
                    searchResults.add(s);
                }
            }
        }
        //Return a list of movies in the given category
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


    public static ArrayList<IMedia> getUsersWatchedMedia(User user) {
        return user.getWatchedMedia();
    }

    //Add to a
    public static ArrayList<IMedia> getUsersSavedMedia(User user) {
        return user.getSavedMedia();
    }
}
