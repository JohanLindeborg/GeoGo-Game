package client;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.LatLng;

public class ClientMapTest {
	
	private CreateMap newMap;
	
	
	
	
	public void createMap() {
		//53°34′39″N 23°06′22″E.
		LatLng mapCenter = new LatLng(53.3439,23.0622);
		double zoomLevel = 3.0;
		
		
		newMap = new CreateMap(zoomLevel,mapCenter);
	}
	
	public void displayMap() {
		JFrame frame = new JFrame("Client Map");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(newMap.getMap(), BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		ClientMapTest test = new ClientMapTest();
		test.createMap();
		test.displayMap();
	}

} 
