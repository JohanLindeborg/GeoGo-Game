package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import sharedFiles.AddToServerListMessage;
import sharedFiles.MapMessage;
import sharedFiles.Message;
import sharedFiles.RequestGameMessage;

public class GameHandler {
	
	private ClientHandler client1;
	private ClientHandler client2;
	
	private ObjectOutputStream client1OOS;
	private ObjectOutputStream client2OOS;
	
	private ObjectInputStream client1OIS;
	private ObjectInputStream client2OIS;
	
	private Client1Listener client1listener;
	private Client2Listener client2Listener;

	
	public GameHandler(ClientHandler client1, ClientHandler client2) {
		this.client1 = client1;
		this.client2 = client2;
		
		/*this.client1OOS = client1.getOOS();
		this.client2OOS = client2.getOOS();
		
		this.client1OIS = client1.getOIS();
		this.client2OIS = client2.getOIS();
		*/
		
		
		try {
			client1OOS = new ObjectOutputStream(client1.getClientSocket().getOutputStream());
			client1OIS = new ObjectInputStream(client1.getClientSocket().getInputStream());
			
			client2OOS = new ObjectOutputStream(client2.getClientSocket().getOutputStream());
			client2OIS = new ObjectInputStream(client2.getClientSocket().getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		start();
	}
	
	public void start() {
		
		client1.setListeningForMessages(false);
		client2.setListeningForMessages(false);
		
		new Client1Listener().start();
		new Client2Listener().start();
		
		
		
	}
	
	
	private class Client1Listener extends Thread {
		
		public void run(){
			Message message = null;
			
			while(this.isAlive()) {
				try {
					message = (Message) client1OIS.readObject();
				    System.out.println("GameHandler read object ( "+message+" ). Sent to client 1");

		            
		        	
		        }catch(ClassNotFoundException e) {
		        	System.out.println("Class not found exception");
		        	e.printStackTrace();
		        }
				catch (IOException e) {
		        	System.out.println("IO exception");
		            e.printStackTrace();
		        }
				if(message instanceof MapMessage ) {
						MapMessage mapMessage = (MapMessage) message;
						sendMessage(client2OOS, mapMessage);
				}
				
				else if(message instanceof Message) {
					
				}
			}
			
		}
		
	}
	
	private class Client2Listener extends Thread {
		Message message;
		
		public void run(){
			
			while(this.isAlive()) {
				try {
					message = (Message) client2OIS.readObject();
				    System.out.println("GameHandler read object ( "+message+" ). sent to Client 2");

		            
		        	
		        }catch(ClassNotFoundException e) {
		        	System.out.println("Class not found exception");
		        	e.printStackTrace();
		        }
				catch (IOException e) {
		        	System.out.println("IO exception");
		            e.printStackTrace();
		        }
				if(message instanceof AddToServerListMessage ) {

				}
				
				else if(message instanceof Message) {
					
				}
			}
			
		}
		
	}
	
	public void sendMessage(ObjectOutputStream receiverOOS, Message message) {
		
		try {
			
			receiverOOS.writeObject(message);
			
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
	}

}
