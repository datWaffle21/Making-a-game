package com.ELA.src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	@SuppressWarnings("unused")
	private Handler handler;
	@SuppressWarnings("unused")
	private Game game;
	private LevelManager levelManager;
	
	public KeyInput(Handler handler, LevelManager levelManager) {
		this.handler = handler;
		this.levelManager = levelManager;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		/*
		if(!(game.gameState == Game.STATE.End)) {
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				// Put Key commands here
			}
		}
		*/
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		if(key == KeyEvent.VK_L) levelManager.setIndex(levelManager.getIndex() + 1);
		if(key == KeyEvent.VK_K) levelManager.setIndex(levelManager.getIndex() - 1);
	}
}
