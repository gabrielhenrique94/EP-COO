package entities.states;

import interfaces.States;
import entities.drawer.body.basic_body.Body;

public class ProjectileInactiveState implements States {

	private Body body;

	public ProjectileInactiveState(Body body) {
		this.body = body;
	}

	@Override
	public void doState() {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawState() {
		// TODO Auto-generated method stub
		
	}

}
