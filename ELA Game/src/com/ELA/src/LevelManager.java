package com.ELA.src;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LevelManager extends MouseAdapter {
	
	@SuppressWarnings("unused")
	private Level level;
	private Level01 level01;
	@SuppressWarnings("unused")
	private Page2 page2;
	
	public int index;
	
	public Level currentLevel;
	
	public void tick() {
		clamp(index, 0, 6);
	}
	
	public LevelManager(Level01 level01, Page2 page2) {
		index = 0;
		this.level01 = level01;
		this.page2 = page2;
		setCurrentLevel(index);
	}
	
	public int getLevelIndex() {
		return index;
	}
	
	public void setCurrentLevel(int i) {
		if(i == 0) {
			currentLevel = level01;
		}
	}
	
	public void nextLevel() {
		index++;
	}
	
	public void setIndex(int i) {
		index = i;
	}
	public int getIndex() {
		return index;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
			
		// Arrow to Plot and conflicts
		if(index == 0) {
			if(mouseOver(mx, my, 1260, 30, 77, 40)) {
				setIndex(1);
			}
		}
		
		if(mouseOver(mx, my, 20, 100, 271, 530)) {
			setIndex(2);
		}
		
		// Arrow back to characters
		if(index != 0) {
			if(mouseOver(mx, my, 50, 50, 100, 100)) {
				setIndex(0);
			}
		}
		
		if(mouseOver(mx, my, 320, 100, 230, 530)) {
			setIndex(3);
		}
		if(mouseOver(mx, my, 580, 100, 246, 530)) {
			setIndex(4);
		}
		if(mouseOver(mx, my, 830, 100, 240, 530)) {
			setIndex(5);
		}
		if(mouseOver(mx, my, 1080, 100, 260, 530)) {
			setIndex(6);
		}
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
	
	private void clamp(int var, int min, int max) {
		if(var >= max) {
			setIndex(max);
		} else if(var <= min) {
			setIndex(min);
		} else {
			setIndex(var);
		}
	}
}
