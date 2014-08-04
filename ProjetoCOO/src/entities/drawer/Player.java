package entities.drawer;

import java.util.ArrayList;
import java.util.List;

import lib.GameLib;
import entities.drawer.body.basic_body.Body;
import entities.drawer.body.dinamic_bodies.DBody;
import entities.states.PlayerActiveState;
import game.gameloop.MainLoop;

/*Player e Enemy usam Factory?*/

public class Player extends DBody {
	private static Player instance;
	 
	private List<ProjectTiles> projectiles;
	
	/* Dados padrão */
	public Player() {
		super(GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25, 0.25, 12, 0, 0,
				System.currentTimeMillis()); // TODO Esse ultimo
															// zero ta errado
		this.setCurrentState(new PlayerActiveState(this));
		this.projectiles = new ArrayList<ProjectTiles>();
	}

	@Override
	public void draw() {
		this.getCurrentState().drawState();
	}

	public synchronized static Body getInstance() {
		if(instance == null)
			instance = new Player();
		return instance;
	}
	
	public void addProjectTile(){
		ProjectTiles pt = new ProjectTiles(this.getPosicao().getX(), this.getPosicao().getY() - 2 * this.getRadius(), 0.0, -1.0, false);
		this.projectiles.add(pt);
		MainLoop.getInstance().addBody(pt);
	}

}
