import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Models {
    public static final String TABLE_BDO = "bdo";
    public static final String TABLE_GPM = "gpm";
    public static final String TABLE_MEMBER = "member";
    public static final String TABLE_PROJECT = "project";
    public static final String TABLE_PROJECT_MEMBER = "project_member";
    public static final String TABLE_COMPLAINTS = "complaints";
    public Connection conn;

    Dbconnection dbconnection = new Dbconnection();

    public void model() throws SQLException {
        try {
            conn = dbconnection.ConnectDb();
            Statement statement = conn.createStatement();

            statement.execute("create table if not exists " + TABLE_BDO +
                    "(id integer primary key autoincrement,email text not null,password text not null)");

            statement.execute("create table if not exists " + TABLE_GPM +
                    "(id integer primary key autoincrement,name text not null," +
                    "email text not null,password text not null,area text not null,pincode integer not null," +
                    "bdo_id integer not null,foreign key (bdo_id) references bdo(id))");
            statement.execute("create table if not exists " + TABLE_MEMBER +
                    "(id integer primary key autoincrement,name text not null," +
                    "email text not null,password text not null,area text not null,pincode integer not null," +
                    "status text default 'not assigned',age integer not null,gpm_id integer not null,foreign key (gpm_id) references gpm(id))");
            statement.execute("create table if not exists " + TABLE_PROJECT +
                    "(id integer primary key autoincrement,name text not null," +
                    "area text not null,pincode integer not null,total_members integer not null,cost_estimated integer not null," +
                    "start_date date not null,end_date date not null,is_alloted boolean default 'false')");
            statement.execute("create table if not exists " + TABLE_PROJECT_MEMBER +
                    "(id integer primary key autoincrement,p_id integer not null,member_id integer not null," +
                    "gpm_id integer not null,approved_date Timestamp,wage_approved boolean default 'false'," +
                    "status boolean default 'false')");
            statement.execute("create table if not exists " + TABLE_COMPLAINTS +
                    "(id integer primary key autoincrement,member_id integer not null," +
                    "complaint text not null,gpm_id integer not null,status_gpm boolean default 'false'," +
                    "status_bdo boolean default 'false')");

//            statement.execute("insert into bdo(id,email,password)" +
//                    "values(1,'sayan@gmail.com','Sayan@1')");

//            statement.execute("drop table "+TABLE_BDO);
//            statement.execute("drop table "+TABLE_GPM);
//            statement.execute("drop table "+TABLE_MEMBER);
//            statement.execute("drop table "+TABLE_PROJECT);
//            statement.execute("drop table "+TABLE_PROJECT_MEMBER);
//            statement.execute("drop table "+TABLE_COMPLAINTS);

            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }

}
