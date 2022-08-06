import java.util.*;
import java.io.BufferedReader;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;

import javax.swing.*;

class Server {
	
	// Creating JFrame object for template implementation 
	JFrame o = new JFrame();
	
	//Variables - Socket
	ServerSocket server;
	Socket socket;
	
	BufferedReader br;
	PrintWriter out;
	
	//Declare Components
		private JLabel heading;
		private JTextArea msgArea = new JTextArea();
		private JTextField msgIn = new JTextField();
		public Font font = new Font("Times new Roman", Font.PLAIN, 20);
		String name;
		
	public Server() {
		
			gettingstarted();
			createGUI();
			handleEvents();
			startWriting();
			startReading();
		} 
		
	
	// Default implementation of Server
	public void gettingstarted() {
		
		Login login = new Login();
		login.LoginGui();
		name = login.getNickname();
		heading = new JLabel(name);
		
		// Setting up a server 
		try {
			server = new ServerSocket(2222);
			System.out.println("Server is ready to accecpt connection.");
			socket = server.accept();
		
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// GUI Implementation
	private void createGUI() {
		
		o.setTitle("Server Messenger");
		o.setSize(500, 500);
		o.setLocationRelativeTo(null);
		o.setBackground(Color.BLUE);
		o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		// coding for components
		heading.setFont(font);
		msgArea.setFont(font);
		msgIn.setFont(font);
		
		//heading Component
		heading.setIcon(new ImageIcon("D:\\Eclipse Work\\Chat Application\\src\\iServer.png"));
		heading.setHorizontalAlignment(SwingConstants.CENTER);
		heading.setHorizontalTextPosition(SwingConstants.CENTER);
		heading.setVerticalTextPosition(SwingConstants.BOTTOM);
		heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); //Top, Right, Bottom, Left
		
		//Message Input Components
		msgArea.setEditable(false);
		msgArea.setForeground(Color.WHITE);
		msgArea.setBackground(Color.black);
		msgIn.setSize(10, 20);
		msgIn.setColumns(45);
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

	private void handleEvents() {
		msgIn.addKeyListener(new KeyListener() { //KeyListener() is a interface

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==10) {
				
					String contentToSend = msgIn.getText();
					msgArea.append("Me : " + contentToSend + "\n");
					out.println(contentToSend);
					out.flush();
					msgIn.setText("");
					msgIn.requestFocus();
				}
			}
			
		});
		
	}
	
	// Writing Implementation
	public void startWriting() {
		Runnable r1 = ()->{
			try {
				while(!socket.isClosed()) {
				
					BufferedReader br1 = new BufferedReader(new 
							InputStreamReader(System.in));
					String content = br1.readLine();
					
					out.println(content);
					out.flush();
					
					// Exit command
					if(content.equals("exit")) {
						socket.close();
						break;
					}
					
				} 
				
			} catch(Exception e) {
				System.out.println("Connection is closed , Try to reach out to server first.");
			}
		};
		
		// Executing Thread
		new Thread(r1).start();
	}
	
	// Reading Implementation
	public void startReading() {
		Runnable r2 = ()->{
			try {
				while(true) {
					String msg = br.readLine();
					
					if(msg.equals("exit")) {
						JOptionPane.showMessageDialog(o, "Client has Terminated the chat");
						msgIn.setEnabled(false);
						socket.close();
						
						String hn = heading.getText();
						System.out.println(hn);
						
						// Mediator Implementation
						InetAddress addr = InetAddress.getByName("127.0.0.1");
						User u = new User(hn);
						
						String DaT = new Date().toString();
						u.Description(addr.getHostAddress(), "Client", DaT);
						
						// Initializing Database
						DataBaseInfo db = new DataBaseInfo(hn, addr.getHostAddress(), DaT, "Server");
						Queries.insertinfo(db);
						break;
					}
					
					msgArea.append("Clients : " + msg + "\n");
				}
			
			}	catch(Exception e) {
				System.out.println("Client Connection is closed");
			}
			
		};
		
		// Executing Thread
		new Thread(r2).start();
	}
	public static void main(String args[]) {
		new Server();
	}
}