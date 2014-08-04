package entities.drawer;

import entities.drawer.body.dinamic_bodies.TBody;

public class ProjectTiles extends TBody{

	private boolean fromEnemy;
	
	public ProjectTiles(double posX, double posY, double velX, double velY, boolean fromEnemy) {
		super(posX, posY, velX, velY);
		this.fromEnemy = fromEnemy;
	}
	
	public boolean isFromEnemy() {
		return fromEnemy;
	}
}