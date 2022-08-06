public class User {
	
	// MEDIATOR - DESIGN PATTERN 
	private String name; 
	
	public String getName() { 
		return name; 
	} 
	
	public void setName(String name) { 
		this.name = name; 
	}
	
	public User(String name){ 
		this.name = name; 
	} 
	
	public void Description(String IP, String Communicates, String Date_Time){
		User_Info.Show_Info(this,IP,Communicates, Date_Time); 
	}
}