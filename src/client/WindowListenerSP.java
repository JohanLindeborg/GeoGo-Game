package client;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowListenerSP implements WindowListener {

	private GameControllerSP gameController;
	
	public WindowListenerSP(GameControllerSP gameController) {
		this.gameController = gameController;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		gameController.exitGame();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

}
