import java.util.ArrayList;

public class Application {

    private static User currentUser;
    public static ArrayList <IMedia> Movies;
    public static ArrayList <IMedia> Series;
   public Application() {

   }
    public static void run() {
        currentUser = StartMenu.logIn();
    }
    public void watch(IMedia media) {

    }
}
