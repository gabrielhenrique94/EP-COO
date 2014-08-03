package entities.states;

import java.awt.Color;

import lib.GameLib;
import entities.drawer.body.basic_body.Body;
import game.gameloop.MainLoop;
import interfaces.States;

public class ProjectileActiveState implements States {

	private Body body;

	public ProjectileActiveState(Body body) {
		this.body = body;
	}

	@Override
	public void doState() {
		if (this.body.getPosicao().getY() < 0
				|| this.body.getPosicao().getY() > GameLib.HEIGHT) {
			this.body.setCurrentState(new ProjectileInactiveState(this.body));
		} else {
			this.body.getPosicao().move(0, this.body.getPosicao().getY() * MainLoop.getInstance().getDelta());
		}
	}

	@Override
	public void drawState() {
		GameLib.setColor(Color.GREEN);
		GameLib.drawLine(this.body.getPosicao().getX(), this.body.getPosicao().getY() - 5, this.body.getPosicao().getX(), this.body.getPosicao().getY() + 5);
		GameLib.drawLine(this.body.getPosicao().getX() - 1, this.body.getPosicao().getY() - 3, this.body.getPosicao().getX() - 1, this.body.getPosicao().getY() + 3);
		GameLib.drawLine(this.body.getPosicao().getX() + 1, this.body.getPosicao().getY() - 3, this.body.getPosicao().getX() + 1, this.body.getPosicao().getY() + 3);
	}
}