package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Instructions extends JPanel {
	private static final long serialVersionUID = 1L;

	private String text;
	private String text2;
	private String text3;
	private Image i;
	private JFrame frame;
	private GraphicsEnvironment ge;
	private JTextArea wrapArea;
	private final Font font;

	public Instructions() {
		// Getting the size of the screen
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();

		// The games outer panel
		setPreferredSize(new Dimension(bounds.width - 200, bounds.height - 200));

		// Getting the image
		i = new ImageIcon("images/instructions.png").getImage();
		JLabel imageLbl = new JLabel();
		setLayout(null);
		imageLbl.setBounds(400, 0, bounds.width - 600, bounds.height - 200);
		Image dimg = i.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(), Image.SCALE_SMOOTH);
		imageLbl.setIcon((new ImageIcon(dimg)));
		this.add(imageLbl);

		// Setting the text of instructions
		JLabel textLbl = new JLabel();
		textLbl.setBounds(0, 0, bounds.width - 200, bounds.height - 200);
		Font tr = new Font("TimesRoman", Font.PLAIN, 16);
		Font tr2 = new Font("TimesRoman", Font.BOLD, 16);			
		
		text = "\n" + "\n" +"\n" + "\n" +  "GeoGo-game instructions (for multiplayer):"  + "\n";

		text2 = "\n" + "\n" + "Step one: Start a game." + "\n" + "\n"
				+ "Step two: Guess the location of the city being given." + "\n"
				+ "Keep on eye on the time: you only have 15 seconds." + "\n"
				+ "Your guess is displayed with a blue marker. " + "\n" + "\n"
				+ "Step three: Wait for your opponent to make their move." + "\n"
				+ "Your opponents guess is displayed with a red marker. " + "\n" + "\n"
				+ "Step four: The correct spot of the city is displayed with " + "\n" + "a green dot on the map." + "\n"
				+ "\n" + "\n";

		text3 = "The winner is the person who's guess where " + "\n" + "closest to the correct position on the map.";

		wrapArea = new JTextArea(text+ text2+ text3, bounds.width - 200, bounds.height - 200);
		wrapArea.setBounds(0, 0, bounds.width - 300, bounds.height - 200);
		this.font = new Font(text, Font.BOLD, 14);

		textLbl.add(wrapArea);
		textLbl.setLayout(null);
		wrapArea.setFont(font);		

		this.add(textLbl);
	}

	void showUI() {
		frame = new JFrame("ClientGUI");
		frame.setLayout(new BorderLayout());
		frame.add(BorderLayout.CENTER, this);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Instructions ins = new Instructions();
		ins.showUI();
	}
}
