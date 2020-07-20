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
        System.out.println("**************** Welcome to GPM Portal ***************");
        this.id = id;
        System.out.println("\nSelect the task!\n" +
                "1: Create Member \n" +
                "2: Assign Project \n" +
                "3: Show All Complaint Requests \n" +
                "4: Approve Complaint Requests \n" +
                "5: Show All Members \n" +
                "6: Show Ongoing Projects \n");
        System.out.println("Enter Choice: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            createMember();
        } else if (choice == 2) {
            assignProject();

        } else if (choice == 3) {
            showAllRequests();

        } else if (choice == 4) {
            handleRequests();

        } else if (choice == 5) {
            showAllMembers();

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
            System.out.println("Error is:" + e.getMessage());
        } finally {
            gpmTasks(id);

        }

    }

    public void showAllMembers() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select id,name,email,area,pincode,status from member where gpm_id='" + id + "'");
            while (res.next()) {
                System.out.println("1.Member Id: " + res.getInt(1));
                System.out.println("2.Name: " + res.getString(2));
                System.out.println("3.Email: " + res.getString(3));
                System.out.println("4.Area: " + res.getString(4));
                System.out.println("5.Pincode: " + res.getInt(5));
                System.out.println("6.Status: " + res.getString(6));
                System.out.println("--------------------------------");
                System.out.println("\n");
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
            System.out.println("$$$$$$$$$$$$ Available Members $$$$$$$$$$$$$ \n");
            while (res.next()) {
                System.out.println("1.Member Id: " + res.getInt(1));
                System.out.println("2.Name: " + res.getString(2));
                System.out.println("3.Email: " + res.getString(3));
                System.out.println("4.Area: " + res.getString(4));
                System.out.println("5.Pincode: " + res.getInt(5));
                System.out.println("6.Status: " + res.getString(6));
                System.out.println("--------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        }

    }

    public void handleRequests() throws SQLException {
        try {
            unApprovedRequests();
            Statement statement = conn.createStatement();
            System.out.println("Enter Complaint Id:");
            int cid = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter 'A' to approve or 'R' to reject:");
            String req = scanner.nextLine();
            if (req.equalsIgnoreCase("A")) {

                statement.execute("update complaints set status_gpm = 'approved' where id='" + cid + "'");
                System.out.println("Approved Successfuly!");
                statement.close();
            } else if (req.equalsIgnoreCase("R")) {
                statement.execute("update complaints set status_gpm = 'rejected' where id = '" + cid + "'");
                System.out.println("Rejected Successfully! ");
                statement.close();
            } else {
                System.out.println("Wrong Choice. Please Enter valid choice:");
                handleRequests();
            }

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            gpmTasks(id);
        }
    }

    public void unApprovedRequests() {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select * from complaints where gpm_id='" + id + "' and status_gpm='false'");
            while (res.next()) {
                System.out.println("1.Complaint Id: " + res.getInt(1));
                System.out.println("2.Member Id: " + res.getInt(2));
                System.out.println("3.Complaint: " + res.getString(3));
                System.out.println("------------------------------");
                System.out.println("\n");
            }
            statement.close();


        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        }
    }

    public void showAllRequests() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select * from complaints where gpm_id='" + id + "'");
            while (res.next()) {
                System.out.println("1.Complaint Id:" + res.getInt(1));
                System.out.println("2.Member Id:" + res.getInt(2));
                System.out.println("3.Complaint:" + res.getString(3));
                System.out.println("4.Status Gpm:" + res.getString(5));
                System.out.println("5.Status Bdo:" + res.getString(6));
                System.out.println("---------------------------------");
                System.out.println("\n");
            }

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            gpmTasks(id);
        }

    }

    public void assignProject() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            showUnAssignedProjects();
            System.out.println("Enter Project Id: ");
            int pid = scanner.nextInt();
            scanner.nextLine();
            showUnassignedMembers();
            System.out.println("Enter Member Id: ");
            int mid = scanner.nextInt();
            statement.execute("insert into project_member(p_id,member_id,gpm_id)" +
                    "values('" + pid + "','" + mid + "','" + id + "')");
            statement.close();
            System.out.println("Assigned Successfully");
            statement.execute("update member set status='assigned' where id='" + mid + "'");
            statement.close();
            statement.execute("update project set is_alloted='true' where id='" + pid + "'");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error is: " + e.getMessage());
        } finally {
            gpmTasks(id);
        }
    }

    public void showUnAssignedProjects() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select * from project where is_alloted='false'");
            System.out.println("$$$$$$$$$$$$ Available Projects $$$$$$$$$$$$ \n");
            while (res.next()) {
                System.out.println("1.Project Id: " + res.getInt(1));
                System.out.println("2.Project Name :" + res.getString(2));
                System.out.println("3.Area: " + res.getString(3));
                System.out.println("4.Pincode: " + res.getInt(4));
                System.out.println("5.Total Members: " + res.getInt(5));
                System.out.println("6.Cost Estimated: " + res.getInt(6));
                System.out.println("7.Start Date: " + res.getString(7));
                System.out.println("8.End Date: " + res.getString(8));
                System.out.println("---------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        }
    }


}
