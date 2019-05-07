package client;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;


public class ControllerGUI {
	private HashSet<User> usersOnline = new HashSet<User>();
	private HashSet<User> usersLocal = new HashSet<User>();
	private User userLocal;
	private File fiUsers = new File("data/users.dat");


	@SuppressWarnings("unchecked")
	public ControllerGUI() {

		// Create data folder
		File foData = new File("data/");
		if (!foData.exists()) {
			foData.mkdir();
		}
//skanner läser in hur lång filen är
		// Read list of local users
		if (fiUsers.exists()) {
			System.out.println("Reading file users.dat...");
			usersLocal = (HashSet<User>) UserHelper.readFile(fiUsers);
			if (usersLocal == null)
				usersLocal = new HashSet<>();
		}
	}

	// Add local user // Får Nullpoineter!
	public void addLocalUser(String name) {
		User newUser = new User(name);
		System.out.println("Adding user " + newUser);

			for (User u : usersLocal)
				if (u.equals(newUser)) { 
					System.out.println("User already excists");
		} else {
			System.out.println("User doesn't exist");
			usersLocal.add(newUser);
			System.out.println(usersLocal);
		}
	}

	// Get list of local users
	public HashSet<User> getLocalUsers() {
		System.out.println(usersLocal);
		return usersLocal;
	}

	//Behövs ej ännu?
//	public User getLocalUser() {
//		return userLocal;
//	}

//setUserMenu find!
	// Get list of online users
	public HashSet<User> getOnlinelist() {
		HashSet<User> users = new HashSet<User>();
		for (User u : usersOnline)
			System.out.println("Online size: " + usersOnline.size() + ", " + usersOnline);
		users.addAll(usersOnline);
		return users;
	}

//    // Add new local user
//    private void addNewUser() {
//        String name = "";
//        while (name.equals(""))
//            name = JOptionPane.showInputDialog("Enter username:");
//        addLocalUser(name);
//        setUserSelected(name);
//        setUserMenu();
//    }
//    
	private void showPlayer() {
		
	}

    //Om nödvändig
//    synchronized void setUserList() { // Update the user list (for example, when a new user connects)
//        lmUsers = new UserListModel(cc.getUserlist());
//        lmUsers.sort(sortBy);
//        jlUsers.setModel(lmUsers);
//    }

    

//Nedan är för multiplayer:
	
	// Select a user from the menu to connect with
	void selectUser(ActionEvent e) { 
		JRadioButtonMenuItem mi = (JRadioButtonMenuItem) e.getSource();
		setUserSelected(mi.getText());
	}
	
	// What to do when a user has been selected from the menu
	public void setUserSelected(String sUser) { 
		setLocalUser(sUser);
	}
	
	// Select which user to log in with
	public void setLocalUser(String name) {
		for (User u : usersLocal)
			if (name.equals(u.toString()))
				userLocal = u;
	}

// 		public LinkedList<User> multiPlayer() {
// 			String name = JOptionPane.showInputDialog(null, "Create a user");
// 			// har lagt till enny användare
// 			gui.addNewUser(name);
// 			// ska uppdatera listan nånstans och sen göra om den till toArray(new User[0]); (dvs User array)
	//
// 			list.toArray(new User[0]);
// 			return list;
// 		}

	public static void main(String[] args) {
		// System.setProperty("sun.java2d.uiScale", "1.0");
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		ControllerGUI controller = new ControllerGUI();
		ClientGUI gui = new ClientGUI();
		gui.showUI();
	}
}