package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.swing.MapView;

public class GameControllerSP  {
	
	private CreateMap createMap;
	
	private GameWindow gameWindow;
	private GameInfoWindow gameInfoWindow;
	private WindowListenerSP windowListener;
	
	private MapView gameMapView;
	private Map map;
	private CitiesData citiesData;
	private City currentCity;
	private int totalRounds;
	private int currentRound;
	
	private String currentMap;
	
	private CountDownTimer countDownTimer;
		
	
	public GameControllerSP(double zoomLvl, LatLng latlng, String currentMap, int totalRounds) {
		
		windowListener = new WindowListenerSP(this);
		
		currentRound = 0;
		this.countDownTimer = new CountDownTimer(this);
		
		this.totalRounds = totalRounds;
		this.currentMap = currentMap;		
		this.createMap = new CreateMap(totalRounds, zoomLvl, latlng, currentMap, this);
		gameMapView = createMap.getMapView();
		map = gameMapView.getMap();
		
		citiesData = new CitiesData(currentMap);
		
		gameWindow = new GameWindow(gameMapView);
		gameInfoWindow = new GameInfoWindow(totalRounds, this);
		
		//Sets this window as the "active" one
		gameWindow.requestFocus();
		
		gameWindow.addWindowListener(windowListener);
		gameInfoWindow.getFrame().addWindowListener(windowListener);
		
		
		startNewRound();
	}
	
	//what the controller should do when user has clicked on the map within timerLimit
	public City onMapClickInTime(LatLng clickLatLng) {
		countDownTimer.stopTimer();
		
		double distance = getDistance(clickLatLng, currentCity.getLatLng());
		gameInfoWindow.setDistanceLbl(Double.toString(distance));
		
		if(currentRound < totalRounds) {
			gameInfoWindow.showContinueLbl("Click on map to continue");
		}
		else {
			gameInfoWindow.showContinueLbl("Game Finished, close window to continue");
		}		
		//returns the "target city" to the map
		return currentCity;
	}
	//what the controller should do when user has clicked on the map when timerLimit = 0
	public City onMapClickOutOfTime() {
		countDownTimer.stopTimer();
		
		if(currentRound < totalRounds) {
			gameInfoWindow.showContinueLbl("Click on map to continue");
		}
		else {
			gameInfoWindow.showContinueLbl("Game Finished, close window to continue");
		}
		//returns the "target city" to the map
		return currentCity;
	}
	
	public void startNewRound() {
		currentRound ++;
		gameInfoWindow.setCurrentRound(currentRound);
		
		currentCity = citiesData.getRandomCity();

		gameInfoWindow.setClickCityLbl(currentCity.getName());
		gameInfoWindow.removeContinueLbl();
		gameInfoWindow.removeDistanceLbl();

		countDownTimer.startTimer();
	}
	
	
	public void exitGame() {		
		gameWindow.dispose();
		gameInfoWindow.getFrame().dispose();
		
	}
	
	//Temporary method, should be replaced, allows access to change gui from CreateMap
	public void rmvContinueLblInInfo() {
		gameInfoWindow.removeContinueLbl();
	}
	
	public void updateCountDown(int cntDown) {
		gameInfoWindow.setTimerLbl(cntDown);
		createMap.updateTimer(cntDown);
		
	}
	
	public int getCurrentRound() {
		return currentRound;
	}
	
	
	
	
	
	//Returns distance in km between two coordinates
	private double getDistance(LatLng latlong1, LatLng latlong2) {
		double lat1 = latlong1.getLat();
		double lng1 = latlong1.getLng();
		
		double lat2 = latlong2.getLat();
		double lng2 = latlong2.getLng();
		
		double theta = lng1 - lng2;
		double dist = Math.sin(degToRad(lat1)) * Math.sin(degToRad(lat2)) + Math.cos(degToRad(lat1)) * Math.cos(degToRad(lat2)) * Math.cos(degToRad(theta));
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
	
	
	
	


