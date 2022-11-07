import java.util.ArrayList;

public class StartMenu {

    public static User logIn() {

        String currentPassword;
        String currentUsername = TextUI.getInput("Please enter a username: ");
        if(existingUsername(currentUsername)){
            TextUI.displayMessage("Looks like the username already exists.");
            currentPassword = TextUI.getInput("Please enter your password: ");
           if(correctPassword(currentPassword)){
               System.out.println("You've successfully logged in to Dataflix");


           } else {

               while(!correctPassword(currentPassword)){
                   TextUI.displayMessage("-- Incorrect Password --");
                   currentPassword = TextUI.getInput("Please enter your password: ");

               }
               System.out.println("You've successfully logged in to Dataflix");
                return new User(currentUsername, currentPassword);

           }

        }
        else if(!existingUsername(currentUsername)){
            TextUI.displayMessage("Looks like we don't have any users with that username.");
            TextUI.displayMessage("What would you like to do?");
                String input;
            do {
                    input = TextUI.getInput("Create new user(N) or Try again(T)");
                if (input.equalsIgnoreCase("T")) {

                    return StartMenu.logIn();

                } else if (input.equalsIgnoreCase("N")) {

                    TextUI.displayMessage("Alright, let's create a new user.");
                    currentPassword = TextUI.getInput("Please enter a password: ");
                    System.out.println("You've successfully created an account and is being logged in to Dataflix");
                    User user = new User(currentUsername, currentPassword);
                    FileIO.addToFile(user);
                    return user;
                }
            }
            while(!input.equalsIgnoreCase("N") && !input.equalsIgnoreCase("T"));
        }
        return new User("-1", "-1");
    }


    private static boolean existingUsername(String username) {
        ArrayList<String> data = FileIO.readFile("data/userdata.csv");
        for(int i = 0; i < data.size(); i++) {
            if (data.get(i).split(",")[0].equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
    private static boolean correctPassword(String password) {

        ArrayList<String> data = FileIO.readFile("data/userdata.csv");
        for(int i = 0; i < data.size(); i++) {
            if (data.get(i).split(",")[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
} //Class end
