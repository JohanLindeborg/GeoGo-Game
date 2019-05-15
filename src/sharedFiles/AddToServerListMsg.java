package sharedFiles;

public class AddToServerListMsg extends Message {

	private static final long serialVersionUID = 1L;

	private String username;

	public AddToServerListMsg(String username) {
		super(username);
		
	}

}
