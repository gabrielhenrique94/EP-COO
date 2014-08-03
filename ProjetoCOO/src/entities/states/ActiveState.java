package entities.states;

import lib.GameLib;
import entities.drawer.body.basic_body.Body;
import interfaces.States;

public class ActiveState implements States{

	private Body body;
	
	public ActiveState(Body body) {
		this.body = body;
	}
	
	@Override
	public void doState() {
		if(this.body.getPosicao().getY() > GameLib.HEIGHT + 10) {
			this.body.setCurrentState(new InactiveState());
		}
	}
}
