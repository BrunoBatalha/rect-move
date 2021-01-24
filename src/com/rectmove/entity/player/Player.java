package com.rectmove.entity.player;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.rectmove.entity.Entity;

public abstract class Player extends Entity {

	private int speedX;
	private int speedY;
	private int speedDefaultX;
	private int speedDefaultY;

	public Player(int x, int y, int width, int height, int speedX, int speedY, Color color, int speedDefaultX,
				 	int speedDefaultY) {
		super(x, y, width, height, color);
		this.speedX = speedX;
		this.speedY = speedY;
		this.speedDefaultX = speedDefaultX;
		this.speedDefaultY = speedDefaultY;
	}

	@Override
	public void update() {
		this.setX(this.getX() + this.getSpeedX());
		this.setY(this.getY() + this.getSpeedY());
	}
	
	public abstract void keyTyped(KeyEvent e);

	public abstract void keyPressed(KeyEvent e);

	public abstract void keyReleased(KeyEvent e);
	
	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int nextX() {
		return this.getX() + this.getSpeedX();
	}

	public int nextY() {
		return this.getY() + this.getSpeedY();
	}

	public int getSpeedDefaultX() {
		return speedDefaultX;
	}

	public int getSpeedDefaultY() {
		return speedDefaultY;
	}	
}
