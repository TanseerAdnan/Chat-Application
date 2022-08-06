public class CorrectName extends AbstractUser{
	
	// NULL POINTER
	public CorrectName(String userName) {
		this.userName=userName;
	}

	public String NameFound() {
		return userName;
	}

	public boolean notFound() {
		return false;
	}
}
