package com.rectmove.entity.player;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class Rect extends Player {

	public Rect(int x, int y, int height, int width, int speedX, int speedY, Color color, int speedDefaultX, int speedDefaultY) {
		super(x, y, height, width, speedX, speedY, color, speedDefaultX, speedDefaultY);
	}

	@Override
	public void update() {
		super.update();
	}
	
	public void stopSpeed() {
		this.setSpeedX(0);
		this.setSpeedY(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (this.getSpeedX() == 0 && this.getSpeedY() == 0) {			
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				this.setSpeedY(this.getSpeedDefaultY());
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				this.setSpeedY(-this.getSpeedDefaultY());
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				this.setSpeedX(this.getSpeedDefaultX());
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				this.setSpeedX(-this.getSpeedDefaultX());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}