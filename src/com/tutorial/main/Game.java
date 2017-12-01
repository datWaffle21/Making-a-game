package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	public final int reletiveSize = 1;
	public static final int RelSize = 1;
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 1500 / RelSize,  HEIGHT = (WIDTH / 12 * 9);
	
	
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Menu menu;
	private Random r;
	
	private HUD hud;
	private Spawn spawner;
	
	public enum STATE {
		Menu,
		Help,
		Game
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game() {
		
		handler = new Handler();
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		
		new window(WIDTH, HEIGHT, "My Game", this);
		
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		
		r = new Random();
		
		if(gameState == STATE.Game) {
			// Make the starting objects in the game here
			//handler.addObject(new Player((WIDTH/2) / reletiveSize, (HEIGHT/2) / reletiveSize, ID.Player, handler));
			handler.addObject(new BasicEnemy((r.nextInt(Game.WIDTH)) / reletiveSize, (r.nextInt(Game.HEIGHT)) / reletiveSize, ID.BasicEnemy, handler));
			handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler ));
		}
		
		
			
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
		handler.tick();
		if(gameState == STATE.Game ) {
			hud.tick();
		spawner.tick();	
		}else if(gameState == STATE.Menu) {
			menu.tick();
		}
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		handler.render(g);
		
		if(gameState == STATE.Game ) {
				hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help) {
			menu.render(g);
		}
		
	
		
		g.dispose();
		bs.show();
		
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >=  max) {
			return var= max;
		}
		else if(var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
