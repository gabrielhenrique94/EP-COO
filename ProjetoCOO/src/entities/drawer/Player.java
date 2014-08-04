package entities.drawer;

import sun.security.jca.GetInstance;
import lib.GameLib;
import entities.drawer.body.basic_body.Body;
import entities.drawer.body.dinamic_bodies.DBody;
import game.gameloop.MainLoop;

/*Player e Enemy usam Factory?*/

public class Player extends DBody {
	private static Player instance = new Player();
	 
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

	public static Body getInstance() {
		return instance;
	}

}
