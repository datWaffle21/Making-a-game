package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {

	private Random r = new Random();
	private Game game; 
	private Handler handler;
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
	
		if(game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx, my, (Game.WIDTH / 3) + 20, 400 / game.reletiveSize - 7, (Game.WIDTH /3), 64 / game.reletiveSize + 5)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player((Game.WIDTH/2) / game.reletiveSize, (Game.HEIGHT/2) / game.reletiveSize, ID.Player, handler));
				handler.addObject(new BasicEnemy((r.nextInt(Game.WIDTH)) / game.reletiveSize, (r.nextInt(Game.HEIGHT)) / game.reletiveSize, ID.BasicEnemy, handler));
			}
			
			//help button
			if(mouseOver(mx, my, (Game.WIDTH / 3) + 20, 600 / game.reletiveSize - 7, (Game.WIDTH) / 3, 64 / game.reletiveSize) ) {
				game.gameState = STATE.Help;
			}
			
			//back button for help
			if(game.gameState == STATE.Help) {
				if(mouseOver(mx, my,  (Game.WIDTH / 3) + 20,	800 / game.reletiveSize - 7, (Game.WIDTH /3), 64 / game.reletiveSize))
				game.gameState = STATE.Menu;
				return;
			} 
			
			//quit button
			if(mouseOver(mx, my, (Game.WIDTH / 3) + 20,	800 / game.reletiveSize - 7, (Game.WIDTH /3), 64 / game.reletiveSize)) {
				System.exit(1); 
			}
		}
		
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font font = new Font("arial", 1, 75);
			Font font2 = new Font("arial", 1, 30);
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Menu", 100 / game.reletiveSize - 25, 150 / game.reletiveSize );
			
			g.setFont(font2);
			g.drawString("Play", 720 / game.reletiveSize, 440 / game.reletiveSize);
			g.drawRect((Game.WIDTH / 3) + 20, 400 / game.reletiveSize - 7, (Game.WIDTH /3), 64 / game.reletiveSize + 5);
			
			g.drawString("Help", 720 / game.reletiveSize, 640 / game.reletiveSize);
			g.drawRect((Game.WIDTH / 3) + 20, 600 / game.reletiveSize - 7, (Game.WIDTH) / 3, 64 / game.reletiveSize);
			
			g.drawString("Quit", 720 / game.reletiveSize, 840 / game.reletiveSize);
			g.drawRect((Game.WIDTH / 3) + 20, 800 / game.reletiveSize - 7, (Game.WIDTH /3), 64 / game.reletiveSize);
		} else if(game.gameState == STATE.Help) {
			Font font = new Font("arial", 1, 75);
			Font font2 = new Font("arial", 1 , 30);		
			
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Help Screen", 50, 100);
			
			g.setFont(font2);
			g.drawString("Press WASD Keys to move around and dodge enemies", 100, 200);
			
			g.setFont(font2);
			g.drawString("Back", 720 / game.reletiveSize, 840 / game.reletiveSize);
			g.drawRect((Game.WIDTH / 3) + 20, 800 / game.reletiveSize - 7, (Game.WIDTH /3), 64 / game.reletiveSize);
			
			
		}
		
	}
	
}
