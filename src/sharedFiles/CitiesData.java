package sharedFiles;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class used for reading in cities and their coordinates from files.
 * The cities which has been read are stored in a Linkedlist and is then used by either the {@link GameControllerSP}
 * or the {@link GameData} in the server when running a game. This class can read from a number of diferent files, it
 * reads from a file depending on which map has been selected by the user.
 * @author johanlindeborg
 * @author Adam
 */
public class CitiesData {
	private LinkedList<City> mapCities = new LinkedList<City>();
	private Scanner scanner;
	private String mapName;

	public CitiesData(String mapName) {
		this.mapName = mapName;
		getCitiesForMap(mapName);
	}

	public City getRandomCity() {
		if (mapCities.size() == 0){
			System.out.println("CityList Empty");
			return null;
		}
		Random rand = new Random();
		int index = rand.nextInt(mapCities.size());
		return mapCities.remove(index);
	}

	private void getCitiesForMap(String mapName) {

		if (mapName.equals("France")){
			readCitiesFileAndStore("citiesList/citiesFr.txt");
			
		} else if (mapName.equals("Sweden")){
			readCitiesFileAndStore("citiesList/citiesSW.txt");
			
		} else if (mapName.contentEquals("Italy")){
			readCitiesFileAndStore("citiesList/citiesIt.txt");
			
		} else if (mapName.contentEquals("Germany")){
			readCitiesFileAndStore("citiesList/citiesGe.txt");
			
		} else if (mapName.contentEquals("Greece")){
			readCitiesFileAndStore("citiesList/citiesGr.txt");
			
		} else if (mapName.equals("Africa")) {
			readCitiesFileAndStore("citiesList/capitolsAfrica.txt");
			
		} else if (mapName.equals("Europe")) {
			readCitiesFileAndStore("citiesList/capitolsEurope1.txt");
			
		} else if (mapName.equals("Asia")) {
			readCitiesFileAndStore("citiesList/capitolsAsia.txt");
			
		} else if (mapName.equals("Oceania")) {
			readCitiesFileAndStore("citiesList/capitolsOceania.txt");
			
		} else if (mapName.equals("North America")) {
			readCitiesFileAndStore("citiesList/NordAmerika.txt");
		}
		
	}

	private void readCitiesFileAndStore(String filePath) {
		try {
			scanner = new Scanner(new File(filePath));
			System.out.println("-----------------Read from file-------------------");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scanner.hasNextLine()){
			String cityName = scanner.nextLine();
			double lat = Double.parseDouble(scanner.nextLine());
			double lng = Double.parseDouble(scanner.nextLine());

			Point2D.Double cityPoint = new Point2D.Double (lat, lng);

			City city = new City(cityName, cityPoint);
			mapCities.add(city);
			System.out.println("added: " + city.getName() + ", " + city.getPoint().toString());
		}
		System.out.println("-----------------------------------------------");

	}

}
