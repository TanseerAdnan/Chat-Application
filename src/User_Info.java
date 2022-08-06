
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// SETTING MEDIATOR DESIGN PATTERN
public class User_Info{
		public static void Show_Info(User user, String IP, String Communicates, String Date_Time) {
			JOptionPane.showMessageDialog(new JFrame(), user.getName() + " send the message to the " + Communicates + "\n" + user.getName() + " communicate with " +  
					Communicates + " at " + Date_Time + "\n" + user.getName() + " from IP Adress: " + IP);
		}
		
	}