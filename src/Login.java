import javax.swing.JOptionPane;

// Asking for Server Name
public class Login {
	
    private String nickname;

    public String getNickname() {
        return this.nickname;
    }
	
	public void LoginGui() {
		
		this.nickname = JOptionPane.showInputDialog("Enter THE SERVER name : ");
		JOptionPane.showMessageDialog(null,"Welcome to the "+this.nickname+" Chat_App Server");
		
	}
	
} 