package sharedFiles;

public class NewRoundMsg extends Message {
	
	private City city;
	private int round;
	
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

}
