package gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.teamdev.jxmaps.LatLng;
import multiplayer.GameControllerMP;
import singleplayer.GameControllerSP;
import singleplayer.MapHolderSP;


public class CountriesGameMenu extends JPanel implements ActionListener, WindowListener {
	private JButton btnStart = new JButton("Start");
	private Image image;
	
	// Alternatives for choosing map and gametype
	private JTextField roundsTxtFld = new JTextField();
	private JLabel roundsLbl = new JLabel("Rounds (1-20):");
	private String[] options = { "Choose country", "France", "Sweden", "Italy", "Germany", "Greece" };
	private JComboBox<String> cmbChooseMap = new JComboBox<String>(options); // Choose map combo box
	private JButton goBackBtn = new JButton("Go Back");
	private JFrame frame;
	private MapHolderSP createMap;
	private GameControllerSP gameControllerSP;
	private GameControllerMP gameControllerMP;
	private int rounds;
	private String opposingPlayer;

	public CountriesGameMenu() {
		initWindow();
	}
	
	public CountriesGameMenu(String opposingPlayer, GameControllerMP gameControllerMP) {
		this.opposingPlayer = opposingPlayer;
		this.gameControllerMP = gameControllerMP;
		initWindow();
		
	}
	
	private void initWindow() {
		add(goBackBtn);
		
		// used for choosing between countries
		cmbChooseMap.setBounds(110, 100, 120, 23);
		add(cmbChooseMap);
		
		add(roundsLbl);
		roundsTxtFld.setColumns(3);
		add(roundsTxtFld);
		btnStart.setBounds(380, 300, 120, 23);
		add(btnStart);
		btnStart.addActionListener(this);
		cmbChooseMap.addActionListener(this);
		goBackBtn.addActionListener(this);
		try {
			image = ImageIO.read(new File("images/worldmap2.jpg"));
			JLabel label = new JLabel(new ImageIcon(image));
			this.add(label);

		} catch (IOException ex) {
			System.out.print("Image exception" + ex);
		}
	}

	public void showUI() {
		frame = new JFrame("GeoGo-mapLocator");
		Image image = new ImageIcon("images/globe.16x16.png").getImage();
		frame.setIconImage(image);
		frame.addWindowListener(this);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnStart && opposingPlayer != null){

			if (checkRounds(roundsTxtFld.getText())){

				if (cmbChooseMap.getSelectedItem() == "Choose country"){
					JOptionPane.showMessageDialog(null, "Error: Please select a map", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}

				else if (cmbChooseMap.getSelectedItem() == "France"){
					frame.dispose();

					Point2D.Double point = new Point2D.Double(46.4534, 2.2404);
					gameControllerMP.requestGame(point, 5.9, opposingPlayer, rounds, "France");
					
				} else if (cmbChooseMap.getSelectedItem() == "Sweden"){
					frame.dispose();

					Point2D.Double point = new Point2D.Double(64.00, 20.00);
					gameControllerMP.requestGame(point, 4.9, opposingPlayer, rounds, "Sweden");
					
				} else if (cmbChooseMap.getSelectedItem() == "Italy"){
					frame.dispose();

					Point2D.Double point = new Point2D.Double(41.50, 12.50);
					gameControllerMP.requestGame(point, 6.1, opposingPlayer, rounds, "Italy");
					
				} else if (cmbChooseMap.getSelectedItem() == "Germany"){
					frame.dispose();

					Point2D.Double point = new Point2D.Double(51.133481, 10.018343);
					gameControllerMP.requestGame(point, 5.5, opposingPlayer, rounds, "Germany");
					
				} else if (cmbChooseMap.getSelectedItem() == "Greece"){
					frame.dispose();

					Point2D.Double point = new Point2D.Double(37.983810, 23.727539);
					gameControllerMP.requestGame(point, 6.0, opposingPlayer, rounds, "Greece");
				}
			}

		} else if (e.getSource() == btnStart){

			if (checkRounds(roundsTxtFld.getText())){

				if (cmbChooseMap.getSelectedItem() == "Choose country"){
					JOptionPane.showMessageDialog(null, "Error: Please select a map", "Error Message",
							JOptionPane.ERROR_MESSAGE);
					
				} else if (cmbChooseMap.getSelectedItem() == "France"){
					frame.dispose();

					LatLng latlng = new LatLng(46.4534, 2.2404);
					gameControllerSP = new GameControllerSP(5.9, latlng, "France", rounds);
					
				} else if (cmbChooseMap.getSelectedItem() == "Sweden"){
					frame.dispose();

					LatLng latlng = new LatLng(64.00, 20.00);
					gameControllerSP = new GameControllerSP(4.9, latlng, "Sweden", rounds);
					
				} else if (cmbChooseMap.getSelectedItem() == "Italy"){
					frame.dispose();

					LatLng latlng = new LatLng(41.50, 12.50);
					gameControllerSP = new GameControllerSP(6.1, latlng, "Italy", rounds);
					
				} else if (cmbChooseMap.getSelectedItem() == "Germany"){
					frame.dispose();

					LatLng latlng = new LatLng(51.133481, 10.018343);
					gameControllerSP = new GameControllerSP(5.5, latlng, "Germany", rounds);
					
				} else if (cmbChooseMap.getSelectedItem() == "Greece"){
					frame.dispose();

					LatLng latlng = new LatLng(37.983810, 23.727539);
					gameControllerSP = new GameControllerSP(6.0, latlng, "Greece", rounds);
				}
			}

		} else if (e.getSource() == goBackBtn) {
			frame.dispose();
		}
	}

	private boolean checkRounds(String str) {
		int val;
		try {
			val = Integer.parseInt(str);

			if (val > 20 || val <= 0) {
				JOptionPane.showMessageDialog(null, "Error: Please enter number from 1 to 20", "Error Message",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			rounds = val;
			return true;

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error: Please enter a integer", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
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