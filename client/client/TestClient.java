package client;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

import sharedFiles.ConnectMessage;
import sharedFiles.MapMessage;
import sharedFiles.Message;
import sharedFiles.RequestMapMessage;

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
		
		JFrame frame = new JFrame("JxMaps - Hello, World!");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(map, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public void connectToServer() {
		
		try {
			oos.writeObject(new ConnectMessage("testKlient"));
		} catch (IOException e) {
			System.out.println("IO Exception:");
			e.printStackTrace();
		}
	}
	
	public void requestMap() {
			
			try {
				oos.writeObject(new RequestMapMessage());
			} catch (IOException e) {
				System.out.println("IO Exception:");
				e.printStackTrace();
			}
		}
	
	
	
	public static void main(String[] args) {
		TestClient test = new TestClient();
		
		test.connectToServer();
		test.requestMap();
	
	}
}
