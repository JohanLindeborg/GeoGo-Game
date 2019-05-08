package client;

import java.awt.event.ActionListener;
import java.util.HashSet;

public interface ControllerListener  {

//	public void usersChanged();
	//lista med actions finns i listenreklass, gamemenu. och måse känna till objekt 
	public void updateUsers(HashSet<User> usersLocal);
}
