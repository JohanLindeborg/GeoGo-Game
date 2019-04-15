package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sharedFiles.Message;

public class ClientHandler extends Thread {

	private Socket clientSocket;
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	/*
	 * ObjectOutputStream for communication with the
	 * other player (client).
	 */
	
	private ObjectOutputStream otherPlayerOOS;
	
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		
		try {
			this.ois = new ObjectInputStream(clientSocket.getInputStream());    
			this.oos = new ObjectOutputStream(clientSocket.getOutputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Lyssnar efter inkommande meddelanden.
	 * 
	 */
	public void run() {
		Message message = null;
        
		while(this.isAlive()) {
			try {
				message = (Message) ois.readObject();
				
	            
	        	
	        }catch(ClassNotFoundException e) {
	        	System.out.println("Class not found exception");
	        	e.printStackTrace();
	        }
			catch (IOException e) {
	        	System.out.println("IO exception");
	            e.printStackTrace();
	        }
			if(message instanceof Message ) {
				
			}
			else if(message instanceof Message) {
				
			}
			else if(message instanceof Message) {
				
			}
		}
    }
	
	public void sendMessageTo() {
		
	}
	
	public void setOtherPlayerSocket(Socket otherPlayerSocket) {
		try {
			this.otherPlayerOOS = new ObjectOutputStream(otherPlayerSocket.getOutputStream());
		}
		catch(IOException e) {
        	System.out.println("IO exception, when getting other player outputstream");
            e.printStackTrace();

		}
	}
	
	public Socket getSocket() {
		return this.clientSocket;
	}
	
}
