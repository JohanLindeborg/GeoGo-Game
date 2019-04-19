package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.swing.MapView;

import jxMaps_Demo.HelloWorld;
import sharedFiles.AddToServerListMessage;
import sharedFiles.MapMessage;
import sharedFiles.Message;
import sharedFiles.RequestGameMessage;

/*
 * This class represents the connection between a client and the server
 * it has methods for communication between this client and other clients.
 */
public class ClientHandler extends Thread {

	private Socket clientSocket;
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private boolean isInGame = false;
	private Boolean listeningForMessages = true;
	
	private Server server;
	
	/*
	 * ObjectOutputStream for communication with the
	 * other player (client).
	 */
	private ObjectOutputStream otherClientOOS = null;
	
	
	public ClientHandler(Socket clientSocket, Server server) {
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
			if(message instanceof AddToServerListMessage ) {
				
				server.addClientToServerList(((AddToServerListMessage) message).getUsername(), this);
			    System.out.println("Clienthandler added to serverlist with key: "+((AddToServerListMessage) message).getUsername());

			}
			else if(message instanceof RequestGameMessage) {
	        	System.out.println("CH: requestGameMessage received");

				RequestGameMessage requestMessage = (RequestGameMessage) message;
				
				HashMap<String, ClientHandler> clientMap = server.getClientMap();
				
				ClientHandler otherClient = clientMap.get(requestMessage.getOtherClientName());
				
				
	        	System.out.println("Starting GameHandler");
				new GameHandler(this, otherClient).start();
	        	System.out.println("GameHandler started");

				
			}
			else if(message instanceof Message) {
				
			}
		}
    }
	/**
	 * This method will be used for communication to the client, to send a message
	 * to the client connected through this ClientHandler.
	 * @param message The message to be sent
	 */
	public void sendMessage(Message message) {
		
		try {
			
			oos.writeObject(message);
			
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
	}
	
	public ObjectOutputStream getOOS() {
		return oos;
	}
	
	public ObjectInputStream getOIS() {
		return ois;
	}
	
	public Socket getClientSocket() {
		return clientSocket;
	}
	
	
	public void setListeningForMessages(boolean listening) {
		listeningForMessages = listening;
	}
	
	public void SetIsInGame(boolean inGame) {
		isInGame = inGame;
	}
	
	
	
}
