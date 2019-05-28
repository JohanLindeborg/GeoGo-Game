package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import multiplayer.GameControllerMP;

public class MultiPlayerMenu extends JPanel implements ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;
	
	// L�gger till username till denna ArrayList fr�n JOptionPane som poppra upp n�r man v�ljer multiplayer
	// fr�n startmenu menyn. 
	// String username = JOptionPane
	// MultiPlayerMenu.arrayL.add(username); 
	
	private String[] str;
	private JList<String> userlist = new JList<String>(new String[]{"No connected users"});

	private JFrame frame;
	private JLabel userLabel;

	private ImageButton btnHostGame;
	private Image i;
	
	private GameControllerMP controllerMP;

	public MultiPlayerMenu(GameControllerMP controllerMP) {

		this.controllerMP = controllerMP;
		// setup list
		userlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // kan bara v�lja en user
//		list.setBounds(330, 90, 220, 250);
		userlist.setFont(new Font("Century Gothic", Font.BOLD, 16));
		userlist.setForeground(Color.WHITE);
		userlist.setBackground(Color.black);

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
		userlist.setBounds((int)(imageLbl.getWidth() / 2), 90, 220, 250);
		
		Image dimg = i.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(), Image.SCALE_SMOOTH);
		imageLbl.setIcon((new ImageIcon(dimg)));
		
		this.add(imageLbl);
		imageLbl.add(btnHostGame);
		imageLbl.add(userLabel);
		imageLbl.add(userlist);
//		imageLbl.add(new JScrollPane(list)); // Fick inte denna att fungera

		showUI();
	}

	void showUI() {

		frame = new JFrame("MultiPlayeMenu");
		Image imageI = new ImageIcon("images/globe.16x16.png").getImage();
		frame.setIconImage(imageI);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.addWindowListener(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void updateUsers(ArrayList<String> users) {

		str = new String[users.size()];
		str = users.toArray(str);

		
		userlist.setVisibleRowCount(users.size());
		
		userlist.setListData(str);
		//userlist.setListData(str);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== btnHostGame && userlist.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(this, "Please select an opposing player", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else if(e.getSource() == btnHostGame && userlist.getSelectedValue().equals(controllerMP.getUserName())) {
			JOptionPane.showMessageDialog(this, "Cant play against yourself, please select another user", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		else if (e.getSource() == btnHostGame) {
			new GameSetup(userlist.getSelectedValue(), controllerMP);
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		controllerMP.disconnect();
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