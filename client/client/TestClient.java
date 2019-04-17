package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.teamdev.jxmaps.swing.MapView;

import sharedFiles.MapMessage;
import sharedFiles.Message;

public class TestClient extends Thread{
	
	private Socket socket;
	private int port;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	
	public TestClient() {
		
		this.port = 4242;
		
		try {
			this.socket = new Socket("localhost", 4242);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		
		} catch (UnknownHostException e) {
			System.out.println("Unknown Host Exception:");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Excception:");
			e.printStackTrace();
		}
		
		start();
	}
	
	public void run() {
		Message message = null;
		
		while(true) {
			try {
			
				
				message = (Message) ois.readObject();
				
			}catch(ClassNotFoundException e) {
				System.out.println("Class Not Found Exception:");
				e.printStackTrace();
			}catch(IOException e) {
				System.out.println("IO Exception:");
				e.printStackTrace();
			}
			
			
			if(message instanceof MapMessage) {
				initializeMap(((MapMessage) message).getMapView());
			}
		}
	}
	public void initializeMap(MapView map) {
		
	}
	
	
	
	public static void main(String[] args) {
		TestClient test = new TestClient();
	
	}
}
