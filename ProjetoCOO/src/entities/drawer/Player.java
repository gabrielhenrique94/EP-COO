package entities.drawer;

import entities.drawer.body.dinamic_bodies.DBody;
import interfaces.States;

/*Player e Enemy usam Factory?*/

public class Player extends DBody{

	private static final int MAX_PROJECTS = 10;
	private int[] projectTiles;
	
	public Player(double posX, double posY, double velX, double velY,
			double radius, double explosionS, double explosionE) {
		super(posX, posY, velX, velY, radius, explosionS, explosionE);
	}
}
