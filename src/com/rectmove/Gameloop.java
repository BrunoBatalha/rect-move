package com.rectmove;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import com.rectmove.entity.obstacle.RectObstacle;
import com.rectmove.entity.player.Rect;
import com.rectmove.map.Map;

public class Gameloop extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private static final int HEIGHT_AREA_GUI = 50;
	private static final int HEIGHT_WINDOW = 500 + HEIGHT_AREA_GUI;
	private static final int POS_TEXT_Y = HEIGHT_WINDOW - HEIGHT_AREA_GUI / 2;
	private static final int SECOND_DEFAULT = 10;
	private static final int SPEED_DEFAULT = 5;
	private static int seconds = SECOND_DEFAULT;
	private static Timer timer;
	
	public static GameStateEnum gameState;

	private int remainingMoves;
	private Menu menu;
	private Rect rect;
	private Map map;
	
	private boolean canPress = true; 

	public Gameloop() {
		Gameloop.gameState = GameStateEnum.MENU;
		this.setPreferredSize(new Dimension(Constants.WIDTH_WINDOW, HEIGHT_WINDOW));
		this.addKeyListener(this);
		this.menu = new Menu();
		this.rect = new Rect(0, 0, 50, 50, 0, 0, Color.red, SPEED_DEFAULT, SPEED_DEFAULT);
		this.map = new Map(this.getPreferredSize().width, this.getPreferredSize().height - 50, 0);
		this.remainingMoves = this.map.getQntMoves();
		Gameloop.timer = new Timer();

	}

	public static void initTimer() {
		Gameloop.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Gameloop.seconds--;
			}
		}, 1000, 1000);
	}

	private void reset() {
		this.rect = new Rect(0, 0, 50, 50, 0, 0, Color.red, SPEED_DEFAULT, SPEED_DEFAULT);
		this.remainingMoves = this.map.getQntMoves();
		Gameloop.seconds = Gameloop.SECOND_DEFAULT;
	}

	private void resetSeconds() {
		Gameloop.seconds = SECOND_DEFAULT;
	}

	private void checkColisions() {
		Rectangle rFuture = new Rectangle(this.rect.nextX(), this.rect.nextY(), this.rect.getWidth(),
				this.rect.getHeight());

		for (RectObstacle obs : map.getObstacles()) {
			Rectangle r2 = new Rectangle(obs.getX(), obs.getY(), obs.getWidth(), obs.getHeight());
			if (rFuture.intersects(r2)) {
				this.rect.setSpeedX(-this.rect.getSpeedDefaultX());
				this.rect.stopSpeed();
				this.resetSeconds();
				this.remainingMoves--;
				break;
			}
		}

	}

	private void checkColisionWin() {
		Rectangle rFuture = new Rectangle(this.rect.nextX(), this.rect.nextY(), this.rect.getWidth(),
				this.rect.getHeight());

		if (this.map.rectWin != null) {
			Rectangle rWin = new Rectangle(this.map.rectWin.getX(), this.map.rectWin.getY(),
					this.map.rectWin.getWidth(), this.map.rectWin.getHeight());
			if (rFuture.intersects(rWin))
				Gameloop.gameState = GameStateEnum.WIN;
		}
	}

	private void checkBoundsWindow() {
		if (rect.getX() < 0 || rect.getY() < 0 || rect.getXf() > Constants.WIDTH_WINDOW
				|| rect.getYf() > HEIGHT_WINDOW) {
			Gameloop.gameState = GameStateEnum.LOSE;
		}
	}

	private void checkQntMoves() {
		if (this.remainingMoves < 0)
			Gameloop.gameState = GameStateEnum.LOSE;
	}

	private void manageStateGame() {		

		if (Gameloop.gameState == GameStateEnum.MENU || 
			Gameloop.gameState == GameStateEnum.WIN || 
			Gameloop.gameState == GameStateEnum.LOSE)
			this.reset();

		switch (Gameloop.gameState) {
		case MENU: {
			this.map.setCurrentPhase(0);
			break;
		}
		case PLAYING: {
			if (Gameloop.seconds <= 0)
				Gameloop.gameState = GameStateEnum.LOSE;
			break;
		}
		case WIN: {
			try {
				this.map.nextPhase();
				Gameloop.gameState = GameStateEnum.PLAYING;
			} catch (Exception e) {
				Gameloop.gameState = GameStateEnum.GAME_FINISHED;
			}
			break;
		}
		case LOSE: {
			if (this.map.getCurrentPhase() > 0) {
				this.map.previousPhase();
				Gameloop.gameState = GameStateEnum.PLAYING;
			} else {
				Gameloop.gameState = GameStateEnum.MENU;
			}
			break;
		}
		default:
			break;
		}
	}

	private void update() {
		rect.update();
	}

	private void draw() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setFont(new Font("Arial", Font.PLAIN, 16));

		this.drawBackground(g);

		switch (Gameloop.gameState) {
		case MENU: {
			this.menu.draw(g);
			break;
		}
		case PLAYING: {
			this.drawPlaying(g);
			break;
		}
		case WIN: {
			this.drawWin(g);
			break;
		}
		case LOSE: {
			this.drawLose(g);
			break;
		}
		case GAME_FINISHED: {
			this.drawGameFinished(g);
			break;
		}
		default: {
			break;
		}
		}

		g.dispose();
		bs.show();
	}

	private void drawPlaying(Graphics g) {
		this.map.draw(g);
		this.rect.draw(g);
		this.drawSeconds(g);
		this.drawRemainingMoves(g);
	}

	private void drawText(Graphics g, Color color, int x, int y, String text) {
		g.setColor(color);
		g.drawString(text, x, y);
	}

	private void drawWin(Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (getWidth() - fm.stringWidth("Você venceu")) / 2;
		this.drawText(g, Color.GREEN, x, POS_TEXT_Y, Constants.TEXT_WIN);
	}

	private void drawLose(Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (getWidth() - fm.stringWidth("Você perdeu")) / 2;
		this.drawText(g, Color.RED, x, POS_TEXT_Y, Constants.TEXT_LOSE);
	}

	private void drawSeconds(Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (getWidth() - fm.stringWidth("Tempo restante: " + Gameloop.seconds)) / 2;
		this.drawText(g, Color.RED, x, POS_TEXT_Y, Constants.TEXT_TIMER + Gameloop.seconds);
	}

	private void drawRemainingMoves(Graphics g) {
		this.drawText(g, Color.BLACK, 50, POS_TEXT_Y, Constants.TEXT_MOVES + this.remainingMoves);
	}

	private void drawBackground(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WIDTH_WINDOW, HEIGHT_WINDOW);
	}

	private void drawGameFinished(Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (getWidth() - fm.stringWidth(Constants.THANKS_FINISH_GAME)) / 2;
		this.drawText(g, Color.RED, x, HEIGHT_WINDOW / 2 - 18, Constants.THANKS_FINISH_GAME);
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.draw();

				if (Gameloop.gameState == GameStateEnum.PLAYING) {
					this.checkColisions();
					this.checkColisionWin();
					this.checkBoundsWindow();
					this.checkQntMoves();
				}

				this.update();

				this.manageStateGame();

				Thread.sleep(60 / 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (Gameloop.gameState == GameStateEnum.PLAYING && canPress) {
			this.rect.keyPressed(e);
			canPress = false;
		}
		if (Gameloop.gameState == GameStateEnum.MENU)
			this.menu.keyPressed(e);
		if (Gameloop.gameState == GameStateEnum.GAME_FINISHED) {
			Gameloop.gameState = GameStateEnum.MENU;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		canPress = true;
	}

}