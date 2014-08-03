package entities.drawer;

import java.awt.Color;

import com.sun.corba.se.spi.orbutil.fsm.State;

import lib.GameLib;
import entities.drawer.body.dinamic_bodies.DBody;
import game.gameloop.MainLoop;
import interfaces.States;

/*Player e Enemy usam Factory?*/

public class Player extends DBody {
	
	public Player(double posX, double posY, double velX, double velY,
			double radius, double explosionS, double explosionE, long nextShot) {
		super(posX, posY, velX, velY, radius, explosionS, explosionE, nextShot);
	}

	/* Dados padrão */
	public Player() {
		super(GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25, 0.25, 12, 0, 0,
				MainLoop.getInstance().getCurrentTime()); // TODO Esse ultimo
															// zero ta errado
	}

	@Override
	public void draw() {
		this.getCurrentState().drawState();
	}

}
