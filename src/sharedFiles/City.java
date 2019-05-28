package sharedFiles;

import java.awt.geom.Point2D;
import java.io.Serializable;

import com.sun.javafx.scene.paint.GradientUtils.Point;
import com.teamdev.jxmaps.LatLng;

/**
 * Simple class that represents a city with a name and a location
 * 
 * @author Adam
 *
 */
public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Point2D.Double point;

	public City(String name, Point2D.Double point) {
		this.name = name;
		this.point = point;
	}

	public Point2D.Double getPoint() {
		return point;
	}

	public String getName() {
		return name;
	}
}
