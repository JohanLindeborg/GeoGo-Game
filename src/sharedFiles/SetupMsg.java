package sharedFiles;

import java.awt.geom.Point2D;

public class SetupMsg extends Message {
	private static final long serialVersionUID = 1L;

	private Point2D mapCenter;
	private double zoomLevel;
	private String mapName;
	private int totalRounds;
	private String pl1;
	private String pl2;

	public SetupMsg(String mapName,int totalRounds, Point2D mapCenter, double zoomLevel, String pl1, String pl2) {
		super("server");
		this.mapCenter = mapCenter;
		this.zoomLevel = zoomLevel;
		this.mapName = mapName;
		this.totalRounds = totalRounds;
		this.pl1 = pl1;
		this.pl2 = pl2;

	}

	public Point2D getMapCenter() {
		return mapCenter;
	}

	public double getZoomLevel() {
		return zoomLevel;
	}
	
	public String getMapName() {
		return mapName;
	}
	
	public int getTotalRounds() {
		return totalRounds;
	}
	
	public String getPl1() {
		return pl1;
	}
	
	public String getPl2() {
		return pl2;
	}
}
