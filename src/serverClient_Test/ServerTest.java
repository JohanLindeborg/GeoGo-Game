package serverClient_Test;

import java.awt.Point;

import com.teamdev.jxmaps.LatLng;

public class ServerTest {
	
	public static void main(String[] args) {
		
		Point mapCenter = new Point();
		mapCenter.setLocation(53.3439, 23.0622);
		double zoomLevel = 3.0;
		
		
		TestClient client1 = new TestClient("Johan");
		TestClient client2 = new TestClient("Andreas");
		
		client1.connectToServer();
		client2.connectToServer();
		
		client1.startNewGame(mapCenter, zoomLevel, "Andreas");
	}

}
