package entities.drawer;

import interfaces.States;
import entities.drawer.body.dinamic_bodies.DBody;

public class Enemies extends DBody{

	public Enemies(double posX, double posY, double velX, double velY,
			States currentState, double radius, double explosionS,
			double explosionE) {
		super(posX, posY, velX, velY, currentState, radius, explosionS, explosionE);
		// TODO Auto-generated constructor stub
	}

}
