package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import shared.User;

public class StartMenu extends JPanel implements ActionListener {
	private JButton bnSingle = new JButton("Singleplayer");
	private JButton bnMulti = new JButton("Multiplayer");
	private JButton bnRules = new JButton("Game rules");

	public StartMenu() {
		setLayout(new CardLayout());

		// Add buttons to this panel
		bnSingle.setBounds(240, 80, 120, 23);// GridbagLayout, dynamiska layouter
		add(bnSingle);
		bnMulti.setBounds(240, 160, 120, 23);
		add(bnMulti);
		bnRules.setBounds(240, 240, 120, 23);
		add(bnRules);

		// Add actionelisteners
		bnSingle.addActionListener(this);
		bnMulti.addActionListener(this);
		bnRules.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object input = e.getSource();
		if (input.equals(bnSingle)) {
//			singlePlayer();
		} else if (input.equals(bnMulti)) {
//			multiPlayer();
		} else if (input.equals(bnRules)) {
			gameRules();
		}
	}

//	public LinkedList<User> multiPlayer() {
//		String name = JOptionPane.showInputDialog(null, "Create a user");
//		// har lagt till enny användare
//		gui.addNewUser(name);
//		// ska uppdatera listan nånstans och sen göra om den till toArray(new User[0]); (dvs User array)
//
//		list.toArray(new User[0]);
//		return list;
//	}

	public void singlePlayer() {
		String name = JOptionPane.showInputDialog(null, "Create a user");
//		gui.addNewUser(name);
//		gui.showUI();
	}

	public void gameRules() {
		System.out.println("Add what happens when pressed button Game Rules");
		JOptionPane.showMessageDialog(null, "Game rules..");
	}
}