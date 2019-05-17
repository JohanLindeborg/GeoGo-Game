package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import sharedFiles.AddToServerListMsg;
import sharedFiles.GameData;
import sharedFiles.Message;
import sharedFiles.RequestGameMsg;
import sharedFiles.MapClickMsg;

public class GameServer extends Thread {

	// Hashmap to store usernames (key) and their associated threads.
	private HashMap<String, ClientHandler> clientMap = new HashMap<String, ClientHandler>();
	private ArrayList<GameData> gameList = new ArrayList<GameData>();

	private ServerSocket serverSocket;
	private int serverPort = 8888;

	public GameServer() {

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
	}
}

// Behålla gameData? kolla så att användare inte kan skicka samtidigt, 
// alt. uppdatera gamedata genom att kommandon skickas, synkronisera metoder i gameData
//
