package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	public static boolean health = false;
	public static boolean lose = false;
	
	
	private Handler handler;
	private Player player;
	private Game game;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i=0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player)  {
				// all the key events for player one
				
				if(key == KeyEvent.VK_W) { tempObject.setVelY(- 5); keyDown[0] = true;}
				if(key == KeyEvent.VK_S) {tempObject.setVelY(+ 5); keyDown[1] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setVelX(- 5); keyDown[2] = true;}
				if(key == KeyEvent.VK_D) {tempObject.setVelX(+ 5); keyDown[3] = true;}
				
				if(key== KeyEvent.VK_H) health = true;
				if(key== KeyEvent.VK_L) lose = true;
				
				if(key == KeyEvent.VK_SPACE) {
					
					handler.addObject(new PlayerBullet(Game.WIDTH /2, Game.HEIGHT /2 , ID.PlayerBullet, handler));
					
				}
				
				
			}
			
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);			
		
		 }
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i=0; i< handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player)  {
				// all the key events for player one
				
				if(key == KeyEvent.VK_W) keyDown[0] = false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) keyDown[1] = false; //tempObject.setVelY(0);
				if(key == KeyEvent.VK_A) keyDown[2] = false; //tempObject.setVelX(0);
				if(key == KeyEvent.VK_D) keyDown[3] = false; //tempObject.setVelX(0);
				
				if(key == KeyEvent.VK_H) health = false;
				if(key == KeyEvent.VK_L) lose = false;
				
				
				
				// Vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				// Horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
			
		}
	}
}
