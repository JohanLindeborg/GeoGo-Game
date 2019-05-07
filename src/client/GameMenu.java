package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class GameMenu extends JPanel implements ActionListener {
	private JButton btnStart = new JButton("Start Playing");// Start game
	private Image image;
	// Alternatives for choosing map and gametype
//	private JLabel lblMap; // Choose map
//	private JLabel lblGameType; // Choose gametype
	private String[] options = { "Choose country", "France", "Sweden", "Italy", "Germany", "Greece" };
	private JComboBox<String> cmbChooseMap = new JComboBox<String>(options); // Choose map combo box
	private LinkedList<User> usersOnline = new LinkedList<User>();
	private JComboBox<User> cmbChooseUser = new JComboBox<User>();
	private JFrame frame;

	public GameMenu() {
		// The games outer panel
		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 400));
		this.getLayout();

		btnStart.setBounds(330, 145, 140, 40);
		this.add(btnStart);
		cmbChooseMap.setBounds(130, 145, 140, 40);
		this.add(cmbChooseMap);
		cmbChooseUser.setBounds(380, 100, 120, 23);
		add(cmbChooseUser);

		btnStart.addActionListener(this);
		cmbChooseMap.addActionListener(this);
		cmbChooseUser.addActionListener(this);
		try {
			image = ImageIO.read(new File("images/worldmap2.jpg"));
			JLabel label = new JLabel(new ImageIcon(image));
			this.add(label);

		} catch (IOException ex) {
			System.out.print("Image exception" + ex);
		}
	}

	void showUI() {
		frame = new JFrame("GeoGo-mapLocator");
//		setUserMenu();
//      setUserList();
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void dispose() {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object input = e.getSource();
		if (input.equals(cmbChooseMap)) {
			dispose();
		} else if (input.equals(btnStart)) {
			dispose();
		}

	}
}