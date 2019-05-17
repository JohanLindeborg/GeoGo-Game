package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class MultiPlayerMenu extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	// Lägger till username till denna ArrayList från JOptionPane som poppra upp när man väljer multiplayer
	// från startmenu menyn. 
	// String username = JOptionPane
	// MultiPlayerMenu.arrayL.add(username); 
	
	public static ArrayList<String> arrayL = new ArrayList<>();
	String[] str = new String[arrayL.size()];
	JList<String> list = new JList<>(arrayL.toArray(str));

	private JFrame frame;
	private JLabel userLabel;

	private ImageButton btnHostGame;
	private Image i;

	public MultiPlayerMenu() {

		// setup list
		list = new JList(arrayL.toArray(str));
		list.setVisibleRowCount(arrayL.size());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // kan bara välja en user
//		list.setBounds(330, 90, 220, 250);
		list.setFont(new Font("Century Gothic", Font.BOLD, 16));
		list.setForeground(Color.WHITE);
		list.setBackground(Color.black);

		// setup CONEECTED USERS JLabel
		userLabel = new JLabel("CONNECTED USERS");
		userLabel.setFont(new Font("Century Gothic", Font.BOLD, 22));
		userLabel.setForeground(Color.white);

		// setup custom button
		ImageIcon ii = new ImageIcon("images/btnHostGame.png");
		btnHostGame = new ImageButton(ii.getImage());
		
		// Adding actionlisteners
		btnHostGame.addActionListener(this);

		setPreferredSize(new Dimension(600, 400));

		// sets background, resizes to fit window
		i = new ImageIcon("images/world1337.jpg").getImage();
		JLabel imageLbl = new JLabel();
		
		// setting bounds
		imageLbl.setBounds(0, 0, 600, 400);
		btnHostGame.setBounds((int)(imageLbl.getWidth() / 10), (int)(imageLbl.getWidth() / 13), 200, 60);
		userLabel.setBounds((int)(imageLbl.getWidth() / 2), (int)(imageLbl.getWidth() / 13), 240, 30);
		list.setBounds((int)(imageLbl.getWidth() / 2), 90, 220, 250);
		
		Image dimg = i.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(), Image.SCALE_SMOOTH);
		imageLbl.setIcon((new ImageIcon(dimg)));
		
		this.add(imageLbl);
		imageLbl.add(btnHostGame);
		imageLbl.add(userLabel);
		imageLbl.add(list);
//		imageLbl.add(new JScrollPane(list)); // Fick inte denna att fungera

		showUI();
	}

	void showUI() {

		frame = new JFrame("MultiPlayeMenu");
		Image imageI = new ImageIcon("images/globe.16x16.png").getImage();
		frame.setIconImage(imageI);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHostGame) {
			new GameSetup();
		}
	}
}