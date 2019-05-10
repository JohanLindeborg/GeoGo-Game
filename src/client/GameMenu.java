package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.teamdev.jxmaps.LatLng;

//import shared.User;

public class GameMenu extends JPanel implements ActionListener, WindowListener {
	private JButton btnStart = new JButton("Start");// Start game
	private Image image;
	// Alternatives for choosing map and gametype
	private JLabel lblMap; // Choose map
	private JLabel lblGameType; // Choose gametype
	private JTextField roundsTxtFld = new JTextField();
	private JLabel roundsLbl = new JLabel("Rounds (1-20):");
	private String[] options = { "Choose map", "France","Sweden","Italy","Germany", "Greece" , "Africa" };
	private String[] options1 = {"Choose Continent", "Africa", "Europe"};
	private JComboBox<String> cmbChooseMap = new JComboBox<String>(options); // Choose map combo box
	private JComboBox<String> cmbChooseContinent = new JComboBox<String>(options1);
	private JButton goBackBtn = new JButton("Go Back");
//	private JComboBox cmbGameType; // Choose game type combo box
	//private LinkedList<User> usersOnline = new LinkedList<User>();
	//private JComboBox<User> cmbChooseUser = new JComboBox<User>();
	
	private JFrame frame;
	
	CreateMap createMap;
	GameControllerSP gameControllerSP;
	private int rounds;
	
	public GameMenu() {
		
		add(goBackBtn);
		// used for choosing between countries
		cmbChooseMap.setBounds(110, 100, 120, 23);
		add(cmbChooseMap);
		//used for choosing between continents
		cmbChooseContinent.setBounds(110,75,120,23);
		add(cmbChooseContinent);
		
		add(roundsLbl);
		
		roundsTxtFld.setColumns(3);
		add(roundsTxtFld);
		
		btnStart.setBounds(380, 300, 120, 23);
		add(btnStart);
		
		//cmbChooseUser.setBounds(380, 100, 120, 23);
		//add(cmbChooseUser);

		btnStart.addActionListener(this);
		cmbChooseMap.addActionListener(this);
		cmbChooseContinent.addActionListener(this);
		goBackBtn.addActionListener(this);
		//cmbChooseUser.addActionListener(this);
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
		frame.addWindowListener(this);
//		setUserMenu();
//      setUserList();
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			
			if(checkRounds(roundsTxtFld.getText())) {
				/**
				 * Villkor för att användaren måste välja en karta för att kunna spela
				 */
				if(cmbChooseMap.getSelectedItem() == "Choose map" && cmbChooseContinent.getSelectedItem() == "Choose Continent") {
					JOptionPane.showMessageDialog(null,"Error: Please select a map", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				/**
				 * Villkor för att användaren endast kan välja en karta för att kunna spela
				 * jag har gjort något logiskt fel så fixa gärna det om det är uppenbart.
				 */
//				else if(cmbChooseMap.getSelectedItem() != "Choose map" && cmbChooseContinent.getSelectedItem() !="Choose Contintent") {
//				 JOptionPane.showMessageDialog(null,"Error: You can only choose one map", "Error Message", JOptionPane.ERROR_MESSAGE);
//				}
				else if(cmbChooseMap.getSelectedItem() == "France") {
					frame.dispose();
					
					LatLng latlng = new LatLng(46.4534, 2.2404);
					gameControllerSP = new GameControllerSP(5.7,latlng, "France", rounds);
				}
				else if(cmbChooseMap.getSelectedItem() == "Sweden") {
					frame.dispose();
					
					LatLng latlng = new LatLng(62.00, 15.00);
					gameControllerSP = new GameControllerSP(4.7,latlng, "Sweden", rounds);
				}
				else if(cmbChooseMap.getSelectedItem() == "Italy") {
					frame.dispose();
					
					LatLng latlng = new LatLng(42.50, 12.50);
					gameControllerSP = new GameControllerSP(5.4,latlng, "Italy", rounds);
				}
				else if(cmbChooseMap.getSelectedItem() == "Germany") {
					frame.dispose();
					
					LatLng latlng = new LatLng(51.133481, 10.018343);
					gameControllerSP = new GameControllerSP(5.5,latlng, "Germany", rounds);
				}
				else if(cmbChooseMap.getSelectedItem() == "Greece") {
					frame.dispose();
					
					LatLng latlng = new LatLng(37.983810, 23.727539);
					gameControllerSP = new GameControllerSP(5.5,latlng, "Greece", rounds);
				}
				else if(cmbChooseContinent.getSelectedItem() == "Africa") {
					frame.dispose();
					/**
					 * Center of Africa
					 */
					LatLng latlng = new LatLng(5.65, 26.17); 
					gameControllerSP = new GameControllerSP(3.0,latlng, "Africa", rounds);
				}
				else if(cmbChooseContinent.getSelectedItem() == "Europe") {
					frame.dispose();
					
					LatLng latlng = new LatLng(53.5775, 23.106111);
					gameControllerSP = new GameControllerSP(3.5, latlng, "Europe", rounds);
				}
			}
			
		}
		else if(e.getSource() == goBackBtn) {
			frame.dispose();
		}
	}
	
	private boolean checkRounds(String str) {
		int val;
		try {  
		    val = Integer.parseInt(str); 
		    
		    if (val > 20 || val <= 0){
				JOptionPane.showMessageDialog(null,"Error: Please enter number from 1 to 20", "Error Message", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		    
			rounds = val;
			return true;
		    
		} 
		catch(NumberFormatException e){
		    JOptionPane.showMessageDialog(null,"Error: Please enter a integer", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		return false;
		
	}
	
	public static void main(String[] args) {
		GameMenu test = new GameMenu();
		test.showUI();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		frame.dispose();
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
