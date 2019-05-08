package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

public class GameInfoWindow extends JPanel {
	
    private GraphicsEnvironment ge;
    private int width;
    private int height;
    
    private JLabel clickOnCityLbl;
	private JLabel timerLbl;
	private JLabel distanceLbl;
	private JLabel clickToContinueLbl;
	private JPanel southPnl;
	
	private BufferedImage bfImage;
	private JLabel imageLbl;
	
	private String currentCity;
	
	public GameInfoWindow() {
		
		// sets the size according to screen size
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle windowBounds = ge.getMaximumWindowBounds();
        width = (int) windowBounds.getWidth();
        height = (int) windowBounds.getHeight();
        
        BufferedImage image = null;
		try {
		    image = ImageIO.read(new File("images/InfoWindowImage.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		imageLbl = new JLabel();
		imageLbl.setBounds(0, 0, width-100, 200);
		
		Image dimg = image.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(),Image.SCALE_SMOOTH);
		System.out.println(new ImageIcon(dimg).getIconWidth());
		
		imageLbl.setIcon((new ImageIcon(dimg)));
		this.add(imageLbl);
		//bfImage.getGraphics().drawImage(image, 0, 0, null);
		
        
        this.setSize(width-100, height/4);
        
		this.setName("Info Panel");
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        
		//Initializes buttons and panels.
        initWindow();
        
        this.setVisible(true);
        showUI();
	}
	
	private void initWindow() {
		//this.setLayout(new BorderLayout());
		imageLbl.setLayout(new BorderLayout());
		
		southPnl = new JPanel();
		southPnl.setLayout(new FlowLayout());
		
		
		timerLbl = new JLabel("10 sek");
		clickOnCityLbl = new JLabel("Click on: ");
		distanceLbl = new JLabel("");
		clickToContinueLbl = new JLabel("Click on the map to continue");
		
		//Esthetical settings
		timerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		clickOnCityLbl.setHorizontalAlignment(SwingConstants.CENTER);
		//distanceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		//distanceLbl.setVerticalAlignment(SwingConstants.CENTER);
		//clickToContinueLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		timerLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
		clickOnCityLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
		distanceLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		clickToContinueLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
		//this.add(northPnl, BorderLayout.NORTH);
		
		
		//northPnl.add(timerLbl);
		//northPnl.add(clickOnCityLbl);
		//this.add(southPnl, BorderLayout.SOUTH);
		//this.add(timerLbl, BorderLayout.NORTH);
		//this.add(clickOnCityLbl, BorderLayout.CENTER);
		//southPnl.add(distanceLbl, BorderLayout.SOUTH);
		//southPnl.add(clickToContinueLbl, BorderLayout.SOUTH);
		
		imageLbl.add(timerLbl, BorderLayout.NORTH);
		imageLbl.add(clickOnCityLbl, BorderLayout.CENTER);
		imageLbl.add(distanceLbl, BorderLayout.EAST);
		imageLbl.add(clickToContinueLbl, BorderLayout.WEST);

	}
	
	private void showUI() {
		JFrame frame = new JFrame("Info Panel");
		frame.setSize(new Dimension(width-100, 200));
		
		
		
		frame.setLocation(0,(height/4)*3);
		
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setClickCityLbl(String cityName) {
		currentCity = cityName;
		clickOnCityLbl.setText("Click on: "+cityName);
	}
	
	public void setDistanceLbl(String distance) {
		distanceLbl.setText(distance+" km from "+currentCity);

	}
	
	public void removeDistanceLbl() {
		distanceLbl.setText("");
	}
	
	public void setTimerLbl(String cntDown) {
		timerLbl.setText(cntDown+" Sek");
	}
	
	public void showContinueLbl() {
		clickToContinueLbl.setText("Click on map to continue");
	}
	
	public void removeContinueLbl() {
		clickToContinueLbl.setText("");
	}
	
  
	
}
