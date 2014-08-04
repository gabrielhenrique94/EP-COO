package enemies.strategy;


import java.awt.Color;

import sun.applet.Main;
import game.gameloop.MainLoop;
import game.gamerule.GameRule;
import interfaces.States;
import lib.GameLib;
import entities.drawer.Enemies;
import entities.drawer.Player;
import entities.drawer.body.basic_body.Velocidade;
import entities.states.Enemy1ActiveState;

public class Enemy1Strategy implements IStrategy {
	private Enemies enemy = null;
	private States state;
	
	public Enemy1Strategy(Enemies enemy) {
		this.enemy = enemy;
		enemy.setCurrentState(new Enemy1ActiveState(enemy)); 
	}
}
