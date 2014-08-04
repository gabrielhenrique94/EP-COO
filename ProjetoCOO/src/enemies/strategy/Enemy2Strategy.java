package enemies.strategy;

import interfaces.States;
import entities.drawer.Enemies;
import entities.states.Enemy1ActiveState;
import entities.states.Enemy2ActiveState;

public class Enemy2Strategy implements IStrategy {
	private Enemies enemy = null;
	private States state;
	
	public Enemy2Strategy(Enemies enemy) {
		this.enemy = enemy;
		enemy.setCurrentState(new Enemy2ActiveState(enemy)); 
	}
}