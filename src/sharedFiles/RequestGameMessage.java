package sharedFiles;

public class RequestGameMessage extends Message {

	
	private static final long serialVersionUID = 1L;
	
	private String otherClientName;
	
	public RequestGameMessage(String otherClientUserName) {
		otherClientName = otherClientUserName;
	}

	public String getOtherClientName() {
		return otherClientName;
	}


}
