import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    private static User currentUser;
    public static ArrayList<IMedia> movies;
    public static ArrayList<IMedia> series;

    public static void run() {
        //Gets data from the moviedata file
        movies = getMediaData("data/moviedata.csv", "movie");
        //Gets data from the seriesdata file
        series = getMediaData("data/seriesdata.csv", "series");

        //Welcome message
        TextUI.displayMessage("------------------------------");
        TextUI.displayMessage("     Welcome to Dataflix.");
        TextUI.displayMessage("------------------------------");
        //Get the logged-in user via StartMenu. This returns a User object, with data from the userdata file.
        currentUser = StartMenu.logIn();

        TextUI.displayMessage("------------------------------");
        TextUI.displayMessage("Welcome " + currentUser.getUsername() + ".");
        TextUI.getInput("Press enter to start browsing.");
        // uses the MainMenu class to display options. This is where the primary program loop takes place
        displayUserOptions();
        //Writes the currently logged-in users data to the userdata file, before "logging out".
        FileIO.writeUserDataToFile(currentUser);

        TextUI.displayMessage("---------------------------------------------------------");
        TextUI.displayMessage("     Thank you for using Dataflix. Logging out...");
        TextUI.displayMessage("---------------------------------------------------------");
    }
    public static void watch(IMedia media) {
        //Play the passed in IMedia type
        media.play();
        //Add the passed in IMedia type to the users watched list
        currentUser.addToWatchedMedia(media);
        //Prompt the user for input, to make sure they see the message.
        TextUI.getInput("Press enter to continue.");
    }

    private static void displayUserOptions() {
        String input;
        //do makes sure that the loop always runs at least once.
        do {
            //print 100 empty lines, so it looks like the console has been cleared.
            TextUI.clearConsole();
            //Display options to the user, and prompt them to make a selection. Then save the selection in the input variable
            TextUI.displayMessage("---------------------------------------------------------");
            TextUI.displayMessage("What action do you wish to make?");
            TextUI.displayMessage("1)   Search for media\n" +
                                          "2)   View your saved medias.\n" +
                                          "3)   View your watched medias.\n");
            input = TextUI.getInput("Enter your selection, or press 'Q' to log out:");
            //Switch statement to perform different logic based on input
            switch (input) {
                case "q":
                case "Q":
                    //If input is "Q" or "q" break out of the switch statement
                    break;
                //This is for when the user wants to search for a media
                case "1": {
                    displaySearchOptions();
                    break;
                }
                case "2":
                    //Receives a list from MainMenus getUsersSavedMedia function, which we store in a new ArrayList.
                    ArrayList<IMedia> savedMedia = MainMenu.getUsersSavedMedia(currentUser);
                    //Prompt the user to let them know how many hits their search returned.
                    TextUI.getInput("There is " + savedMedia.size() + " media in your saved media list." + "\nPress enter to view the list.");
                    /* Displays the list 1 by 1 with their given index in front.
                     * Ex.: "1)  Game Of Thrones from ..."
                     */
                    for (int i = 0; i < savedMedia.size(); i++) {
                        IMedia media = savedMedia.get(i);
                        TextUI.displayMessage((i+1) + ")    " + media.getName() + " from " + media.getPublishingYear() + " has a rating of " + media.getRating() + "/10.");
                    }
                    //Gets an IMedia from the selectMedia function.
                    IMedia selected = selectMedia(savedMedia);
                    /* Call onMediaSelected function with the IMedia from selectMedia,
                     * and a call to the currentUsers listContainsMedia, that returns a boolean based on
                     * if the selected media is in the list.
                     */
                    onMediaSelected(selected, currentUser.listContainsMedia(currentUser.getSavedMedia(), selected));
                    break;

                case "3":
                    //Receives a list from MainMenus getUsersWatchedMedia function, which we store in a new ArrayList.
                    ArrayList<IMedia> watchedMedia = MainMenu.getUsersWatchedMedia(currentUser);
                    //Prompt the user to let them know how many hits their search returned.
                    TextUI.getInput("There is " + watchedMedia.size() + " media in your watched media list." + "\nPress enter to view the list.");
                    /* Displays the list 1 by 1 with their given index in front.
                     * Ex.: "1)  Game Of Thrones from ..."
                     */
                    for (int i = 0; i < watchedMedia.size(); i++) {
                        IMedia media = watchedMedia.get(i);
                        TextUI.displayMessage((i+1) + ")    " + media.getName() + " from " + media.getPublishingYear() + " has a rating of " + media.getRating() + "/10.");
                    }
                    //Gets an IMedia from the selectMedia function.
                    selected = selectMedia(watchedMedia);
                    /* Call onMediaSelected function with the IMedia from selectMedia,
                     * and a call to the currentUsers listContainsMedia, that returns a boolean based on
                     * if the selected media is in the list.
                     */
                    onMediaSelected(selected, currentUser.listContainsMedia(currentUser.getSavedMedia(), selected));
                    break;

                default:
                    //If none of the above is reached, prompt the user to let them know.
                    TextUI.getInput("That was not a valid action. Press enter to try again.");
                    TextUI.clearConsole();
                    break;
            }
        }
        //Display users options again, except when input is equal to "q" or "Q".
        while (!input.equalsIgnoreCase("q"));
    }

    private static void displaySearchOptions() {
        //Create an Arraylist that will be populated in the do-while loop.
        ArrayList<IMedia> media;

        String input;
        do {
            //print 100 empty lines, so it looks like the console has been cleared.
            TextUI.clearConsole();

            //Display options to the user, and prompt them to make a selection. Then save the selection in the input variable
            TextUI.displayMessage("---------------------------------------------------------");
            TextUI.displayMessage("How do you wish to search?");
            TextUI.displayMessage("1)   Search by name.\n" +
                                          "2)   Search by category.\n" +
                                          "3)   Search by rating.\n");
            input = TextUI.getInput("Enter your selection, or press 'Q' to return to main menu:");

            switch (input) {
                case "q":
                case "Q":
                    //If input is "Q" or "q" break out of the switch statement
                    break;

                case "1": {
                    TextUI.clearConsole();
                    do {
                        //Prompt the user with their actions
                        TextUI.displayMessage("Press 'Q' to return.");
                        String mediaName = TextUI.getInput("What is the name of the media you want to watch?");

                        //Exit this loop if the user has entered "q" or "Q".
                        if (mediaName.equalsIgnoreCase("q")) {
                            media = null; //Setting media to null, to be able to break out of the switch statement.
                            break;
                        }

                        //Receives a list from MainMenus searchName function, which we store in the Arraylist created above.
                        media = MainMenu.searchName(mediaName);

                        //If there is no elements in the list, we prompt the user to try again.
                        if (media.size() == 0) {
                            TextUI.clearConsole();
                            TextUI.displayMessage("That name was not found, try again.");
                        }
                    } while (media.size() == 0);

                    // if media is null, then the user wants to exit to the main menu, therefore we break.
                    if (media == null) break;

                    //Prompt the user to let them know how many hits their search returned.
                    TextUI.displayMessage("Your search returned " + media.size() + " media.");
                    TextUI.getInput("Press enter to view the list.");

                    /* Displays the list 1 by 1 with their given index in front.
                     * Ex.: "1)  Game Of Thrones from ..."
                     */
                    for (int i = 0; i < media.size(); i++) {
                        IMedia m = media.get(i);
                        TextUI.displayMessage(
                                (i + 1) + ")    " + m.getName() + " from " + m.getPublishingYear() + " has a rating of " + m.getRating() + "/10.");
                    }

                    //Gets an IMedia from the selectMedia function.
                    IMedia selected = selectMedia(media);

                    /*
                     * Call onMediaSelected function with the IMedia from selectMedia,
                     * and a call to the currentUsers listContainsMedia, that returns a boolean based on
                     * if the selected media is in the list.
                     */
                    onMediaSelected(selected, currentUser.listContainsMedia(currentUser.getSavedMedia(), selected));
                    break;
                }

                case "2": {
                    TextUI.clearConsole();
                    TextUI.displayMessage("Categories: ");
                    displayListOfCategories();

                    String categoryName = null;
                    do {
                        //Prompt the user to enter the category they want to search by.
                        String categoryNum = TextUI.getInput(
                                "Enter the category you would like to filter by:");

                        try {
                            int index = Integer.parseInt(categoryNum) - 1;
                            categoryName = getCategoryFromList(index);
                        } catch (Exception e) {
                            TextUI.clearConsole();
                            TextUI.displayMessage("That was not a valid input. Please try again.");
                        }
                    } while (categoryName == null);

                    TextUI.clearConsole();
                    //Receives a list from MainMenus searchCategory function, which we store in the Arraylist created above.
                    media = MainMenu.searchCategory(categoryName);

                    //Prompt the user to let them know how many hits their search returned.
                    TextUI.getInput("There is " + media.size() + " media in " + categoryName + ".\nPress enter to view the list.");
                    /* Displays the list 1 by 1 with their given index in front.
                     * Ex.: "1)  Game Of Thrones from ..."
                     */
                    for (int i = 0; i < media.size(); i++) {
                        IMedia m = media.get(i);
                        TextUI.displayMessage(
                                (i + 1) + ")    " + m.getName() + " from " + m.getPublishingYear() + " has a rating of " + m.getRating() + "/10.");
                    }

                    //Gets an IMedia from the selectMedia function.
                    IMedia selected = selectMedia(media);

                    /* Call onMediaSelected function with the IMedia from selectMedia,
                     * and a call to the currentUsers listContainsMedia, that returns a boolean based on
                     * if the selected media is in the list.
                     */
                    onMediaSelected(selected, currentUser.listContainsMedia(currentUser.getSavedMedia(), selected));
                    break;
                }

                case "3": {
                    do {
                        TextUI.clearConsole();
                        //Prompt the user with their options
                        TextUI.displayMessage("Press 'Q' to return.");
                        String ratingStr = TextUI.getInput("What is the lowest rating you want? (1-10)");

                        //Exit this loop if the user has entered "q" or "Q".
                        if (ratingStr.equalsIgnoreCase("q")) {
                            media = null; //Setting media to null, to be able to break out of the switch statement.
                            break;
                        }

                        try {
                            //Convert from string to float
                            float rating = Float.parseFloat(ratingStr);
                            if (rating > 10) {
                                throw new Exception();
                            }
                            //Get a list of media based on the rating the user entered
                            media = MainMenu.searchRating(rating);
                            if (media.size() == 0) {
                                TextUI.displayMessage("Your search returned 0 media");
                                TextUI.getInput("Press 'ENTER' to try again.");

                                TextUI.clearConsole();
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            //If any exception is thrown this will be displayed.
                            TextUI.clearConsole();
                            TextUI.getInput("That was not valid input. Press 'ENTER' to try again.");
                        }

                    } while (true);

                    // if media is null, then the user wants to exit to the main menu, therefore we break.
                    if (media == null) break;

                    TextUI.clearConsole();
                    //Prompt the user to let them know how many hits their search returned.
                    TextUI.displayMessage("Your search returned " + media.size() + " media.");
                    TextUI.getInput("Press 'ENTER' to view the list.");
                    /* Displays the list 1 by 1 with their given index in front.
                     * Ex.: "1)  Game Of Thrones from ..."
                     */
                    for (int i = 0; i < media.size(); i++) {
                        IMedia m = media.get(i);
                        TextUI.displayMessage(
                                (i + 1) + ")    " + m.getName() + " from " + m.getPublishingYear() + " has a rating of " + m.getRating() + "/10.");
                    }

                    //Gets an IMedia from the selectMedia function.
                    IMedia selected = selectMedia(media);
                    /*
                     * Call onMediaSelected function with the IMedia from selectMedia,
                     * and a call to the currentUsers listContainsMedia, that returns a boolean based on
                     * if the selected media is in the list.
                     */
                    onMediaSelected(selected, currentUser.listContainsMedia(currentUser.getSavedMedia(), selected));
                    break;
                }

                default:
                    //If none of the above is reached, prompt the user to let them know.
                    TextUI.getInput("That was not a valid action. Press enter to try again.");
                    TextUI.clearConsole();
                    break;
            }
        } while (!input.equalsIgnoreCase("q"));
    }

    private static IMedia selectMedia(ArrayList<IMedia> medias) {
        //If media is null or empty return out of the function
        if (medias == null || medias.size()  == 0) {
            return null;
        }
        // we only exit this loop if we reach a return statement, else it will continue running.
        while (true) {
            //Prompt the user for a selection. This function is called after a list of options has been displayed.
            String input = TextUI.getInput("Enter your selection here, or press 'Q' to return:");
            switch (input) {
                case "Q":
                case "q":
                    //If input equals "Q" or "q", return from the function. (this will return to the main menu)
                    return null;

                default:
                    //this code is reached if input was not q
                    try {
                        //try to parse the input to an int. We subtract 1 because we display the first element as 1.
                        int index = (Integer.parseInt(input)) - 1;
                        //return the media at the selected index
                        return medias.get(index);

                    } catch(Exception e) {
                        //Any exception thrown will cause this code to be run.
                        TextUI.displayMessage("That was not valid input. Try again.");
                    }
                    break;
            }
        }
    }

    private static void onMediaSelected(IMedia selectedMedia, boolean savedMedia) {
        if (selectedMedia == null) {
            return;
        }
        String input;
        //Print 100 empty lines, so it looks like the console has been cleared.
        TextUI.clearConsole();
        //Display the selected media to the user.
        TextUI.displayMessage("You selected " + selectedMedia.getName() + " from " + selectedMedia.getPublishingYear() + ".");
        do {
            //Display the users options
            TextUI.displayMessage("What do you wish to do?");
            TextUI.displayMessage("1)   Watch " + selectedMedia.getName());
            if (!savedMedia) {
                //If the media is NOT saved in the currentUsers saved media, display this option.
                TextUI.displayMessage("2)   Save to your saved media list.");
            } else {
                //If the media IS saved in the currentUsers saved media, display this option.
                TextUI.displayMessage("2)   Remove from your saved media list.");
            }
            //Prompt the user to make a selection or exit to the main menu
            input = TextUI.getInput("Enter your selection, or press 'Q' to return:");

            switch (input) {
                case "q":
                case "Q":
                    //If input is "q" or "Q" return from the function, to the main menu.
                    return;

                case "1":
                    TextUI.clearConsole();
                    //Watch the selectedMedia
                    watch(selectedMedia);
                    TextUI.clearConsole();
                    break;

                case "2":
                    //Either save the media or remove the media from users savedMedia, based on savedMedias value
                    if (!savedMedia) {
                        currentUser.addToSavedMedia(selectedMedia);
                    } else {
                        currentUser.removeFromSavedMedia(selectedMedia);
                    }
                    //Set saved media so the options displays correctly on next iteration.
                    savedMedia = currentUser.listContainsMedia(currentUser.getSavedMedia(), selectedMedia);
                    break;

                default:
                    //Invalid actions is handled here.
                    TextUI.getInput("That was not a valid action. Press 'ENTER' to try again.");
                    TextUI.clearConsole();
                    break;
            }
            //Print 100 empty lines to make it look like the console has been cleared.
            TextUI.clearConsole();
        } while(!input.equalsIgnoreCase("q"));
        TextUI.clearConsole();
    }

    private static ArrayList<IMedia> getMediaData(String filePath, String type) {
        //The type parameter should tell us whether it's a movie or a series (An enum would be better).
        //Read the file at located at filePath (FileIO.readFile(filePath)) and save it in an Arraylist<String> called data
        //Split each string in data, so that we can create a IMedia type object based on the type (either a movie or series)
        //then add that IMedia to an Arraylist<IMedia>.
        //When we have looped over all the strings in data, return the Arraylist<IMedia>
        ArrayList<IMedia> medias = new ArrayList<>();
        ArrayList<String> data = FileIO.readFile(filePath);

        switch (type) {
            case "movie":
                //Loop over all the date from the file.
                for (String s : data) {
                    //Split each line of data into a String array.
                    String[] movieData = s.split(";");
                    //The first element in the array is the movies name
                    String name = movieData[0].trim();
                    //The second element in the array is the movies publishing year
                    String publishingYear = movieData[1].trim();
                    //The third element in the array is the movies categories
                    List<String> list = Arrays.asList(movieData[2].split(","));
                    list.replaceAll(String::trim);
                    ArrayList<String> categories = new ArrayList<>(list);
                    //The fourth element in the array is the movies rating
                    float rating = Float.parseFloat(movieData[3].replace(',', '.'));
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
                    String name = movieData[0].trim();
                    //The second element in the array is the series' publishing year
                    String publishingYear = movieData[1].trim();
                    //The third element in the array is the series' categories
                    List<String> list = Arrays.asList(movieData[2].split(","));
                    list.replaceAll(String::trim);
                    ArrayList<String> categories = new ArrayList<>(list);
                    //The fourth element in the array is the series' rating
                    float rating = Float.parseFloat(movieData[3].replace(',', '.'));
                    //The fifth element in the array is the seasons and episodes
                    String[] seasonsAndEpisodes = movieData[4].split(",");
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

    private static void displayListOfCategories() {
        //An array of all the categories represented in the program
        String[] categories = "Talk-show, Documentary, Crime, Drama, Action, Adventure, Drama, Comedy, Fantasy, Animation, Horror, Sci-fi, War, Thriller, Mystery, Biography, History, Family, Western, Romance, Sport".split(",");
        //loop over each category in the array
        for (int i = 0; i < categories.length; i++) {
            //display each element, formatted like this: "1)    'category'"
            TextUI.displayMessage((i+1) + ")    " + categories[i]);
        }
    }

    private static String getCategoryFromList(int index) {
        String[] categories = "Talk-show, Documentary, Crime, Drama, Action, Adventure, Drama, Comedy, Fantasy, Animation, Horror, Sci-fi, War, Thriller, Mystery, Biography, History, Family, Western, Romance, Sport".split(",");

        if (index < categories.length) {
            return categories[index].trim();
        }
        return null;
    }

    //Making a function where we are able to search by name in our arraylist
    //Does not make any difference whether it's a movie or a series
    public static IMedia getMediaByName(String mediaName) {
        //Using a foreach loop to be able to search in our arraylist
        // will return the media if it's name is in the list
        for (IMedia m: Application.movies) {
            if(m.getName().equalsIgnoreCase(mediaName)) {
                return m;
            }
        }
        //Using a foreach loop to be able to search in our arraylist
        // will return the media if it's name is in the list
        for (IMedia s : Application.series) {
            if(s.getName().equalsIgnoreCase(mediaName)) {
                return s;
            }
        }
        return null;
    }
}
