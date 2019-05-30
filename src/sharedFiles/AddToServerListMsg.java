package sharedFiles;

/**
 * This message is used for making the server add 
 * another userName in its list of connected users.
 * @author johanlindeborg
 *
 */
public class AddToServerListMsg extends Message {
	private static final long serialVersionUID = 1L;

	public AddToServerListMsg(String username) {
		super(username);
		
	}

}
