package com.ELA.src;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 1366,  HEIGHT = 768;
	
	private Thread thread;
	private boolean running = false;
	
	@SuppressWarnings("unused")
	private Level level;
	private Image background;
	private Handler handler;
	private GuideText guideText;
	private LevelManager levelManager;
	
	private Level01 level01;
	private Page2 page2;
	private RachelBio rachelBio;
	private TomBio tomBio;
	private AnnaBio annaBio;
	private ScottBio scottBio;
	private MeganBio meganBio;
	
	public enum STATE {
		End,
		Game
	};
	
	public STATE gameState = Game.STATE.Game;
		
	public Game() {
		
		try {
			background = ImageIO.read(new File("background-characters.jpg"));
		} catch(Exception e) {}
		
		handler = new Handler();
		levelManager = new LevelManager(level01, page2);
		guideText = new GuideText(10, 630, 1340, 125, handler, levelManager);
		
		new Window(WIDTH, HEIGHT, "My Game", this);
		
		level01 = new Level01(1, levelManager);
		page2 = new Page2(1, levelManager);
		rachelBio = new RachelBio(1, levelManager);
		tomBio = new TomBio(1, levelManager);
		annaBio = new AnnaBio(1, levelManager);
		scottBio = new ScottBio(1, levelManager);
		meganBio = new MeganBio(1, levelManager);
		
		
		handler.addLevel(level01);
		handler.addObject(guideText);
		
		handler.addLevel(rachelBio);
		handler.addLevel(page2);
		handler.addLevel(tomBio);
		handler.addLevel(scottBio);
		handler.addLevel(annaBio);
		handler.addLevel(meganBio);
		
		this.addKeyListener(new KeyInput(handler, levelManager));
		this.addMouseListener(levelManager);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick() {
		levelManager.tick();
		handler.tick();		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		while(background == null) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}
		
		
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
		
	//	g.setColor(Color.white);
	//	g.drawString((levelManager.getIndex() + ""), 100, 100);
		
		
		handler.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >=  max) {
			return var = max;
		}
		else if(var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
	
	public boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
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
	
	public static void main(String[] args) {
		new Game();
	}
}