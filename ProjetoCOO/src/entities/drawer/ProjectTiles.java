package entities.drawer;

import entities.drawer.body.dinamic_bodies.TBody;
import interfaces.States;

public class ProjectTiles extends TBody{

	public ProjectTiles(double posX, double posY, double velX, double velY,
			States currentState) {
		super(posX, posY, velX, velY, currentState);
	}
}
