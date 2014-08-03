package game.gamerule;

import java.util.ArrayList;
import java.util.List;

import entities.drawer.body.basic_body.Body;
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
		for(Body b : this.playerList) {
			for(Body check: this.playerList) {
				if (b.equals(check))
					continue;
				// Calculo de colisão
			}
		}
	}

	@Override
	public void processColission(Body a, Body b) {

	}

	@Override
	public void processStep() {

	}

	@Override
	public void processUserInput() {

	}

	@Override
	public void draw() {

	}
}
