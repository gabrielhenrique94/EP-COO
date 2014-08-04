package game.gamerule;

import interfaces.GameRules;

import java.util.ArrayList;
import java.util.List;

import entities.drawer.Player;
import entities.drawer.ProjectTiles;
import entities.drawer.body.basic_body.Body;
import entities.drawer.body.basic_body.Posicao;
import entities.drawer.body.basic_body.Velocidade;
import entities.drawer.body.dinamic_bodies.TBody;
import entities.states.PlayerExplodingState;

public class GameRule implements GameRules {

	private List<Body> playerList;

	public GameRule() {
		this.playerList = new ArrayList<Body>();
		initialize();
	}

	public void createProjectile(Posicao pos , Velocidade v, boolean fromEnemy){
		ProjectTiles bala = new ProjectTiles(pos.getX(), pos.getY(), v.getX(), v.getY(), fromEnemy);
		playerList.add(bala);
	}
	private void initialize() {
		this.playerList.add(new Player());
	}
	
	public void removeBody(Body body){
		this.playerList.remove(body);
	}

	public void addBody(Body body) {
		this.playerList.add(body);
	}

	public void checkColision() {
		for (int i = 0; i < this.playerList.size(); i++) {
			Body b = this.playerList.get(i);
			for (int j = i + 1; j < this.playerList.size(); j++) {
				Body check = playerList.get(j);
				if (b.checkColission(check) && processColission(b, check)) {
					return;
				}
			}
		}
	}

	@Override
	public boolean processColission(Body a, Body b) {
		if (a instanceof Player || b instanceof Player) {
			Player p;
			p = (Player) (a instanceof Player ? a : b);
			p.setCurrentState(new PlayerExplodingState(p));
			// Se houve uma colisão entre Player e mais algo, retorna true sai
			// da checagem de colisão.
			return true;
		}
		return false;
	}

	@Override
	public void processStep() {
		checkColision();
		for(Body b: this.playerList)
			b.getCurrentState().doState();
	}

	@Override
	public void processUserInput() {

	}

	@Override
	public void draw() {
		for(Body b : this.playerList)
			b.draw();
	}
}
