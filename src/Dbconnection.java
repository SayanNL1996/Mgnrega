import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    public static final String DB_NAME = "mydb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/home/nineleaps/IdeaProjects/Mgnrega/" + DB_NAME;
    public Connection conn;

    public Connection ConnectDb() {

        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return conn;

    }
}
