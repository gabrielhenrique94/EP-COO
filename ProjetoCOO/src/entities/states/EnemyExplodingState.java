package entities.states;

import entities.drawer.Enemies;
import lib.GameLib;
import game.gameloop.MainLoop;
import game.gamerule.GameRule;
import interfaces.States;

public class EnemyExplodingState implements States {
	private Enemies enemy;

	public EnemyExplodingState(Enemies enemy) {
		this.enemy = enemy;
	}

	@Override
	public void doState() {
		if (MainLoop.getInstance().getCurrentTime() > enemy.getExplosionE()) {
			((GameRule) MainLoop.getInstance().getRules()).removeBody(enemy);
		}
	}

	@Override
	public void drawState() {
		System.out.println("CurrentTime: " + MainLoop.getInstance().getCurrentTime() + " ExplosionE e S" + enemy.getExplosionS() + "  " + enemy.getExplosionE());
		
		double alpha = (MainLoop.getInstance().getCurrentTime()
				- enemy.getExplosionS() / enemy.getExplosionE() - enemy
				.getExplosionS());
		GameLib.drawExplosion(enemy.getPosicao().getX(), enemy.getPosicao()
				.getY(), alpha);
	}

}
