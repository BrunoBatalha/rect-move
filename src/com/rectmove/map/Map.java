package com.rectmove.map;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.rectmove.entity.obstacle.RectObstacle;
import com.rectmove.map.phase.Phases.MOVES_ENUM;
import com.rectmove.map.phase.Phases.PHASES_ENUM;
import com.rectmove.map.phase.Phases.TYPES_TILED_ENUM;

public class Map {
	
	private int width;
	private int height;
	private int currentPhase;
	private int qntMoves;
	private ArrayList<RectObstacle> obstacles ; 
	private int mapCoords[][];	
	public RectObstacle rectWin;
	
	public Map(int width, int height, int currentPhase) {
		this.width = width;
		this.height = height;		
		this.currentPhase = currentPhase;
		this.mapCoords = PHASES_ENUM.values()[currentPhase].getPhase();
		this.obstacles = new ArrayList<RectObstacle>();
		this.qntMoves = MOVES_ENUM.values()[currentPhase].getMove();
	}
	
	
	public Map(int width, int height) {
		this.width = width;
		this.height = height;		
		this.currentPhase = 0; 
		this.mapCoords = PHASES_ENUM.values()[this.currentPhase].getPhase();
		this.obstacles = new ArrayList<RectObstacle>();
		this.qntMoves = MOVES_ENUM.values()[currentPhase].getMove();
	}
	
	public int getCurrentPhase() {
		return this.currentPhase;
	}
	
	public void setCurrentPhase(int currentPhase) {
		this.currentPhase = currentPhase; 
		this.managePhase();
	}	
	
	public void nextPhase() {
		this.currentPhase++; 
		this.managePhase();
	}	
	
	public void previousPhase() {
		this.currentPhase--;
		this.managePhase();
	}
	
	public void managePhase() {
		this.mapCoords = PHASES_ENUM.values()[this.currentPhase].getPhase();
		this.qntMoves = MOVES_ENUM.values()[currentPhase].getMove();
	}
	
	public void draw(Graphics g) {
		Integer widthTiled = 50;
		Integer heightTiled = 50; 	
		obstacles.clear();
	
		for (int i = 0; i < this.width / widthTiled; i++) {
			for (int j = 0; j < this.height / heightTiled; j++) {
				
				if (this.mapCoords[j][i] == TYPES_TILED_ENUM.FINISH.getTiledValue()) {
					g.setColor(Color.green);
					rectWin = new RectObstacle(i * widthTiled, j * heightTiled, widthTiled, heightTiled);
				}
				
				if (this.mapCoords[j][i] == TYPES_TILED_ENUM.WAY.getTiledValue())
					g.setColor(Color.black);
				
				if (this.mapCoords[j][i] == TYPES_TILED_ENUM.OBSTACLE.getTiledValue()) {
					g.setColor(Color.white);
					RectObstacle obs = new RectObstacle(i * widthTiled, j * heightTiled, widthTiled, heightTiled);
					obstacles.add(obs);
				}

				g.fillRect(i * widthTiled, j * heightTiled, widthTiled, heightTiled);
			}
		}
	}
	
	public ArrayList<RectObstacle> getObstacles() {
		return obstacles;
	}

	public int getQntMoves() {
		return this.qntMoves;
	}
}
