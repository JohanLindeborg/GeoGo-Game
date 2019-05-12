package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class CountDownTimer {

	private GameControllerSP controller;

	private Timer countDownTimer;
	private TimerListener timerListener;
	private int countdown;

	public CountDownTimer(GameControllerSP controller) {
		this.controller = controller;

		timerListener = new TimerListener();
		countDownTimer = new Timer(1000, timerListener);
		countdown = 15;
	}

	public void startTimer() {
		countdown = 16;
		countDownTimer.restart();
	}

	public void stopTimer() {
		countDownTimer.stop();
	}

	public int getCountDown() {
		return countdown;
	}

	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (countdown <= 0) {
				stopTimer();
			} else {
				countdown -= 1;
				controller.updateCountDown(countdown);
			}
		}
	}
}