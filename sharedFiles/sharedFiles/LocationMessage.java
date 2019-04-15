package sharedFiles;

import com.teamdev.jxmaps.LatLng;

public class LocationMessage extends Message {
	
	private LatLng coordinates;
	
	public LocationMessage(LatLng coordinates ) {
		this.coordinates = coordinates;
		
	}
	
	public LatLng getLatLng() {
		return coordinates;
	}

}
