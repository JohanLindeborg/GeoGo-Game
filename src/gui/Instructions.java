package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Instructions extends JPanel {
	private static final long serialVersionUID = 1L;

	private String text, text2, text3;

	private Image i;
	private JFrame frame;
	private GraphicsEnvironment ge;
	private JTextArea wrapArea;
	private final Font font, font2, font3;

	public Instructions() {
		// Getting the size of the screen
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle bounds = ge.getMaximumWindowBounds();

		// The games outer panel
		setPreferredSize(new Dimension(bounds.width - 200, bounds.height - 200));

		// Getting the image
		i = new ImageIcon("images/instructions.PNG").getImage();
		JLabel imageLbl = new JLabel();
		setLayout(null); // Sätter min JPanel till null, overriding BouderLayout
		imageLbl.setBounds(400, 0, bounds.width - 600, bounds.height - 200);
		Image dimg = i.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(), Image.SCALE_SMOOTH);
		imageLbl.setIcon((new ImageIcon(dimg)));
		this.add(imageLbl);

		// Setting the text of instructions
		JLabel textLbl = new JLabel();
		textLbl.setBounds(0, 0, bounds.width - 200, bounds.height - 200);
		Font tr = new Font("TimesRoman", Font.PLAIN, 16);
		Font tr2 = new Font("TimesRoman", Font.BOLD, 16);

//		text = "\n" +"\n"+ "When a game is started the players are given one city"+"\n"
//				+ " and a timeframe of 15 seconds."+"\n"
//				+ "Every players task is to guess the location "+"\n"
//				+ "of the city that is being given. "+"\n"
//				+ "A clock is displayed in the center of the window, "+"\n"
//				+ "which ticks down until it reached 0."+"\n"
//				+"\n"+"\n"
//				+ "After one player has guessed location or the time is up "+"\n"
//				+ "it's up to the other player to guess the location of the same city."+"\n"
//				+ "When both players has made their guesses, the positions of the guesses "+"\n"
//				+ "and the actual location is showed on the map. "
//				+ "Player number one has a red marker, player number two has a blue marker "
//				+ "and the actual location is displayed with a green dot."+"\n"
//				+"\n"+"\n"
//				+ "One game is over when all rounds are over."+"\n"
//				+"The player with most guesses the closest to the real target winns!!"+"\n"
//				+"\n"+"\n"
//				+ "Play now and upgrade your geographical knowledge!"+
//				
		
		text = "\n" + "\n" +"\n" + "\n" +  "GeoGo-game instructions:"  + "\n";

		text2 = "\n" + "\n" + "Step one: Start a game." + "\n" + "\n"
				+ "Step two: Guess the location of the city being given." + "\n"
				+ "Keep on eye on the time: you only have 15 seconds." + "\n"
				+ "Your guess is displayed with a blue marker. " + "\n" + "\n"
				+ "Step three: Wait for your opponent to make their move." + "\n"
				+ "Your opponents guess is displayed with a red marker. " + "\n" + "\n"
				+ "Step four: The correct spot of the city is displayed with " + "\n" + "a green dot on the map." + "\n"
				+ "\n" + "\n";

		text3 = "The winner is the person who's guess where " + "\n" + "closest to the correct position on the map.";


		wrapArea = new JTextArea(text+ text2+ text3, bounds.width - 200, bounds.height - 200);// Sätter texten på en
																							// JtextArea
		wrapArea.setBounds(0, 0, bounds.width - 300, bounds.height - 200);// sätter position på texten
		this.font = new Font(text, Font.BOLD, 14);
		this.font2 = new Font(text2, Font.PLAIN, 8);
		this.font3 = new Font(text3, Font.PLAIN, 14);


		textLbl.add(wrapArea);// Sätter JTextArea på JLabel
		textLbl.setLayout(null);
		wrapArea.setFont(font);
//		wrapArea.setFont(font2);
//		wrapArea.setFont(font3);

		

		this.add(textLbl);

	}

	void showUI() {

		frame = new JFrame("ClientGUI");
		frame.setLayout(new BorderLayout());
		frame.add(BorderLayout.CENTER, this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Instructions ins = new Instructions();
		ins.showUI();
	}
}
