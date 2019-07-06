package sharedFiles;

/**
 * This message is sent from the server to the clients when a new round of a game is going to start.
 * It contains information on the next city to find on the map.
 * @author johanlindeborg
 *
 */
public class NewRoundMsg extends Message {
	private static final long serialVersionUID = 1L;
	
	private City city;
	private int round;
	private boolean isFirstRound = false;
	
	public NewRoundMsg(int round, City city) {
		super("server");
		this.city = city;
		this.round = round;
		
	}
	
	public int getRound() {
		return round;
	}
	
	public City getCity() {
		return city;
	}
	
	public void setIsFirstRound(boolean isFirstRound) {
		this.isFirstRound = isFirstRound;
	}
	
	public boolean getIsFirstRound() {
		return isFirstRound;
	}

}
