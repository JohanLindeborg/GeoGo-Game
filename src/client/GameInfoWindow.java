package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

public class GameInfoWindow extends JFrame {
	
    private GraphicsEnvironment ge;
    private int width;
    private int height;
    
    private JLabel clickOnCityLbl;
	private JLabel timerLbl;
	private JLabel distanceLbl;
	private JLabel clickToContinueLbl;
	private JPanel southPnl;
	
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
		
		southPnl = new JPanel();
		southPnl.setLayout(new FlowLayout());
		
		this.add(southPnl, BorderLayout.SOUTH);
		
		timerLbl = new JLabel("timer goes here");
		clickOnCityLbl = new JLabel("Click on: ");
		distanceLbl = new JLabel("");
		clickToContinueLbl = new JLabel("Click on the map to continue");
		
		//Esthetical settings
		timerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		clickOnCityLbl.setHorizontalAlignment(SwingConstants.CENTER);
		//distanceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		//distanceLbl.setVerticalAlignment(SwingConstants.CENTER);
		//clickToContinueLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		timerLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		clickOnCityLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
		distanceLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		clickToContinueLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		//this.add(northPnl, BorderLayout.NORTH);
		
		
		//northPnl.add(timerLbl);
		//northPnl.add(clickOnCityLbl);
		this.add(timerLbl, BorderLayout.NORTH);
		this.add(clickOnCityLbl, BorderLayout.CENTER);
		southPnl.add(distanceLbl, BorderLayout.SOUTH);
	}
	
	public void setClickCityLbl(String cityName) {
		currentCity = cityName;
		clickOnCityLbl.setText("Click on: "+cityName);
	}
	
	public void setDistanceLbl(String distance) {
		distanceLbl.setText(distance+" km from "+currentCity);

	}
	public void showContinueLbl() {
		southPnl.add(clickToContinueLbl, BorderLayout.SOUTH);
	}
	
	public void removeContinueLbl() {
		southPnl.remove(clickToContinueLbl);
	}
}
