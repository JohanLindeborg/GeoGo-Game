package sharedFiles;

import com.teamdev.jxmaps.LatLng;

public class LocationMessage extends Message {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LatLng coordinates;
	
	public LocationMessage(LatLng coordinates) {
		this.coordinates = coordinates;
		
	}
	
	public LatLng getLatLng() {
		return coordinates;
	}

}
