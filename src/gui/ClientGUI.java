package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

//import client.StartMenu2;


public class ClientGUI extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JButton btnStart = new JButton("Start Game");
	private JButton btnExit = new JButton("Exit Game");

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
		btnStart.setBounds((bounds.width/2)-70, (bounds.height/2)-20, 140, 40);
		btnExit.setBounds((bounds.width/2)-70, (bounds.height/2)+40, 140, 40);
		
		// Adding actionlisteners
		btnStart.addActionListener(this);
		btnExit.addActionListener(this);
		
		// Adding button
		this.add(btnStart);
		this.add(btnExit);

		// Create the panel that contains the "cards":
		this.setLayout(cl); // Put the cardlayout on the panel

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
		Image image = new ImageIcon("images/globe.16x16.png").getImage();
		frame.setIconImage(image);
//		setUserMenu();
//      setUserList();
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

//	 Add new local user
//	public void addNewUser(String name) {
//		String name = "";
//		while (name.equals(""))
//			name = JOptionPane.showInputDialog("Enter username:");
//		setUserSelected(name);
//		setUserMenu();
//	} 
//	void selectUser(ActionEvent e) { // Select a user from the menu to connect with
//		JRadioButtonMenuItem mi = (JRadioButtonMenuItem) e.getSource();
//		setUserSelected(mi.getText());
//	} */
//	public void setUserSelected(String sUser) { // What to do when a user has been selected from the menu
//		controller.setLocalUser(sUser);
//	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnStart) {

			new StartMenu().showUI();
			frame.dispose();
		} 
		
		else if(e.getSource() == btnExit) {
			frame.dispose();
		}
	}

	public static void main(String[] args) {
		ClientGUI gui = new ClientGUI();
		gui.showUI();
	}
}