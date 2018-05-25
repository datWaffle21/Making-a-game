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

import javax.imageio.ImageIO;

public class Page2 extends Level {
	
	private Image reverseArrow;
	private Image background;
	
	private int mx, my;
	
	private LevelManager levelManager;
	
	public static boolean showback;
	
	public Page2(int numImages, LevelManager levelManager) {
		super(numImages);
		this.levelManager = levelManager;
		
		try {
			background = ImageIO.read(new File("background-characters.jpg"));
			reverseArrow = ImageIO.read(new File("reverseArrow.png"));
		} catch(Exception e) {}
	}
	@SuppressWarnings("unused")
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
		Point cursor = MouseInfo.getPointerInfo().getLocation();
		mx = cursor.x;
		my = cursor.y;
		
		if(mouseOver(mx, my, 50, 50, 100, 100)) {
			showback = true;
		} else {
			showback= false;
		}
	}
	public void render(Graphics g) {
		if(levelManager.getLevelIndex() == 1) {
			g.drawImage(background, 0, 0, null);
			g.drawImage(reverseArrow, 50, 50, 100, 100, null);
			
			g.setColor(Color.black);
			g.fillRect(160,40,1100,150);
			g.fillRect(90, 250, 550, 450);
			g.fillRect(680, 250, 550, 450);
			
			g.setColor(Color.white);
			
			g.drawRect(160, 40, 1100, 150);
			
			g.setFont(new Font("arial", 1, 30));
			g.drawString("Theme: ", 165, 72);
			g.drawString("In bad situations,  it can help to imagine the world to be the way you", 280, 72);
			g.drawString("However, there's a danger of going too far; instead of simply having goals,", 165, 115);
			g.drawString("you can become delusional.", 165, 155);
			
			g.drawRect(90, 250, 550, 450);
			g.drawRect(680, 250, 550, 450);
			
			g.drawString("Main Conflict: ", 100, 300);
			g.drawString("Rachel - experiences conflicts from", 100, 350);
			g.drawString("her previous abusive marriage and", 100, 380);
			g.drawString("her alcoholism.", 100, 410);
			
			g.drawString("Tom - He is selfish, manipulative,", 100, 490);
			g.drawString("and a pathological liar. He makes", 100, 520);
			g.drawString("Rachel feel like the dissolution of", 100, 550);
			g.drawString("their marriage was all her fault.", 100, 580);
			
			g.drawString("Main Themes: ", 700, 300);
			g.drawString("Insecutity", 700, 390);
			g.drawString("Guilt", 700, 480);
			g.drawString("Betrayal", 700, 570);
			g.drawString("Obsession", 700, 660);
					
		}
		
	}

}