package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import multiplayer.GameControllerMP;

public class EndGameMenu extends JPanel implements WindowListener, ActionListener {

	private static final long serialVersionUID = 1L;;
	private JLabel winner = new JLabel("WINNER:", SwingConstants.CENTER);
	private JLabel pl1 = new JLabel("player1", SwingConstants.CENTER);
	private JLabel pl2 = new JLabel("player2", SwingConstants.CENTER);
	private JLabel dist1 = new JLabel("distance1", SwingConstants.CENTER);
	private JLabel dist2 = new JLabel("distance2", SwingConstants.CENTER);
	private JPanel pnlText = new JPanel();
	private JButton ok = new JButton("ok");
	private GameControllerMP controller;
	private ImageButton btnOk;
	private Font font = new Font("Century Gothic", Font.BOLD, 25);
	private JFrame frame;

	public EndGameMenu(String player1, String player2, double player1Distance, double player2Distance, String winner, GameControllerMP controller) {
		setBackground(Color.WHITE);
		this.controller = controller;
		this.setLayout(new BorderLayout());
		this.pl1.setText(player1);
		this.pl2.setText(player2);
		this.dist1.setText(String.valueOf("Total distance: "+player1Distance));
		this.dist2.setText(String.valueOf("Total distance: "+player2Distance));
		this.winner.setText("Winner: "+winner);
		this.winner.setFont(font);
		
		pl1.setFont(font);
		pl2.setFont(font);
		dist1.setFont(font);
		dist2.setFont(font);
		pnlText.setLayout(new GridLayout(2, 2));
		pl1.setVerticalAlignment(SwingConstants.BOTTOM);
		pl2.setVerticalAlignment(SwingConstants.BOTTOM);
		pnlText.add(pl1);
		pnlText.add(pl2);
		dist1.setVerticalAlignment(SwingConstants.TOP);
		dist2.setVerticalAlignment(SwingConstants.TOP);
		pnlText.add(dist1);
		pnlText.add(dist2);

		setPreferredSize(new Dimension(700, 500));
		ok.setFont(font);
		
		this.add(ok, BorderLayout.NORTH);
		this.add(pnlText, BorderLayout.CENTER);
		this.add(this.winner, BorderLayout.SOUTH);
		
		ok.addActionListener(this);

		showUI();
	}

	void showUI() {

		frame = new JFrame("WinnerWindow");
		Image imageI = new ImageIcon("images/globe.16x16.png").getImage();
		frame.setIconImage(imageI);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ok) {
			controller.closeGameWindows();
			frame.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		controller.closeGameWindows();
		frame.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
