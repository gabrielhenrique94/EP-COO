package entities.states;

import java.awt.Color;

import game.gameloop.MainLoop;
import interfaces.States;
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
				/*
				 * Esperando o controle de Body no GameRule int free =
				 * findFreeIndex(projectile_states); if (free <
				 * projectile_states.length) {
				 * 
				 * projectile_X[free] = player_X; projectile_Y[free] = player_Y
				 * - 2 * player_radius; projectile_VX[free] = 0.0;
				 * projectile_VY[free] = -1.0; projectile_states[free] = 1;
				 * player_nextShot = currentTime + 100; }
				 */
			}
		}
	}

	@Override
	public void drawState() {
		GameLib.setColor(Color.BLUE);
		GameLib.drawPlayer(this.body.getPosicao().getX(), this.body
				.getPosicao().getY(), this.body.getRadius());
	}

}
