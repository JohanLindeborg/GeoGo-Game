package client;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.swing.MapView;

import sharedFiles.AddToServerListMessage;
import sharedFiles.MapMessage;
import sharedFiles.Message;
import sharedFiles.RequestGameMessage;

public class TestClient extends Thread{
	
	private Socket socket;
	private int port;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private CreateMap map;
	
	
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
				MapMessage mapMessage = (MapMessage) message;
				
				map = new CreateMap(mapMessage.getZoomLevel(), mapMessage.getMapCenter());
				
				displayMap(map.getMap());
				
			}
		}
	}
	
	
	
	public void connectToServer(String userName) {
		
		try {
			oos.writeObject(new AddToServerListMessage(userName));
		} catch (IOException e) {
			System.out.println("IO Exception:");
			e.printStackTrace();
		}
	}
	
	public void StartGameHandler(String playWithUser) {
			
			try {
				oos.writeObject(new RequestGameMessage(playWithUser));
			} catch (IOException e) {
				System.out.println("IO Exception:");
				e.printStackTrace();
			}
		}
	public void sendMapMessage(LatLng mapCenter, double zoomLevel) {
		MapMessage mapMessage = new MapMessage(mapCenter, zoomLevel);
		
		try {
			oos.writeObject(mapMessage);
		} catch (IOException e) {
			System.out.println("IO Exception:");
			e.printStackTrace();
		}
	}
	
	public void displayMap(MapView map) {
		JFrame frame = new JFrame("ClientMap");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(map, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
