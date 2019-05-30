package sharedFiles;

/**
 * This message is used for making the server take the correct actions to 
 * disconnect a client.
 * @author johanlindeborg
 *
 */
public class DisconnectMsg extends Message{
	private static final long serialVersionUID = 1L;

	public DisconnectMsg(String sender) {
		super(sender);
	}

}
