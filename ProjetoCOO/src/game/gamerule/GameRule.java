package game.gamerule;

import java.util.ArrayList;
import java.util.List;

import entities.drawer.Player;
import entities.drawer.ProjectTiles;
import entities.drawer.body.basic_body.Body;
import entities.states.ExplodingState;
import entities.states.InactiveState;
import interfaces.GameRules;

public class GameRule implements GameRules {

	private List<Body> playerList;

	public GameRule() {
		this.playerList = new ArrayList<Body>();
	}

	public void addBody(Body body) {
		this.playerList.add(body);
	}

	public void checkColision() {
		for (Body b : this.playerList) {
			for (Body check : this.playerList) {
				if (b.equals(check))
					continue;
				// TODO: Calculo de colisão
				processColission(b, check);
			}
		}
	}

	@Override
	public void processColission(Body a, Body b) {
		if (a instanceof Player || b instanceof Player) {
			Player p;
			p = (Player) (a instanceof Player?a:b);
			p.setCurrentState(new ExplodingState());
		}
	}

	@Override
	public void processStep() {
		checkColision();
	}

	@Override
	public void processUserInput() {

	}

	@Override
	public void draw() {

	}
}
