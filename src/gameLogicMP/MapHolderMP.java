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

	private boolean clickedThisRound = false;
	private Marker cityMarker;
	private Marker clickMarker;

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

	private void placeMarker(LatLng latlong) {

		// �ndrar marker f�r player, finns ett par olika i images
		Icon icon = new Icon();
		File file = new File("images/blackPin32.png");
		icon.loadFromFile(file);
		MarkerOptions markerOpt = new MarkerOptions();
		markerOpt.setIcon(icon);

		clickMarker = new Marker(gameMapView.getMap());
		clickMarker.setOptions(markerOpt);
		clickMarker.setPosition(latlong);
	}

	private void placeCityPos(Point2D.Double point, String cityName) {

		// �ndrar marker f�r korrekt position
		Icon icon = new Icon();
		File file = new File("images/greenPin32.png");
		icon.loadFromFile(file);
		MarkerOptions markerOpt = new MarkerOptions();
		markerOpt.setIcon(icon);

		cityMarker = new Marker(gameMapView.getMap());

//		markerOpt.setLabelString(cityName);
		cityMarker.setOptions(markerOpt);
		
		
		cityMarker.setPosition(new LatLng(point.getX(), point.getY()));
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

								if (clickedThisRound == false && countDown > 0) {
									clickedThisRound = true;

									LatLng clickLatLng = mouseEvent.latLng();
									placeMarker(clickLatLng);
									City city = gameController.onMapClickInTime(clickLatLng);
									placeCityPos(city.getPoint(), city.getName());
								}

								else if (clickedThisRound == false && countDown <= 0) {
									clickedThisRound = true;

									City city = gameController.onMapClickOutOfTime();
									placeCityPos(city.getPoint(), city.getName());
								}

								else {
									clickedThisRound = false;

									cityMarker.remove();

									if (clickMarker != null) {
										clickMarker.remove();
									}
									int round = gameController.getCurrentRound();

									if (round < totalRounds) {
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