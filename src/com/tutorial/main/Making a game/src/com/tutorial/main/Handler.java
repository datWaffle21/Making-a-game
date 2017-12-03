package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.tutorial.main.Game.STATE;

public class Handler {
	
	private Game game;
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i =0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i=0; i < object.size(); i++) {
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

	public void removeAll() {
		if(Game.gameState == STATE.End) {	
			for(int i = 0; i < this.object.size(); i++) {
					GameObject tempObject = this.object.get(i);
					this.removeObject(tempObject);
					i--;
			}
			
		} else {
			for(int i = 0; i < this.object.size(); i++) {
				GameObject tempObject = this.object.get(i);
				if(tempObject.getId() != ID.Player) {
					this.removeObject(tempObject);
					i--;
				}
			} 
		}
				
	}
	
	
}
