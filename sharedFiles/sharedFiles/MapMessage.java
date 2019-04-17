package sharedFiles;

import com.teamdev.jxmaps.swing.MapView;

public class MapMessage extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MapView map;
	
	public MapMessage(MapView map) {
		this.map = map;
		
	}
	
	public MapView getMapView() {
		return map;
	}
}
