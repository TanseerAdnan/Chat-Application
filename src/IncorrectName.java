import javax.swing.JOptionPane;

public class IncorrectName extends AbstractUser{
	
	// NULL POINTER
	public String NameFound() {
		JOptionPane.showMessageDialog(null,"Not avaliable in Database");
		return null;
	}

	public boolean notFound() {
		
		return true;
	}
	
}
