package interfaces;

import entities.drawer.body.basic_body.Body;

public interface GameRules {
	
    boolean processColission(Body a, Body b);

	void processStep();

	void processUserInput();

	void draw();

}
