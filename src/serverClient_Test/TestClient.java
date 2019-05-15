package serverClient_Test;

import java.awt.BorderLayout;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.swing.MapView;

import gameLogicSP.MapHolderSP;
import sharedFiles.AddToServerListMsg;
import sharedFiles.GameData;
import sharedFiles.MapMsg;
import sharedFiles.Message;
import sharedFiles.RequestGameMsg;

public class TestClient extends Thread{
	
	private String userName;
	
	private Socket socket;
	private int port;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private MapHolderSP map;
	
	private GameData currentGame;
	private boolean inGame = false;
	
	
	public TestClient(String userName) {
		
		this.userName = userName;
		this.port = 4242;
		
		try {
			this.socket = new Socket("localhost", 4242);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		
		} catch (UnknownHostException e) {
			System.out.println("Unknown Host Exception:");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Excception:");
			e.printStackTrace();
		}
		
		start();
	}
	
	public void run() {
		Object obj = null;
		
		while(true) {
			try {
				obj = ois.readObject();
				
			}catch(ClassNotFoundException e) {
				System.out.println("Class Not Found Exception:");
				e.printStackTrace();
			}catch(IOException e) {
				System.out.println("IO Exception:");
				e.printStackTrace();
			}
			
			
			if(obj instanceof GameData) {
				currentGame = (GameData) obj;
				System.out.println("Client "+userName+" received GameData");
				
				//Game is active
				if(currentGame.getGameStarted()) {
					if(currentGame.getCurrentRound() == 1) {
						System.out.println(userName +" received new game: "+currentGame.toString());
					}
					
				}
				//Client has not acceptedGame
				else{
					//TODO: what happens on Game request goes here.
					//Auto accepts
					currentGame.acceptGame();
					System.out.println(userName+ " accepted new game");
					sendGameData(currentGame);
				}
				
			}
		}
	}
	
	
	
	public void connectToServer() {
		
		try {
			oos.writeObject(new AddToServerListMsg(userName));
		} catch (IOException e) {
			System.out.println("IO Exception:");
			e.printStackTrace();
		}
	}
	
	public void sendGameData(GameData gameData) {
		try {
			oos.writeObject(gameData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startNewGame(Point mapCenter, double zoomLevel, String otherPlayer, int rounds) {
		GameData gameData = new GameData(userName, otherPlayer,rounds, mapCenter, zoomLevel);
		System.out.println(gameData.getPlayer1()+"player1"+gameData.getPlayer2()+"player2");
		try {
			oos.writeObject(gameData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void displayMap(MapView map) {
		JFrame frame = new JFrame("ClientMap");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(map, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		TestClient johan = new TestClient("Johan");
		TestClient adam = new TestClient("Adam");
		
		
		johan.connectToServer();
		adam.connectToServer();
		
		johan.startNewGame(new Point(64, 20), 4.9, "Adam", 10);
	}
	
}
