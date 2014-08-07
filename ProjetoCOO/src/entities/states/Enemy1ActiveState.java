package entities.states;

import interfaces.States;

import java.awt.Color;

import lib.GameLib;
import entities.drawer.Enemies;
import entities.drawer.Player;
import entities.drawer.body.basic_body.Velocidade;
import game.gameloop.MainLoop;
import game.gamerule.GameRule;

public class Enemy1ActiveState implements States{
	Enemies enemy = null;
	
	public Enemy1ActiveState(Enemies enemy) {
		this.enemy = enemy;
	}
	
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
				((GameRule) MainLoop.getInstance().getRules()).createEnemyProjectile(enemy.getPosicao() ,
						new Velocidade(Math.cos(enemy.getAngulo())*0.45, Math.sin(enemy.getAngulo())*0.45 *-1.0), 2.0, true);
				
				enemy.setNextShot((long) (MainLoop.getInstance().getCurrentTime() + 200 + Math.random() * 500));
			}
		}
	}

	@Override
	public void drawState() {
		GameLib.setColor(Color.CYAN);
		GameLib.drawCircle(enemy.getPosicao().getX(), enemy.getPosicao().getY(), enemy.getRadius());
	}

}
