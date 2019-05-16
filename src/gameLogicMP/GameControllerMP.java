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
import sharedFiles.MapClickMsg;
import sharedFiles.SetupMsg;
import sharedFiles.Message;
import sharedFiles.NewRoundMsg;
import sharedFiles.RequestGameMsg;
import sharedFiles.ResultMsg;
import sharedFiles.StartGameMsg;

public class GameControllerMP extends Thread{
	
	private String userName;
	
	private Socket socket;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	
	private CountDownTimer gameTimer = new CountDownTimer(this, 16, true);
	private CountDownTimer resultsTimer = new CountDownTimer(this, 5, false);
	private int resultsTimerCountDown;
	
	private MapHolderMP mapHolder;
	private String mapName;
	
	private GameWindow gameWindow;
	private GameInfoWindowMP gameInfoWindow;
	
	private City currentCity;
	private int scorePl1 = 0;
	private int scorePl2 = 0;
		
	
	public GameControllerMP(String userName) {
		
		this.userName = userName;
		
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
			
			if(obj instanceof SetupMsg) {
				SetupMsg msg = (SetupMsg) obj;
				System.out.println(userName + "Received MapMsg");
				
				mapName = msg.getMapName();
				
				Point2D point = msg.getMapCenter();
				LatLng latlng = new LatLng(point.getX(),point.getY());
				
				mapHolder = new MapHolderMP(msg.getTotalRounds(), msg.getZoomLevel(),latlng, mapName, this);
				
				gameWindow = new GameWindow(mapHolder.getMapView());
				gameInfoWindow = new GameInfoWindowMP(msg.getTotalRounds());
				
				gameInfoWindow.setPlayers(msg.getPl1(), msg.getPl2());
				
				gameWindow.requestFocus();
				
				
				//Game setup complete, 
				//sendMsg(new StartGameMsg(userName));
				//System.out.println("Clienthandler for "+userName +" sent StartGameMsg");
			}
			else if(obj instanceof NewRoundMsg) {
				NewRoundMsg msg = (NewRoundMsg) obj;
				
				if(msg.getIsFirstRound()) {
					//Game starts in ...
					//resultsTimer.startTimer();
					gameInfoWindow.setInfoLbl("Game Starting...");
					
					try {
						this.sleep(5000);
						gameInfoWindow.removeInfoLbl();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else {
					//Start timer for results
					gameInfoWindow.setInfoLbl("Round Complete");
					//resultsTimer.startTimer();
					try {
						this.sleep(5000);
						gameInfoWindow.removeInfoLbl();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Clean map	
					mapHolder.removeMarkers();
				}
				
				
				currentCity = msg.getCity();
				
				mapHolder.setClickedThisRound(false);
				gameTimer.startTimer();
				
				gameInfoWindow.setClickCityLbl(currentCity.getName());
				gameInfoWindow.setCurrentRound(msg.getRound());
				
				System.out.println(userName + "Received NewRoundMsg");
				
			}
			//Shows results and pauses the game temporarily
			else if(obj instanceof ResultMsg) {
				ResultMsg msg = (ResultMsg) obj;
				
				System.out.println(userName + "Received ResultMsg");
				
				scorePl1 += msg.getScorePl1();
				scorePl2 += msg.getScorePl2();
				
				String distPl1 = Double.toString(msg.getDistPl1());
				String distPl2 = Double.toString(msg.getDistPl2());
				
				//Otherplayer did not click in time
				if(msg.getPointPl2() == null) {
					//Show own score+distance
					gameInfoWindow.setScoreLbls(Integer.toString(scorePl1), Integer.toString(scorePl2));
					gameInfoWindow.setDistanceLbls(distPl1, distPl2);
				}
				//Show
				else {
					//show own score + distance
					//Show other player score+distance+marker
					gameInfoWindow.setScoreLbls(Integer.toString(scorePl1), Integer.toString(scorePl2));
					gameInfoWindow.setDistanceLbls(distPl1, distPl2);

					Point2D pointPl2 = msg.getPointPl2();
					
					mapHolder.placeMarkerPl2(new LatLng(pointPl2.getX(),pointPl2.getY()));
				}
			}
			
			
		}
	}
	
	public void onMapClickInTime(LatLng latLng) {
		gameTimer.stopTimer();
		
		mapHolder.placeCityPos(currentCity.getPoint(), currentCity.getName());
		mapHolder.placeMarkerPl1(latLng);
		
		Point2D.Double clickLatLng = new Point2D.Double(latLng.getLat(), latLng.getLng());
		MapClickMsg msg = new MapClickMsg(userName, clickLatLng, true);

		sendMsg(msg);
		System.out.println("Controller for "+userName+" registered and sent Mapclick in time");
	}

	
	public void onMapClickOutOfTime() {
		gameTimer.stopTimer();
		
		mapHolder.placeCityPos(currentCity.getPoint(), currentCity.getName());

		Point2D.Double clickLatLng = new Point2D.Double(0, 0);
		
		MapClickMsg msg = new MapClickMsg(userName, clickLatLng, false);

		sendMsg(msg);
		System.out.println("Controller for "+userName+" registered and sent Mapclick out of time");

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
	
	public void updateGameTimer(int cntDown) {
		gameInfoWindow.setTimerLbl(cntDown);
		mapHolder.updateTimer(cntDown);
		
		// If the player never clicks during the time
		if( cntDown <= 0 && mapHolder.getClickedThisRound() == false) {
			
			onMapClickOutOfTime();
			System.out.println("GameControllerMP registered mapclick out of time");
		}

	}
	public void updateResultsTimer(int cntDown) {
		resultsTimerCountDown = cntDown;

	}
	
}