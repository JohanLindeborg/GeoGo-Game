package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.teamdev.jxmaps.LatLng;

public class CreateMapTest extends JFrame {
	
	private JButton africaBtn = new JButton("Africa");
	private JButton europeBtn = new JButton("Europe");
	private BtnListener listener = new BtnListener();
	
	JPanel mapPnl = new JPanel();

	
	private CreateMap map;
	
	public CreateMapTest() {
		
		this.setTitle("API Map Test");
		
		JPanel mainPnl = new JPanel();
		JPanel btnPnl = new JPanel();
		
		mainPnl.setLayout(new BorderLayout());
		btnPnl.setLayout(new FlowLayout());
		
		
		btnPnl.add(africaBtn);
		btnPnl.add(europeBtn);
		
		mainPnl.add(mapPnl, BorderLayout.CENTER);
		mainPnl.add(btnPnl, BorderLayout.SOUTH);
		this.add(mainPnl);
		
		this.setVisible(true);
		this.setSize(400, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		africaBtn.addActionListener(listener);
		europeBtn.addActionListener(listener);
		
	}
	
	
	private class BtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			if(e.getSource() == africaBtn) {
				map = new CreateMap(3.0 ,new LatLng(2.37 ,16.06));
				mapPnl.add(map.getMap());
			}
			else if (e.getSource() == europeBtn) {
				map = new CreateMap( 3.0,new LatLng(53.3439,23.0622));
				mapPnl.add(map.getMap());
			}
		}
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		
		
		CreateMapTest test = new CreateMapTest();
	}

}
