package serverClient_Test;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import com.teamdev.jxmaps.LatLng;

import gameLogicMP.GameControllerMP;

public class ServerTest {
	
	public static void main(String[] args) {
		
		Point2D.Double mapCenter = new Point2D.Double(46.4534, 2.2404);
		double zoomLevel = 5.9;
		
		
		GameControllerMP johan = new GameControllerMP("Johan");
		GameControllerMP adam = new GameControllerMP("Adam");
		
		johan.requestGame( mapCenter, zoomLevel, "Adam" , 10, "France");

	}

}
