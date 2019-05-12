package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

//import shared.*;

public class ClientGUI extends JPanel implements ActionListener {
	
	private Controller controller;
	
	private JButton bnStart = new JButton("Start Game");
	private JButton bnExit = new JButton("Exit Game");
	private StartMenu startMenu = new StartMenu(); // This is one of my JPanels
	private JPanel cards; // Parent for cardlayout
	private JLabel geoGo;
	private JFrame frame;
	private CardLayout cl;
	
	private Image image;
	private GraphicsEnvironment ge;
	
//	To add a component to a container that a CardLayout object manages, specify a string that identifies the component being added. 
	final static String STARTGAMEPANEL = "Start Game";


	public ClientGUI() {
		
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();
		
		setPreferredSize(new Dimension(bounds.width, bounds.height)); // The games outer panel
		
		// Setting position 
		bnStart.setBounds((bounds.width/2)-70, (bounds.height/2)-20, 140, 40);
		bnExit.setBounds((bounds.width/2)-70, (bounds.height/2)+40, 140, 40);
		
		// Adding actionlisteners
		bnStart.addActionListener(this);
		bnExit.addActionListener(this);
		
		// Adding button
		this.add(bnStart);
		this.add(bnExit);

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
//		cards = new JPanel(new CardLayout()); // Create the panel(parent?) that contains the "cards".
//		cl = (CardLayout) (cards.getLayout());// Nödvändig? Ja annars nullpointer, måste ha getlayout
//		cards.add(startMenu, STARTGAMEPANEL); // lägger denna till startmenu med namnet startgamepanel? VIKTIG??!!
		
		// How to set JPanel to class StartMenu

		BufferedImage image = null;
		try {
		    image = ImageIO.read(new File("images/world.jpg"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		JLabel imageLbl = new JLabel();
		imageLbl.setBounds(0, 0, bounds.width, bounds.height);
		
		Image dimg = image.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(),Image.SCALE_SMOOTH);
		
		imageLbl.setIcon((new ImageIcon(dimg)));
		this.add(imageLbl);
	}

	void showUI() {
		frame = new JFrame("ClientGUI");
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
//			cl.show(cards, STARTGAMEPANEL); // Error: wrong parent, why?
			new StartMenu().showUI();
		
			frame.dispose();
//			"deck" you want to use as the first parameter, then a "card" from that deck as the second parameter.
		} // Vad händer här?
		else if(input.equals(bnExit)) {
			frame.dispose();
		}
	}

	public static void main(String[] args) {
		ClientGUI gui = new ClientGUI();
		gui.showUI();
	}
}