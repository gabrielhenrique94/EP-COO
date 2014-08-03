package entities.states;

import entities.drawer.body.basic_body.Body;
import interfaces.States;

public class InactiveState implements States {

	private Body body;

	public InactiveState(Body body) {
		this.body = body;
	}

	@Override
	public void doState() {
		// TODO Remover da lista
	}

	@Override
	public void drawState() {}

}
