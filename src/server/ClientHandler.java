package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sharedFiles.AddToServerListMsg;
import sharedFiles.DisconnectMsg;
import sharedFiles.MapClickMsg;
import sharedFiles.Message;
import sharedFiles.RequestGameMsg;
import sharedFiles.StartGameMsg;

/*
 * This class represents the connection between a client and the server
 * it has methods for communication between this client and other clients.
 */
public class ClientHandler extends Thread {

	/**
	 * 
	 */

	private Socket clientSocket;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private boolean isInGame = false;
	private Boolean listeningForMessages = true;

	private GameServer server;
	private GameData2 gameData;
	private String userName;

	/*
	 * ObjectOutputStream for communication with the other player (client).
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
		start();
	}

	/*
	 * Lyssnar efter inkommande meddelanden.
	 * 
	 */
	public void run() {
		Object obj = null;

		while (listeningForMessages) {
			try {
				obj = ois.readObject();
				System.out.println("Clienthandler for " + userName + " read object ( " + obj + " ).");

			} catch (ClassNotFoundException e) {
				System.out.println("Class not found exception");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IO exception");
				e.printStackTrace();
			}

			// Messages concerning the server
			if (obj instanceof AddToServerListMsg || obj instanceof RequestGameMsg || obj instanceof DisconnectMsg) {
				server.processDataFromClient(obj, this);
			}
			// Messages concerning a specific game
			else {

				if (obj instanceof StartGameMsg) {
					gameData.setPlayerReady(this, true);
					System.out.println("Clienthandler for " + userName + " sets ready for game.");

				} else if (obj instanceof MapClickMsg) {
					MapClickMsg msg = (MapClickMsg) obj;
					System.out.println("Clienthandler for " + userName + " received mapClickMsg");

					gameData.updateMapClick(msg);
				}

			}
		}
	}

	/**
	 * This method will be used for communication to the client, to send a message
	 * to the client connected through this ClientHandler.
	 * 
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

	public void SetIsInGame(boolean inGame) {
		isInGame = inGame;
	}

	public void setUserName(String name) {
		userName = name;
	}

	public String getUserName() {
		return userName;
	}
	
	public void stopThread() {
		listeningForMessages = false;
	}

	public void setGameData(GameData2 gameData) {
		if (this.gameData == null) {
			this.gameData = gameData;
			System.out.println("ClientHandler for " + userName + ": Received new GameData");
		} else {
			System.out.println("ClientHandler for " + userName + ": Already in game");
		}
	}

	/*
	 * public void newGame(RequestGameMsg msg, ClientHandler otherplayer) {
	 * if(this.gameData == null) { gameData = new GameData2(this, otherplayer,
	 * msg.getTotalRounds(),
	 * msg.getMapCenter(),msg.getZoomLevel(),msg.getMapName());
	 * System.out.println("ClientHandler for " +userName+": created new GameData");
	 * 
	 * otherplayer.setGameData(gameData); } else {
	 * System.out.println("ClientHandler for " +userName+": Already in game"); } }
	 */
}
