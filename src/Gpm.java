import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Gpm {

    Scanner scanner = new Scanner(System.in);
    Dbconnection dbconnection = new Dbconnection();
    Connection conn = dbconnection.ConnectDb();
    int id;

    public void gpmTasks(int id) throws SQLException {
        System.out.println("Welcome to GPM Portal...");
        this.id = id;
        System.out.println("\nSelect the task!\n" +
                "1: Create Member \n" +
                "2: Assign Project \n" +
                "3: Approve Request \n" +
                "4: Show All Members \n" +
                "5: Show Unassigned Members \n");
        System.out.println("Enter Choice: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            createMember();
        } else if (choice == 2) {


        } else if (choice == 3) {

        } else if (choice == 4) {
            showAllMembers();

        } else if (choice == 5) {
            showUnassignedMembers();

        } else {
            System.out.println("Wrong Choice. Please Enter again!");
            gpmTasks(id);
        }

    }

    public void createMember() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            scanner.nextLine();
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            System.out.println("Enter area:");
            String area = scanner.nextLine();
            System.out.println("Enter pincode:");
            int pincode = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter age:");
            int age = scanner.nextInt();
            scanner.nextLine();
            statement.execute("insert into member(name,email,password,area,pincode,age,gpm_id)" +
                    "values('" + name + "','" + email + "','" + password + "','" + area + "','" + pincode + "','" + age + "','" + id + "')");
            System.out.println("Member Successfully Created!");
            statement.close();


        } catch (SQLException e) {
            System.out.println("Error is" + e.getMessage());
        } finally {
            gpmTasks(id);

        }

    }

    public void showAllMembers() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select id,name,email,area,pincode,status from member where gpm_id='" + id + "'");
            while (res.next()) {
                System.out.println(res.getString("id") + " " +
                        res.getString("name") + " " +
                        res.getString("email") + " " +
                        res.getString("area") + " " +
                        res.getString("pincode") + " " +
                        res.getString("status"));
            }


        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            gpmTasks(id);
        }

    }

    public void showUnassignedMembers() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select id,name,email,area,pincode,status from member where gpm_id='" + id + "' and status = 'not assigned'");
            while (res.next()) {
                System.out.println(res.getString("id") + " " +
                        res.getString("name") + " " +
                        res.getString("email") + " " +
                        res.getString("area") + " " +
                        res.getString("pincode") + " " +
                        res.getString("status"));
            }

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            gpmTasks(id);
        }

    }


}
