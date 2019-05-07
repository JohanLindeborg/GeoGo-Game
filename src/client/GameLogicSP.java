package client;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.javafx.MapView;

import client.City;
/**
 * Used for handling game logic, contains methods used by every client to update the map and get map information 
 * @author Adam
 *
 */
public class GameLogicSP {
	
	private com.teamdev.jxmaps.swing.MapView mapView;
	private Map map;
	
	private City currentCity;
	private LinkedList<City> cities = new LinkedList<City>();
//	private ArrayList<City> cities = new ArrayList<City>();
//	private City[] cityArray = new City[]; 
	
	public GameLogicSP(com.teamdev.jxmaps.swing.MapView gameMapView, LinkedList <City> cities ) {
		
		this.mapView = gameMapView;
		this.map =  gameMapView.getMap();
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
	public void setMarker(LatLng latlong) {
//		final Map map = getMap();
		final Marker marker = new Marker(map);
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
	
	
	


