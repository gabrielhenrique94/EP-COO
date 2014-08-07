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
		double alpha = (MainLoop.getInstance().getCurrentTime() - this.enemy.getExplosionS())
				/ (this.enemy.getExplosionE() - this.enemy.getExplosionS());
		
		// Pra tirar o erro que dá quando alpha fica maior que 1
		alpha = alpha>1.0?1.0:alpha;
		
		GameLib.drawExplosion(enemy.getPosicao().getX(), enemy.getPosicao()
				.getY(), alpha);
	}

}
