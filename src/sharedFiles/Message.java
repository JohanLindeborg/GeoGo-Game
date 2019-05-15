package sharedFiles;

import java.io.Serializable;

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
