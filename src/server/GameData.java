package server;

import java.awt.geom.Point2D;
import server.ClientHandler;
import sharedFiles.CitiesData;
import sharedFiles.City;
import sharedFiles.EndGameMsg;
import sharedFiles.MapClickMsg;
import sharedFiles.SetupMsg;
import sharedFiles.NewRoundMsg;
import sharedFiles.ResultMsg;

/**
 * A class for keeping track of active games and the data needed to run them.
 * After both clients are connected and have started a game together, this class
 * should be used by two {@link ClientHandler} instances to send the right data to 
 * the clients at the right time.
 * 
 * @author johanlindeborg
 *
 */
public class GameData {
	private CitiesData cities;
	private City currentCity;

	private Point2D mapCenter;
	private double zoomLevel;
	private String mapName;

	private int totalRounds;
	private int currentRound = 0;

	private ClientHandler player1;
	private ClientHandler player2;

	private String player1Str;
	private String player2Str;
	
	private double totScorePl1;
	private double totScorePl2;

	private boolean pl1HasClicked;
	private boolean pl2HasClicked;

	private MapClickMsg pl1MapClick = null;
	private MapClickMsg pl2MapClick = null;

	private boolean pl1Ready = false;
	private boolean pl2Ready = false;

	/**
	 * The constructor method for this class, used to setup all necessary data to run a game.
	 * @param player1 the {@link ClientHandler} for player 1.
	 * @param player2 the {@link ClientHandler} for player 2.
	 * @param totalRounds The number of rounds to be played.
	 * @param mapCenter The geographical center of the map to be displayed.
	 * @param zoomLevel The level of zoom to be used on the map.
	 * @param mapName The name of this map.
	 */
	public GameData(ClientHandler player1, ClientHandler player2, int totalRounds, Point2D mapCenter, double zoomLevel,
			String mapName) {
		this.player1 = player1;
		this.player2 = player2;

		player1Str = player1.getUserName();
		player2Str = player2.getUserName();

		this.mapCenter = mapCenter;
		this.zoomLevel = zoomLevel;
		this.totalRounds = totalRounds;
		this.mapName = mapName;
		this.cities = new CitiesData(mapName);
	}

	/**
	 * This method is used when the clienthandler sends information about the new game setup.
	 */
	public void setupGame() {
		player1.sendToClient(new SetupMsg(mapName, totalRounds, mapCenter, zoomLevel, player1Str, player2Str));
		player2.sendToClient(new SetupMsg(mapName, totalRounds, mapCenter, zoomLevel, player2Str, player1Str));
		startRound();
	}
	
	/**
	 * This method is used by a {@link ClientHandler} to set the status of their client to
	 * ready when a certain message is received by the ClientHandler.
	 * @param player The ClientHandler whose client is ready to start the game.
	 * @param ready The boolean to indicate if the player is ready or not.
	 */
	public void setPlayerReady(ClientHandler player, boolean ready) {
		if (player == player1){
			pl1Ready = ready;

		} else if (player == player2){
			pl2Ready = ready;
		} else {
			System.out.println("GameData ERROR: ClientHandlers dont match");
		}
	}
	
	/**
	 * This method is called by both clienthandlers when they have received a message that their client is ready
	 * to start the game, when this message is received the status of their clienthandler change to ready.
	 * when this method is called and both players are ready the game starts.
	 */
	private void startGame() {
		while (pl1Ready == false || pl2Ready == false) {

		}

		startRound();
	}
	/**
	 * This method handles the logic of one whole round and sends the relevant information to
	 * the clients.
	 */
	public void startRound() {

		pl1HasClicked = false;
		pl2HasClicked = false;
		currentRound++;

		currentCity = cities.getRandomCity();
		System.out.println("GameData: new City: "+currentCity.getName());

		if (currentRound == 1){
			NewRoundMsg msg = new NewRoundMsg(currentRound, currentCity);
			msg.setIsFirstRound(true);

			player1.sendToClient(msg);
			player2.sendToClient(msg);
		} else {
			player1.sendToClient(new NewRoundMsg(currentRound, currentCity));
			player2.sendToClient(new NewRoundMsg(currentRound, currentCity));
		}

		System.out.print("GameData: Sent NewRoundMsg to players");
	}
	
