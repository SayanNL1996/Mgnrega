import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Bdo {

    Scanner scanner = new Scanner(System.in);
    Dbconnection dbconnection = new Dbconnection();
    Connection conn = dbconnection.ConnectDb();
    Statement statement = conn.createStatement();

    public Bdo() throws SQLException {
    }


    public void bdoTasks() throws SQLException {
        System.out.println("Welcome to BDO Portal...");
        System.out.println("\nSelect the task!\n" +
                "1: GPM Management \n" +
                "2: Project Management \n" +
                "3: Request Management \n" +
                "4: show Gpm and Member Details \n");
        System.out.println("Enter Choice: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            gpmManagement();

        } else if (choice == 2) {

        } else if (choice == 3) {

        } else {
            System.out.println("Wrong Choice. Please Enter again!");
            bdoTasks();
        }

    }

    public void gpmManagement() throws SQLException {
        System.out.println("\nSelect the task!\n" +
                "1: Create GPM. \n" +
                "2: Update GPM. \n" +
                "3: Delete GPM. \n");
        System.out.println("Enter task number: ");
        int taskId = scanner.nextInt();
        if (taskId == 1) {
            createGpm();

        } else if (taskId == 2) {

        } else if (taskId == 3) {

        } else {
            System.out.println("Wrong Choice. Please Enter again!");
            gpmManagement();
        }
    }

    public void createGpm() throws SQLException {
        try {
            scanner.nextLine();
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            System.out.println("Enter email id:");
            String email = scanner.nextLine();
            System.out.println("Enter Password:");
            String password = scanner.nextLine();
            System.out.println("Enter Area:");
            String area = scanner.nextLine();
            System.out.println("Enter PinCode:");
            int pincode = scanner.nextInt();
            int bdo_id = 1;
            ResultSet res = statement.executeQuery("insert into gpm(name,email,password,area,pincode,bdo_id)" +
                    "values('" + name + "','" + email + "','" + password + "','" + area + "','" + pincode + "','" + bdo_id + "')");
            if (res.next()) {
                System.out.println("Gpm Successfully crreated!");
            } else {
                System.out.println("Error Occured!");
            }

        } catch (SQLException e) {
            System.out.println("Error is" + e.getMessage());
        } finally {
            gpmManagement();

        }


    }
}
