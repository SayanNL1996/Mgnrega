import java.security.PublicKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bdo {

    Scanner scanner = new Scanner(System.in);
    Dbconnection dbconnection = new Dbconnection();
    Connection conn = dbconnection.ConnectDb();


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
            projManagement();

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
                System.out.println(res.getString("id") + " " +
                        res.getString("name") + " " +
                        res.getString("email") + " " +
                        res.getString("area") + " " +
                        res.getString("pincode"));
            }
            statement.close();
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
            while (res.next()) {
                System.out.println(res.getString("id") + " " +
                        res.getString("name") + " " +
                        res.getString("area") + " " +
                        res.getString("pincode") + " " +
                        res.getString("total_members") + " " +
                        res.getString("cost_estimated") + " " +
                        res.getString("start_date") + " " +
                        res.getString("end_date") + " " +
                        res.getString("is_alloted"));
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            projManagement();
        }
    }

}

