import java.security.PublicKey;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bdo {

    Scanner scanner = new Scanner(System.in);
    Dbconnection dbconnection = new Dbconnection();
    Connection conn = dbconnection.ConnectDb();


    public void bdoTasks() throws SQLException {
        System.out.println("**************** Welcome to BDO Portal ***************");
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
            projManagement();

        } else if (choice == 3) {
            requestManagement();

        } else {
            System.out.println("Wrong Choice. Please Enter again!");
            bdoTasks();
        }

    }

    public void gpmManagement() throws SQLException {
        System.out.println("\nSelect the task!\n" +
                "1: Create GPM. \n" +
                "2: Update GPM. \n" +
                "3: Delete GPM. \n" +
                "4: Show all GPM.\n");
        System.out.println("Enter task number: ");
        int taskId = scanner.nextInt();
        if (taskId == 1) {
            createGpm();

        } else if (taskId == 2) {

        } else if (taskId == 3) {
            deleteGpm();

        } else if (taskId == 4) {
            showAllGpm();

        } else {
            System.out.println("Wrong Choice. Please Enter again!");
            gpmManagement();
        }
    }

    // CREATE A GPM
    public void createGpm() throws SQLException {
        try {
            Statement statement = conn.createStatement();
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

            statement.execute("insert into gpm(name,email,password,area,pincode,bdo_id)" +
                    "values('" + name + "','" + email + "','" + password + "','" + area + "','" + pincode + "','" + bdo_id + "')");
            System.out.println("Gpm Successfully created!");
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error is" + e.getMessage());
        } finally {
            gpmManagement();

        }

    }

    public void deleteGpm() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            System.out.println("Enter Gpm Id: ");
            int gpmId = scanner.nextInt();
            statement.execute("delete from gpm where id='" + gpmId + "'");
            System.out.println("Gpm deleted successfully!");

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            gpmManagement();
        }
    }

    public void showAllGpm() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select id,name,email,area,pincode from gpm");
            while (res.next()) {
                System.out.println("1.Gpm Id: " + res.getInt(1));
                System.out.println("2.Name: " + res.getString(2));
                System.out.println("3.Email: " + res.getString(3));
                System.out.println("4.Area: " + res.getString(4));
                System.out.println("5.Pincode: " + res.getInt(5));
                System.out.println("--------------------------------");
                System.out.println("\n");
            }
//            statement.close();
        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            gpmManagement();
        }
    }

    // PROJECT MANAGEMENT
    public void projManagement() throws SQLException {
        System.out.println("\nSelect the task!\n" +
                "1: Create Project. \n" +
                "2: Update Project. \n" +
                "3: Delete Project. \n" +
                "4: Show All Projects. \n");
        System.out.println("Enter task number: ");
        int taskId = scanner.nextInt();
        if (taskId == 1) {
            createProject();

        } else if (taskId == 2) {

        } else if (taskId == 3) {
            deleteProject();

        } else if (taskId == 4) {
            showAllProject();
        } else {
            System.out.println("Wrong Choice. Please Enter again!");
            projManagement();
        }
    }

    public void createProject() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            scanner.nextLine();
            System.out.println("Enter project name:");
            String name = scanner.nextLine();
            System.out.println("Enter area:");
            String area = scanner.nextLine();
            System.out.println("Enter pincode:");
            int pincode = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter total members:");
            int totmembers = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter estimated cost:");
            int cost = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter start date in MM/DD/YYYY:");
            String sdate = scanner.nextLine();
            System.out.println("Enter end date in MM/DD/YYYY:");
            String edate = scanner.nextLine();

            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sdate);
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(edate);

            statement.execute("insert into project(name,area,pincode,total_members,cost_estimated,start_date,end_date)" +
                    "values('" + name + "','" + area + "','" + pincode + "','" + totmembers + "','" + cost + "','" + date1 + "','" + date2 + "')");

            System.out.println("Project created successfuly!");
            statement.close();

        } catch (SQLException | ParseException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            projManagement();
        }
    }

    public void deleteProject() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            System.out.println("Enter project Id: ");
            int projid = scanner.nextInt();
            statement.execute("delete from project where id='" + projid + "'");
            System.out.println("Project deleted successfully");
        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            projManagement();
        }

    }

    public void showAllProject() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select * from project");
            System.out.println("$$$$$$$ Projects $$$$$$$ \n");
            while (res.next()) {

                System.out.println("1.Project Id: " + res.getInt(1));
                System.out.println("2.Project Name: " + res.getString(2));
                System.out.println("3.Area: " + res.getString(3));
                System.out.println("4.Pincode: " + res.getInt(4));
                System.out.println("5.Total Mmbers: " + res.getInt(5));
                System.out.println("6.Cost: " + res.getInt(6));
                System.out.println("7.StartDate: " + res.getString(7));
                System.out.println("8.EndDate: " + res.getString(8));
                System.out.println("9.Alloted: " + res.getBoolean(9));
                System.out.println("--------------------------------");
                System.out.println("\n");
            }
//            statement.close();
        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            projManagement();
        }
    }

    public void requestManagement() throws SQLException {
        System.out.println("\nSelect the task!\n" +
                "1: Show Pending Project Requests \n" +
                "2: Show Pending Wage Requests \n" +
                "3: Show Pending Complaints \n");
        System.out.println("Enter Choice: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            projectRequests();

        } else if (choice == 2) {

        } else if (choice == 3) {
            handleComplaints();
        } else {
            System.out.println("Wrong Choice. Please Enter again!");
            requestManagement();
        }

    }

    public void handleComplaints() throws SQLException {
        try {
            pendingComplaints();
            Statement statement = conn.createStatement();
            System.out.println("Enter Complaint Id: ");
            int cid = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter 'A' to approve or 'R' to reject:");
            String req = scanner.nextLine();
            if (req.equalsIgnoreCase("A")) {

                statement.execute("update complaints set status_bdo = 'approved' where id='" + cid + "'");
                System.out.println("Approved Successfuly!");
                statement.close();
            } else if (req.equalsIgnoreCase("R")) {
                statement.execute("update complaints set status_bdo = 'rejected' where id = '" + cid + "'");
                System.out.println("Rejected Successfully! ");
                statement.close();
            } else {
                System.out.println("Wrong Choice. Please Enter valid choice:");
                handleComplaints();
            }

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            requestManagement();
        }
    }

    public void pendingComplaints() {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select * from complaints where status_gpm='approved' and status_bdo='false'");
            while (res.next()) {
                System.out.println("1.Complaint Id:" + res.getInt(1));
                System.out.println("2.Member Id:" + res.getInt(2));
                System.out.println("3.Complaint:" + res.getString(3));
                System.out.println("4.Gpm Id:" + res.getBoolean(4));
                System.out.println("---------------------------------");
                System.out.println("\n");
            }
        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        }
    }

    public void projectRequests() throws SQLException {
        try {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select * from project where id in(select p_id from project_member where status='false')");
            while (res.next()) {
                System.out.println("1.Project Id:" + res.getInt(1));
                System.out.println("2.Project Name:" + res.getString(2));
                System.out.println("---------------------------------");
            }
            statement.close();
            System.out.println("Enter Project Id to Approve: ");
            int pid = scanner.nextInt();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("timest"+timestamp);
            statement.execute("update project_member set status= 'approved' and approved_date = '"+timestamp+"' where p_id='" + pid + "'");
            System.out.println("Approved Successfully!");
        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            requestManagement();
        }
    }

}

