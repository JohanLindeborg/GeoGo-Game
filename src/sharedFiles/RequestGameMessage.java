package sharedFiles;

import java.awt.Point;

import com.teamdev.jxmaps.LatLng;

public class RequestGameMessage extends Message {

	private static final long serialVersionUID = 1L;
	String otherPlayer;
	private Point mapCenter;
	private double zoomLevel;

	public RequestGameMessage(Point mapCenter, double zoomLevel, String otherPlayer) {
		this.otherPlayer = otherPlayer;
		this.mapCenter = mapCenter;
		this.zoomLevel = zoomLevel;
	}

	public String getOtherPlayer() {
		return otherPlayer;
	}

	public Point getMapCenter() {
		return mapCenter;
	}

	public double getZoomLevel() {
		return zoomLevel;
	}
}