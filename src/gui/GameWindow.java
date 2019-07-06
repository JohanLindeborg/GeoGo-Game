package gui;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

/**
 * This class creates a full screen window for the map part of the game while playing.
 * Receives a MapView object through the constructor which represents a map with the chosen settings
 * and displays it on a JFrame.
 * 
 * @author Johan Lindeborg
 *
 */

public class GameWindow extends JFrame {

	private GraphicsEnvironment ge;
	private int width;
	private int height;
	private Rectangle windowBounds;

	public GameWindow(MapView map){

		// sets the size according to screen size
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		windowBounds = ge.getMaximumWindowBounds();
		width = (int) windowBounds.getWidth();
		height = (int) windowBounds.getHeight();
		this.setSize(width - 100, (height / 4) * 3);
		this.setLocation(0, 0);
		this.setTitle("GameWindow");
		this.add(map, BorderLayout.CENTER);
		Image image = new ImageIcon("images/globe.16x16.png").getImage();
		this.setIconImage(image);
		this.setVisible(true);
	}

	public Rectangle getWindowBounds(){
		return windowBounds;
	}
}