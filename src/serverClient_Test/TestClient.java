package serverClient_Test;

import java.awt.BorderLayout;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.swing.MapView;

import client.CreateMap;
import sharedFiles.AddToServerListMessage;
import sharedFiles.GameData;
import sharedFiles.MapMessage;
import sharedFiles.Message;
import sharedFiles.RequestGameMessage;

public class TestClient extends Thread{
	
	private String userName;
	
	private Socket socket;
	private int port;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private CreateMap map;
	
	private GameData currentGame;
	private boolean inGame = false;
	
	
	public TestClient(String userName) {
		
		this.userName = userName;
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
		Object obj = null;
		
		while(true) {
			try {
				obj = ois.readObject();
				System.out.println("Client "+userName+" received object");
				
			}catch(ClassNotFoundException e) {
				System.out.println("Class Not Found Exception:");
				e.printStackTrace();
			}catch(IOException e) {
				System.out.println("IO Exception:");
				e.printStackTrace();
			}
			
			
			if(obj instanceof GameData) {
				currentGame = (GameData) obj;
				System.out.println("Client "+userName+" received GameData");
				
				if( !inGame) {
					Point point = currentGame.getMapCenter();
					LatLng latlng = new LatLng(point.getX(),point.getY());
					
					map = new CreateMap(currentGame.getZoomLevel(), latlng);
					System.out.println("Client "+userName+" created map");
					displayMap(map.getMap());
					
					inGame = true;
				}
			}
		}
	}
	
	
	
	public void connectToServer() {
		
		try {
			oos.writeObject(new AddToServerListMessage(userName));
		} catch (IOException e) {
			System.out.println("IO Exception:");
			e.printStackTrace();
		}
	}
	
	public void startNewGame(Point mapCenter, double zoomLevel, String otherplayer) {
		RequestGameMessage msg = new RequestGameMessage( mapCenter,  zoomLevel, otherplayer);
		
		try {
			oos.writeObject(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMapMessage(Point mapCenter, double zoomLevel) {
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
