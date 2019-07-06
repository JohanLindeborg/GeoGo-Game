package sharedFiles;

import java.awt.geom.Point2D;

/**
 * This message is sent from a client to the server when the user has made a click on the
 * map during an ongoing game. It contains information about if the player clicked in time 
 * and where the player clicked.
 * @author johanlindeborg
 *
 */
public class MapClickMsg extends Message {
	private Point2D.Double mapClickPoint;
	private boolean inTime;

	public MapClickMsg(String sender, Point2D.Double mapClickPoint, boolean inTime) {
		super(sender);
		this.mapClickPoint = mapClickPoint;
		this.inTime = inTime;
		
	}
	
	public boolean getInTime() {
		return inTime;
	}
	
	public Point2D.Double getClickPoint(){
		return mapClickPoint;
	}

}
