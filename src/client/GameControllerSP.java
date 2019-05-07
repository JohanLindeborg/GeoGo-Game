package client;

import java.util.LinkedList;

import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.swing.MapView;

public class GameControllerSP  {
	
	private CreateMap createMap;
	private GameWindow gameWindow;
	private GameInfoWindow gameInfoWindow;
	private GameLogicSP gameLogic;
	private MapView gameMapView;
	private Map map;
	private CitiesData citiesData;
	
	private String currentMap;
	private LinkedList<City> citiesForMap;
	
	
	public GameControllerSP(CreateMap createMap, String currentMap) {
		
		this.currentMap = currentMap;
		this.createMap = createMap;
		gameMapView = createMap.getMapView();
		map = gameMapView.getMap();
		
		citiesData = new CitiesData(currentMap);
		citiesForMap = citiesData.getCitiesList();
		
		gameWindow = new GameWindow(gameMapView);
		gameInfoWindow = new GameInfoWindow();
		
		gameLogic = new GameLogicSP(gameMapView,citiesForMap);

	}
	
	

}
