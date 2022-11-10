import java.util.ArrayList;

public class StartMenu {

    public static User logIn() {

        String currentPassword;
        //Assigning the users input to currentUsername.
        String currentUsername = TextUI.getInput("Please enter a username: ");
        /*
           Calling the boolean method existingUsername(), that checks if the users input
           match an already existing username.
         */
        if(existingUsername(currentUsername)) {
            String input;
            //If the username already exists, it will ask for your matching password.
            currentPassword = TextUI.getInput("Please enter your password: ");
           if(correctPassword(currentPassword)) {
               //If the given password match the one we have stored in userdata.csv, you will be logged in.
               System.out.println("You've successfully logged in to Dataflix");
               return new User(currentUsername, currentPassword);
           } else {
               while (!correctPassword(currentPassword)) {
                   TextUI.displayMessage("-- Incorrect Password --");
                   do {
                       input = TextUI.getInput("Try again(T) or go back(B)");

                       if (input.equalsIgnoreCase("B")) {
                           logIn();
                       } else if (input.equalsIgnoreCase("T")) {
                           currentPassword = TextUI.getInput("Please enter your password: ");
                           //If the password doesn't match, it will let you know, and ask for your password again.

                           if (correctPassword(currentPassword)) {
                               System.out.println("You've successfully logged in to Dataflix");
                               return new User(currentUsername, currentPassword);
                           }
                       }
                   } while (!input.equalsIgnoreCase("T") && !input.equalsIgnoreCase("B"));
               }
           }
        }
        //If the users input for username doesn't match any usernames from the userdata.csv, it will give you following options.
        else if(!existingUsername(currentUsername)) {
            TextUI.displayMessage("Looks like we don't have any users with that username.");
            TextUI.displayMessage("What would you like to do?");
                String input;
                /*
                Giving the options to create a new user, or try to log in with another username.
                The following do-while-loop will only accept a 'T' or 'N' as response.
                 */
            do {
                    input = TextUI.getInput("Create new user(N) or Try again(T)");
                    //If the user choose to try again with another username, it will call the logIn() method again and start over.
                if (input.equalsIgnoreCase("T")) {
                    return StartMenu.logIn();

                    //If the user choose to make a new user, it will simply ask for a password.
                } else if (input.equalsIgnoreCase("N")) {
                    TextUI.displayMessage("Alright, let's create a new user.");
                    currentPassword = TextUI.getInput("Please enter a password: ");
                    System.out.println("You've successfully created an account and is being logged in to Dataflix");
                    User user = new User(currentUsername, currentPassword);
                    /*
                    //Once the user is created, it will add the users new username and password to the userdata.csv file
                      for it to be stored and accessible when logging in next time.
                     */
                    FileIO.addToFile(user);
                    return user;
                }
            }
            while(!input.equalsIgnoreCase("N") && !input.equalsIgnoreCase("T"));
        }
        return new User("-1", "-1");
    }
    /*
    The existingUsername() method, takes a String as parameter, and will then add the whole userdata.csv file
    to a new arraylist 'data'. Then the for-loop will compare all the usernames in the file with
    the username the method got as input.
    The .split method returns an array, and we are fetching index[0] as that is where the usernames are stored in the file.
     */
    private static boolean existingUsername(String username) {
        ArrayList<String> data = FileIO.readFile("data/userdata.csv");
        for(int i = 0; i < data.size(); i++) {
            if (data.get(i).split(",")[0].equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
    /*
    The correctPassword method does exactly the same as the existingUsername method,
    just with index[1] as that is where the passwords are stored.
     */
    private static boolean correctPassword(String password) {
        ArrayList<String> data = FileIO.readFile("data/userdata.csv");

        for(int i = 0; i < data.size(); i++) {
            if (data.get(i).split(",")[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
}
