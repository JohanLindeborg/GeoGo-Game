package gameLogicMP;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.swing.MapView;

import gameLogicSP.CountDownTimer;
import gameLogicSP.MapHolderSP;
import gui.GameInfoWindow;
import gui.GameWindow;
import sharedFiles.AddToServerListMsg;
import sharedFiles.CitiesData;
import sharedFiles.City;
import sharedFiles.GameData;
import sharedFiles.MapMsg;
import sharedFiles.Message;
import sharedFiles.NewRoundMsg;
import sharedFiles.RequestGameMsg;
import sharedFiles.StartGameMsg;

public class GameControllerMP extends Thread{
	
	private String userName;
	
	private Socket socket;
	private int port;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	
	private CountDownTimer timer = new CountDownTimer(this);
	private MapHolderMP mapHolder;
	private String mapName;
	
	private GameWindow gameWindow;
	private GameInfoWindowMP gameInfoWindow;
	
	private CitiesData cities;
	private City currentCity;
		
	
	public GameControllerMP(String userName) {
		
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
		
		addToServerList(userName);
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
			
			if(obj instanceof MapMsg) {
				MapMsg msg = (MapMsg) obj;
				System.out.println(userName + "Received MapMsg");
				
				mapName = msg.getMapName();
				
				Point2D point = msg.getMapCenter();
				LatLng latlng = new LatLng(point.getX(),point.getY());
				
				mapHolder = new MapHolderMP(msg.getTotalRounds(), msg.getZoomLevel(),latlng, mapName, this);
				
				gameWindow = new GameWindow(mapHolder.getMapView());
				gameInfoWindow = new GameInfoWindowMP(msg.getTotalRounds(), this);
				
				//Game setup complete, 
				//sendMsg(new StartGameMsg(userName));
				//System.out.println("Clienthandler for "+userName +" sent StartGameMsg");
			}
			else if(obj instanceof NewRoundMsg) {
				NewRoundMsg msg = (NewRoundMsg) obj;
				
				timer.startTimer();
				//Start Timer
				gameInfoWindow.setClickCityLbl(msg.getCity().getName());
				gameInfoWindow.setCurrentRound(msg.getRound());
				
			}
			
			
		}
	}
	
	
	
	private void addToServerList(String username) {
		
		try {
			oos.writeObject(new AddToServerListMsg(username));
		} catch (IOException e) {
			System.out.println("IO Exception:");
			e.printStackTrace();
		}
	}
	
	private void sendMsg(Message msg) {
		try {
			oos.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void requestGame(Point2D.Double mapCenter, double zoomLevel, String otherPlayer, int rounds, String mapName) {
		
		RequestGameMsg msg = new RequestGameMsg(rounds, mapName, mapCenter, zoomLevel, userName, otherPlayer);
		
		sendMsg(msg);
		System.out.println(userName+ "Sent RequestMapMsg");
		
		//Sets player is 
		sendMsg(new StartGameMsg(userName));
	}

	public void updateCountDown(int countdown) {
		gameInfoWindow.setTimerLbl(countdown);
	}
	
}