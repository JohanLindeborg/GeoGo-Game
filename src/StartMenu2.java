package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartMenu2 extends JFrame {

	private Image mapBackground;
	private JButton btnSinglePlayer;
	private JButton btnMultiPlayer;
	private JButton btnGameRules;
	private JPanel panel;
	private GUIController guiController;

	public StartMenu2() {

		guiController = new GUIController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(StartMenu2.class.getResource("/images/globe.16x16.png")));
		mapBackground = new ImageIcon("src\\images\\worldMap.jpg").getImage();

		initFrame();
		initPanel();
	}

	public void initFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(mapBackground.getWidth(null), mapBackground.getHeight(null));
		setLocation(400, 180);
		//
		System.out.println("width frame: " + mapBackground.getWidth(null));
		System.out.println("height frame: " + mapBackground.getHeight(null));
		System.out.println();
	}

	public void initPanel() {

		panel = new JPanel();
		panel.setLayout(null);

		btnSinglePlayer = new JButton("Single Player");
		btnSinglePlayer.setIgnoreRepaint(true);
		btnSinglePlayer.setIconTextGap(0);
		btnSinglePlayer.setForeground(Color.WHITE);
		btnSinglePlayer.setBackground(Color.BLACK);
		btnSinglePlayer.setFont(new Font("Arial", Font.BOLD, 20));
		btnSinglePlayer.setBounds(425, 210, 170, 60);


		btnSinglePlayer.addActionListener(e -> {
			new GameMenu2();
			System.out.println("btnSinglePlayer was pressed");
//			guiController.showWindow(e.getSource());
		});

		panel.add(btnSinglePlayer);

		btnMultiPlayer = new JButton("Multi Player");
		btnMultiPlayer.setBackground(Color.BLACK);
		btnMultiPlayer.setForeground(Color.WHITE);
		btnMultiPlayer.setFont(new Font("Arial", Font.BOLD, 20));
		btnMultiPlayer.setBounds(425, 290, 170, 60);
	

		btnMultiPlayer.addActionListener(e -> {
			System.out.println("btnMultiPlayer was pressed");
			guiController.showWindow(e.getSource());
		});

		panel.add(btnMultiPlayer);

		btnGameRules = new JButton("Game Rules");
		btnGameRules.setForeground(Color.WHITE);
		btnGameRules.setBackground(Color.BLACK);
		btnGameRules.setFont(new Font("Arial", Font.BOLD, 20));
		btnGameRules.setBounds(425, 450, 170, 60);

		btnGameRules.addActionListener(e -> {
			System.out.println("btnGameRules was pressed");
			new GameRules();
			guiController.showWindow(e.getSource());
		});

		panel.add(btnGameRules);

		getContentPane().add(panel, BorderLayout.CENTER);
		setVisible(true);
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(mapBackground, 0, 0, null);
	}

	public static void main(String[] args) {
		new StartMenu2();
	}
}