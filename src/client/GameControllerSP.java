package client;

import com.teamdev.jxmaps.swing.MapView;

public class GameControllerSP {
	
	private CreateMap createMap;
	private GameWindow gameWindow;
	private GameInfoWindow gameInfoWindow;
	private GameLogicSP gameLogic;
	private MapView map;
	
	
	public GameControllerSP(CreateMap createMap) {
		
		this.createMap = createMap;
		map = createMap.getMap();
		
		gameLogic = new GameLogic();
		
		gameWindow = new GameWindow(map);
		gameInfoWindow = new GameInfoWindow();
		
	}

}
