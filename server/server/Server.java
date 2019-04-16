package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server extends Thread {
	
	//Hashmap to store usernames (key) and their associated threads.
	private HashMap<String, ClientHandler> clientMap;
	
	
	
	private ServerSocket serverSocket;
	private int serverPort = 4242;
		
	public Server() {
		
		
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();

		
	}
	public void run() {
		while(this.isAlive()){
	       
			Socket clientSocket = null;
	        try {
	            clientSocket = this.serverSocket.accept();
	        } catch (IOException e) {
	            if(this.isAlive() != true) {
	                System.out.println("Server Stopped.") ;
	                return;
	            }
	            throw new RuntimeException(
	                "Error accepting client connection", e);
	        }
	        new ClientHandler(clientSocket, this).start();
	    }
	}
	
	public void addClientToServerList(String key, ClientHandler clienthandler) {
		clientMap.put(key, client);
	}
	
}
