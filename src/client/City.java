package client;

import com.sun.javafx.scene.paint.GradientUtils.Point;
/**
 * Simple class that represents a city with a name and a location
 * @author Adam
 *
 */
public class City {
	
	private String name; 
	private Point coordinates;
	
	public City(String name, Point coordinates) {
		this.name = name;
		this.coordinates = coordinates;
	}

	public Point getCoordinates() {
		return coordinates;
	}
	
	public String getName() {
		return name;
	}
}
