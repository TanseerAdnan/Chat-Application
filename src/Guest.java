import java.io.BufferedReader;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Date;

import javax.swing.*;


public class Guest extends Template{
	
	JFrame o = new JFrame();
	
	//Constructor
	public Guest() {
		
	}
	
	public void gettingStarted() {
		
		heading = new JLabel(name);
		
		try {
			
			System.out.println("Sending request to server");
			socket = new Socket("127.0.0.1", 2222);
			System.out.println("Connection done....");
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			// Setting a time limit for guest Frame/Class
			new Timer(20_000, (e) ->{
				o.setVisible(false);
				String st = "U need to Registered yourself";
				JOptionPane.showMessageDialog(null, st);
				o.dispose();
				
				try {
					String hn = heading.getText();
					
					// Mediator Implementation
					InetAddress addr;
					addr = InetAddress.getByName("127.0.0.1");
					String DaT = new Date().toString();
					
					// Initializing Database
					DataBaseInfo db = new DataBaseInfo(hn, addr.getHostAddress(), DaT, "Guests");
					Queries.insertinfo(db);
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
				
			}).start();
			
			} catch (Exception e) {
				System.out.println("Interuption while sending req to server");
		}
		
	}
	
	// GUI Implementation
	public void createGUI() {
		
		o.setTitle("Guest Messenger");
		o.setSize(500, 500);
		o.setLocationRelativeTo(null);
		o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// coding for components
		heading.setFont(font);
		msgArea.setFont(font);
		msgIn.setFont(font);
		
		//heading Component
		heading.setIcon(new ImageIcon("D:\\Eclipse Work\\Chat Application\\src\\iGuest.png"));
		heading.setBackground(Color.DARK_GRAY);
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setHorizontalTextPosition(SwingConstants.CENTER);
		heading.setVerticalTextPosition(SwingConstants.BOTTOM);
		heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); //Top, Right, Bottom, Left
		
		//Message Input Components
		msgArea.setEditable(false);
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


	//Writing method
	public void startReading() {
		Runnable r2 = ()->{
			try {
				while(true) {
					String msg = br.readLine();
					if(msg.equals("exit")) {
						JOptionPane.showMessageDialog(o, "Server Terminated "+ "the chat");
						msgIn.setEnabled(false);
						socket.close();
						String name = heading.getText();
						System.out.println(name);
						
						//Mediator Implementation
						InetAddress addr = InetAddress.getByName("127.0.0.1");
						User u = new User(name);
						
						String DaT = new Date().toString();
						u.Description(addr.getHostAddress(), "Server", DaT);
						
						// Initializing Database
						DataBaseInfo db = new DataBaseInfo(name, addr.getHostAddress(), DaT, "Guests");
						Queries.insertinfo(db);
						break;
					}
				
				msgArea.append("Server : " + msg + "\n");

				}
				
			} catch(Exception e) {
			System.out.println("Server not found");
		}
	};
	
	// Executing Thread
	new Thread(r2).start();
	
	}
}