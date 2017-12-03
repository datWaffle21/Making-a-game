package com.tutorial.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private GameObject object;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 600) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			//handler.addObject(new EnemyBoss(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.EnemyBoss, handler));
			//handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.BasicEnemy, handler));
			if(hud.getLevel() == 2 || hud.getLevel() == 3) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH) - 50, r.nextInt(Game.HEIGHT) - 50, ID.BasicEnemy, handler));
			
			if(hud.getLevel() == 4) handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
			
			if(hud.getLevel() > 4 && hud.getLevel() < 10) handler.addObject(new FastEnemy(r.nextInt(Game.HEIGHT), r.nextInt(Game.WIDTH), ID.FastEnemy, handler));
			
			if(hud.getLevel() == 10) {
				handler.removeAll();
				handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 50, -120, ID.EnemyBoss, handler));
			}
			/*if(hud.getLevel() > 10) {
				handler.removeAll();
				
				
			}*/
				
		}
	
	}	


}
