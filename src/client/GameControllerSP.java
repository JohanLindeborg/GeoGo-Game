package client;

import com.teamdev.jxmaps.swing.MapView;

public class GameControllerSP {
	
	private CreateMap createMap;
	private GameWindow gameWindow;
	private GameInfoWindow gameInfoWindow;
	private GameLogicSP gameLogic;
	private MapView gameMapView;
	
	//Lägg in städer i GameLogic, gärna beroende på val
	//av karta, lägg till lyssnare
	public GameControllerSP(CreateMap createMap) {
		
		this.createMap = createMap;
		gameMapView = createMap.getMap();
		
		gameLogic = new GameLogicSP(gameMapView,);
		
		gameWindow = new GameWindow(gameMapView);
		gameInfoWindow = new GameInfoWindow();
		
	}

}
