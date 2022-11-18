import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabase {

    static final String DB_URL = "jdbc:mysql://localhost/SP3+";
    static final String USER = "username";
    static final String PASS = "password";


    // Open a connection
    Connection conn;

    {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
