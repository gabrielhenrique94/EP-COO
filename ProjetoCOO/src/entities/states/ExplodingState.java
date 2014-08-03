package entities.states;

import entities.drawer.body.basic_body.Body;
import interfaces.States;

public class ExplodingState implements States{

	private Body body;
	
	public ExplodingState(Body body) {
		this.body = body;
	}
	
	@Override
	public void doState() {
		/*if com o currentTime*/
		this.body.setCurrentState(new InactiveState());
	}

	@Override
	public void drawState() {
		// TODO Auto-generated method stub
		
	}

}
