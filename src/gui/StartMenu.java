package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import multiplayer.GameControllerMP;
import multiplayer.MapHolderMP;

/**
 * This class creates the StartGame menu frame with the single, multiplayer,
 * instruction and exit game buttons. If a user choose multiplayer they are
 * asked to enter a username, and the IP-adress for the server. A
 * {@link GameControllerMP} is then created to connect to the server.
 * 
 * @author Andreas Holm
 */

public class StartMenu extends JPanel implements ActionListener {

	private Image i;
	private GraphicsEnvironment ge;
	private JFrame frame;
	private ImageButton btnSingle;
	private ImageButton btnMulti;
	private ImageButton btnIntructions;
	private ImageButton btnExitGame;
	private GameControllerMP controllerMP;

	/**
	 * Constructor, sets this window up with buttons and background picture, adds
	 * actionlisteners.
	 */
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

		setPreferredSize(new Dimension(bounds.width, bounds.height)); // The games outer panel

		btnSingle.setBounds((bounds.width / 2) - 100, (bounds.height / 2) - 160, 200, 60);
		btnMulti.setBounds((bounds.width / 2) - 100, (bounds.height / 2) - 40, 200, 60);
		btnIntructions.setBounds((bounds.width / 2) - 100, (bounds.height / 2) + 80, 200, 60);
		btnExitGame.setBounds((bounds.width / 2) - 100, (bounds.height / 2) + 200, 200, 60);

		// Add actionListeners
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

	/**
	 * sets up the frame
	 */
	public void showUI() {

		frame = new JFrame("StartMenu");
		Image image = new ImageIcon("images/globe.16x16.png").getImage();
		frame.setIconImage(image);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Puts actions when a button is pressed
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSingle) {
			singlePlayer();

		} else if (e.getSource() == btnMulti && btnMulti.isEnabled()) {
			multiPlayer();

		} else if (e.getSource() == btnIntructions) {
			Instructions ins = new Instructions();
			ins.showUI();

		} else if (e.getSource() == btnExitGame) {
			System.exit(0);
		}
	}

	public void singlePlayer() {
		new GameSetup();

	}

	public void multiPlayer() {
		enableMultiBtn(false);

		JTextField usernameTxtField = new JTextField();
		JTextField ipTxtField = new JTextField();
		Object[] message = { "Username:", usernameTxtField, "Server IP Address:", ipTxtField };

		int option = JOptionPane.showConfirmDialog(null, message, "Connect to server", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {

			if (!usernameTxtField.getText().equals("") && !ipTxtField.getText().equals("")) {
				System.out.println("Trying to connect to server...");
				controllerMP = new GameControllerMP(usernameTxtField.getText(), ipTxtField.getText(), this);

			} else {
				System.out.println("login failed");
				enableMultiBtn(true);
			}
		} else {
			System.out.println("Login canceled");
			enableMultiBtn(true);
		}

	}

	/**
	 * 
	 * @param boolean that enables or disables the multiplayerbutton
	 */
	public void enableMultiBtn(boolean bool) {
		btnMulti.setEnabled(bool);
		System.out.println("MultiBtn = " + bool);
	}
}
