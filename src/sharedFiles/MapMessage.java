package sharedFiles;

import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.swing.MapView;

public class MapMessage extends Message {

	private static final long serialVersionUID = 1L;
	
	
	private LatLng mapCenter;
	private double zoomLevel;
	
	public MapMessage(LatLng mapCenter, double zoomLevel) {
		this.mapCenter = mapCenter;
		this.zoomLevel = zoomLevel;
		
	}
	
	public LatLng getMapCenter() {
		return mapCenter;
	}
	
	public double getZoomLevel() {
		return zoomLevel;
	}
}
