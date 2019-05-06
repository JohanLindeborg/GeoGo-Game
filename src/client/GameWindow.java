package client;

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
	
	public GameWindow(MapView map) {
		
		// sets the size according to screen size
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle windowBounds = ge.getMaximumWindowBounds();
        width = (int) windowBounds.getWidth();
        height = (int) windowBounds.getHeight();
        this.setSize(width-100, height-200);

        
		this.setTitle("GameWindow");
		
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(map, BorderLayout.CENTER);
        this.setVisible(true);
	}

}
