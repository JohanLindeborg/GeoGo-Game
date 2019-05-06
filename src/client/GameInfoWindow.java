package client;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

public class GameInfoWindow extends JFrame {
	
    private GraphicsEnvironment ge;
    private int width;
    private int height;
	
	public GameInfoWindow() {
		
		// sets the size according to screen size
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle windowBounds = ge.getMaximumWindowBounds();
        width = (int) windowBounds.getWidth();
        height = (int) windowBounds.getHeight();
        
        this.setSize(width-100, 200);
        
		this.setTitle("Info Panel");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
        
        this.setLocation(0,height-150);
        this.setVisible(true);
	}
}
