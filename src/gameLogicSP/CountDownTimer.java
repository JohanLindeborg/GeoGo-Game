package gameLogicSP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import gameLogicMP.GameControllerMP;

public class CountDownTimer {

	private GameControllerSP controllerSP;
	private GameControllerMP controllerMP;

	private Timer countDownTimer;
	private TimerListener timerListener;
	private int countdown;

	public CountDownTimer(GameControllerSP controller) {
		this.controllerSP = controller;

		timerListener = new TimerListener();
		countDownTimer = new Timer(1000, timerListener);
		countdown = 15;
	}
	public CountDownTimer(GameControllerMP controller) {
		this.controllerMP = controller;

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
				
				if(controllerSP == null) {
					controllerMP.updateCountDown(countdown);
				}
				else {
					controllerSP.updateCountDown(countdown);
				}
			}
		}
	}
}