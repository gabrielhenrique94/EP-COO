package entities.states;

import game.gameloop.MainLoop;
import interfaces.States;
import lib.GameLib;
import entities.drawer.Player;

public class PlayerExplodingState implements States {

	private Player body;

	public PlayerExplodingState(Player body) {
		this.body = body;
	}

	@Override
	public void doState() {
		if(MainLoop.getInstance().getCurrentTime() > this.body.getExplosionE())
			this.body.setCurrentState(new PlayerActiveState(body));
	}

	@Override
	public void drawState() {
		double alpha = (MainLoop.getInstance().getCurrentTime() - this.body
				.getExplosionS())
				/ (this.body.getExplosionE() - this.body.getExplosionS());
		GameLib.drawExplosion(this.body.getPosicao().getX(), this.body.getPosicao().getY(), alpha);
	}

}
