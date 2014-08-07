package entities.states;

import game.gameloop.MainLoop;
import interfaces.States;
import entities.drawer.body.basic_body.Body;

public class ProjectileInactiveState implements States {

	private Body body;

	public ProjectileInactiveState(Body body) {
		this.body = body;
	}

	@Override
	public void doState() {
		MainLoop.getInstance().removeBody(this.body);
	}

	@Override
	public void drawState() {
	}

}
