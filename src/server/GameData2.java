package server;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.HashMap;

import com.teamdev.jxmaps.LatLng;

import server.ClientHandler;
import sharedFiles.CitiesData;
import sharedFiles.City;
import sharedFiles.MapClickMsg;
import sharedFiles.MapMsg;
import sharedFiles.NewRoundMsg;
import sharedFiles.ResultMsg;

/**
 * A class for keeping track of active games and the data needed to run them.
 * After both clients are connected and have started a game together,
 * this class should be used by the GameServer to determine where messages 
 * should be sent.
 * 
 * @author johanlindeborg
 *
 */
public class GameData2 {
	
	//Players
	private CitiesData cities;
	private City currentCity;
	
	private Point2D mapCenter;
	private double zoomLevel;
	private String mapName;
	
	private int totalRounds;
	private int currentRound = 1;
	
	private ClientHandler player1;
	private ClientHandler player2;
	
	private boolean plyr1HasClicked;
	private boolean plyr2HasClicked;
	
	private MapClickMsg pl1MapClick = null;
	private MapClickMsg pl2MapClick = null;
	
	private boolean pl1Ready = false;
	private boolean pl2Ready = false;
		
	public GameData2(ClientHandler player1, ClientHandler player2, int totalRounds, Point2D mapCenter, double zoomLevel, String mapName) {
		this.player1 = player1;
		this.player2 = player2;
		
		this.mapCenter = mapCenter;
		this.zoomLevel = zoomLevel;
		this.totalRounds = totalRounds;
		this.mapName = mapName;
		
		this.cities = new CitiesData(mapName);
	}
	
	
	//player2 accepted the game, gameSetup starts.
	public void setupGame() {
		player1.sendToClient(new MapMsg(mapName,totalRounds, mapCenter, zoomLevel));
		player2.sendToClient(new MapMsg(mapName,totalRounds, mapCenter, zoomLevel));
		//startGame();
		runGame();
	}
	
	private void startGame() {
		while(pl1Ready == false || pl2Ready == false){
			
		}
		
		runGame();
	}
	
	public void runGame() {
		double dist;
		
		for(currentRound = 1; currentRound <= totalRounds; currentRound++) {
			plyr1HasClicked = false;
			plyr2HasClicked = false;
			
			currentCity = cities.getRandomCity();
			
			player1.sendToClient(new NewRoundMsg(currentRound,currentCity ));
			player2.sendToClient(new NewRoundMsg(currentRound,currentCity));
			System.out.print("GameData: Sent NewRoundMsg to players");
			
			while(!(plyr1HasClicked || plyr2HasClicked)) {
				
			}
			System.out.println("GameData: Both players clicked on map");
			
			//calcResults
			if(pl1MapClick.getInTime()) {
				dist = calcResults(pl1MapClick.getClickPoint(), currentCity.getPoint());
				player1.sendToClient(new ResultMsg(dist));
			}
			if(pl2MapClick.getInTime()) {
				dist = calcResults(pl2MapClick.getClickPoint(), currentCity.getPoint());
				player2.sendToClient(new ResultMsg(dist));
			}
			
			//Wait to let players see results
		}
	}
	
	public void updateMapClick(MapClickMsg mapClick) {
		
		if(mapClick.getSender().equals(player1.getUserName())) {
			pl1MapClick = mapClick;
			
			plyr1HasClicked = true;
		}
		else {
			pl2MapClick = mapClick;
			
			plyr2HasClicked = true;
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
	
	public void setPlayerReady(ClientHandler player, boolean ready) {
		if(player == player1) {
			pl1Ready = ready;
			
		}else if(player == player2) {
			pl2Ready = ready;
		}
		else {
			System.out.println("GameData ERROR: ClientHandlers dont match");
		}
		
	}
	

	

}