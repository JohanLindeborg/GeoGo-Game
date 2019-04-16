package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.swing.MapView;

import demo.HelloWorld;
import sharedFiles.ConnectMessage;
import sharedFiles.MapMessage;
import sharedFiles.Message;

/*
 * This class represents the connection between a client and the server
 * it has methods for communication between this client and other clients.
 */
public class ClientHandler extends Thread {

	private Socket clientSocket;
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private boolean isInGame = false;
	
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
			if(message instanceof ConnectMessage ) {
				
				server.addClientToServerList(((ConnectMessage) message).getUsername(), this);
			}
			else if(message instanceof MapMessage) {
	
				MapView requestedMap =  ((MapMessage) message).getMapView();
				
				MapViewOptions options = new MapViewOptions();
				
				options.importPlaces();
		        options.setApiKey("AIzaSyBtefj5xL2e6j-qt65FaXdevjKB3oErQjo");
		        final HelloWorld mapView = new HelloWorld(options);
			}
			else if(message instanceof Message) {
				
			}
		}
    }
	/**
	 * This method will be used for communication to all clients, to send a message
	 * to the client connected through this ClientHandler, use: OOS as parameter,
	 * otherwise use the otherPlayerOOS for communication to the current opposing player.
	 * @param receiverOOS The ObjectOutputStream to be used
	 * @param message The message to be sent
	 */
	public void sendMessageToClient(ObjectOutputStream receiverOOS, Message message) {
		
		try {
			if(receiverOOS == null) {
				System.out.println("ObjectOutputStream = null");
				throw new IOException();
			}
			receiverOOS.writeObject(message);
			
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
	}
	/*
	 * setter for the objectoutputstream to be used when communicating
	 * to other client "player
	 */
	public void setOtherPlayerOOS(ObjectOutputStream otherPlayerOOS) {
		this.otherClientOOS = otherPlayerOOS;
	}
	/*
	 * getter method for the ObjectOutputStream used by this 
	 * clienthandler
	 */
	public ObjectOutputStream getOOS() {
		return oos;
	}
	
	
	
	
}
