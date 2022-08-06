
public class DataBaseInfo {
	String User_Name;
	String User_IP;
	String Date_Time;
	String As_An;
	public DataBaseInfo(String user_Name, String user_IP, String date_Time, String as_An) {
		super();
		User_Name = user_Name;
		User_IP = user_IP;
		Date_Time = date_Time;
		As_An = as_An;
	}
	public String getUser_Name() {
		return User_Name;
	}
	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}
	public String getUser_IP() {
		return User_IP;
	}
	public void setUser_IP(String user_IP) {
		User_IP = user_IP;
	}
	public String getDate_Time() {
		return Date_Time;
	}
	public void setDate_Time(String date_Time) {
		Date_Time = date_Time;
	}
	public String getAs_An() {
		return As_An;
	}
	public void setAs_An(String as_An) {
		As_An = as_An;
	}
	public DataBaseInfo() {
		super();
	}
	@Override
	public String toString() {
		return "DataBaseInfo [User_Name=" + User_Name + ", User_IP=" + User_IP + ", Date_Time=" + Date_Time + ", As_An="
				+ As_An + "]";
	}
	
}
