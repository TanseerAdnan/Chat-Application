import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class LoginFrame extends JFrame implements ActionListener {
	
	// Initializing Variables
	String name;
	String userText;
    String pwdText;
    
    // SettingUp GUI
    Container container = getContentPane();
    
    JLabel heading = new JLabel("Login file"); 
    JLabel userLabel = new JLabel("Insert Name");
    JLabel passwordLabel = new JLabel("PASSWORD");
    
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    
    JCheckBox showPassword = new JCheckBox("Show Password");
    Font font = new Font("Times new roman", Font.PLAIN, 36);	
 
    //Calling methods in Constructor
    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
 
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
    	
        userLabel.setBounds(50, 250, 100, 30);
        passwordLabel.setBounds(50, 320, 100, 30);
        userTextField.setBounds(150, 250, 150, 30);
        passwordField.setBounds(150, 320, 150, 30);
        showPassword.setBounds(150, 350, 150, 30);
        loginButton.setBounds(50, 400, 100, 30);
        resetButton.setBounds(200, 400, 100, 30);
        
        heading.setIcon(new ImageIcon("D:\\Eclipse Work\\Chat Application\\src\\icon.png"));
        heading.setFont(font);
        heading.setBounds(50, 0, 250, 250);
    	heading.setHorizontalAlignment(SwingConstants.CENTER);
    	heading.setHorizontalTextPosition(SwingConstants.CENTER);
    	heading.setVerticalTextPosition(SwingConstants.BOTTOM);
    	heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
 
    public void addComponentsToContainer() {
    	
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(heading);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
 
	@Override
    public void actionPerformed(ActionEvent e) {
		
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            this.name = userTextField.getText();
            System.out.println(name);
            
            // Creating an object from Singleton and calling Template / implementation of Factory 
    		AbstractUser ab = NullFactory.UserInfo(name);
    		
            if (userText.equalsIgnoreCase(ab.NameFound()) && pwdText.equalsIgnoreCase("12345")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
				Template cs = Clients.getInstances();
				
				cs.template();
				cs.heading.setText(name);
				
            }
            
            // Default Implementation of Null
            else if (userText != (ab.NameFound()) && pwdText != ("12345")){
    					Template gs = new Guest();
    					gs.template();
    					gs.heading.setText(name);
            }
        }
        
        //Coding Part of RESET button
        if (e.getSource() == resetButton || e.getSource() == loginButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        
       //Coding Part of showPassword JCheckBox
       if (e.getSource() == showPassword) {
    	   if (showPassword.isSelected()) {
    		   passwordField.setEchoChar((char) 0);
    		   
    		   } else {
    			   passwordField.setEchoChar('*');
    	    }
        }
    }
	
    public static void main(String[] a) {
    	
        LoginFrame frame = new LoginFrame();
        
        frame.setBackground(Color.BLACK);
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 350, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
 
    }
 
}