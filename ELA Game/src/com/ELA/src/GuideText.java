package com.ELA.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public final class GuideText extends GameObject {
	
	public int width, height;
	@SuppressWarnings("unused")
	private Handler handler;
	private LevelManager levelManager;
	
	public String showNextString = "Plot and Theme";
	public String characters = "Back to Characters";

	public void tick() {
		
	}
	public void render(Graphics g) {
		if(Level01.showNext && levelManager.getLevelIndex() == 0) {
			g.setColor(Color.black);
			g.fillRect((int)x, (int) y, width, height);
			g.setColor(Color.WHITE);
			g.drawRect((int) x, (int) y, width, height);
			
			g.setFont(new Font("arial", 1, 35));
			g.drawString(showNextString,(int) x + 30 ,(int) (y + (height / 2)) + 10);
		}
		if(Page2.showback && levelManager.getLevelIndex() != 0) {
			g.setColor(Color.black);
			g.fillRect((int)x, (int) y, width, height);
			g.setColor(Color.WHITE);
			g.drawRect((int) x, (int) y, width, height);
			
			g.setFont(new Font("arial", 1, 35));
			g.drawString(characters,(int) x + 30 ,(int) (y + (height / 2)) + 10);
		}
		
	}
	
	public GuideText(int x, int y, int width, int height, Handler handler, LevelManager levelManager) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.handler = handler;
		this.levelManager = levelManager;
	}
	
	
	public Rectangle getBounds() {
		return null;
	}
	
}
