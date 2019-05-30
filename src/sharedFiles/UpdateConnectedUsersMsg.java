package sharedFiles;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This message is sent from the server when the list of connected users has been updated. It contains
 * a list of all currently connected users.
 * @author johanlindeborg
 *
 */
public class UpdateConnectedUsersMsg extends Message implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> users;
	
	public UpdateConnectedUsersMsg(ArrayList<String> users) {
		super("server");
		
		this.users = users;
	}

	public ArrayList<String> getUsers() {
		return users;
	}
}
