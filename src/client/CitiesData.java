package client;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import com.teamdev.jxmaps.LatLng;

public class CitiesData {
	
	private LinkedList<City> mapCities = new LinkedList<City>();
	private Scanner scanner;
	private String mapName;
	
	public CitiesData(String mapName) {
		
			this.mapName = mapName;
			getCitiesForMap(mapName);
	}
	
	public City getRandomCity() {
		if(mapCities.size()== 0) {
			System.out.println("CityList Empty");
			return null;
		}
		Random rand = new Random();
		int index = rand.nextInt(mapCities.size());
		return mapCities.remove(index);
	}
	
	private void getCitiesForMap(String mapName){
		
		if(mapName.equals("France")) {
			readCitiesFileAndStore("citiesList/citiesFr.txt");
		}
		else if (mapName.equals("Sweden")) {
			readCitiesFileAndStore("citiesList/citiesSW.txt");
		}
		else if(mapName.contentEquals("Italy")) {
			readCitiesFileAndStore("citiesList/citiesIt.txt");
		}
		else if(mapName.contentEquals("Germany")) {
			readCitiesFileAndStore("citiesList/citiesGe.txt");
		}
		else if(mapName.contentEquals("Greece")) {
			readCitiesFileAndStore("citiesList/citiesGr.txt");
		}
		//Lagt in huvudstäder fram till Somalien i den här listan
		else if (mapName.equals("Africa")) {
			readCitiesFileAndStore("citiesList/capitolsAfrica.txt");
		}
		else if(mapName.equals("Europe")) {
			readCitiesFileAndStore("citiesList/capitolsEurope.txt");
		}	
	}
	
	
	private void readCitiesFileAndStore(String filePath) {
		try {
			scanner = new Scanner(new File(filePath));
			System.out.println("-----------------Read from file-------------------");

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
			mapCities.add(city);
			System.out.println("added: "+city.getName()+", "+city.getLatLng().toString());
		}
		System.out.println("-----------------------------------------------");

	}
	
	

}
