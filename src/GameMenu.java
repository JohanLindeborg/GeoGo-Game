package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import shared.User;

public class GameMenu extends JPanel implements ActionListener {
	private JButton btnGameRules = new JButton("Game rules");// Show game rules
	private JButton btnStart = new JButton("Start");// Start game
	private ClientGUI gui = new ClientGUI();
	// Alternatives for choosing map and gametype
	private JLabel lblMap; // Choose map
	private JLabel lblGameType; // Choose gametype
	private String[] options = { "Africa", "etc" };
	private JComboBox<String> cmbChooseMap = new JComboBox<String>(options); // Choose map combo box
//	private JComboBox cmbGameType; // Choose game type combo box
	private LinkedList<User> usersOnline = new LinkedList<User>();
	private JComboBox<User> cmbChooseUser = new JComboBox<User>();

	public GameMenu() {

		btnGameRules.setBounds(110, 300, 120, 23);
		add(btnGameRules);
		btnStart.setBounds(380, 300, 120, 23);
		add(btnStart);

		cmbChooseMap.setBounds(110, 100, 120, 23);
		add(cmbChooseMap);
		cmbChooseUser.setBounds(380, 100, 120, 23);
		add(cmbChooseUser);

		btnGameRules.addActionListener(this);
		btnStart.addActionListener(this);
		cmbChooseMap.addActionListener(this);
		cmbChooseUser.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
