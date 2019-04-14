package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
	
	//Hashmap to store usernames (key) and their associated threads.
	private HashMap<String, ClientHandler> clientMap;
	
	
	private ConnectionAccepter connectionAccepter;
		
	public Server() {
		
		
		connectionAccepter = new ConnectionAccepter();
		
		connectionAccepter.start();
	}
	
	
	private class ConnectionAccepter extends Thread{
		private ServerSocket serverSocket;
		private int serverPort = 4242;
		
		public ConnectionAccepter() {
			
			try {
				serverSocket = new ServerSocket(serverPort);
			} catch (IOException e) {
				e.printStackTrace();
			}

			
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
		        new ClientHandler(clientSocket).start();
		    }
		}
	}

}
