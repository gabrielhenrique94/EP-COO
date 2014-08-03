package entities.states;

import entities.drawer.Player;
import lib.GameLib;
import game.gameloop.MainLoop;
import interfaces.States;

public class PlayerExplodingState implements States {

	private Player body;

	public PlayerExplodingState(Player body) {
		this.body = body;
	}

	@Override
	public void doState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawState() {
		double alpha = (MainLoop.getInstance().getCurrentTime() - this.body
				.getExplosionS())
				/ (this.body.getExplosionE() - this.body.getExplosionS());
		GameLib.drawExplosion(this.body.getPosicao().getX(), this.body.getPosicao().getY(), alpha);
	}

}
