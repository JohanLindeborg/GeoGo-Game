package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CustomButton extends JPanel implements MouseListener {

		private static final long serialVersionUID = 1L;
		private ArrayList array = new ArrayList();

		public CustomButton() {
			addMouseListener(this);
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
			fireEvent(new ActionEvent(this, 0, null));
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void addActionListener(ActionListener listener) {
			array.add(listener);
		}

		private void fireEvent(ActionEvent event) {
			for (int i = 0; i < array.size(); i++) {
				ActionListener listener = (ActionListener) array.get(i);
				listener.actionPerformed(event);
			}
		}
	}