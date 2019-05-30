package sharedFiles;

/**
 * This message is sent from the server to client when an ongoing game has ended. It contains 
 * data regarding the result to be displayed.
 * @author johanlindeborg
 *
 */
public class EndGameMsg extends Message {
	
	private static final long serialVersionUID = 1L;
	private String player1;
	private String player2;
	private double scorePl1;
	private double scorePl2;
	private String winner;

	public EndGameMsg(String player1, String player2, double scorePl1, double scorePl2, String winner) {
		super("server");
		this.player1 = player1;
		this.player2 = player2;
		this.scorePl1 = scorePl1;
		this.scorePl2 = scorePl2;
		this.winner = winner;
	}
	
	public String getPlayer1() {
		return player1;
	}
	
	public String getPlayer2() {
		return player2;
	}
	
	public double getScorePl1() {
		return scorePl1;
	}
	
	public double getScorePl2() {
		return scorePl2;
	}
	
	public String getWinner() {
		return winner;
	}

}
