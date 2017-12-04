package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject {
	
	private Handler handler;
	private Game game;
	private Random random;
	
	Random r = new Random();
	
	Color color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
	
	

	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		int dir = r.nextInt(3);
		if(dir == 0) {
			velX = 2;
			velY = 9;
		} else if(dir == 1) {
			velX = 9;
			velY = 2;
		} else {
			velX = r.nextInt(9);
			velY = r.nextInt(9);
			
			if(velX == 0) velX =  r.nextInt(9);
			if(velY == 0) velY =  r.nextInt(9);
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
	
		handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.099f, handler));
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}
