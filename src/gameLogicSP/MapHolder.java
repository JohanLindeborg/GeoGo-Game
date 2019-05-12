package gameLogicSP;

import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.MapTypeId;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MarkerOptions;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.swing.MapView;

public class MapHolder{
	private GameControllerSP gameController;
	
	private MapViewOptions options;
	private GameMapView gameMapView;
	private String mapName;
	
	private boolean clickedThisRound = false;
	private Marker cityMarker;
	private Marker clickMarker;
	
	private int totalRounds;
	private int countDown;
	
	public MapHolder(int totalRounds,double zoomLevel, LatLng mapCenter, String mapName, GameControllerSP gc) {
		
		this.totalRounds = totalRounds;
		gameController = gc;
		options = new MapViewOptions();
    	options.setApiKey("AIzaSyBtefj5xL2e6j-qt65FaXdevjKB3oErQjo");
    	
    	gameMapView = new GameMapView(options, mapCenter, zoomLevel);
    	this.mapName = mapName;
		
	}
	
	public MapView getMapView() {
		return gameMapView;
	}
	
	public void updateTimer(int cntDown) {
		countDown = cntDown;
	}
	
	private void placeMarker(LatLng latlong) {
		clickMarker = new Marker(gameMapView.getMap());
		clickMarker.setPosition(latlong);
		
	}
	private void placeCityPos(LatLng latlong,String cityName) {
		cityMarker = new Marker(gameMapView.getMap());
		MarkerOptions markeropt = new MarkerOptions();
		markeropt.setLabelString(cityName);
		cityMarker.setOptions(markeropt);
		cityMarker.setPosition(latlong);
		
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
	                   	                   
	                   map.addEventListener("click", new MapMouseEvent(){

						@Override
						public void onEvent(MouseEvent mouseEvent) {
							

							if(clickedThisRound == false && countDown > 0) {
								clickedThisRound = true;
								
								LatLng clickLatLng = mouseEvent.latLng();
							
								placeMarker(clickLatLng);
							
								City city = gameController.onMapClickInTime(clickLatLng);
								placeCityPos(city.getLatLng(),city.getName());
								
							}
							else if(clickedThisRound == false && countDown <= 0) {
								clickedThisRound = true;
								
								City city = gameController.onMapClickOutOfTime();
								placeCityPos(city.getLatLng(),city.getName());
							}
							else {

								clickedThisRound = false;

								cityMarker.remove();

								if(clickMarker != null) {
									clickMarker.remove();
								}
								int round = gameController.getCurrentRound();
								
								if(round < totalRounds) {
									gameController.startNewRound();
								}
								
								
							}
						}
	                   });
	                   
	                }
	            }
	        });
	    }
		
		
	}

	

}