	/**
	 * This method is called when a {@link ClientHandler}
	 * receives a {@link mapClickMsg}. This message contains information about a
	 * input from a client during an active game.
	 * @param mapClick
	 */
	public void updateMapClick(MapClickMsg mapClick) {

		if (mapClick.getSender().equals(player1.getUserName())) {
			pl1MapClick = mapClick;

			pl1HasClicked = true;
			
		} else if (mapClick.getSender().equals(player2.getUserName())) {
			pl2MapClick = mapClick;

			pl2HasClicked = true;
		}
		if (pl1HasClicked && pl2HasClicked) {

			processRoundInput();
		}
		
	}

	/**
	 * This method is used to process the input from the clients during an
	 * active round of the game.
	 */
	private void processRoundInput() {
		double distPl1 = 0;
		double distPl2 = 0;

		int scorePl1 = 0;
		int scorePl2 = 0;

		Point2D pointPl1 = null;
		Point2D pointPl2 = null;

		if (pl1MapClick.getInTime()) {
			distPl1 = calcResults(pl1MapClick.getClickPoint(), currentCity.getPoint());
			pointPl1 = pl1MapClick.getClickPoint();

			System.out.println("GameData: sent ResultMsg to " + player1.getUserName());
			totScorePl1 += distPl1;
		}

		if (pl2MapClick.getInTime()) {
			distPl2 = calcResults(pl2MapClick.getClickPoint(), currentCity.getPoint());
			pointPl2 = pl2MapClick.getClickPoint();
			
			totScorePl2 += distPl2;
		}
		
		ResultMsg msgPl1 = new ResultMsg(distPl1, scorePl1, distPl2, scorePl2, pointPl2);
		ResultMsg msgPl2 = new ResultMsg(distPl2, scorePl2, distPl1, scorePl1, pointPl1);

		player1.sendToClient(msgPl1);
		player2.sendToClient(msgPl2);
		System.out.println("GameData: sent ResultMsg to " + player2.getUserName() + ", " + player1.getUserName());
		System.out.println("GameData: currentRound: " + currentRound + ", totalRounds: " + totalRounds);

		// starts a new Round
		if (currentRound < totalRounds){
			startRound();
			
		// Game finished
		} else {
			
			if( totScorePl1 < totScorePl2){
				EndGameMsg msgEnd = new EndGameMsg(player1Str, player2Str, totScorePl1, totScorePl2, player1Str );
				
				player1.sendToClient(msgEnd);
				player2.sendToClient(msgEnd);
				
				player1.destroyGameData();
				player2.destroyGameData();
				System.out.println("GameData sent EndGameMsg");
			
			} else {
				EndGameMsg msgEnd = new EndGameMsg(player1Str, player2Str, totScorePl1, totScorePl2, player2Str );
				
				player1.sendToClient(msgEnd);
				player2.sendToClient(msgEnd);
				
				player1.destroyGameData();
				player2.destroyGameData();
				System.out.println("GameData sent EndGameMsg");
			}
		}
	}

	
	private double calcResults(Point2D.Double guess, Point2D.Double cityLatLng) {
		double lat1 = guess.getX();
		double lng1 = guess.getY();

		double lat2 = cityLatLng.getX();
		double lng2 = cityLatLng.getY();

		double theta = lng1 - lng2;
		double dist = Math.sin(degToRad(lat1)) * Math.sin(degToRad(lat2))
				+ Math.cos(degToRad(lat1)) * Math.cos(degToRad(lat2)) * Math.cos(degToRad(theta));
		dist = Math.acos(dist);

		dist = radToDeg(dist);
		dist = dist * 60 * 1.1515;
		// Distance in km
		dist = dist * 1.609344;

		return Math.round(dist);
	}

	private double degToRad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double radToDeg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}