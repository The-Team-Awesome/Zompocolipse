package clientServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

/**
 * This is the Server-side Thread which receives input from Clients via
 * the Socket and sends it to the game. Game updates are also sent back to
 * the Client.
 * 
 * @author Sam Costigan
 *
 */
public class Server extends Thread {

	private final Socket socket;
	private final int id;
	private final int networkClock;
	
	public Server(Socket socket, int id, int networkClock) {
		this.socket = socket;
		this.id = id;
		this.networkClock = networkClock;
	}
	
	public void run() {
		try {
			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			
			boolean running = true;
			output.writeInt(id);
			
			while(running) {
				try {
					if(input.available() != 0) {
						int code = input.readInt();
						switch(code) {
							case 1:
								// In this case, the key pressed
								// corresponds to left
								System.out.println("left");
								break;
							case 2:
								// In this case, the key pressed
								// corresponds to up
								System.out.println("up");
								break;
							case 3:
								// In this case, the key pressed
								// corresponds to right
								System.out.println("right");
								break;
							case 4:
								// In this case, the key pressed
								// corresponds to down
								System.out.println("down");
								break;
							case 5:
								// In this case, the event passed was a
								// mouse click somewhere on the screen
								int x = input.readInt();
								int y = input.readInt();
								System.out.println(x + ": " + y);
								break;
							case 6:
								// In this case, a Swing component was
								// triggered, such as a button press.
								// The command is given and will be passed on
								int length = input.readInt();
								char[] string = new char[length];
								for(int i = 0; i < length; i++) {
									string[i] = input.readChar();
								}
								String command = String.copyValueOf(string);
								System.out.println(command);
								break;
						}
					}
					
					Thread.sleep(networkClock);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			socket.close();
			
		} catch (IOException e) {
			System.out.println("Player " + id + " has disconnected");
			// TODO: handle removal of Player from game
		}
		
	}
	
}