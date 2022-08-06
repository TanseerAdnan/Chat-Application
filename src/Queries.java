import java.sql.*;
public class Queries {
	
	 public static void insertinfo(DataBaseInfo db) {
		 try {
			 
			 Connection con = CP.createC();
			 String q = "insert into info(User_Name, User_IP, Date_Time, As_An) values(?,?,?,?)";
			 PreparedStatement pstmt = con.prepareStatement(q);
			 pstmt.setString(1, db.User_Name);
			 pstmt.setString(2, db.User_IP);
			 pstmt.setString(3, db.Date_Time);
			 pstmt.setString(4, db.As_An);
			 pstmt.executeUpdate();
		 }catch(Exception e) {
			 
		 }
	 }
		 public static void deletequ(int User_ID) {
			 try {
				Connection con = CP.createC();
			 	String q = "delete from info where User_ID = ?";
			 	PreparedStatement pstmt = con.prepareStatement(q);
			 	System.out.println("\nQuery: " + q + "\n");
			 	pstmt.setInt(1, User_ID);
			 	pstmt.executeUpdate();
			 	System.out.println("Deleted");
			 }catch(Exception e) {
				 
			 }
		 }
		public static void ShowAll() {
			try {
				Connection con = CP.createC();
			 	String q = "Select * from info";
			 	Statement stmt = con.createStatement();
			 	ResultSet set = stmt.executeQuery(q);
			 	System.out.println("\nQuery: " + q + "\n");
			 	while(set.next()) {
			 	int id = set.getInt(1);
				String name = set.getString(2);
				String IP = set.getString(3);
				String dt = set.getString(4);
				String as = set.getString(5);
				System.out.println("User_ID | " + id + "| User_Name | " + name + " | User_IP | " + IP + " | Date_Time | " + dt + " | AS_An | " + as + "\n");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
			 	}
			 }catch(Exception e) {
				 
			 }
		}
		public static void ShowPri(int User_ID) {
			 try {
				Connection con = CP.createC();
			 	String q = "Select * from info where User_ID = ?";
			 	PreparedStatement pstmt = con.prepareStatement(q);
			 	pstmt.setInt(1, User_ID);
			 	ResultSet set = pstmt.executeQuery();
			 	System.out.println("\nQuery: " + q + "\n");
			 	while(set.next()) {
			 	int id = set.getInt(1);
				String name = set.getString(2);
				String IP = set.getString(3);
				String dt = set.getString(4);
				String as = set.getString(5);
				System.out.println("\nUser_ID | " + id + "| User_Name | " + name + " | User_IP | " + IP + " | Date_Time | " + dt + " | AS_An | " + as + "\n");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
			 	}
			 }catch(Exception e) {
				 
			 }
		 }
		public static void Like(String User_Name) {

			 try {
				Connection con = CP.createC();
			 	String q = "Select * from info where User_Name like ?";
			 	PreparedStatement pstmt = con.prepareStatement(q);
			 	pstmt.setString(1, User_Name);
			 	ResultSet set = pstmt.executeQuery();
			 	System.out.println("\nQuery: " + q + "\n");
			 	while(set.next()) {
			 	int id = set.getInt(1);
				String name = set.getString(2);
				String IP = set.getString(3);
				String dt = set.getString(4);
				String as = set.getString(5);
				System.out.println("\nUser_ID | " + id + "| User_Name | " + name + " | User_IP | " + IP + " | Date_Time | " + dt + " | AS_An | " + as + "\n");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
			 	}
			 }catch(Exception e) {
				 
			 }
		 }
		public static void toprows(int top) {
			try {
				Connection con = CP.createC();
				String q = "select * from info LIMIT " + top;
			 	Statement stmt = con.createStatement();
			 	ResultSet set = stmt.executeQuery(q);
			 	System.out.println("\nQuery: " + q + "\n");
			 	while(set.next()) {
			 	int id = set.getInt(1);
				String name = set.getString(2);
				String IP = set.getString(3);
				String dt = set.getString(4);
				String as = set.getString(5);
				System.out.println("User_ID | " + id + "| User_Name | " + name + " | User_IP | " + IP + " | Date_Time | " + dt + " | AS_An | " + as + "\n");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
			 	}
			 }catch(Exception e) {
				 
			 }
		 }
		public static void As_An(String As_An) {
			try {
				Connection con = CP.createC();
				String q = "select User_Name from info where As_An like Clients";
				PreparedStatement pstmt = con.prepareStatement(q);
				
			 	pstmt.setString(1, As_An);
			 	ResultSet set = pstmt.executeQuery();
			 	System.out.println("\nQuery: " + q + "\n");
			 	while(set.next()) {
				String name = set.getString(1);
				System.out.println("User_Name : " + name + "\n");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
			 	}
			 }catch(Exception e) {
				 
			 }
		}
}
