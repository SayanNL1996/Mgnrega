import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    Scanner scanner = new Scanner(System.in);
    Auth auth = new Auth();


    public void login() {
        System.out.println("Welcome To MGNREGA....");
        System.out.println("Enter Choice: \n" +
                "1: BDO \n" +
                "2: GPM \n" +
                "3: Member");
        try{
            int choice = scanner.nextInt();
            if (choice == 1) {
                auth.auth(choice);

            }else if(choice == 2) {
                String email = scanner.nextLine();
                String password = scanner.nextLine();
            }else if(choice == 3) {
                String email = scanner.nextLine();
                String password = scanner.nextLine();
            }else {
                System.out.println("Wrong input. Please enter again!");
                login();
            }
        }catch (SQLException e){
            System.out.println("Error Occured"+e);
        }


    }

    public static void main(String[] args) {
        Dbconnection obj = new Dbconnection();
        Connection conn = obj.ConnectDb();
        System.out.println("conn is:" + conn);
        Login log = new Login();
        log.login();


    }
}
