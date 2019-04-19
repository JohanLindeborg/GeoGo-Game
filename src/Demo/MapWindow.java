package Demo;

import javax.swing.JFrame;

import com.teamdev.jxmaps.LatLng;

public class MapWindow extends JFrame{

	private CreateMap baseMap = new CreateMap(3.0,new LatLng(53.3439,23.0622));
	
	public MapWindow() {
		this.setTitle("API Map Test:  EUROPE");
		
		this.add(baseMap.getMap());
		
		this.pack();
		this.setVisible(true);
		this.setSize(600, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	
	
	
	
	
	public void changemap(double zoom, LatLng mapCenter) {
		baseMap = new CreateMap(zoom, mapCenter);
		this.add(baseMap.getMap());
		
	}
}
