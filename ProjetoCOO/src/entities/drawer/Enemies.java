package entities.drawer;

import interfaces.States;
import entities.drawer.body.dinamic_bodies.DBody;

public class Enemies extends DBody {

	public Enemies(double posX, double posY, double velX, double velY,
			double radius, double explosionS, double explosionE, long nextShot) {
		super(posX, posY, velX, velY, radius, explosionS, explosionE, nextShot);
		// TODO Auto-generated constructor stub
	}

}