import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Auth {
    Scanner scanner = new Scanner(System.in);
    Dbconnection dbconnection = new Dbconnection();
    Gpm gpm = new Gpm();
    Member member = new Member();
    int id, gpmId;


    public void auth(int choice) throws SQLException {
        Connection conn = dbconnection.ConnectDb();
        Statement statement = conn.createStatement();
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        ResultSet res;
        Bdo bdo = new Bdo();


        if (choice == 1) {
            res = statement.executeQuery("select * from bdo where email= '" + email + "' and password ='" + password + "'");
            if (res.next()) {
                conn.close();
                bdo.bdoTasks();
            } else {
                System.out.println("Wrong Credentials!");
                auth(choice);

            }

        } else if (choice == 2) {
            res = statement.executeQuery("select id from gpm where email= '" + email + "' and password ='" + password + "'");
            id = res.getInt("id");
            if (res.next()) {
                conn.close();
                gpm.gpmTasks(id);
            } else {
                System.out.println("Wrong Credentials!");
                auth(choice);
            }
        } else {
            res = statement.executeQuery("select id,gpm_id from member where email= '" + email + "' and password ='" + password + "'");
            id = res.getInt("id");
            gpmId = res.getInt("gpm_id");
            if (res.next()) {
                conn.close();
                member.memberTasks(id, gpmId);
            } else {
                System.out.println("Wrong Credentials!");
                auth(choice);
            }
        }


    }
}
