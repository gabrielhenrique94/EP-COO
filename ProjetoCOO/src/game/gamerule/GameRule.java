package game.gamerule;

import game.gameloop.MainLoop;
import interfaces.GameRules;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import lib.GameLib;
import enemies.strategy.Enemy1Strategy;
import enemies.strategy.Enemy2Strategy;
import entities.drawer.Background;
import entities.drawer.Enemies;
import entities.drawer.Player;
import entities.drawer.ProjectTiles;
import entities.drawer.body.basic_body.Body;
import entities.drawer.body.basic_body.Posicao;
import entities.drawer.body.basic_body.Velocidade;
import entities.states.EnemyExplodingState;
import entities.states.PlayerExplodingState;

public class GameRule implements GameRules {

	private List<Body> playerList;
	private List<Background> bgList;
	private long nextEnemy2;
	private int enemy2_count;
	private long nextEnemy1;
	private double enemy2_spawnX;
	private long nextEnemy3 = 0;
	
	public GameRule() {
		this.playerList = new ArrayList<Body>();
		this.bgList = new ArrayList<Background>();
		initialize();
	}

	public void createProjectile(Posicao pos , Velocidade v, boolean fromEnemy){
		ProjectTiles bala = new ProjectTiles(pos.getX(), pos.getY(), v.getX(), v.getY(), fromEnemy);
		playerList.add(bala);
	}
	
	public void createEnemyProjectile(Posicao pos , Velocidade v, double raio, boolean fromEnemy){
		ProjectTiles bala = new ProjectTiles(pos.getX(), pos.getY(), v.getX(), v.getY(), raio, fromEnemy);
		playerList.add(bala);
	}
	
	private void initialize() {
		Background bg1 = new Background(20, 0.070, 0.0, Color.DARK_GRAY);
		Background bg2 = new Background(50, 0.045, 0.0, Color.GRAY);
		
		this.bgList.add(bg1);
		this.bgList.add(bg2);
		
		this.playerList.add(Player.getInstance());
		
		this.nextEnemy2 = System.currentTimeMillis() + 7000;
		this.nextEnemy1 = System.currentTimeMillis() + 2000; 
	}
	
	public void removeBody(Body body){
		this.playerList.remove(body);
	}

	public void addBody(Body body) {
		this.playerList.add(body);
	}

	public void checkColision() {
		for (int i = 0; i < this.playerList.size(); i++) {
			Body b = this.playerList.get(i);
			for (int j = i + 1; j < this.playerList.size(); j++) {
				Body check = playerList.get(j);
				if (b.checkColission(check) && processColission(b, check)) {
					return;
				}
			}
		}
	}
	
	public List<Body> getPlayerList() {
		return playerList;
	}

	@Override
	public boolean processColission(Body a, Body b) {
		if (a instanceof Player || b instanceof Player) {
			Player p;
			p = (Player) (a instanceof Player ? a : b);
			p.setCurrentState(new PlayerExplodingState(p));
			System.out.println("Colidiu");
			p.setExplosionS(MainLoop.getInstance().getCurrentTime());
			p.setExplisionE(MainLoop.getInstance().getCurrentTime() + 2000);
			// Se houve uma colisão entre Player e mais algo, retorna true sai
			// da checagem de colisão.
			return true;
		} else if((a instanceof Enemies || b instanceof Enemies) && (a instanceof ProjectTiles || b instanceof ProjectTiles)) {
			Enemies e = (Enemies) (a instanceof Enemies?a:b);
			ProjectTiles pt = (ProjectTiles) (a instanceof ProjectTiles?a:b);
			
			if(!pt.isFromEnemy()) {
				e.setCurrentState(new EnemyExplodingState(e));
				e.setExplosionS(MainLoop.getInstance().getCurrentTime());
				e.setExplisionE(MainLoop.getInstance().getCurrentTime() + 500);
			}
		}
		
		return false;
	}

	@Override
	public void processStep() {
		checkColision();
		for(int i = 0; i < this.playerList.size(); i++)
		{
			Body b = this.playerList.get(i);
			if(b != null)
				b.getCurrentState().doState();
		}
	
		if(MainLoop.getInstance().getCurrentTime() > nextEnemy1){
		
			Enemies enemy = new Enemies(new Posicao(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0),
					0.20 + Math.random() * 0.15, 9.0,3 * Math.PI / 2, 0.0, null);
			enemy.setStrategy(new Enemy1Strategy(enemy));
			this.playerList.add(enemy);
			nextEnemy1 = MainLoop.getInstance().getCurrentTime() + 500;
		}
		
		if(MainLoop.getInstance().getCurrentTime() > nextEnemy2){				
				System.out.println("can you see me here??");
				Enemies enemy = new Enemies(new Posicao(enemy2_spawnX, -10.0), 
						0.42, 12.0,3*Math.PI/2, 0.0, null);
				enemy.setStrategy(new Enemy2Strategy(enemy));
				
				this.playerList.add(enemy);
				enemy2_count++;
				
				
				if(enemy2_count < 10){
					nextEnemy2 = MainLoop.getInstance().getCurrentTime() + 120;
				}else {
					enemy2_count = 0;
					enemy2_spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
					nextEnemy2 = (long) (MainLoop.getInstance().getCurrentTime()+ 3000 + Math.random() * 3000);
			
				}
			}
		if(MainLoop.getInstance().getCurrentTime() > nextEnemy3){
			Enemies enemy = new Enemies(new Posicao(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0),
					0.20 + Math.random() * 0.15, 9.0,3 * Math.PI / 2, 0.0, null);
			enemy.setStrategy(new Enemy3Strategy(enemy));
			this.playerList.add(enemy);
			nextEnemy3 = MainLoop.getInstance().getCurrentTime() + 3000;
		}
	}

	@Override
	public void processUserInput() {

	}

	@Override
	public void draw() {
		for(Background b : this.bgList)
			if(b != null)
				b.drawMe();
		
		for(int i = 0; i < this.playerList.size(); i++) {
			Body b = this.playerList.get(i);	
			if(b != null)
				b.draw();
			
		}
		
		GameLib.display();
	}
}
