package client;

import com.teamdev.jxmaps.LatLng;

public class ServerTest {

	public static void main(String[] args) {
		
		LatLng mapCenter = new LatLng(53.3439,23.0622);
		double zoomLevel = 3.0;
		
		
		TestClient client1 = new TestClient();
		TestClient client2 = new TestClient();
		
		client1.connectToServer("Johan");
		client2.connectToServer("Andreas");
		
		client1.StartGameHandler("Andreas");
		
		client1.sendMapMessage(mapCenter, zoomLevel);
		
	}

}
