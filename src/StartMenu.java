import java.util.ArrayList;

public class StartMenu {
    
    private static String currentUsername;
    private static String currentPassword;

    public void signUp() {

    }
    public static User logIn() {

        TextUI.displayMessage("Welcome to Dataflix.");
        currentUsername = TextUI.getInput("Please enter username: ");


        if(existingUsername(currentUsername)){
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
            String input = TextUI.getInput("Create new user(N) or Try again(T)");
            if(input.equalsIgnoreCase("T")){

                //MAKE THE USER TRY TO ENTER ANOTHER USERNAME

            } else if (input.equalsIgnoreCase("N")) {

                TextUI.displayMessage("Alright, let's create a new user.");
               currentPassword = TextUI.getInput("Please enter a password");
                return new User(currentUsername, currentPassword);


            } else {
                System.out.println("Something went wrong#2");
            }
        }
        return new User("1", "11");
    }


    private static boolean existingUsername(String username) {
        ArrayList<String> data = FileIO.readFile("data/userdata.csv");
        for(int i = 0; i < data.size(); i++) {
            if (data.get(i).split(",")[0].equals(username)) {
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
