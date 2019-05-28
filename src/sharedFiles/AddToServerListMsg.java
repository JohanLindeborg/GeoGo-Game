package sharedFiles;

public class AddToServerListMsg extends Message {
	private static final long serialVersionUID = 1L;

	public AddToServerListMsg(String username) {
		super(username);
		
	}

}
