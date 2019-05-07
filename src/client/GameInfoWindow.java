package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

public class GameInfoWindow extends JFrame {
	
    private GraphicsEnvironment ge;
    private int width;
    private int height;
    
    private JLabel clickOnCityLbl;
	private JLabel timerLbl;
	private JLabel distanceLbl;
	private JPanel northPnl;
	
	private String currentCity;
	
	public GameInfoWindow() {
		
		// sets the size according to screen size
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle windowBounds = ge.getMaximumWindowBounds();
        width = (int) windowBounds.getWidth();
        height = (int) windowBounds.getHeight();
        
        this.setSize(width-100, 200);
        
		this.setTitle("Info Panel");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        initWindow();
        
        this.setLocation(0,height-150);
        this.setVisible(true);
	}
	
	private void initWindow() {
		this.setLayout(new BorderLayout());
		
		northPnl = new JPanel();
		timerLbl = new JLabel("timer goes here");
		clickOnCityLbl = new JLabel("Click on: ");
		distanceLbl = new JLabel("");
		
		//this.add(northPnl, BorderLayout.NORTH);
		
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		
		northPnl.setLayout(fl);
		
		//northPnl.add(timerLbl);
		//northPnl.add(clickOnCityLbl);
		this.add(timerLbl, BorderLayout.NORTH);
		this.add(clickOnCityLbl, BorderLayout.CENTER);
		this.add(distanceLbl, BorderLayout.SOUTH);
	}
	
	public void setClickCityLbl(String cityName) {
		currentCity = cityName;
		clickOnCityLbl.setText("Click on: "+cityName);
	}
	
	public void setDistanceLbl(String distance) {
		distanceLbl.setText("Distance from "+currentCity+": "+distance);
	}
}
