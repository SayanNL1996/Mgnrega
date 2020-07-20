import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Member {

    Scanner scanner = new Scanner(System.in);
    Dbconnection dbconnection = new Dbconnection();
    Connection conn = dbconnection.ConnectDb();
    int id, gpmId;

    public void memberTasks(int id, int gpmId) {
        System.out.println("*************** Welcome to Member Portal ***************");
        this.id = id;
        this.gpmId = gpmId;
        System.out.println("\nSelect the task!\n" +
                "1: View Details \n" +
                "2: File Complaints \n" +
                "3: Show Complaints \n");
        System.out.println("Enter Choice: ");
        int choice = scanner.nextInt();
        if (choice == 1) {

        } else if (choice == 2) {
            fileComplaint();
        } else if (choice == 3) {
            showComplaints();
        } else {
            System.out.println("Wrong Choice. Please Enter again!");
            memberTasks(id, gpmId);
        }
    }

    public void fileComplaint() {
        try {
            Statement statement = conn.createStatement();
            scanner.nextLine();
            System.out.println("Write Complaint:");
            String complaint = scanner.nextLine();
            statement.execute("insert into complaints(member_id,complaint,gpm_id)" +
                    "values('" + id + "','" + complaint + "','" + gpmId + "')");
            System.out.println("Complaint filed successfully!");
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            memberTasks(id, gpmId);
        }
    }

    public void showComplaints() {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select * from complaints where member_id='" + id + "'");
            while (res.next()) {
                System.out.println("1.Complaint Id:" + res.getInt(1));
                System.out.println("2.Complaint:" + res.getString(3));
                System.out.println("4.Status Gpm:" + res.getBoolean(5));
                System.out.println("5.Status Bdo:" + res.getBoolean(6));
                System.out.println("---------------------------------");
                System.out.println("\n");
            }

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            memberTasks(id, gpmId);
        }
    }
}
