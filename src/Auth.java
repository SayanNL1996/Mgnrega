import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Auth {
    Scanner scanner = new Scanner(System.in);
    Dbconnection dbconnection = new Dbconnection();
    Bdo bdo = new Bdo();
    Gpm gpm = new Gpm();
    Member member = new Member();



    public void auth(int choice) throws SQLException {
        Connection conn = dbconnection.ConnectDb();
        Statement statement = conn.createStatement();
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();
        ResultSet res;

        if (choice == 1){
            res = statement.executeQuery("select * from bdo where email= '"+email+"' and password ='"+password+"'");
            if(res.next()){
                bdo.bdoTasks();
            }
            else {
                System.out.println("Wrong Credentials!");
                auth(choice);
            }

        }else if(choice == 2){
            res = statement.executeQuery("select * from gpm where email= '"+email+"' and password ='"+password+"'");
            if(res.next()){
                gpm.gpmTasks();
            }
            else {
                System.out.println("Wrong Credentials!");
                auth(choice);
            }
        }else {
            res = statement.executeQuery("select * from member where email= '"+email+"' and password ='"+password+"'");
            if(res.next()){
                member.memberTasks();
            }
            else {
                System.out.println("Wrong Credentials!");
                auth(choice);
            }
        }





    }
}
