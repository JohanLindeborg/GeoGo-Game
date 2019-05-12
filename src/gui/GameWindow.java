package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

public class GameWindow extends JFrame{
	
	private GraphicsEnvironment ge;
	private int width;
	private int height;
	private Rectangle windowBounds;
	
	public GameWindow(MapView map) {
		
		// sets the size according to screen size
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		windowBounds = ge.getMaximumWindowBounds();
        width = (int) windowBounds.getWidth();
        height = (int) windowBounds.getHeight();
        this.setSize(width-100, (height/4)*3);
        this.setLocation(0, 0);
        
		this.setTitle("GameWindow");
		
        this.add(map, BorderLayout.CENTER);
        this.setVisible(true);
	}
	
	public Rectangle getWindowBounds() {
		return windowBounds;
	}
	

}
