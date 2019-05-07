package client;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

import com.teamdev.jxmaps.LatLng;

public class CitiesData {
	
	//Lists of cities used by the game, format: "cities-Country name".
	private LinkedList<City> citiesFr = new LinkedList<City>();
	private BufferedReader br;
	private Scanner scanner;
	private String mapName;
	
	public CitiesData(String mapName) {
		
			this.mapName = mapName;
			getCitiesForMap(mapName);
		
	}
	
	public LinkedList<City> getCitiesList(){
		
		if(mapName.equals("France")){
			return citiesFr;
		}
		else {
			System.out.println("CitiesData: City list not found");
			return null;
		}
		
	}
	
	private void getCitiesForMap(String mapName) {
		
		if(mapName.equals("France")) {
			//createFileReader("/GeoGo-Game/citiesList/citiesFr");
			readCitiesFileAndStore("citiesList/citiesFr.txt");
			
				
			}
			
		
		
	}
	/*
	private void createFileReader(String filePath) {
		try {
			br = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("CitiesData: file: "+filePath+" not found");
			e.printStackTrace();
		}
	}
	*/
	
	private void readCitiesFileAndStore(String filePath) {
		try {
			
			System.out.println("-------------"+new File(filePath).canRead()+"-------------------");
			scanner = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(scanner.hasNextLine()) {
			String cityName = scanner.nextLine();
			double lat = Double.parseDouble(scanner.nextLine());
			double lng = Double.parseDouble(scanner.nextLine());
			
			LatLng cityLatLng = new LatLng(lat,lng);
			
			City city = new City(cityName, cityLatLng);
			citiesFr.add(city);
			System.out.println("added: "+city.getName()+", "+city.getCoordinates().toString());
		}
	}
	
	

}
