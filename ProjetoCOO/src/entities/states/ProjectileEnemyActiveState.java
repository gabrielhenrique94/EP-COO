package entities.states;

import java.awt.Color;

import lib.GameLib;
import entities.drawer.body.basic_body.Body;
import game.gameloop.MainLoop;
import game.gamerule.GameRule;
import interfaces.States;

public class ProjectileEnemyActiveState implements States{

	private Body body;
	
	public ProjectileEnemyActiveState(Body body) {
		this.body = body;
	}
	
	@Override
	public void doState() {
		if(this.body.getPosicao().getY() > GameLib.HEIGHT) {
			((GameRule)MainLoop.getInstance().getRules()).removeBody(this.body);
		} else {
			this.body.getPosicao().move(this.body.getVelocidade().getX() * MainLoop.getInstance().getDelta(), this.body.getVelocidade().getY() * MainLoop.getInstance().getDelta());
		}
	}

	@Override
	public void drawState() {
		GameLib.setColor(Color.RED);
		GameLib.drawCircle(this.body.getPosicao().getX(), this.body.getPosicao().getY(), this.body.getRaio());	
	}
}
