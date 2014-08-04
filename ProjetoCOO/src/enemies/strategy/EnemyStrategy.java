package enemies.strategy;


import java.awt.Color;

import sun.applet.Main;
import game.gameloop.MainLoop;
import game.gamerule.GameRule;
import interfaces.States;
import lib.GameLib;
import entities.drawer.Enemies;
import entities.drawer.Player;
import entities.drawer.body.basic_body.Velocidade;

public class EnemyStrategy implements IStrategy {
	private Enemies enemy = null;
	private States state;
	private long endExplosion;
	public EnemyStrategy(Enemies enemy) {
		this.enemy = enemy;
		this.state = new Active(); 
	}
	
	@Override
	public void update() {
		state.doState();
	}
	
	private class Active implements States{
		@Override
		public void doState() {
			if(enemy.getPosicao().getY() > GameLib.HEIGHT + 10) {
				enemy.setStrategy(null);//removendo refrencia circular
				((GameRule) MainLoop.getInstance().getRules()).removeBody(enemy); 
			}else{
				enemy.getPosicao().move(enemy.getVelocidadeLinear() * Math.cos(enemy.getAngulo())*MainLoop.getInstance().getDelta(),
						enemy.getVelocidadeLinear() * Math.sin(enemy.getAngulo())*MainLoop.getInstance().getDelta()* -1.0);
				enemy.setAngulo(enemy.getAngulo() + enemy.getVelocidadeRotacao() * MainLoop.getInstance().getDelta());
			
				if(MainLoop.getInstance().getCurrentTime() > enemy.getNextShot() && enemy.getPosicao().getY() < Player.getInstance().getPosicao().getY()){
					((GameRule) MainLoop.getInstance().getRules()).createProjectile(enemy.getPosicao() ,
							new Velocidade(Math.cos(enemy.getAngulo())*0.45, Math.sin(enemy.getAngulo())*0.45 *-1.0), true);
					
					enemy.setNextShot((long) (MainLoop.getInstance().getCurrentTime() + 200 + Math.random() * 500));
				}
			}
		}

		@Override
		public void drawState() {
			GameLib.setColor(Color.CYAN);
			GameLib.drawCircle(enemy.getPosicao().getX(),enemy.getPosicao().getY(),enemy.getRaio());
		}
		
	}

	private class Exploding implements States{
		@Override
		public void doState() {
			if(MainLoop.getInstance().getCurrentTime() > endExplosion){
				((GameRule) MainLoop.getInstance().getRules()).removeBody(enemy);
			}				
		}

		@Override
		public void drawState() {
			double alpha = (MainLoop.getInstance().getCurrentTime() - enemy.getExplosionS() / enemy.getExplosionE()- enemy.getExplosionS());
			GameLib.drawExplosion(enemy.getPosicao().getX(), enemy.getPosicao().getY(), alpha);			
		}
		
	}

	@Override
	public void draw() {
		state.drawState();
	}
}
