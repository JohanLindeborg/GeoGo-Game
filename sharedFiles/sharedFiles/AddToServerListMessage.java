package sharedFiles;

public class AddToServerListMessage extends Message{

	private static final long serialVersionUID = 1L;
	
	
	private String username;
	
	public AddToServerListMessage(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

}
