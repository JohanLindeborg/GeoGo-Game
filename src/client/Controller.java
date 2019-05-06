package gui;

import java.util.LinkedList;
import shared.*;

public class Controller {
	private LinkedList<User> usersOnline = new LinkedList<User>();
	private LinkedList<User> usersLocal = new LinkedList<User>(); 
    private User userLocal;
    private ClientGUI gui;
    private ClientConnection cc;
    
    public Controller() {
    }
    
    public void setGUI(ClientGUI ui) { //varför är GUIt kopplat här?
        this.gui = gui;
    }

    public void addLocalUser(String name) {
        User newUser = new User(name);
        System.out.println("Adding user " + newUser);
        if (usersLocal.contains(newUser)) {
            System.out.println("User exists");
            for (User u : usersLocal)
                if (u.equals(newUser))
                   System.out.println("User already excists");
        } else {
            System.out.println("User doesn't exist");
            usersLocal.add(newUser);
        }
    }

    public LinkedList<User> getLocalUsers() {
        return usersLocal;
    }

    // Select which user to log in with
    public void setLocalUser(String name) {
        for (User u : usersLocal)
            if (name.equals(u.toString()))
                userLocal = u;
    }

    public User getLocalUser() {
        return userLocal;
    }

    // Get list of online users
    public LinkedList<User> getUserlist() {
    	LinkedList<User> users = new LinkedList<User> ();
        for (User u : usersOnline)//kan använda bättre metod här, använde rinte u
        System.out.println("Online size: " + usersOnline.size() + ", " + usersOnline);
        users.addAll(usersOnline);
        return users;
    }
//Kan ta bort dessa nedan?
//    public void connect(String ip, int port) {
//        cc = new ClientConnection(this, ip, port);
//        cc.connect(new ConnectMessage(userLocal));
//    }
//
//    public void disconnect() {
//        if (cc != null)
//            cc.sendPacket(new DisconnectMessage(userLocal));
//    }

    public void sendPacket(Message message) {
        cc.sendPacket(message);
    }

    public void receivePacket(Message message) {
        if (message instanceof UserMessage)
        	System.out.println("Other messages");
        else if (message instanceof ConnectMessage)
            receiveConnect(((ConnectMessage) message));
        else if (message instanceof DisconnectMessage)
            receiveDisconnect((DisconnectMessage) message);
    }

    // Receiving a Message of a new connected user
    private void receiveConnect(ConnectMessage connect) {
        if (connect.getSender().equals(userLocal)) { // If it's my own connections message, add all users
            usersOnline.addAll(connect.getUsers());
        } else {
            usersOnline.add(connect.getSender()); // Otherwise, just add the latest user
            System.out.println("Server/Client list match: " + usersOnline.equals(connect.getUsers()));
        }
        System.out.println("Client: " + connect.getSender() + " connected");
//        ui.receiveConnect(connect);
    }

    // Receiving a Message of a disconnected user
    private void receiveDisconnect(DisconnectMessage disconnect) {
        if (disconnect.getSender().equals(userLocal)) { // If it's my own disconnect message echoing, shut down connection
            usersOnline.clear();
            cc = null;
            System.out.println("Client disconnected.");
        } else {
            usersOnline.remove(disconnect.getSender());
            System.out.println("Client: " + disconnect.getSender() + " disconnected");
        }
    }


    public static void main(String[] args) {
        //System.setProperty("sun.java2d.uiScale", "1.0");
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        Controller controller = new Controller();
        ClientGUI gui = new ClientGUI();
        controller.setGUI(gui);
        gui.showUI();
    }
}