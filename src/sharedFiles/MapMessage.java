package sharedFiles;

import java.awt.Point;

public class MapMessage extends Message {

	private static final long serialVersionUID = 1L;
	
	
	private Point mapCenter;
	private double zoomLevel;
	
	public MapMessage(Point mapCenter, double zoomLevel) {
		this.mapCenter = mapCenter;
		this.zoomLevel = zoomLevel;
		
	}
	
	public Point getMapCenter() {
		return mapCenter;
	}
	
	public double getZoomLevel() {
		return zoomLevel;
	}
}