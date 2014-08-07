package entities.drawer;

import interfaces.States;
import entities.drawer.body.dinamic_bodies.TBody;
import entities.states.ProjectileActiveState;
import entities.states.ProjectileEnemyActiveState;

public class ProjectTiles extends TBody{

	private boolean fromEnemy;
	
	
	public ProjectTiles(double posX, double posY, double velX, double velY, boolean fromEnemy) {
		super(posX, posY, velX, velY);
		this.fromEnemy = fromEnemy;
		this.setCurrentState(new ProjectileActiveState(this));
	}
	
	public ProjectTiles(double posX, double posY, double velX, double velY, double radius, boolean fromEnemy) {
		super(posX, posY, velX, velY);
		this.fromEnemy = fromEnemy;
		this.setRaio(radius);
		this.setCurrentState(new ProjectileEnemyActiveState(this));
	}
	
	public boolean isFromEnemy() {
		return fromEnemy;
	}
	
	@Override
	public void setCurrentState(States currentState) {
		// TODO Auto-generated method stub
		super.setCurrentState(currentState);
	}

	@Override
	public void draw() {
		this.getCurrentState().drawState();
	}
}