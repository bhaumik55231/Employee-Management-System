package DAO;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;


public class Dbconn {
	static Connection con=null;
    public static Connection getConnection()
    {
        if (con != null) return con;
        String url = "jdbc:mysql://localhost/software_engineering";
		String uname = "root";
		String pass = "root";
        return getConnection(url, uname, pass);
    }
    
    private static Connection getConnection(String db_name,String user_name,String password)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("Creating a connection object");
            con=(Connection) DriverManager.getConnection(db_name, user_name, password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return con;        
    }
}
