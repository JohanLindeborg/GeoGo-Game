package sharedFiles;

import java.awt.geom.Point2D;

public class RequestGameMsg extends Message {
	private static final long serialVersionUID = 1L;
	
	String player2;
	private Point2D.Double mapCenter;
	private double zoomLevel;
	private String mapName;
	private int totalRounds;

	public RequestGameMsg(int totalRounds, String mapName, Point2D.Double mapCenter, double zoomLevel,String sender, String player2) {
		super(sender);
		this.player2 = player2;
		this.mapCenter = mapCenter;
		this.zoomLevel = zoomLevel;
		this.mapName = mapName;
		this.totalRounds = totalRounds;
	}
	
	public int getTotalRounds() {
		return totalRounds;
	}

	public String getOtherPlayer() {
		return player2;
	}

	public Point2D.Double getMapCenter() {
		return mapCenter;
	}

	public double getZoomLevel() {
		return zoomLevel;
	}
	
	public String getMapName() {
		return mapName;
	}
	
}