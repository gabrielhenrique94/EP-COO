package entities.drawer;

import lib.GameLib;
import entities.drawer.body.dinamic_bodies.DBody;
import interfaces.States;

/*Player e Enemy usam Factory?*/

public class Player extends DBody {
	
	private static final int MAX_PROJECTS = 10;
	private int[] projectTiles;
	

	public Player(double posX, double posY, double velX, double velY,
			double radius, double explosionS, double explosionE, long nextShot) {
		super(posX, posY, velX, velY, radius, explosionS, explosionE, nextShot);
	}

	/* Dados padrão */
	public Player() {
		super(GameLib.WIDTH/2, GameLib.HEIGHT*0.90, 0.25, 0.25, 12, 0, 0, 0); //TODO Esse ultimo zero ta errado
	}
}
