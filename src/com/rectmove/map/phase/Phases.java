package com.rectmove.map.phase;

public class Phases{
	public static enum TYPES_TILED_ENUM {
		OBSTACLE(1), FINISH(2) ,WAY(0);

		private final int tiledValue;

		TYPES_TILED_ENUM(int tiledValue) {
			this.tiledValue = tiledValue;
		}
		
		public int getTiledValue() {
			return tiledValue;
		}
	};
	
	private static int w = TYPES_TILED_ENUM.WAY.getTiledValue();
	private static int o = TYPES_TILED_ENUM.OBSTACLE.getTiledValue();
	private static int f = TYPES_TILED_ENUM.FINISH.getTiledValue();
	
	public static enum PHASES_ENUM {
		PHASE_1(new int[][]{
			{w,w,w,w,o,w,w,o,o,o,w,w,w,w,w,w},
			{w,o,o,w,o,w,w,w,w,w,w,w,w,w,w,o},
			{w,w,w,w,w,w,w,o,w,w,o,w,w,w,w,o},
			{w,o,w,w,w,w,w,w,w,o,w,w,w,o,w,o},
			{w,w,w,w,w,w,o,w,w,w,w,o,w,w,w,f},
			{w,w,w,o,w,w,w,w,w,w,w,o,o,w,w,w},
			{w,w,w,w,w,w,w,w,w,w,w,o,w,w,w,w},
			{w,o,w,w,w,w,w,o,w,w,w,w,w,w,w,w},
			{w,w,w,w,w,o,o,w,o,w,w,w,w,w,w,w},
			{o,w,w,w,w,w,w,w,o,w,w,w,w,w,o,w},
		}),
		PHASE_2(new int[][]{
			{w,w,o,o,f,w,w,w,w,w,w,w,w,w,w,w},
			{w,w,w,w,w,w,o,w,w,w,w,o,w,w,w,w},
			{w,w,w,w,w,w,o,w,w,w,w,w,w,w,w,w},
			{w,w,w,w,w,w,w,w,w,w,o,w,w,w,o,o},
			{w,w,w,w,w,w,w,w,o,o,o,w,w,w,w,w},
			{w,w,w,o,w,w,w,w,w,w,w,w,w,w,w,o},
			{w,w,w,o,w,w,w,w,w,w,w,w,o,o,o,o},
			{w,o,w,w,w,w,w,o,w,w,w,w,w,w,w,o},
			{w,w,w,w,o,o,w,w,w,w,w,w,w,w,w,o},
			{o,w,w,w,w,o,o,w,w,w,w,w,w,w,w,w},
		});
		
		private final int[][] phase;
		
		PHASES_ENUM(int[][] phase) {
			this.phase= phase;
		}
		
		public int[][] getPhase() {
			return phase;
		}
	};
	
	public static enum MOVES_ENUM {
		PHASE_1(15),PHASE_2(20);
		
		private final int moves;
		
		MOVES_ENUM(int moves) {
			this.moves = moves;
		}
		
		public int getMove() {
			return moves;
		}
	};

}
