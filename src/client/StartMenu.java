
package client;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class StartMenu extends JPanel implements ActionListener {
	private JButton bnSingle = new JButton("Singleplayer");
	private JButton bnMulti = new JButton("Multiplayer");
	private JButton bnRules = new JButton("Game Instructions");
	private Image image;
	private GraphicsEnvironment ge;
	private JFrame frame;
	
	private JButton goBackBtn = new JButton("Exit Game");

	
	public StartMenu() {
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();
		
		//this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(bounds.width, bounds.height)); // The games outer panel

		// Add buttons to this panel
		bnSingle.setBounds(bounds.width/2, bounds.height/2, 130, 30);// GridbagLayout, dynamiska layouter
		add(bnSingle);
		bnMulti.setBounds(bounds.width/2, (bounds.height/2)+80, 130, 30);
		add(bnMulti);
		bnRules.setBounds(bounds.width/2, (bounds.height/2)+160, 130, 30);
		add(bnRules);
		add(goBackBtn);

		// Add actionelisteners
		bnSingle.addActionListener(this);
		bnMulti.addActionListener(this);
		bnRules.addActionListener(this);
		goBackBtn.addActionListener(this);
		
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
		/*
		try {
			image = ImageIO.read(new File("images/worldmap2.jpg"));
			
			JLabel label = new JLabel(new ImageIcon(image));
			this.add(label);
			label.setBounds(0, 0, bounds.width, bounds.height);

		} catch (IOException ex) {
			System.out.print("Image exception" + ex);
		}
		*/
	}

	void showUI() {
		frame = new JFrame("GeoGo-mapLocator");
//			setUserMenu();
//	      setUserList();
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object input = e.getSource();
		if (input.equals(bnSingle)) {
				singlePlayer();
		} else if (input.equals(bnMulti)) {
//				multiPlayer();
		} else if (input.equals(bnRules)) {
			gameRules();
		}
		else if(input.equals(goBackBtn)) {
			frame.dispose();
		}
	}

//		public LinkedList<User> multiPlayer() {
//			String name = JOptionPane.showInputDialog(null, "Create a user");
//			// har lagt till enny användare
//			gui.addNewUser(name);
//			// ska uppdatera listan nånstans och sen göra om den till toArray(new User[0]); (dvs User array)
	//
//			list.toArray(new User[0]);
//			return list;
//		}

	public void singlePlayer() {
		String name = JOptionPane.showInputDialog(null, "Create a user");
		GameMenu game = new GameMenu();
		game.showUI();
//			gui.addNewUser(name);
//			gui.showUI();
	}

	public void gameRules() {
		JOptionPane.showMessageDialog(null, "The task of a player of GeoGo is to estimate the locations of different cities on a worldmap. "+ "\n" 
								+ "A score is then given the player as a result of the difference in distance between the estimated spot and the actual location."+"\n" + 
								"Players can choose what part of the world him or her wishes to play in."+"\n"+"\n"+"Upgrade your geographical knowledge now!");
	}
}
