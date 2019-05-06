package gui;

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

public class CreateMap {

	private MapViewOptions mapViewOptions;
	private MyMap myMap;

	public CreateMap(double zoomLevel, LatLng latLng) {

		mapViewOptions = new MapViewOptions();
		mapViewOptions.setApiKey("AIzaSyBtefj5xL2e6j-qt65FaXdevjKB3oErQjo");

		myMap = new MyMap(mapViewOptions, latLng, zoomLevel);
	}

	public MyMap getMap() {

		return myMap;
	}

	private class MyMap extends MapView {

		public MyMap(MapViewOptions mapViewOptions, LatLng latLng, double zoomLevel) {
			super(mapViewOptions);
			setOnMapReadyHandler(new MapReadyHandler() {

				@Override
				public void onMapReady(MapStatus status) {
					if (status == MapStatus.MAP_STATUS_OK) {

						Map map = getMap();

						MapTypeControlOptions controlOptions = new MapTypeControlOptions();

						MapOptions mapOptions = new MapOptions();

						map.setMapTypeId(MapTypeId.SATELLITE);

						mapOptions.setDraggable(false);
						mapOptions.setDisableDoubleClickZoom(true);
						mapOptions.setDisableDefaultUI(true);

						map.setOptions(mapOptions);
						map.setCenter(latLng);
						map.setZoom(zoomLevel);
					}
				}
			});
		}
	}
}