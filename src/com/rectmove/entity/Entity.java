package com.rectmove.entity;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Entity implements IEntity {
	protected int x;
	protected int y;
	protected int width, height;
	protected Color color;

	public Entity(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getXf() {
		return this.getX() + this.getWidth();
	}

	public int getYf() {
		return this.getY() + this.getHeight();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
