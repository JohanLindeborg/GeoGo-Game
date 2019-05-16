package serverClient_Test;

import java.awt.geom.Point2D;

import gameLogicMP.GameControllerMP;
import server.GameServer;

public class JohanTest {
	
	
	public static void main(String[] args) {
		
		
		
		
		GameControllerMP johan = new GameControllerMP("Johan");
		
		Point2D.Double mapCenter = new Point2D.Double(46.4534, 2.2404);
		double zoomLevel = 5.9;
		
		johan.requestGame( mapCenter, zoomLevel, "Adam" , 10, "France");

	}

}
