package gui2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GameMenu2 extends JFrame {
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public GameMenu2() {

		initFrame();
		setIconImage(Toolkit.getDefaultToolkit().getImage(StartMenu2.class.getResource("/images/globe.16x16.png")));
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(421, 34, 565, 417);
		panel.add(panel_1);

		JButton btnBack = new JButton();
		btnBack.addActionListener(e ->{
			new StartMenu2();
		});
		btnBack.setIcon(new ImageIcon(GameMenu2.class.getResource("/images/back-arrow.png")));
		btnBack.setFont(new Font("Arial", Font.BOLD, 20));
		btnBack.setBounds(27, 34, 170, 60);
		panel.add(btnBack);

		JButton btnStart = new JButton("Start game");
		btnStart.setBackground(Color.GREEN);
		btnStart.setForeground(Color.WHITE);
		btnStart.setFont(new Font("Arial", Font.BOLD, 20));
		btnStart.setBounds(773, 581, 170, 60);
		panel.add(btnStart);

		JButton btnRulesSngle = new JButton("Rules");
		btnRulesSngle.setForeground(Color.WHITE);
		btnRulesSngle.setFont(new Font("Arial", Font.BOLD, 20));
		btnRulesSngle.setBounds(515, 581, 170, 60);
		panel.add(btnRulesSngle);

		JLabel lblChooseMap = new JLabel("Choose Map");
		lblChooseMap.setFont(new Font("Arial", Font.BOLD, 20));
		lblChooseMap.setBounds(40, 159, 170, 35);
		panel.add(lblChooseMap);

		JRadioButton rdbtnWorld = new JRadioButton("World");
		rdbtnWorld.setFont(new Font("Arial", Font.BOLD, 20));
		buttonGroup.add(rdbtnWorld);
		rdbtnWorld.setBounds(40, 215, 127, 25);
		panel.add(rdbtnWorld);

		JRadioButton rdbtnEurope = new JRadioButton("Europe");
		rdbtnEurope.setFont(new Font("Arial", Font.BOLD, 20));
		buttonGroup.add(rdbtnEurope);
		rdbtnEurope.setBounds(40, 261, 127, 25);
		panel.add(rdbtnEurope);

		JRadioButton rdbtnAsia = new JRadioButton("Asia");
		rdbtnAsia.setFont(new Font("Arial", Font.BOLD, 20));
		buttonGroup.add(rdbtnAsia);
		rdbtnAsia.setBounds(40, 307, 127, 25);
		panel.add(rdbtnAsia);

		JRadioButton rdbtnAfrica = new JRadioButton("Africa");
		rdbtnAfrica.setFont(new Font("Arial", Font.BOLD, 20));
		buttonGroup.add(rdbtnAfrica);
		rdbtnAfrica.setBounds(40, 351, 127, 25);
		panel.add(rdbtnAfrica);

		JRadioButton rdbtnNorthAmerica = new JRadioButton("North America");
		rdbtnNorthAmerica.setFont(new Font("Arial", Font.BOLD, 20));
		buttonGroup.add(rdbtnNorthAmerica);
		rdbtnNorthAmerica.setBounds(40, 393, 170, 25);
		panel.add(rdbtnNorthAmerica);

		JRadioButton rdbtnSouthAmerica = new JRadioButton("South America");
		rdbtnSouthAmerica.setFont(new Font("Arial", Font.BOLD, 20));
		buttonGroup.add(rdbtnSouthAmerica);
		rdbtnSouthAmerica.setBounds(40, 439, 181, 25);
		panel.add(rdbtnSouthAmerica);

		JRadioButton rdbtnOceania = new JRadioButton("Oceania");
		rdbtnOceania.setFont(new Font("Arial", Font.BOLD, 20));
		buttonGroup.add(rdbtnOceania);
		rdbtnOceania.setBounds(40, 484, 127, 25);
		panel.add(rdbtnOceania);
		getContentPane().add(panel, BorderLayout.CENTER);

		this.setVisible(true);

//		LatLng mapCenter = new LatLng(53.3439, 23.0622);
//		double zoomLevel = 3.0;
//		CreateMap cm = new CreateMap(zoomLevel, mapCenter);
//		MapView map = cm.getMap();
//
//		add(map, BorderLayout.CENTER);

		initPanel();

	}

	private void initPanel() {
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	private void initFrame() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1041, 750);
		this.setLocation(400, 180);

	}

	public static void main(String[] args) {
		new GameMenu2();
	}
}