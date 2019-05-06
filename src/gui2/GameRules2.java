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

public class GameRules extends JFrame {

	private final ButtonGroup buttonGroup = new ButtonGroup();

	public GameRules() {

		initFrame();

		this.setVisible(true);

//		LatLng mapCenter = new LatLng(53.3439, 23.0622);
//		double zoomLevel = 3.0;
//		CreateMap cm = new CreateMap(zoomLevel, mapCenter);
//		MapView map = cm.getMap();
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
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Single plamyer");
		lblNewLabel.setBounds(64, 65, 56, 16);
		panel.add(lblNewLabel);
	}
}