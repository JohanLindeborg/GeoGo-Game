package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

public class GameSetup extends JFrame implements ActionListener, WindowListener {

	private JLabel countryInst = new JLabel("Choose this option to play on country maps");
	private JPanel countryPnl = new JPanel();
	private JLabel countryImg = new JLabel();
	private JButton chooseCountry = new JButton("Choose map size: country");

	private JLabel continentInst = new JLabel("Choose this option to play on continent maps");
	private JPanel continentPnl = new JPanel();
	private JLabel continentImg = new JLabel();
	private JButton chooseContinent = new JButton("Choose map size: Continent");

	public GameSetup() {

		this.setLayout(new GridLayout(2, 2, 10, 10));
		Image image = new ImageIcon("images/globe.16x16.png").getImage();
		setIconImage(image);
		this.setTitle("Choose map size:");

		initWindow();

		this.addWindowListener(this);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setBackground(Color.BLACK);
		this.getContentPane().setBackground(Color.BLACK);
		this.setVisible(true);
	}

	private void initWindow() {

		// countryPnl.add(countryInst);
		countryPnl.add(chooseCountry);
//		countryImg.setIcon(loadImage("images/countryImg.png"));
		countryImg.setIcon(loadImage("images/countryItalyImage.PNG")); // Blev för pixlig :/

		// continentPnl.add(continentInst);
		continentPnl.add(chooseContinent);
		continentImg.setIcon(loadImage("images/continentAfricaImage.PNG"));

		// Adds to gridlayout with position specified by GridLayout
		this.add(countryPnl);
		this.add(countryImg);
		this.add(continentPnl);
		this.add(continentImg);

		chooseContinent.addActionListener(this);
		chooseCountry.addActionListener(this);
	}

	// loads and scales image
	private ImageIcon loadImage(String filepath) {
		BufferedImage image = null;
		BufferedImage scaledImage = null;

		try {
			image = ImageIO.read(new File(filepath));

			scaledImage = new BufferedImage(200, 200, image.getType());
			Graphics2D graphics2D = scaledImage.createGraphics();
			graphics2D.drawImage(image, 0, 0, 200, 200, null);
			graphics2D.dispose();

		} catch (IOException ex) {
			System.out.print("Image exception" + ex);
		}
		return new ImageIcon(scaledImage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == chooseCountry) {
			new CountriesGameMenu().showUI();
			this.dispose();
		} else if (e.getSource() == chooseContinent) {
			new ContinentsGameMenu().showUI();
			this.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
}