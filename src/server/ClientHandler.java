package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sharedFiles.AddToServerListMsg;
import sharedFiles.DisconnectMsg;
import sharedFiles.MapClickMsg;
import sharedFiles.RequestGameMsg;
import sharedFiles.StartGameMsg;

/**
 * This class is used for handling connections established by the {@link GameServer}.
 * With the {@link Socket} received from the server it creates streams for receiving and sending data
 * from and to the client. It also handles ongoing games in which the associated client is participating.
 * Informatoion of a ongoing game is stored and updated with the class {@link GameData}.
 * To be able to continously listen for incomming data this class extends {@link Thread}.
 * @author johanlindeborg
 *
 */
public class ClientHandler extends Thread {
	private Socket clientSocket;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private boolean isInGame = false;
	private Boolean listeningForMessages = true;

	private GameServer server;
	private GameData gameData;
	private String userName;

	/**
	 * The constructor method of this class.
	 * @param clientSocket The socket used to create streams for communication.
	 * @param server The {@link GameServer} that creates this clienthandler.
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
	
	/**
	 * The run method for this class, this method listens for incomming messages and depending on their type, 
	 * sends them to the {@link GameServer} or sets the data in the {@link GameData} class to update the
	 * status of a ongoing game.
	 */
	public void run() {
		Object obj = null;

		while (listeningForMessages){
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
			if (obj instanceof AddToServerListMsg || obj instanceof RequestGameMsg || obj instanceof DisconnectMsg){
				server.processDataFromClient(obj, this);
				
				// Messages concerning a specific ongoing game
			} else {

				if (obj instanceof StartGameMsg){
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
	 * This method is used for communication to the client, to send a message
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

	public void setIsInGame(boolean inGame) {
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

	/**
	 * This method is used when a new game is started. If the gameData object is not equal to null it means that
	 * a game is already running and that a new game cannot be created.
	 * @param gameData
	 */
	public void setGameData(GameData gameData) {
		if (this.gameData == null) {
			this.gameData = gameData;
			System.out.println("ClientHandler for " + userName + ": Received new GameData");
		} else {
			System.out.println("ClientHandler for " + userName + ": Already in game");
		}
	}
	
	public void destroyGameData() {
		gameData = null;
	}
}
