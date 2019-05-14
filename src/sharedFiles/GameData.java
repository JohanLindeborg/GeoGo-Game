package sharedFiles;

import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;

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
	private String player1;
	private String player2;  // Needs to be the same as a connected client
	
	private boolean gameStarted = false;
	private boolean gameFinished = false;
	
	//Map
	private Point mapCenter;
	private double zoomLevel;
	
	private int totalRounds;
	private int currentRound = 0;
	
	private HashMap<String, Round> rounds = new HashMap<String, Round>();
	
	/**
	 * 
	 * @param player1 player1
	 * @param player2 player2
	 * @param totalRounds total rounds
	 * @param mapCenter map center
	 * @param zoomLevel zoom level
	 */
	public GameData(String player1, String player2, int totalRounds, Point mapCenter, double zoomLevel) {
		this.player1 = player1;
		this.player2 = player2;
		
		this.mapCenter = mapCenter;
		this.zoomLevel = zoomLevel;
		this.totalRounds = totalRounds;
		
		initRoundsMap();
	}
	
	private void initRoundsMap() {
		for(int i = 0; i < totalRounds; i++) {
			rounds.put(String.valueOf(i), new Round(i));
		}
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
	
	public void acceptGame() {
		gameStarted = true;
	}
	
	public boolean getGameStarted() {
		return gameStarted;
	}
	
	public boolean getGameFinished() {
		return gameFinished;
	}

	public double getZoomLevel() {
		return zoomLevel;
	}

	
	
	private class Round{
		
		private int round;
		private boolean plyr1HasClicked = false;
		private boolean plyr2HasClicked = false;
		
		private Round(int round) {
			this.round = round;
			
		}
		
		private int getRound() {
			return round;
		}
		
		private void setPlyr1HasClicked() {
			plyr1HasClicked = true;
		}
		
		private void setPlyr2HasClicked() {
			plyr2HasClicked = true;
		}
		
		private boolean checkBothPlyrsClicked() {
			if(plyr1HasClicked == true && plyr2HasClicked == true) {
				return true;
			}
			return false;
		}
	}
	
	

}
