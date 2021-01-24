package com.rectmove;

import javax.swing.JFrame;

public class Window {
	public Window() {
		JFrame frame = new JFrame();
    	Gameloop gameloop = new Gameloop ();
    	frame.setTitle("Rect Move");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    	
    	frame.add(gameloop);
    	frame.pack();
    	frame.setVisible(true);
    	frame.setLocationRelativeTo(null);
    	
    	new Thread(gameloop ).start();
	}
}
