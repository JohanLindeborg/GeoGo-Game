
import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.swing.MapView;
import javax.swing.*;
import java.awt.*;

public class Test extends MapView {

	public Test(MapViewOptions mvo) {
		super(mvo);
		System.out.println("test");
		setOnMapReadyHandler(new MapReadyHandler() {

			@Override
			public void onMapReady(MapStatus status) {

				if (status == MapStatus.MAP_STATUS_OK) {
					
					LatLng latLong = new LatLng(4.847143, 23.230868);
					
					Map map = getMap();
					MapOptions mapO = new MapOptions(map);
					mapO.setDraggable(false);
					map.setZoom(3);
					mapO.setMaxZoom(3);
					mapO.setMinZoom(3);
					map.setOptions(mapO);
					
					
					GeocoderRequest request = new GeocoderRequest(map);
					request.setLocation(latLong);
					

					getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {

						public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
							if (status == GeocoderStatus.OK) {
								map.setCenter(result[0].getGeometry().getLocation());
//								Marker marker = new Marker(map);
//								marker.setPosition(result[0].getGeometry().getLocation());
//
//								InfoWindow window = new InfoWindow(map);
//								window.setContent("sadmökamsdög");
//								window.open(map, marker);
							}
						}
					});
				}
			}
		});
	}

	public static void main(String[] args) {

		MapViewOptions mv = new MapViewOptions();
		
		mv.importPlaces(); // Oklart vad den här metoden gör?
		mv.setApiKey("AIzaSyBtefj5xL2e6j-qt65FaXdevjKB3oErQjo");
		Test test = new Test(mv);

		JFrame frame = new JFrame("TESTING TESTING");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(test, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
