package gui;

import java.awt.*;

import multiplayer.GameControllerMP;

/**
 * This class is used to make a {@link CustomButton} with an image.
 * 
 * @author Andreas Holm
 *
 */
public class ImageButton extends CustomButton {

	private static final long serialVersionUID = 1L;
	Image image;

	public ImageButton(Image image) {
		super();
		this.image = image;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		if (image != null) {
			g2D.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		} else {
			System.out.println("image == null");
		}
	}
}