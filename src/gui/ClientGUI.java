package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ClientGUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private ImageButton btnExitGame;
	private ImageButton btnStartGame;
	private JLabel lblGeoGo;

	private Image i;
	private GraphicsEnvironment ge;
	
	public ClientGUI() {
		
		// Header
		lblGeoGo = new JLabel("G e o G o");
		lblGeoGo.setFont(new Font("Century Gothic", Font.BOLD, 100));
		lblGeoGo.setForeground(Color.white);
		
		// Getting the size of the screen
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();
		
		// Making custom imagebuttons and setting position
		ImageIcon imageIcon = new ImageIcon("images/btnExitGame.png");
		btnExitGame = new ImageButton(imageIcon.getImage());
		ImageIcon ii = new ImageIcon("images/btnStartGame.png");
		btnStartGame = new ImageButton(ii.getImage());

		btnStartGame.setBounds((bounds.width / 2) - 100, (bounds.height / 2) - 70, 200, 60);
		btnExitGame.setBounds((bounds.width / 2) - 100, (bounds.height / 2) + 50, 200, 60);
		
		// Adding actionlisteners
		btnExitGame.addActionListener(this);
		btnStartGame.addActionListener(this);
		
		setPreferredSize(new Dimension(bounds.width, bounds.height)); // The games outer panel
		
		lblGeoGo.setBounds((int) ((bounds.getWidth() / 2))-235 ,  130, 900, 200);
	
		i = new ImageIcon("images/world1337.jpg").getImage();

		JLabel imageLbl = new JLabel();
		imageLbl.setBounds(0, 0, bounds.width, bounds.height);
		Image dimg = i.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(), Image.SCALE_SMOOTH);
		
		imageLbl.setIcon((new ImageIcon(dimg)));
		imageLbl.add(btnStartGame);
		imageLbl.add(btnExitGame);
		imageLbl.add(lblGeoGo);
		this.add(imageLbl);
	}

	void showUI() {

		frame = new JFrame("ClientGUI");
		Image imageI = new ImageIcon("images/globe.16x16.png").getImage();
		frame.setIconImage(imageI);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(this.getPreferredSize());
		frame.setUndecorated(true);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnStartGame){
			new StartMenu().showUI();
			frame.dispose();
		}

		else if (e.getSource() == btnExitGame){
			System.exit(0);
		}
	}

	public static void main(String[] args){
		ClientGUI gui = new ClientGUI();
		gui.showUI();
	}
}