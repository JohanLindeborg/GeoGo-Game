package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.swing.MapView;

public class GameControllerSP  {
	
	private CreateMap createMap;
	private GameWindow gameWindow;
	private GameInfoWindow gameInfoWindow;
	//private GameLogicSP gameLogic;
	private MapView gameMapView;
	private Map map;
	private CitiesData citiesData;
	
	private String currentMap;
		
	
	public GameControllerSP(CreateMap createMap, String currentMap) {
		
		this.currentMap = currentMap;
		this.createMap = createMap;
		gameMapView = createMap.getMapView();
		map = gameMapView.getMap();
		
		citiesData = new CitiesData(currentMap);
		
		gameWindow = new GameWindow(gameMapView);
		gameInfoWindow = new GameInfoWindow();
		
		//gameLogic = new GameLogicSP(gameMapView,citiesForMap);
		initGame();
	}
	
	private void initGame() {
		City currentCity = citiesData.getRandomCity();
		gameInfoWindow.setClickCityLbl(currentCity.getName());
	}
	
	
	
	

}
