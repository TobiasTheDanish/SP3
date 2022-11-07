public class StartMenu {

    private String currentUsername;
    private String currentPassword;

    public void signUp() {

    }
    public static User logIn() {
        return new User("Bob", "1234");
    }
    private boolean existingUsername(String username) {
        return false;
    }
    private boolean correctPassword(String password) {
        return false;
    }
}
