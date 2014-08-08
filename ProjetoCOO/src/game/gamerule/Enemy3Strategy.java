package game.gamerule;

import interfaces.States;
import enemies.strategy.IStrategy;
import entities.drawer.Enemies;

import entities.states.Enemy3ActiveState;

public class Enemy3Strategy implements IStrategy {
	private Enemies enemy = null;
	private States state;
	
	public Enemy3Strategy(Enemies enemy) {
		this.enemy = enemy;
		enemy.setCurrentState(new Enemy3ActiveState(enemy)); 
	}
}
