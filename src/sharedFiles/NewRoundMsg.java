package sharedFiles;

public class NewRoundMsg extends Message {
	
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
