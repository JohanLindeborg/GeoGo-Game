package sharedFiles;

import java.awt.geom.Point2D;

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
