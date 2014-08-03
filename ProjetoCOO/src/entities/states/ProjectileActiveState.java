package entities.states;

import lib.GameLib;
import entities.drawer.body.basic_body.Body;
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
			this.body.getPosicao().move(0, this.body.getPosicao().getY() /* * delta*/);
		}
	}
}
