package sharedFiles;

import java.io.Serializable;
import java.util.ArrayList;

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
