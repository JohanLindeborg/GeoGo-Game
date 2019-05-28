package multiplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

import singleplayer.GameControllerSP;

public class GameInfoWindowMP extends JPanel {

	private static final long serialVersionUID = 1L;


	private JFrame windowFrame;

	private GraphicsEnvironment ge;
	private int width;
	private int height;

	
	
	private JLabel roundsLbl;
	private JLabel clickOnCityLbl;
	private JLabel timerLbl;
	private JLabel infoLbl;
	
	private JLabel pl1Lbl;
	private JLabel pl2Lbl;
	private JLabel scorePl1Lbl;
	private JLabel scorePl2Lbl;
	private JLabel distPl1Lbl;
	private JLabel distPl2Lbl;
	private JLabel markerLbl;

	private Font fontText = new Font(Font.DIALOG, Font.BOLD, 30);
	private Font fontCountdown = new Font(Font.DIALOG, Font.BOLD, 64);
	private Font playerInfoFont = new Font(Font.DIALOG, Font.BOLD, 26);

	private JLabel imageLbl;

	private String currentCity;

	private int totalRounds;

	public GameInfoWindowMP(int totalRounds) {

		this.totalRounds = totalRounds;
		// btnStartGame = new JButton("Press to start game");

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
		// this.setLayout(new BorderLayout());
		imageLbl.setLayout(new GridLayout(4, 3));
		
		
		
		pl1Lbl = new JLabel("TEST");
		pl2Lbl = new JLabel("TEST");
		scorePl1Lbl = new JLabel("Score: 0");
		scorePl2Lbl = new JLabel("Score: 0");
		distPl1Lbl = new JLabel("Distance: 0 km");
		distPl2Lbl = new JLabel("Distance: 0 km");
		markerLbl = new JLabel();

		

		timerLbl = new JLabel();
		clickOnCityLbl = new JLabel("Click on: ");
		infoLbl = new JLabel("Click on the map to continue");

		// Label Alignment
		timerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		roundsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		roundsLbl.setVerticalAlignment(SwingConstants.TOP);
		infoLbl.setHorizontalAlignment(SwingConstants.CENTER);
		clickOnCityLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		pl2Lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		scorePl2Lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		distPl2Lbl.setHorizontalAlignment(SwingConstants.RIGHT);

		// Sets label font
		timerLbl.setFont(fontCountdown);
		timerLbl.setForeground(Color.GREEN.darker());
		clickOnCityLbl.setFont(fontText);
		roundsLbl.setForeground(Color.RED.brighter());
		roundsLbl.setFont(new Font(Font.DIALOG, Font.BOLD, 34));
		infoLbl.setFont(fontText);
		
		pl1Lbl.setFont(playerInfoFont);
		pl2Lbl.setFont(playerInfoFont);
		scorePl1Lbl.setFont(playerInfoFont);
		scorePl2Lbl.setFont(playerInfoFont);
		distPl1Lbl.setFont(playerInfoFont);
		distPl2Lbl.setFont(playerInfoFont);

//		btnStartGame.addActionListener(e -> {
//			btnStartGame.setVisible(false);
//			btnStartGame.setEnabled(false);
//			gameController.startNewRound();
//			clickOnCityLbl.setHorizontalAlignment(SwingConstants.CENTER);
//			imageLbl.add(clickOnCityLbl);
//		});

		// Add labels
		
		//Row 1
		imageLbl.add(pl1Lbl);
		imageLbl.add(clickOnCityLbl);
		imageLbl.add(pl2Lbl);
		
		//Row2
		imageLbl.add(scorePl1Lbl);
		imageLbl.add(timerLbl);
		imageLbl.add(scorePl2Lbl);
		
		//Row3
		imageLbl.add(distPl1Lbl);
		imageLbl.add(infoLbl);
		imageLbl.add(distPl2Lbl);
		
		//Row4
		imageLbl.add(markerLbl);
		imageLbl.add(roundsLbl);


	}

	private void showUI() {
		windowFrame = new JFrame("Info Panel");
		windowFrame.setSize(new Dimension(width - 100, 200));

		//windowFrame.setUndecorated(true);
		windowFrame.setLocation(0, ((height / 4) * 3) + 15);
		windowFrame.setLayout(new BorderLayout());
		windowFrame.add(this, BorderLayout.CENTER);
		windowFrame.pack();
//		windowFrame.setUndecorated(true);
		windowFrame.setVisible(true);
	}
	
	public void destroy() {
		windowFrame.dispose();
	}

	public JFrame getFrame() {
		return windowFrame;
	}

	public void setClickCityLbl(String cityName) {
		currentCity = cityName;
		clickOnCityLbl.setText("Click on: " + cityName);
	}

	public void setDistanceLbls(String distpl1, String distpl2) {
		distPl1Lbl.setText(distpl1 + " km from " + currentCity);
		distPl2Lbl.setText(distpl2 + " km from " + currentCity);
	}
	
	public void removeDistanceLbls() {
		distPl1Lbl.setText("");
		distPl2Lbl.setText("");
	}

	public void setCurrentRound(int round) {
		roundsLbl.setText("Round " + round + " of " + totalRounds);
	}

	public void setScoreLbls(String scorePl1, String scorePl2) {
		scorePl1Lbl.setText(scorePl1);
		scorePl2Lbl.setText(scorePl2);
	}
	
	public void setPlayers(String pl1, String pl2) {
		pl1Lbl.setText(pl1);
		pl2Lbl.setText(pl2);
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

	public void setInfoLbl(String txt) {
		infoLbl.setText(txt);
	}

	public void removeInfoLbl() {
		infoLbl.setText("");
	}
	
	public static void main(String[] args) {
		GameInfoWindowMP test = new GameInfoWindowMP(10);
	}
}