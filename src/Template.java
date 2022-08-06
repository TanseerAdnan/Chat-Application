import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// TEMPLATE - DESIGN PATTERN
public abstract class Template {
	
	//Declare Components
	protected Socket socket;
	protected BufferedReader br;
	protected PrintWriter out;
	
	//Declare Components
	protected JLabel heading;
	protected JTextArea msgArea = new JTextArea();
	protected JTextField msgIn = new JTextField();
	protected Font font = new Font("Times new roman", Font.PLAIN, 20);
	protected String name;
	
	public final void template () {
		gettingStarted();
		createGUI();
		handleEvents();
		startWriting();
		startReading();
	}
	
	public abstract void gettingStarted() ;
	
	public abstract void createGUI();

	private  void handleEvents() {
		
		msgIn.addKeyListener(new KeyListener() { //KeyListener() is a interface

		@Override
		public void keyTyped(KeyEvent e) {
					
		}

		@Override
		public void keyPressed(KeyEvent e) {
					
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
	
	private void startWriting() {
		Runnable r1 = ()->{
			try {
				while(!socket.isClosed()) {
			
					BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
					String content = br1.readLine();	
					out.println(content);
					out.flush();

					if(content.equals("exit")) {
						socket.close();
						break;
					}
				}
			} catch(Exception e) {	
				System.out.println("Client Connection is closed ");
				}
		};	
		new Thread(r1).start();
	}

	//Reading method
	public abstract void startReading();
}