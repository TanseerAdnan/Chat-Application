import java.io.BufferedReader;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Date;

import javax.swing.*;


public class Clients extends Template {
	
	// SINGLETON - DESIGN PATTERN
	private static Clients css = new Clients();
	
	public static Clients getInstances() {
		return css;
	}


	JFrame o = new JFrame();

	//Constructor
	private Clients() {
			
	}
	
	// Default implementation of Client
	public void gettingStarted() {
		
		heading = new JLabel(name);
		
		// Setting up a platform
		try {
			System.out.println("Sending request to server");
			socket = new Socket("127.0.0.1", 2222);
			System.out.println("Connection done....");
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			} catch (Exception e) {	
			System.out.println("Connection is closed\n\n"+e);
		}
	}
		
	// GUI Implementation
	public void createGUI() {
		
		o.setTitle("Client Messenger");
		o.setSize(500, 500);
		o.setLocationRelativeTo(null);
		o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// coding for components
		heading.setFont(font);
		msgArea.setFont(font);
		msgIn.setFont(font);
		
		//heading Component
		heading.setIcon(new ImageIcon("D:\\Eclipse Work\\Chat Application\\src\\icon.png"));
		heading.setBackground(Color.DARK_GRAY);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setHorizontalTextPosition(SwingConstants.CENTER);
		heading.setVerticalTextPosition(SwingConstants.BOTTOM);
		heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); //Top, Right, Bottom, Left
		
		//Message Input Components
		msgArea.setEditable(false);
		msgArea.setForeground(Color.WHITE);
		msgArea.setBackground(Color.BLACK);
		msgIn.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Frame Layout
		o.setLayout(new BorderLayout());
		
		//adding the component of frame
		o.add(heading, BorderLayout.NORTH);
		JScrollPane  jsp = new JScrollPane(msgArea);
		o.add(jsp, BorderLayout.CENTER);
		o.add(msgIn, BorderLayout.SOUTH);
		o.setVisible(true);
	}

	
	
	//Reading method
	@Override
	public void startReading() {
		Runnable r2 = ()->{
			try {
				while(true) {
					String msg = br.readLine();
					if(msg.equals("exit")) {
						System.out.println("Server terminated the chat");
						JOptionPane.showMessageDialog(o, "Server Terminated "+ "the chat");
						msgIn.setEnabled(false);
						socket.close();
						
						String name = heading.getText();
						System.out.println(name);
						
						// Mediator Implementation
						InetAddress addr = InetAddress.getByName("127.0.0.1");
						User u = new User(name);
						
						String DaT = new Date().toString();
						u.Description(addr.getHostAddress(), "Server", DaT);
						
						// Initializing Database
						DataBaseInfo db = new DataBaseInfo(name, addr.getHostAddress(), DaT, "Client");
						Queries.insertinfo(db);
						System.out.println(db);
						break;
						}
					
					msgArea.append("Server : " + msg + "\n");
					}
			
				} catch(Exception e) {
				
				System.out.println();
			}
			
		};
		// Executing Thread
		new Thread(r2).start();
	}
	
}