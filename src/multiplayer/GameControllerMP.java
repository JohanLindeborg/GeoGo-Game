package multiplayer;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import com.teamdev.jxmaps.LatLng;
import gui.EndGameMenu;
import gui.GameWindow;
import gui.MultiPlayerMenu;
import gui.StartMenu;
import sharedFiles.AddToServerListMsg;
import sharedFiles.City;
import sharedFiles.CountDownTimer;
import sharedFiles.DisconnectMsg;
import sharedFiles.EndGameMsg;
import sharedFiles.MapClickMsg;
import sharedFiles.SetupMsg;
import sharedFiles.Message;
import sharedFiles.NewRoundMsg;
import sharedFiles.RequestGameMsg;
import sharedFiles.ResultMsg;
import sharedFiles.StartGameMsg;
import sharedFiles.UpdateConnectedUsersMsg;

public class GameControllerMP extends Thread {
	
	private MultiPlayerMenu menuMP;
	private String userName;
	
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	private CountDownTimer gameTimer = new CountDownTimer(this, 16, true);
	private MapHolderMP mapHolder;
	private String mapName;

	private StartMenu startMenu;
	private GameWindow gameWindow;
	private GameInfoWindowMP gameInfoWindow;

	private City currentCity;
	private int scorePl1 = 0;
	private int scorePl2 = 0;

	public GameControllerMP(String userName, String serverIp, StartMenu startMenu) {

		this.startMenu = startMenu;
		this.userName = userName;
		menuMP = new MultiPlayerMenu(this);
		startMenu.enableMultiBtn(false);

		try {
			this.socket = new Socket(serverIp, 8888);

			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

			sendMsg(new AddToServerListMsg(userName));
			start();

		} catch (UnknownHostException e) {
			System.out.println("Unknown Host Exception:");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Excception:");
			e.printStackTrace();
		}
	}

	public void run() {
		Object obj = null;

		while (true){
			try {
				obj = ois.readObject();

			} catch (ClassNotFoundException e) {
				System.out.println("Class Not Found Exception:");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("IO Exception:");
				e.printStackTrace();
			}

			if (obj instanceof SetupMsg){
				SetupMsg msg = (SetupMsg) obj;
				System.out.println(userName + " Received SetupMsg");

				mapName = msg.getMapName();

				Point2D point = msg.getMapCenter();
				LatLng latlng = new LatLng(point.getX(), point.getY());

				mapHolder = new MapHolderMP(msg.getTotalRounds(), msg.getZoomLevel(), latlng, mapName, this);

				gameWindow = new GameWindow(mapHolder.getMapView());
				gameInfoWindow = new GameInfoWindowMP(msg.getTotalRounds());

				gameInfoWindow.setPlayers(msg.getPl1(), msg.getPl2());

				gameWindow.requestFocus();


			} else if (obj instanceof NewRoundMsg){
				NewRoundMsg msg = (NewRoundMsg) obj;

				if (msg.getIsFirstRound()){
					gameInfoWindow.setInfoLbl("Game Starting...");

					try {
						this.sleep(5000);
						gameInfoWindow.removeInfoLbl();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else {
					gameInfoWindow.setInfoLbl("Round Complete");
					try {
						this.sleep(5000);
						gameInfoWindow.removeInfoLbl();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// Clean map
					mapHolder.removeMarkers();
				}

				currentCity = msg.getCity();
				
				System.out.println("gameinfowindow Check: GmaCont 158 "+gameInfoWindow.toString());
				
				mapHolder.setClickedThisRound(false);
				gameTimer.startTimer();

				gameInfoWindow.setClickCityLbl(currentCity.getName());
				gameInfoWindow.setCurrentRound(msg.getRound());

				System.out.println(userName + " Received NewRoundMsg");

			}
			// Shows results and pauses the game temporarily
			else if (obj instanceof ResultMsg) {
				ResultMsg msg = (ResultMsg) obj;

				System.out.println(userName + " Received ResultMsg");

				scorePl1 += msg.getScorePl1();
				scorePl2 += msg.getScorePl2();

				String distPl1 = Double.toString(msg.getDistPl1());
				String distPl2 = Double.toString(msg.getDistPl2());

				// Otherplayer did not click in time
				if (msg.getPointPl2() == null) {
					// Show own score+distance
					gameInfoWindow.setScoreLbls(Integer.toString(scorePl1), Integer.toString(scorePl2));
					gameInfoWindow.setDistanceLbls(distPl1, distPl2);
					
				} else {
					// show own score + distance
					// Show other player score+distance+marker
					gameInfoWindow.setScoreLbls(Integer.toString(scorePl1), Integer.toString(scorePl2));
					gameInfoWindow.setDistanceLbls(distPl1, distPl2);

					Point2D pointPl2 = msg.getPointPl2();

					mapHolder.placeMarkerPl2(new LatLng(pointPl2.getX(), pointPl2.getY()));
				}
			}
			else if(obj instanceof UpdateConnectedUsersMsg){
				UpdateConnectedUsersMsg msg = (UpdateConnectedUsersMsg) obj;
				
				System.out.println(userName + " Received update user msg");
				System.out.println("Connected users "+msg.getUsers().toString());
				
				menuMP.updateUsers(msg.getUsers());
				
			} else if(obj instanceof EndGameMsg) {
				EndGameMsg msg = (EndGameMsg) obj;
				
				new EndGameMenu(msg.getPlayer1(), msg.getPlayer2(), msg.getScorePl1(), msg.getScorePl2(), msg.getWinner(), this);
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
		System.out.println("Controller for " + userName + " registered and sent Mapclick in time");
	}

	public void onMapClickOutOfTime() {
		gameTimer.stopTimer();
		mapHolder.placeCityPos(currentCity.getPoint(), currentCity.getName());

		Point2D.Double clickLatLng = new Point2D.Double(0, 0);
		MapClickMsg msg = new MapClickMsg(userName, clickLatLng, false);

		sendMsg(msg);
		System.out.println("Controller for " + userName + " registered and sent Mapclick out of time");
	}

	private void sendMsg(Message msg) {
		try {
			oos.writeObject(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestGame(Point2D.Double mapCenter, double zoomLevel, String otherPlayer, int rounds, String mapName) {

		RequestGameMsg msg = new RequestGameMsg(rounds, mapName, mapCenter, zoomLevel, userName, otherPlayer);
		sendMsg(msg);
		System.out.println(userName + "Sent RequestMapMsg");

		// Sets player is ready to play
		sendMsg(new StartGameMsg(userName));
	}

	public void updateGameTimer(int cntDown) {
		gameInfoWindow.setTimerLbl(cntDown);
		mapHolder.updateTimer(cntDown);

		// If the player never clicks during the time
		if (cntDown <= 0 && mapHolder.getClickedThisRound() == false){
			onMapClickOutOfTime();
			System.out.println("GameControllerMP registered mapclick out of time");
		}
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void disconnect() {
		sendMsg(new DisconnectMsg(userName));
		startMenu.enableMultiBtn(true);
	}
	
	public void closeGameWindows() {
		gameInfoWindow.destroy();
		gameWindow.dispose();
	}

}