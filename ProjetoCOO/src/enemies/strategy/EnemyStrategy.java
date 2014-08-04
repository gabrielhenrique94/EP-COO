package enemies.strategy;

import org.omg.PortableInterceptor.INACTIVE;

import sun.rmi.server.InactiveGroupException;
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
				
				state = new Inactive();
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
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class Inactive implements States{

		@Override
		public void doState() {
			
		}

		@Override
		public void drawState() {
			
			
		}
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
