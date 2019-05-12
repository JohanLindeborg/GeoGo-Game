package client;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

//import shared.User;

public class ContinentsGameMenu extends JPanel implements ActionListener, WindowListener {
	private JButton btnStart = new JButton("Start");// Start game
	private Image image;
	// Alternatives for choosing map and gametype
	private JTextField roundsTxtFld = new JTextField();
	private JLabel roundsLbl = new JLabel("Rounds (1-20):");
	
	private String[] options = { "Choose continent", "Europe", "Africa", "Asia", "North America", "South America", "Oceania" };
	private JComboBox<String> cmbChooseMap = new JComboBox<String>(options); // Choose map combo box
	private JButton goBackBtn = new JButton("Go Back");
//	private JComboBox cmbGameType; // Choose game type combo box
	// private LinkedList<User> usersOnline = new LinkedList<User>();
	// private JComboBox<User> cmbChooseUser = new JComboBox<User>();

	private JFrame frame;

	CreateMap createMap;
	GameControllerSP gameControllerSP;
	private int rounds;

	public ContinentsGameMenu() {

		add(goBackBtn);
		// used for choosing between countries
		cmbChooseMap.setBounds(110, 100, 120, 23);
		add(cmbChooseMap);
		// used for choosing between continents

		add(roundsLbl);

		roundsTxtFld.setColumns(3);
		add(roundsTxtFld);

		btnStart.setBounds(380, 300, 120, 23);
		add(btnStart);

		// cmbChooseUser.setBounds(380, 100, 120, 23);
		// add(cmbChooseUser);

		btnStart.addActionListener(this);
		cmbChooseMap.addActionListener(this);
		goBackBtn.addActionListener(this);
		// cmbChooseUser.addActionListener(this);
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
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {

			if (checkRounds(roundsTxtFld.getText())) {
				
				if (cmbChooseMap.getSelectedItem() == "Choose continent") {
					JOptionPane.showMessageDialog(null, "Error: Please select a map", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(cmbChooseMap.getSelectedItem() == "Africa") {
					LatLng latlng = new LatLng(6.0, 18.0);
					gameControllerSP = new GameControllerSP(3.5, latlng, "Africa", rounds);
					
				} else if (cmbChooseMap.getSelectedItem() == "Europe") {
					frame.dispose();

					LatLng latlng = new LatLng(53.5775, 23.106111);
					gameControllerSP = new GameControllerSP(4.0, latlng, "Europe", rounds);

				} else if (cmbChooseMap.getSelectedItem() == "Asia") {
					frame.dispose();

					LatLng latlng = new LatLng(42.5775, 80.0);
					gameControllerSP = new GameControllerSP(3.1, latlng, "Asia", rounds);
				}

				else if (cmbChooseMap.getSelectedItem() == "North America") {
					frame.dispose();

					LatLng latlng = new LatLng(49.0, -103.0);
					gameControllerSP = new GameControllerSP(3.3, latlng, "North America", rounds);
				}

				else if (cmbChooseMap.getSelectedItem() == "South America") {
					frame.dispose();

					LatLng latlng = new LatLng(-22.0, -59.0);
					gameControllerSP = new GameControllerSP(3.5, latlng, "South America", rounds);
				}

				else if (cmbChooseMap.getSelectedItem() == "Oceania") {
					frame.dispose();

					LatLng latlng = new LatLng(-24.0, 134.0);
					gameControllerSP = new GameControllerSP(4.0, latlng, "Oceania", rounds);
					
				}
				
				
			}
		
		}
		 
		else if (e.getSource() == goBackBtn) {
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
	
	