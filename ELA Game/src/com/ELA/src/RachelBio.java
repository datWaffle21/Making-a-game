package com.ELA.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RachelBio extends Level {
	
	private LevelManager levelManager;
	
	private Image rachel, scaledRachel;
	@SuppressWarnings("unused")
	private Image background;
	private Image backArrow, scaledArrow;
	
	private int mx, my;

	public RachelBio(int numImages, LevelManager levelManager) {
		super(numImages);
		this.levelManager = levelManager;
		
		try {
		//	background = ImageIO.read(new File("background.png"));
			rachel = ImageIO.read(new File("rachel.jpg"));
			backArrow = ImageIO.read(new File("reverseArrow.png"));
		} catch(IOException e) {}
		
		scaledRachel = scaleImage(rachel, BufferedImage.TYPE_INT_RGB, 525, 525);
		scaledArrow = scaleImage(backArrow, BufferedImage.TYPE_INT_RGB, 100,100);
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
	
	private static Image scaleImage(Image img, int imageType, int newWidth, int newHeight) {
		double thumbRatio = (double) newWidth / (double) newHeight;
		int imageWidth = img.getWidth(null);
		int imageHeight = img.getHeight(null);
		double aspectRatio = (double) imageWidth / (double) imageHeight;

		if (thumbRatio < aspectRatio) {
			newHeight = (int) (newWidth / aspectRatio);
		} else {
			newWidth = (int) (newHeight * aspectRatio);
		}

		BufferedImage newImage = new BufferedImage(newWidth, newHeight, imageType);
		Graphics2D graphics2D = newImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(img, 0, 0, newWidth, newHeight, null);

		return newImage;
	}

	@Override
	public void tick() {
		Point cursor = MouseInfo.getPointerInfo().getLocation();
		mx = cursor.x;
		my = cursor.y;
		
		if(mouseOver(mx, my, 50, 50, 100, 100)) {
			Page2.showback  = true;
		} else {
			Page2.showback = false;
		}
	}

	@Override
	public void render(Graphics g) {
		if(levelManager.getLevelIndex() == 2) {
			g.drawImage(scaledArrow, 50, 50, 100, 100, null);
			g.drawImage(scaledRachel, 1000, 100, null);
			
			
			g.setColor(Color.black);
			g.fillRect(135, 50, 785, 100);
			
			g.setColor(Color.white);
			g.drawRect(135, 50, 785, 100);
			
			g.setFont(new Font("arial", 1, 65));
			g.drawString("Rachel", 150, 125);
			
			g.setColor(Color.black);
			g.fillRect(135, 200, 800, 550);
			
			g.setColor(Color.white);
			g.drawRect(135, 200, 800, 550);
			
			g.setFont(new Font("arial", 1, 30));
			g.drawString("Rachel Watson is an alcoholic who rides a train", 140, 230);
			g.drawString("aimlessly since losing her job. To the other characters", 140, 270);
			g.drawString("however, she is a drunken ruthless stalker. In the story,", 140, 310);
			g.drawString("she has two selves, her sober self, who is analytic", 140, 350);
			g.drawString("and smart, and her drunken self, who is angry, violent", 140, 390);
			g.drawString("and destructive. Rachel's journey is about herself.", 140, 430);
			g.drawString("Sober Rachel takes one or two steps forward, but", 140, 470);
			g.drawString("drunk Rachel pulls her one or two hundred steps back.", 140, 510);
		}
	}
}
