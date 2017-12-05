package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerBullet extends GameObject {
	
	private Handler handler;
	private Game game;

	public PlayerBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 3;
		velY = 3;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) handler.removeObject(this);
		if(x <= 0 || x >= Game.WIDTH - 16) handler.removeObject(this);
	
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 10, 10, 0.06f, handler));
		
	}
	

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 10, 10);
		
	}

}
