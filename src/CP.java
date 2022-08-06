
import java.sql.*;
public class CP {
	
	static Connection con;


    public static Connection createC() {
    	
    	 try{
           Class.forName("com.mysql.jdbc.Driver");
       		String DB_URL = "jdbc:mysql://localhost:3306/chatapplication_db";
       		String username = "root";
       		String password = "A.basit13";
           con = (DriverManager.getConnection(DB_URL, username, password));
           System.out.println("Connection Successful");
    	 }
    
    	 catch(Exception e) {
    		 System.out.println(e);
    	 }
    	 return con;
    }
}