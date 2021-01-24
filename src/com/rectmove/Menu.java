package com.rectmove;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Menu {

	private int option;
	private int maxOptions;
	private Color color;

	public Menu() {
		this.option = 0;
		this.maxOptions = 2;
		this.color = Color.BLACK;
	}

	public Menu(int option) {
		this.option = option;
		this.maxOptions = 2;
		this.color = Color.WHITE;
	}

	public void draw(Graphics g) {
		g.setColor(this.color);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		
		FontMetrics fm = g.getFontMetrics();
		int x = (Constants.WIDTH_WINDOW - fm.stringWidth(Constants.OPTION_MENU_1)) / 2;
		int y = 200;
		int height = 25;

		g.drawString(Constants.OPTION_MENU_1, x, y);

		x = (Constants.WIDTH_WINDOW - fm.stringWidth(Constants.OPTION_MENU_2)) / 2;
		g.drawString(Constants.OPTION_MENU_2, x, y + height);

		g.drawRect(x - 10, y - 18 + height * (option + 1) - height , 50, 25);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			this.goToDown();

		if (e.getKeyCode() == KeyEvent.VK_UP)
			this.goToUp();
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			this.selectOption();
	}

	private void goToDown() {
		this.option++;

		if (this.option >= this.maxOptions)
			this.option = 0;
	}

	private void goToUp() {
		this.option--;

		if (this.option < 0)
			this.option = 1;
	}
	
	private void selectOption() {
		if(this.option == 0) {
			Gameloop.gameState = GameStateEnum.PLAYING;
			Gameloop.initTimer();
		}
		if(this.option == 1) {
			System.exit(0);
		}
	}

}
