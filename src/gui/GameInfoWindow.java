package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import singleplayer.GameControllerSP;

/**
 * This class is used for keeping and updating the lower frame of the two frames
 * used for the game. This frame shows a users current information of the
 * ongoing game. For singleplayer.
 * 
 * @author johanlindeborg
 * @author Andreas Holm
 *
 */
public class GameInfoWindow extends JPanel {
	private static final long serialVersionUID = 1L;

	private GameControllerSP gameController;
	private JFrame windowFrame;
	private GraphicsEnvironment ge;
	private int width;
	private int height;
	private JLabel roundsLbl;
	private JLabel clickOnCityLbl;
	private JLabel timerLbl;
	private JLabel distanceLbl;
	private JLabel clickToContinueLbl;
	private JPanel southPnl;
	private JLabel markerLabel;
	private Font fontText = new Font(Font.DIALOG, Font.BOLD, 30);
	private Font fontCountdown = new Font(Font.DIALOG, Font.BOLD, 80);
	private JLabel imageLbl;
	private String currentCity;
	private int totalRounds;

	public GameInfoWindow(int totalRounds, GameControllerSP gameController) {

		this.gameController = gameController;
		this.totalRounds = totalRounds;
		roundsLbl = new JLabel("Round 1 of " + totalRounds);

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
		imageLbl.setBounds(0, 0, width - 100, 200);
		Image dimg = image.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(), Image.SCALE_SMOOTH);
		imageLbl.setIcon((new ImageIcon(dimg)));
		this.add(imageLbl);

		this.setSize(width - 100, height / 4);
		this.setName("Info Panel");

		// Initializes buttons and panels.
		initWindow();

		this.setVisible(true);
		showUI();
	}

	private void initWindow() {
		imageLbl.setLayout(new GridLayout(2, 3));
		markerLabel = new JLabel();
		southPnl = new JPanel();
		southPnl.setLayout(new FlowLayout());
		timerLbl = new JLabel();
		clickOnCityLbl = new JLabel("Click on: ");
		distanceLbl = new JLabel("");
		clickToContinueLbl = new JLabel("Click on the map to continue");

		// Label Alignment
		timerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		roundsLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		roundsLbl.setVerticalAlignment(SwingConstants.TOP);
		clickToContinueLbl.setHorizontalAlignment(SwingConstants.CENTER);
		distanceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		markerLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Sets label font
		timerLbl.setFont(fontCountdown);
		timerLbl.setForeground(Color.GREEN.darker());
		clickOnCityLbl.setFont(fontText);
		roundsLbl.setForeground(Color.RED.brighter());
		roundsLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 45));
		distanceLbl.setFont(fontText);
		clickToContinueLbl.setFont(fontText);

		// Add labels
		imageLbl.add(timerLbl);
		imageLbl.add(clickOnCityLbl);
		imageLbl.add(roundsLbl);
		imageLbl.add(clickToContinueLbl);
		imageLbl.add(distanceLbl);
		imageLbl.add(markerLabel);
	}

	private void showUI() {
		windowFrame = new JFrame("Info Panel");
		windowFrame.setSize(new Dimension(width - 100, 200));
		windowFrame.setUndecorated(true);
		windowFrame.setLocation(0, ((height / 4) * 3) + 15);
		windowFrame.setLayout(new BorderLayout());
		windowFrame.add(this, BorderLayout.CENTER);
		windowFrame.pack();
		windowFrame.setVisible(true);
	}

	public JFrame getFrame() {
		return windowFrame;
	}

	public void setClickCityLbl(String cityName) {
		currentCity = cityName;
		clickOnCityLbl.setText("Click on: " + cityName);
	}

	public void setDistanceLbl(String distance) {
		distanceLbl.setText(distance + " km from " + currentCity);
	}

	public void setCurrentRound(int round) {
		roundsLbl.setText("Round " + round + " of " + totalRounds);
	}

	public void removeDistanceLbl() {
		distanceLbl.setText("");
	}

	public void setTimerLbl(int cntDown) {

		timerLbl.setFont(fontCountdown);
		timerLbl.setForeground(Color.GREEN.darker());
		timerLbl.setText(cntDown + "");

		if (cntDown <= 3) {
			timerLbl.setForeground(Color.RED);
		}
		if (cntDown == 0) {
			timerLbl.setFont(fontText);
			timerLbl.setText("Times up!");
		}
	}

	public void showContinueLbl(String txt) {
		clickToContinueLbl.setText(txt);
	}

	public void removeContinueLbl() {
		clickToContinueLbl.setText("");
	}
}