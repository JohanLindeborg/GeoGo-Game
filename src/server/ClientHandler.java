package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sharedFiles.Message;


/*
 * This class represents the connection between a client and the server
 * it has methods for communication between this client and other clients.
 */
public class ClientHandler extends Thread{

	/**
	 * 
	 */

	private Socket clientSocket;
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private boolean isInGame = false;
	private Boolean listeningForMessages = true;
	
	private GameServer server;
	
	/*
	 * ObjectOutputStream for communication with the
	 * other player (client).
	 */
	
	
	public ClientHandler(Socket clientSocket, GameServer server) {
		this.clientSocket = clientSocket;
		this.server = server;
		
		try {
			this.ois = new ObjectInputStream(clientSocket.getInputStream());    
			this.oos = new ObjectOutputStream(clientSocket.getOutputStream());

		} catch (IOException e) {
			System.out.println("IO Exception:");
			e.printStackTrace();
		}
	}
	
	/*
	 * Lyssnar efter inkommande meddelanden.
	 * 
	 */
	public void run() {
		Message message = null;
        
		while(listeningForMessages) {
			try {
				message = (Message) ois.readObject();
			    System.out.println("Clienthandler read object ( "+message+" ).");

	            
	        	
	        }catch(ClassNotFoundException e) {
	        	System.out.println("Class not found exception");
	        	e.printStackTrace();
	        }
			catch (IOException e) {
	        	System.out.println("IO exception");
	            e.printStackTrace();
			}
			server.processDataFromClient(message, this);
		}
    }
	/**
	 * This method will be used for communication to the client, to send a message
	 * to the client connected through this ClientHandler.
	 * @param message The message to be sent
	 */
	public void sendToClient(Object obj) {
		
		try {
			
			oos.writeObject(obj);
			
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
	}
	
	public void setListeningForMessages(boolean listening) {
		listeningForMessages = listening;
	}
	
	public void SetIsInGame(boolean inGame) {
		isInGame = inGame;
	}
	
	
	
}
