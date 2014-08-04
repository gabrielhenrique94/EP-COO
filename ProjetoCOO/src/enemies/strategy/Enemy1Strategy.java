package enemies.strategy;


import interfaces.States;
import entities.drawer.Enemies;
import entities.states.Enemy1ActiveState;

public class Enemy1Strategy implements IStrategy {
	private Enemies enemy = null;
	private States state;
	
	public Enemy1Strategy(Enemies enemy) {
		this.enemy = enemy;
		enemy.setCurrentState(new Enemy1ActiveState(enemy)); 
	}
}
