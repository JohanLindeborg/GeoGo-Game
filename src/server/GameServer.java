package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import sharedFiles.AddToServerListMsg;
import sharedFiles.DisconnectMsg;
import server.GameData;
import sharedFiles.RequestGameMsg;
import sharedFiles.UpdateConnectedUsersMsg;

/**
 * This class contains the main functionality for the server. This server with the use of the {@link ClientHandler}
 * class is a multithreaded TCP server.
 * It extends a thread with is used for listening for new connections. 
 * When a connection is made the established socket is sent to a new {@link ClientHandler}
 * specifically made for handling this connection. Users and their ClientHandlers
 * are stored in a hashMap with usernames as keys.
 * @author johanlindeborg
 *
 */
public class GameServer extends Thread {
	// Hashmap to store usernames (key) and their associated threads.
	private HashMap<String, ClientHandler> clientMap = new HashMap<String, ClientHandler>();
	private ArrayList<String> users;

	private ServerSocket serverSocket;
	private int serverPort = 8888;

	public GameServer() {
		
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();
			System.out.println(address.getHostAddress());
			serverSocket = new ServerSocket(serverPort);

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	public HashMap<String, ClientHandler> getClientMap() {
		return clientMap;
	}
	/**
	 * The run method for the thread, this method is started when the GameServer has been instanciated.
	 * It listens for new connections and creates a new {@link ClientHandler} for each new connection. 
	 */
	public void run() {
		while (this.isAlive()){
			System.out.println("Server listening for new connections...");
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
			} catch (IOException e) {
				if (this.isAlive() != true) {
					System.out.println("---------ERROR: Server Stopped--------");
					e.printStackTrace();
				}
			}
			new ClientHandler(clientSocket, this);
			System.out.println("connection accepted new clienthandler started.");
		}
	}
	/**
	 * This method is used to handle data received by ClientHandlers which concerns the server.
	 * This data could be a {@link AddToServerListMsg}, {@link RequestGameMsg} or a {@link DisconnectMsg}.
	 * @param obj The message received by a ClientHandler concerning the GameServer.
	 * @param senderHandler The ClientHandler which received the message.
	 */
	public void processDataFromClient(Object obj, ClientHandler senderHandler) {

		if (obj instanceof AddToServerListMsg){
			AddToServerListMsg msg = ((AddToServerListMsg) obj);

			senderHandler.setUserName(msg.getSender());
			clientMap.put(msg.getSender(), senderHandler);
			System.out.println("Added " + msg.getSender() + " to clientlist");
			
			users = new ArrayList<String>();
			
			//Creates arraylist with all users
			for(String key: clientMap.keySet()){
				users.add(key);
			}
			// sends list of all connected users to the users
			for(ClientHandler handler: clientMap.values()) {
				handler.sendToClient(new UpdateConnectedUsersMsg(users));
			}
		}

		else if (obj instanceof RequestGameMsg){
			RequestGameMsg msg = (RequestGameMsg) obj;

			ClientHandler plyr2 = clientMap.get(msg.getOtherPlayer());
			GameData gameData = new GameData(senderHandler, plyr2, msg.getTotalRounds(), msg.getMapCenter(),
					msg.getZoomLevel(), msg.getMapName());

			plyr2.setGameData(gameData);
			senderHandler.setGameData(gameData);

			gameData.setupGame();

		} else if (obj instanceof DisconnectMsg) {
			DisconnectMsg msg = (DisconnectMsg) obj;
			clientMap.remove(msg.getSender()).stopThread();
			
			updateClientList();
		}
	}

	private void updateClientList() {
		ArrayList<String> users = new ArrayList<String>();
		
		for ( String key : clientMap.keySet() ){
		    users.add(key);
		}
		for( ClientHandler handler : clientMap.values()){
			handler.sendToClient(new UpdateConnectedUsersMsg(users));
		}
	}
}
