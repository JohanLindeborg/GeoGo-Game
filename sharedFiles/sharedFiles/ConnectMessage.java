package sharedFiles;

public class ConnectMessage extends Message{

	private static final long serialVersionUID = 1L;
	
	
	private String username;
	
	public ConnectMessage(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

}
