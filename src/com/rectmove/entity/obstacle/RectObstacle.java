package com.rectmove.entity.obstacle;

import java.awt.Color;

import com.rectmove.entity.Entity;

public class RectObstacle extends Entity {

	public RectObstacle(int x, int y, int width, int height) {
		super(x, y, width, height, Color.white);
	}

	@Override
	public void update() {
	}

}
