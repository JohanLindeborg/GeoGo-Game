package client;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.sun.javafx.collections.MappingChange.Map;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.javafx.MapView;
/**
 * Used for handling game logic, contains methods used by every client to update the map and get map information 
 * @author Adam
 *
 */
public class GameLogicSP {
	private MapView gameMap;
	private Map map;
	private City currentCity;
//	private ArrayList<City> cities = new ArrayList<City>();
//	private City[] cityArray = new City[]; 
	private LinkedList<City> cities = new LinkedList<City>();
	
	public GameLogicSP(MapView gameMap,Map map, LinkedList <City> cities ) {
		this.gameMap = gameMap;
		this.map = map;
		this.cities = cities;
	}
	
//	public void addCities(City[] cityArray) {
//		for(City c : cityArray) {
//			cities.add(c);
//		}
	public City getRandomCity() {
		if(cities.size()== 0) {
			System.out.println("CityList Empty");
			return null;
		}
		Random rand = new Random();
		int index = rand.nextInt(cities.size());
		currentCity = cities.remove(index);
		return currentCity;
	}
	//Updates the map with a marker
	public void onMapClick(LatLng latlong) {
//		final Map map = getMap();
		final Marker marker = new Marker((com.teamdev.jxmaps.Map) map);
		marker.setPosition(latlong);
	}
	
	//returns distance in km
	public double getDistance(LatLng latlong1, LatLng latlong2) {
		double lat1 = latlong1.getLat();
		double lon1 = latlong1.getLng();
		
		double lat2 = latlong2.getLat();
		double lon2 = latlong2.getLng();
		
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		// Distance in km
		dist = dist * 1.609344;
		
		return (dist);
		
	}
	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
	
	
}
	
	
	


