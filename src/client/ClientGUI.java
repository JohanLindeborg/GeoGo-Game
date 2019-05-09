package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ClientGUI extends JPanel implements ActionListener {
	private ControllerGUI controller;
	private Image image;
	private JButton bnStart = new JButton("Start Game");
	private JLabel geoGo;
	private JFrame frame;

	public ClientGUI(ControllerGUI controller) {
		// The games outer panel
		this.controller = controller;
		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 400));
		this.getLayout();

		// Adding button
		bnStart.addActionListener(this);
		this.add(bnStart);
		bnStart.setBounds(240, 150, 140, 40);

		// Add header(JLabel)
		geoGo = new JLabel("GeoGo");
//		geoGo.setSize(300, 200);
//		ggeoGo.setBounds(240, 100, 400, 200);
		geoGo.setLocation(240, 30);
		geoGo.setMinimumSize(new Dimension(30, 20));
		geoGo.setMaximumSize(new Dimension(30, 20));
		geoGo.setPreferredSize(new Dimension(30, 20));
		this.add(geoGo);
		
//		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(StartMenu.class.getResource("images/globe")));

		try {
			image = ImageIO.read(new File("images/worldmap2.jpg"));
			JLabel label = new JLabel(new ImageIcon(image));
			this.add(label);

		} catch (IOException ex) {
			System.out.print("Image exception" + ex);
		}
	}

	public void showUI() {
		frame = new JFrame("GeoGo-mapLocator");
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

	public void dispose() {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object input = e.getSource();
		if (input.equals(bnStart)) {
			StartMenu start = new StartMenu(controller);
			start.showUI();
			dispose();
		}
	}

	public static void main(String[] args) {
		ControllerGUI controller = new ControllerGUI();
		ClientGUI gui = new ClientGUI(controller);
		gui.showUI();
	}
}
