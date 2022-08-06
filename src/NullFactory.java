public class NullFactory {
	
	// SETTING NULL / FACTORY DESIGN PATTERN
	public static final String[] userStoredName = {"tanseer","maaz","haziq","basit"};
	
	public static AbstractUser UserInfo	(String userName) {
		for(int i = 0;i<userStoredName.length;i++) {
			if(userStoredName[i].equalsIgnoreCase(userName)) {
				return new CorrectName(userName);
			}
		}
		 return new IncorrectName();
	}
}
