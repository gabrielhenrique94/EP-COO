package entities.states;

import java.awt.Color;

import lib.GameLib;
import entities.drawer.Enemies;
import entities.drawer.body.basic_body.Posicao;
import entities.drawer.body.basic_body.Velocidade;
import game.gameloop.MainLoop;
import game.gamerule.GameRule;
import interfaces.States;

public class Enemy3ActiveState implements States {
	private Enemies enemy;
	private long nextTeleport = 0;
	public Enemy3ActiveState(Enemies enemy) {
		this.enemy = enemy;
	}
	@Override
	public void doState() {
		if(MainLoop.getInstance().getCurrentTime() > nextTeleport){
			double x = Math.random() * GameLib.WIDTH;
			double y = Math.random() * GameLib.HEIGHT * 0.4;//só aparecer na metade de cima
			enemy.getPosicao().set(x, y);
			nextTeleport = MainLoop.getInstance().getCurrentTime() + 500;
			((GameRule) MainLoop.getInstance().getRules()).createEnemyProjectile(new Posicao(x, y),new Velocidade(Math.random()*0.2,Math.random()),2, true);
		}
	}

	@Override
	public void drawState() {
		GameLib.setColor(Color.YELLOW);
		GameLib.drawDiamond(enemy.getPosicao().getX(), enemy.getPosicao().getY(), enemy.getRaio());
		GameLib.drawCircle(enemy.getPosicao().getX(), enemy.getPosicao().getY(), enemy.getRaio());
	}

}
