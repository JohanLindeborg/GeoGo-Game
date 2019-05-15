package sharedFiles;

import java.awt.Point;
import java.awt.geom.Point2D;

import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.swing.MapView;

public class MapMsg extends Message {

	private static final long serialVersionUID = 1L;

	private Point2D mapCenter;
	private double zoomLevel;
	private String mapName;
	private int totalRounds;

	public MapMsg(String mapName,int totalRounds, Point2D mapCenter, double zoomLevel) {
		super("server");
		this.mapCenter = mapCenter;
		this.zoomLevel = zoomLevel;
		this.mapName = mapName;
		this.totalRounds = totalRounds;

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
}
