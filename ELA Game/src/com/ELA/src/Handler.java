package com.ELA.src;

import java.awt.Graphics;
import java.util.LinkedList;


public final class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	LinkedList<Level> levels = new LinkedList<Level>();
	GuideText guideText;
	//Level level;
	//Level01 level01;

	public void tick() {
		for(int i = 0; i < levels.size(); i++) {
			Level tempLevel = levels.get(i);
			
			tempLevel.tick();
		}
		for(int i =0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < levels.size(); i++) {
			Level tempLevel = levels.get(i);
			
			tempLevel.render(g);
		}
		for(int i =0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
		
	}	
	public void addLevel(Level level) {
		this.levels.add(level);
	}
	public void removeLevel(Level level) {
		this.levels.remove(level);
	}
}
