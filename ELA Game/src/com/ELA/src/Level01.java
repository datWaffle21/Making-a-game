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
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Level01 extends Level {

	// Vars
	private Image background;

	public Image rachel;
	public Image scaledRachel;
	
	public Image tom;
	public Image scaledTom;
	
	public Image anna;
	public Image scaledAnna;
	
	public Image scott;
	public Image scaledScott;
	
	public Image megan;
	public Image scaledMegan;
	
	public Image arrow;
	public Image scaledArrow;

	@SuppressWarnings("unused")
	private Game game;
	private LevelManager levelManager;
	
	private boolean rachelBox;
	private boolean tomBox;
	private boolean annaBox;
	private boolean scottBox;
	private boolean meganBox;

	public static boolean showNext = false;

	public int mx, my;

	/**
	 * 
	 * @param numImages
	 * @param levelManager
	 */
	public Level01(int numImages, LevelManager levelManager) {
		super(numImages);
		this.levelManager = levelManager;
		images = new ArrayList<>();
		try {
			// Individual Images
			rachel = ImageIO.read(new File("rachel.jpg"));
			tom = ImageIO.read(new File("tom.jpg"));
			anna = ImageIO.read(new File("anna.jpg"));
			scott = ImageIO.read(new File("scott.jpg"));
			megan = ImageIO.read(new File("megan.jpg"));
			arrow = ImageIO.read(new File("arrow.png"));
		} catch (IOException e) {}
		try {
			// Puts all images into a list for easier manipulation
			storeImages(background);
		} catch (IOException e) {}
		
		scaledRachel = scaleImage(rachel, BufferedImage.TYPE_INT_RGB, 350, 350);
		scaledTom = scaleImage(tom, BufferedImage.TYPE_INT_RGB, 350,350);
		scaledAnna = scaleImage(anna, BufferedImage.TYPE_INT_RGB, 350,350);
		scaledScott = scaleImage(scott, BufferedImage.TYPE_INT_RGB, 350,350);
		scaledMegan = scaleImage(megan, BufferedImage.TYPE_INT_RGB, 350,350);
		scaledArrow = scaleImage(arrow, BufferedImage.TYPE_INT_RGB, 100,100);
	}

	/**
	 * 
	 * @param img
	 *            The image to be scaled
	 * @param imageType
	 *            Target image type
	 * @param newWidth
	 *            the required width
	 * @param newHeight
	 *            the required height
	 * 
	 * @return The scaled Image
	 */
	public static Image scaleImage(Image img, int imageType, int newWidth, int newHeight) {
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
	
	/**
	 * @param Graphics g
	 * 				the graphics component
	 */
	public void render(Graphics g) {
		if (levelManager.getLevelIndex() == 0) {
			// Screen			
			g.setFont(new Font("arial", 1, 50));
			g.setColor(Color.white);
			g.drawImage(scaledArrow, 1260, 30, null);
			g.drawString("Characters", 50, 70);
			
			// Rachel
			g.drawImage(scaledRachel, 50, 130, null);
			g.drawString("Rachel", 70, 600);
			
			// Tom
			g.drawImage(scaledTom, 350, 130, null);
			g.drawString("Tom", 370, 600);
			
			// Anna
			g.drawImage(scaledAnna, 600, 130, null);
			g.drawString("Anna", 620, 600);
			
			//Scott
			g.drawImage(scaledScott, 850, 130, null);
			g.drawString("Scott", 870, 600);
			
			//Megan
			g.drawImage(scaledMegan, 1100, 130, null);
			g.drawString("Megan", 1120, 600);
			
			if(rachelBox) {
				g.drawRect(20, 100, 271, 530);
			}
			if(tomBox) {
				g.drawRect(320, 100, 230, 530);		
			}
			if(annaBox) {
				g.drawRect(580, 100, 246, 530);
			}
			if(scottBox) {
				g.drawRect(830, 100, 240, 530);
			}
			if(meganBox) {
				g.drawRect(1080, 100, 260, 530);
			}
			
		}
	}
	
	/**
	 * 
	 * @param mx; The X pos of the mouse
	 * @param my; The Y pos of the mouse
	 * @param x; The x coord of the upper right of the box
	 * @param y; the y coord of the upper right of the box
	 * @param width  how wide the box is
	 * @param height how tall the box is 
	 * @return
	 */
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
		
		if(mouseOver(mx, my, 1260, 30, 77, 40)) {
			showNext = true;
		} else {
			showNext = false;
		}
		
		if(mouseOver(mx, my, 20, 100, scaledRachel.getWidth(null) + 60, scaledRachel.getHeight(null) + 180)) {
			rachelBox = true;
		} else {
			rachelBox = false;
		}
		if(mouseOver(mx, my, 320, 100, 230, 530)){
			tomBox = true;
		} else {
			tomBox = false;
		}
		if(mouseOver(mx, my, 580, 100, 246, 530)) {
			annaBox = true;
		} else {
			annaBox = false;
		}
		if(mouseOver(mx, my, 830, 100, 240, 530)) {
			scottBox = true;
		} else {
			scottBox = false;
		}
		if(mouseOver(mx, my, 1080, 100, 260, 530)) { 
			meganBox = true;
		} else {
			meganBox = false;
		}
	}

	/**
	 * 
	 * @param b ; true or false
	 */
	public void setShow(boolean b) {
		showNext = b;
	}

	public boolean getShow() {
		return showNext;
	}
}
