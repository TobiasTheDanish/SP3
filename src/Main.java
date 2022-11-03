public class Main
{
    public static void main(String[] args)
    {
        User user1 = new User("Bob", "kodeord1234");
        FileIO fileIO = new FileIO();
        fileIO.writeToFile(user1);
    }
}