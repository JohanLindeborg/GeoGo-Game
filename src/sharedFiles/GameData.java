package sharedFiles;

import java.awt.Point;
import java.io.Serializable;

import server.ClientHandler;

/**
 * A class for keeping track of active games and the data needed to run them.
 * After both clients are connected and have started a game together,
 * this class should be used by the GameServer to determine where messages 
 * should be sent.
 * 
 * @author johanlindeborg
 *
 */
public class GameData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Players
	String player1;
	String player2;
	
	
	//Map
	private Point mapCenter;
	private double zoomLevel;
	
	
	public GameData(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public String getPlayer1() {
		return player1;
	}
	
	public String getPlayer2() {
		return player2;
	}

	public Point getMapCenter() {
		return mapCenter;
	}

	public void setMapCenter(Point mapCenter) {
		this.mapCenter = mapCenter;
	}

	public double getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(double zoomLevel) {
		this.zoomLevel = zoomLevel;
	}
	
	

}
