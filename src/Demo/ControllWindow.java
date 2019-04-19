package Demo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.teamdev.jxmaps.LatLng;

public class ControllWindow extends JFrame {

	private JPanel mainPnl = new JPanel();
	private JPanel buttonPnl = new JPanel();
	private JPanel newMapPnl = new JPanel();
	
	private JButton afrBtn = new JButton("Africa");
	private JButton eurBtn = new JButton("Europe");
	
	private WindowListener listener = new WindowListener();
	private MapWindow map;
	
	public ControllWindow() {
		this.setTitle("Select Map");
		
		mainPnl.setLayout(new BorderLayout());
		buttonPnl.setLayout(new FlowLayout());
		newMapPnl.setLayout(new FlowLayout());
		
		buttonPnl.add(afrBtn);
		buttonPnl.add(eurBtn);
		
		mainPnl.add(buttonPnl, BorderLayout.NORTH);
		mainPnl.add(newMapPnl, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
		this.setSize(200, 600);
		this.setLocation(600, 0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		afrBtn.addActionListener(listener);
		eurBtn.addActionListener(listener);
		
		map = new MapWindow();
		
	}
	private class WindowListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == afrBtn) {
				map.changemap(3.0,new LatLng(2.37 , 16.06));
			}
			if(e.getSource() == eurBtn){
				map.changemap(2.5, new LatLng(53.3439,23.0622));
			}
		}
		
		
		
		
	}
	
	private class MapWindow extends JFrame{

		private CreateMap baseMap = new CreateMap(3.0,new LatLng(53.3439,23.0622));
		
		public MapWindow() {
			this.setTitle("API Map Test:  EUROPE");
			
			this.add(baseMap.getMap());
			
			this.pack();
			this.setVisible(true);
			this.setSize(600, 600);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			
		}
		
		public void changemap(double zoom, LatLng mapCenter) {
			baseMap = new CreateMap(zoom, mapCenter);
			this.add(baseMap.getMap());
			
		}
	}
	
}
