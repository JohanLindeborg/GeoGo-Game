package sharedFiles;

import com.teamdev.jxmaps.swing.MapView;

public class MapMessage extends Message {

	private MapView map;
	
	public void MapMessage(MapView map) {
		this.map = map;
		
	}
	
	public MapView getMapView() {
		return map;
	}
}