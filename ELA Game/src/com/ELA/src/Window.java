package com.ELA.src;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public final class Window extends Canvas {

	// Vars + References
	private static final long serialVersionUID = 2444948820193693562L;
	@SuppressWarnings("unused")
	private Game game;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
	
}
