package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

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
	
	public HashMap<String, ClientHandler> getClientMap(){
		return clientMap;
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
	
	// Synkroniserad? / eller 1 tråd per aktivt spel? 
	public void processDataFromClient(Object msg, ClientHandler senderHandler) {
		
		if(msg instanceof AddToServerListMessage) {
			AddToServerListMessage listMessage = ((AddToServerListMessage) msg);
			
			senderHandler.setUserName(listMessage.getUsername());
			clientMap.put(listMessage.getUsername(), senderHandler);
		    System.out.println("Added "+listMessage.getUsername()+" to clientlist");

		}
		
		else if(msg instanceof GameData) {
			GameData gameData = (GameData) msg;
			ClientHandler receiverHandler = null;
			System.out.println("Sender: "+senderHandler.getUserName());
			System.out.println("player2: "+gameData.getPlayer2());
			//Makes sure the right receiver is used, since both players can be the sender
			if(senderHandler.getUserName().equals(gameData.getPlayer2())) {
				System.out.println("in if sats");
				receiverHandler = clientMap.get(gameData.getPlayer1());
			}
			else {
				receiverHandler = clientMap.get(gameData.getPlayer2());
			}
			
			// game started
			if(gameData.getGameStarted()) {
				
				// Game Finished
				if(gameData.getGameFinished()) {
					
				}
				
				// game ongoing
				//Updates ongoing games with current information
				else {
					
					
					
					receiverHandler.sendToClient(gameData);    
					senderHandler.sendToClient(gameData);
				}
				
			}
			//Game not started
			else {
				receiverHandler.sendToClient(gameData);
			}
		}
	}
	
}

// Behålla gameData? kolla så att användare inte kan skicka samtidigt, 
// alt. uppdatera gamedata genom att kommandon skickas, synkronisera metoder i gameData
//
