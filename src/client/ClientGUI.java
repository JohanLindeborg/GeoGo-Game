package client;

import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

//import shared.*;

public class ClientGUI extends JPanel implements ActionListener {
	private Controller controller;
	private Image image;
	private JButton bnStart = new JButton("Start Game");
	private StartMenu startMenu = new StartMenu(); // This is one of my JPanels
	private JPanel cards; // Parent for cardlayout
	private CardLayout cl;
	private JLabel geoGo;
//	To add a component to a container that a CardLayout object manages, specify a string that identifies the component being added. 
	final static String STARTGAMEPANEL = "Start Game";

	public ClientGUI() {
		setPreferredSize(new Dimension(600, 400)); // The games outer panel
		// Adding button
		bnStart.addActionListener(this);
		this.add(bnStart);
		bnStart.setBounds(240, 150, 140, 40);

		// Add header(JLabel)
		geoGo = new JLabel("GeoGo");
//		geoGo.setSize(300, 200);
//		ggeoGo.setBounds(240, 100, 400, 200);
		geoGo.setLocation(240, 30);
		geoGo.setMinimumSize(new Dimension(30, 20));
		geoGo.setMaximumSize(new Dimension(30, 20));
		geoGo.setPreferredSize(new Dimension(30, 20));
		this.add(geoGo);


		// Create the panel that contains the "cards":
		this.setLayout(cl); // Put the cardlayout on the panel
		cards = new JPanel(new CardLayout()); // Create the panel(parent?) that contains the "cards".
		cl = (CardLayout) (cards.getLayout());// Nödvändig? Ja annars nullpointer, måste ha getlayout
		cards.add(startMenu, STARTGAMEPANEL); // lägger denna till startmenu med namnet startgamepanel? VIKTIG??!!

		// How to set JPanel to class StartMenu

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
//		frame.add(geoGo);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

//		The first argument in the show method is the container the CardLayout controls 
//		— that is, the container of the components the CardLayout manages. 
//		The second argument is the string that identifies the component to show.
//		This string is the same string that was used when adding the component to the container.
	}

	// Add new local user
	/*public void addNewUser(String name) {
//		String name = "";
		while (name.equals(""))
			name = JOptionPane.showInputDialog("Enter username:");
		setUserSelected(name);
//		setUserMenu();
	} /*
/*
	void selectUser(ActionEvent e) { // Select a user from the menu to connect with
		JRadioButtonMenuItem mi = (JRadioButtonMenuItem) e.getSource();
		setUserSelected(mi.getText());
	} */
/*
	public void setUserSelected(String sUser) { // What to do when a user has been selected from the menu
		controller.setLocalUser(sUser);
	}
*/
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object input = e.getSource();
		if (input.equals(bnStart)) { // Use CardLayout här?
//			CardLayout cl = (CardLayout) (cards.getLayout()); // Create a cardlayout with our JPanel cards. Have i added
			// cards to my panel? above
//			cl.show(startMenu, "hej"); // Add our JPanel to the cardlayout.
//			 set the currently visible component.
//		     cl = (CardLayout)(cards.getLayout());
			cl.show(cards, STARTGAMEPANEL); // Error: wrong parent, why?
			StartMenu start = new StartMenu();
			start.showUI();
//			"deck" you want to use as the first parameter, then a "card" from that deck as the second parameter.
		} // Vad händer här?
	}

	public static void main(String[] args) {
		ClientGUI gui = new ClientGUI();
		gui.showUI();
	}
}
