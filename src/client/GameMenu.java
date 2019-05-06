package client;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.teamdev.jxmaps.LatLng;

//import shared.User;

public class GameMenu extends JPanel implements ActionListener {
	private JButton btnStart = new JButton("Start");// Start game
	private Image image;
	// Alternatives for choosing map and gametype
	private JLabel lblMap; // Choose map
	private JLabel lblGameType; // Choose gametype
	private String[] options = { "Choose country", "France","Sweden","Italy","Germany", "Greece" };
	private JComboBox<String> cmbChooseMap = new JComboBox<String>(options); // Choose map combo box
//	private JComboBox cmbGameType; // Choose game type combo box
	//private LinkedList<User> usersOnline = new LinkedList<User>();
	//private JComboBox<User> cmbChooseUser = new JComboBox<User>();
	
	CreateMap createMap;
	GameWindow gameWindow;
	GameInfoWindow gameInfoWindow;
	
	public GameMenu() {
		btnStart.setBounds(380, 300, 120, 23);
		add(btnStart);

		cmbChooseMap.setBounds(110, 100, 120, 23);
		add(cmbChooseMap);
		//cmbChooseUser.setBounds(380, 100, 120, 23);
		//add(cmbChooseUser);

		btnStart.addActionListener(this);
		cmbChooseMap.addActionListener(this);
		//cmbChooseUser.addActionListener(this);
		try {
			image = ImageIO.read(new File("images/worldmap2.jpg"));
			JLabel label = new JLabel(new ImageIcon(image));
			this.add(label);

		} catch (IOException ex) {
			System.out.print("Image exception" + ex);
		}
	}

	void showUI() {
		JFrame frame = new JFrame("GeoGo-mapLocator");
//		setUserMenu();
//      setUserList();
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			
			if(cmbChooseMap.getSelectedItem() == "France") {
				LatLng latlng = new LatLng(46.4534, 2.2404);
				createMap = new CreateMap(5.7,latlng);
				
				gameWindow = new GameWindow(createMap.getMap());
				gameInfoWindow = new GameInfoWindow();
			}
		}
	}
	
	public static void main(String[] args) {
		GameMenu test = new GameMenu();
		test.showUI();
	}
}
