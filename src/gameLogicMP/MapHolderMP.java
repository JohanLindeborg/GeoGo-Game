package gameLogicMP;

import java.awt.geom.Point2D;
import java.io.File;

import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.Icon;
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
import com.teamdev.jxmaps.MarkerShape;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.swing.MapView;

import sharedFiles.City;

public class MapHolderMP {
	private GameControllerMP gameController;

	private MapViewOptions options;
	private GameMapView gameMapView;
	private String mapName;

	private boolean clickedThisRound;
	private Marker cityMarker;
	private Marker clickMarkerPl1;
	private Marker clickMarkerPl2;
	
	private MarkerOptions cityMarkerOpt;
	private MarkerOptions pl1MarkerOpt;
	private MarkerOptions pl2MarkerOpt;
	
	private LatLng lastClick;

	private int totalRounds;
	private int countDown;

	public MapHolderMP(int totalRounds, double zoomLevel, LatLng mapCenter, String mapName, GameControllerMP gc) {

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

	public void placeMarkerPl1(LatLng latlong) {

		// �ndrar marker f�r player, finns ett par olika i images
		Icon icon = new Icon();
		File file = new File("images/bluePin32.png");
		icon.loadFromFile(file);
		pl1MarkerOpt = new MarkerOptions();
		pl1MarkerOpt.setIcon(icon);

		clickMarkerPl1 = new Marker(gameMapView.getMap());
		clickMarkerPl1.setOptions(pl1MarkerOpt);
		clickMarkerPl1.setPosition(latlong);
	}
	
	public void placeMarkerPl2(LatLng latlong) {

		// �ndrar marker f�r player, finns ett par olika i images
		Icon icon = new Icon();
		File file = new File("images/redPin32.png");
		icon.loadFromFile(file);
		pl2MarkerOpt = new MarkerOptions();
		pl2MarkerOpt.setIcon(icon);

		clickMarkerPl2 = new Marker(gameMapView.getMap());
		clickMarkerPl2.setOptions(pl2MarkerOpt);
		clickMarkerPl2.setPosition(latlong);
	}

	public void placeCityPos(Point2D.Double point, String cityName) {

		// �ndrar marker f�r korrekt position
		Icon icon = new Icon();
		File file = new File("images/greenDotCorrectPos.png");
		icon.loadFromFile(file);
		cityMarkerOpt = new MarkerOptions();
		cityMarkerOpt.setIcon(icon);

		cityMarker = new Marker(gameMapView.getMap());

//		markerOpt.setLabelString(cityName);
		cityMarker.setOptions(cityMarkerOpt);
		
		cityMarker.setPosition(new LatLng(point.getX(), point.getY()));
	}
	
	public void removeMarkers() {
		cityMarkerOpt.setVisible(false);
		
		cityMarker.setOptions(cityMarkerOpt);
		clickMarkerPl1.setOptions(cityMarkerOpt);
		clickMarkerPl2.setOptions(cityMarkerOpt);
	}
	
	public void setClickedThisRound(boolean clicked) {
		clickedThisRound = clicked;
	}
	
	public boolean getClickedThisRound() {
		return clickedThisRound;
	}

	private class GameMapView extends MapView {

		public GameMapView(MapViewOptions options, LatLng mapCenter, double zoomLevel) {
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
						map.addEventListener("click", new MapMouseEvent() {

							@Override
							public void onEvent(MouseEvent mouseEvent) {
								System.out.println("Onevent bool: "+clickedThisRound+ ", countdown: "+countDown);

								if (clickedThisRound == false && countDown > 0) {
									clickedThisRound = true;

									lastClick = mouseEvent.latLng();
									
									gameController.onMapClickInTime(lastClick);
									//placeCityPos(city.getPoint(), city.getName());
									
									System.out.println("GameControllerMP registered mapclick in time");
								}
								/*
								else if (clickedThisRound == false && countDown <= 0) {
									clickedThisRound = true;

									gameController.onMapClickOutOfTime();
									System.out.println("GameControllerMP registered mapclick out of time");

								}
								*/

								/*else {
									clickedThisRound = false;

									cityMarker.remove();

									if (clickMarker != null) {
										clickMarker.remove();
									}
									int round = gameController.getCurrentRound();

									if (round < totalRounds) {
										gameController.startNewRound();
									}
								}*/
							}
							
						});
					}
				}
			});
		}
	}
}