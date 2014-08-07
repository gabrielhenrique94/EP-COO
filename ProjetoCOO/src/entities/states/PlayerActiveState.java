package entities.states;

import game.gameloop.MainLoop;
import interfaces.States;

import java.awt.Color;

import lib.GameLib;
import entities.drawer.Player;

public class PlayerActiveState implements States {

	private Player body;

	public PlayerActiveState(Player body) {
		this.body = body;
	}

	@Override
	public void doState() {

		if (GameLib.iskeyPressed(GameLib.KEY_UP))
			this.body.getPosicao().move(
					0,
					-(MainLoop.getInstance().getDelta() * this.body
							.getVelocidade().getY()));
		if (GameLib.iskeyPressed(GameLib.KEY_DOWN))
			this.body.getPosicao().move(
					0,
					(MainLoop.getInstance().getDelta() * this.body
							.getVelocidade().getY()));
		if (GameLib.iskeyPressed(GameLib.KEY_LEFT))
			this.body.getPosicao().move(
					-(MainLoop.getInstance().getDelta() * this.body
							.getVelocidade().getY()), 0);
		if (GameLib.iskeyPressed(GameLib.KEY_RIGHT))
			this.body.getPosicao().move(
					(MainLoop.getInstance().getDelta() * this.body
							.getVelocidade().getY()), 0);

		if (GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {

			if (MainLoop.getInstance().getCurrentTime() > body.getNextShot()) {
				System.out.println("Atirei");
				this.body.addProjectTile();
				this.body.setNextShot(MainLoop.getInstance().getCurrentTime() + 100);
			}
		}
	}

	@Override
	public void drawState() {
		GameLib.setColor(Color.BLUE);
		GameLib.drawPlayer(this.body.getPosicao().getX(), this.body
				.getPosicao().getY(), this.body.getRaio());
	}

}
