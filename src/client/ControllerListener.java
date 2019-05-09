package client;

import java.util.HashSet;

public interface ControllerListener  {

	public void updateUsers(HashSet<User> usersLocal);
	
	public void showUI() ;
	
}
