package Demo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.teamdev.jxmaps.LatLng;

import client.CreateMap;

public class CreateMapTest extends JFrame {
	
	private JButton africaBtn = new JButton("Africa");
	private JButton europeBtn = new JButton("Europe");
	private BtnListener listener = new BtnListener(this);
	
	JPanel btnPnl = new JPanel();

	
	private CreateMap map;
	
	public CreateMapTest() {
		
		this.setTitle("API Map Test");
		
		this.setLayout(new BorderLayout());
		
		this.add(btnPnl, BorderLayout.SOUTH);
		
		CreateMap baseMap = new CreateMap(3.0 ,new LatLng(2.37 ,16.06));
		this.add(baseMap.getMap(), BorderLayout.CENTER);
		
		this.pack();
		
		this.setVisible(true);
		this.setSize(600, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		africaBtn.addActionListener(listener);
		europeBtn.addActionListener(listener);
		
	}
	
	
	private class BtnListener implements ActionListener{
		
		private JFrame frame;
		public BtnListener(JFrame frame) {
			this.frame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			if(e.getSource() == africaBtn) {
				map = new CreateMap(3.0 ,new LatLng(2.37 ,16.06));
				frame.add(map.getMap(),BorderLayout.CENTER);
			}
			else if (e.getSource() == europeBtn) {
				map = new CreateMap( 3.0,new LatLng(53.3439,23.0622));
				frame.add(map.getMap(), BorderLayout.CENTER);
			}
		}
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		
		
		CreateMapTest test = new CreateMapTest();
	}

}
