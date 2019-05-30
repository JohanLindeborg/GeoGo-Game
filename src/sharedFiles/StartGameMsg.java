package sharedFiles;

/**
 * This message is sent from the clients when they are ready to start a game and the when both
 * are ready the server will start the game. The message is used as a form of making sure the players 
 * are synchronized to som extent.
 * @author johanlindeborg
 *
 */
public class StartGameMsg extends Message{
	private static final long serialVersionUID = 1L;

	public StartGameMsg(String sender) {
		super(sender);
	}

}
