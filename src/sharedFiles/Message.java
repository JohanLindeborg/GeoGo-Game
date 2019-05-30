package sharedFiles;

import java.io.Serializable;

/**
 * This class is used for sending information between the {@link ClientHandler}, {@link GameServer}
 * and the {@link GameControllerMP}. All other classes in this package ending with "Msg" extends this class.
 * @author johanlindeborg
 *
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sender;
	
	public Message(String sender) {
		this.sender = sender;
	}
	
	public String getSender() {
		return sender;
	}

}
