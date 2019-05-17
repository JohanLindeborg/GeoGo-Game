package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.util.*;


import java.awt.event.*;

class ImageButton extends CustomButton {

	private static final long serialVersionUID = 1L;
	
	Image image;
	
	ImageButton(Image image) {
		super();
		this.image = image;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
	
		if (image != null) {
			g2D.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}	else {
			System.out.println("image == null");
		}
	}
}