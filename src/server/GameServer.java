package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.teamdev.jxmaps.LatLng;

import sharedFiles.AddToServerListMessage;
import sharedFiles.GameData;
import sharedFiles.LocationMessage;
import sharedFiles.Message;
import sharedFiles.RequestGameMessage;

public class GameServer extends Thread {
	
	//Hashmap to store usernames (key) and their associated threads.
	private HashMap<String, ClientHandler> clientMap = new HashMap<String, ClientHandler>();
	private ArrayList<GameData> gameList = new ArrayList<GameData>();
	
	
	private ServerSocket serverSocket;
	private int serverPort = 4242;
		
	public GameServer() {
		
		
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();

		
	}
	public void run() {
		while(this.isAlive()){
	       System.out.println("Server listening for new connections...");
			Socket clientSocket = null;
	        try {
	            clientSocket = this.serverSocket.accept();
	        } catch (IOException e) {
	            if(this.isAlive() != true) {
	                System.out.println("Server Stopped.") ;
	                return;
	            }
	            throw new RuntimeException(
	                "Error accepting client connection", e);
	        }
	        new ClientHandler(clientSocket, this).start();
		      System.out.println("connection accepted new clienthandler started.");

	    }
	}
	
	
	/**
	 * Kollar om 
	 * @param msg
	 * @param player1Handler
	 */
	public void processDataFromClient(Message msg, ClientHandler player1Handler) {
		
		if(msg instanceof AddToServerListMessage) {
			AddToServerListMessage listMessage = ((AddToServerListMessage) msg);
			
			clientMap.put(listMessage.getUsername(), player1Handler);
		    System.out.println("Added "+listMessage.getUsername()+" to clientlist");

		}
		
		else if(msg instanceof RequestGameMessage) {
			RequestGameMessage reqMsg = (RequestGameMessage) msg;
			
			//Creates a new game without acceptance from other client.
			String player1 = player1Handler.getName();
			String player2 = reqMsg.getOtherPlayer();
			ClientHandler player2Handler = clientMap.get(player2);
		
			//Stores the game
			GameData newGame = new GameData(player1, player2);
			System.out.println("Server created new GameData");
			
			//sets map data
			newGame.setMapCenter(reqMsg.getMapCenter());
			newGame.setZoomLevel(reqMsg.getZoomLevel());
			
			gameList.add(newGame);
			
			player1Handler.sendToClient(newGame);
			player2Handler.sendToClient(newGame);
		}
		
		else if (msg instanceof LocationMessage) {
			
		}
	}
	
	public HashMap<String, ClientHandler> getClientMap(){
		return clientMap;
	}
	
}
