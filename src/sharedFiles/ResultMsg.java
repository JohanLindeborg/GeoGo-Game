package sharedFiles;

public class ResultMsg extends Message {
	
	private double distance;
	private int score;
	
	public ResultMsg(double distance) {
		super("server");
		this.distance = distance;
		
	}
	
	public double getDistance() {
		return distance;
	}

}
