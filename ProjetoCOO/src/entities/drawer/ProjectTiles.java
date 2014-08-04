package entities.drawer;

import entities.drawer.body.dinamic_bodies.TBody;
import entities.states.ProjectileActiveState;

public class ProjectTiles extends TBody{

	private boolean fromEnemy;
	
	public ProjectTiles(double posX, double posY, double velX, double velY, boolean fromEnemy) {
		super(posX, posY, velX, velY);
		this.fromEnemy = fromEnemy;
		this.setCurrentState(new ProjectileActiveState(this));
	}
	
	public boolean isFromEnemy() {
		return fromEnemy;
	}
}