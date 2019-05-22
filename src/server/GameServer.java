package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sharedFiles.AddToServerListMsg;
import sharedFiles.DisconnectMsg;
import sharedFiles.GameData;
import sharedFiles.Message;
import sharedFiles.RequestGameMsg;
import sharedFiles.UpdateConnectedUsersMsg;
import sharedFiles.MapClickMsg;

public class GameServer extends Thread {

	// Hashmap to store usernames (key) and their associated threads.
	private HashMap<String, ClientHandler> clientMap = new HashMap<String, ClientHandler>();
	private ArrayList<GameData> gameList = new ArrayList<GameData>();
	private ArrayList<String> users;


	private ServerSocket serverSocket;
	private int serverPort = 8888;

	public GameServer() {
		
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			System.out.println(address.getHostAddress());

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}

		start();
	}

	public HashMap<String, ClientHandler> getClientMap() {
		return clientMap;
	}

	public void run() {
		while (this.isAlive()) {
			System.out.println("Server listening for new connections...");
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				if (this.isAlive() != true) {
					System.out.println("Server Stopped.");
					return;
				}

				throw new RuntimeException("Error accepting client connection", e);
			}

			new ClientHandler(clientSocket, this);
			System.out.println("connection accepted new clienthandler started.");
		}
	}

	// Synkroniserad? / eller 1 tråd per aktivt spel?
	public void processDataFromClient(Object obj, ClientHandler senderHandler) {

		if (obj instanceof AddToServerListMsg) {
			AddToServerListMsg msg = ((AddToServerListMsg) obj);

			senderHandler.setUserName(msg.getSender());
			clientMap.put(msg.getSender(), senderHandler);
			System.out.println("Added " + msg.getSender() + " to clientlist");
			
			users = new ArrayList<String>();
			
			//Creates arraylist with all users
			for(String key: clientMap.keySet()) {
				users.add(key);
			}
			// sends list of all connected users to the users
			for(ClientHandler handler: clientMap.values()) {
				handler.sendToClient(new UpdateConnectedUsersMsg(users));
			}
			
		}

		else if (obj instanceof RequestGameMsg) {
			RequestGameMsg msg = (RequestGameMsg) obj;

			ClientHandler plyr2 = clientMap.get(msg.getOtherPlayer());
			GameData2 gameData = new GameData2(senderHandler, plyr2, msg.getTotalRounds(), msg.getMapCenter(),
					msg.getZoomLevel(), msg.getMapName());

			plyr2.setGameData(gameData);
			senderHandler.setGameData(gameData);

			gameData.setupGame();

			// senderHandler.newGame(msg, clientMap.get(msg.getOtherPlayer()));
		}
		else if (obj instanceof DisconnectMsg) {
			DisconnectMsg msg = (DisconnectMsg) obj;
			
			clientMap.remove(msg.getSender()).stopThread();
			
			updateClientList();
		}
	}
	
	private void updateClientList() {
		ArrayList<String> users = new ArrayList<String>();
		
		for ( String key : clientMap.keySet() ) {
		    users.add(key);
		}
		for( ClientHandler handler : clientMap.values()) {
			handler.sendToClient(new UpdateConnectedUsersMsg(users));
		}
	}
	
	private void newClientUpdate(ClientHandler senderHandler) {
		ArrayList<String> users = new ArrayList<String>();
		
		for ( String key : clientMap.keySet() ) {
		    users.add(key);
		}
		senderHandler.sendToClient(new UpdateConnectedUsersMsg(users));
	}
}

// Behålla gameData? kolla så att användare inte kan skicka samtidigt, 
// alt. uppdatera gamedata genom att kommandon skickas, synkronisera metoder i gameData
//
