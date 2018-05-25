package com.ELA.src;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Level {

	public ArrayList<Image> images;
	
	protected LevelManager levelManager;
	
	public void storeImages(Image ...images) throws IOException {
		for(Image img : images) {
			this.images.add(img);
		}
	}
	
	public Level(int numImages) {
		images = new ArrayList<>();
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
}
