package gui;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gameLogicMP.GameControllerMP;
import sharedFiles.UpdateConnectedUsersMsg;

public class StartMenu extends JPanel implements ActionListener {

	private Image i;
	private GraphicsEnvironment ge;
	private JFrame frame;
	private CardLayout cl;

	private ImageButton btnSingle;
	private ImageButton btnMulti;
	private ImageButton btnIntructions;
	private ImageButton btnExitGame;
	
	private JTextField usernameTxtField;
	private JTextField ipTxtField;
	private GameControllerMP controllerMP;
	
	public StartMenu() {

		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();

		ImageIcon imagSngle = new ImageIcon("images/btnSingleplayer.png");
		btnSingle = new ImageButton(imagSngle.getImage());

		ImageIcon imageMulti = new ImageIcon("images/btnMultiplayer.png");
		btnMulti = new ImageButton(imageMulti.getImage());

		ImageIcon imageIns = new ImageIcon("images/btnInstructionsGame.png");
		btnIntructions = new ImageButton(imageIns.getImage());

		ImageIcon imageExit = new ImageIcon("images/btnExitGame.png");
		btnExitGame = new ImageButton(imageExit.getImage());

		// this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(bounds.width, bounds.height)); // The games outer panel

		btnSingle.setBounds((bounds.width / 2) - 100, (bounds.height / 2) - 160, 200, 60);
		btnMulti.setBounds((bounds.width / 2) - 100, (bounds.height / 2) - 40, 200, 60);
		btnIntructions.setBounds((bounds.width / 2) - 100, (bounds.height / 2) + 80, 200, 60);
		btnExitGame.setBounds((bounds.width / 2) - 100, (bounds.height / 2) + 200, 200, 60);
		
		// Add actionelisteners
		btnSingle.addActionListener(this);
		btnMulti.addActionListener(this);
		btnIntructions.addActionListener(this);
		btnExitGame.addActionListener(this);

		i = new ImageIcon("images/world1337.jpg").getImage();

		JLabel imageLbl = new JLabel();
		imageLbl.setBounds(0, 0, bounds.width, bounds.height);
		Image dimg = i.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(), Image.SCALE_SMOOTH);
		imageLbl.setIcon((new ImageIcon(dimg)));
		
		// Add buttons to this label
		imageLbl.add(btnSingle);
		imageLbl.add(btnMulti);
		imageLbl.add(btnIntructions);
		imageLbl.add(btnExitGame);
		
		this.add(imageLbl);
	}

	public void showUI() {

		frame = new JFrame("StartMenu");
//		setUserMenu();
//	    setUserList();
		Image image = new ImageIcon("images/globe.16x16.png").getImage();
		frame.setIconImage(image);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnSingle) {
			singlePlayer();
		} else if (e.getSource() == btnMulti && btnMulti.isEnabled()) {
				multiPlayer();
		} else if (e.getSource() == btnIntructions) {
				Instructions ins = new Instructions();
				ins.showUI();
		} else if (e.getSource() == btnExitGame) {
			frame.dispose();
		}
	}

//		public LinkedList<User> multiPlayer() {
//			String name = JOptionPane.showInputDialog(null, "Create a user");
//			// har lagt till enny användare
//			gui.addNewUser(name);
//			// ska uppdatera listan nånstans och sen göra om den till toArray(new User[0]); (dvs User array)
//			list.toArray(new User[0]);
//			return list;
//		}

	public void singlePlayer() {
		// String name = JOptionPane.showInputDialog(null, "Create a user");
		new GameSetup();
//			gui.addNewUser(name);
//			gui.showUI();
	}
	
	public void multiPlayer() {
        enableMultiBtn(false);


		//username = JOptionPane.showInputDialog("Choose username: ");
		//MultiPlayerMenu.arrayL.add(username);
		//new MultiPlayerMenu();
		
		JTextField usernameTxtField = new JTextField();
		JTextField ipTxtField = new JTextField();
		Object[] message = { "Username:", usernameTxtField, "Server IP Address:", ipTxtField };

		int option = JOptionPane.showConfirmDialog(null, message, "Connect to server", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
		    if (!usernameTxtField.getText().equals("") && !ipTxtField.getText().equals("")) {
		        System.out.println("Trying to connect to server...");
		       
		        controllerMP = new GameControllerMP(usernameTxtField.getText(),ipTxtField.getText(), this);
		        
		        
		    } else {
		        System.out.println("login failed");
		        enableMultiBtn(true);

		    }
		} else {
		    System.out.println("Login canceled");
		    enableMultiBtn(true);
		}
		
//		new GameSetup();
//		frame.dispose();
		// new MultiPlayerWindow(username);
		
	}
	
	public void enableMultiBtn(boolean bool) {
		btnMulti.setEnabled(bool);
		System.out.println("MultiBtn = "+bool);
	}
	

	private void gameRules() {
		// new GameMenu();
		JOptionPane.showMessageDialog(null,
				"The task of a player in GeoGo is to estimate the locations of different cities on a map. " + "\n"
						+ "A score is then given the player as a result of the difference in distance between the estimated spot and the actual location."
						+ "\n" + "Players can choose what part of the world him or her wishes to play in." + "\n" + "\n"
						+ "Upgrade your geographical knowledge now!");
	}
}
