package client;

import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.MapTypeId;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.swing.MapView;

public class CreateMap{
	
	private MapViewOptions options;
	private GameMapView gameMapView;
	private String mapName;
	
	public CreateMap(double zoomLevel, LatLng mapCenter, String mapName) {
		
		options = new MapViewOptions();
		//options.importPlaces();
    	options.setApiKey("AIzaSyBtefj5xL2e6j-qt65FaXdevjKB3oErQjo");
    	
    	gameMapView = new GameMapView(options, mapCenter, zoomLevel);
    	this.mapName = mapName;
		
	}
	
	public MapView getMap() {
		return gameMapView;
	}

	
	private class GameMapView extends MapView{
		
		
		public GameMapView(MapViewOptions options,LatLng mapCenter, double zoomLevel) {
			super(options);
	        setOnMapReadyHandler(new MapReadyHandler() {
	            @Override
	            public void onMapReady(MapStatus status) {
	                if (status == MapStatus.MAP_STATUS_OK) {
	                   final com.teamdev.jxmaps.Map map = getMap();
	                   
	                   MapTypeControlOptions controllOptions = new MapTypeControlOptions();
	                   
	                   MapOptions mapOptions = new MapOptions();
	                   
	                   map.setMapTypeId(MapTypeId.SATELLITE);
	                   
	                   mapOptions.setDraggable(false);
	                   mapOptions.setDisableDoubleClickZoom(true);
	                   mapOptions.setDisableDefaultUI(true);
	                  
	                   map.setOptions(mapOptions);
	                   map.setCenter(mapCenter);
	                   map.setZoom(zoomLevel);
	                   
	                   
	                }
	            }
	        });
	    }
	}

	

}
