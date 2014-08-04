package entities.drawer.body.basic_body;

import game.gameloop.MainLoop;
import interfaces.States;
import entities.drawer.Enemies;
import entities.drawer.Player;
import entities.drawer.ProjectTiles;
import entities.states.EnemyExplodingState;
import entities.states.InactiveState;

public abstract class Body {

	private States currentState;
	private Posicao pos;
	private Velocidade vel;
	private double raio;

	public Body(double posX, double posY, double velX, double velY) {
		this.pos = new Posicao(posX, posY);
		this.vel = new Velocidade(velX, velY);
		this.currentState = new InactiveState(this);
	}

	public Posicao getPosicao() {
		return pos;
	}

	public Velocidade getVelocidade() {
		return vel;
	}

	public double getRaio() {
		return raio;
	}

	public States getCurrentState() {
		return currentState;
	}

	public void setCurrentState(States currentState) {
		this.currentState = currentState;
	}

	private static boolean checkColission(Body b1, Body b2) {
		// Colisão entre Player e Enemy 
		if ((b1 instanceof Player || b2 instanceof Player)
				&& (b1 instanceof Enemies || b2 instanceof Enemies)) {
			if (b1.pos.distancia(b2.pos) < (b1.raio + b2.raio) * 0.8) {
				return true;
			}
			return false;
		}
		
		// Colisão entre Player Projectile e Enemy 
		if ((b1 instanceof Enemies || b2 instanceof Enemies)
				&& (b1 instanceof ProjectTiles || b2 instanceof ProjectTiles)) {
			Enemies p = (Enemies)(b1 instanceof Enemies?b1:b2);
			ProjectTiles pt = (ProjectTiles)(b1 instanceof ProjectTiles?b1:b2);
			
			if(!pt.isFromEnemy()) {
				double dist = dist(p, pt);
				if(dist < p.getRaio() + pt.getRaio() * 0.8){		
					return true;
				}
			}
			return false;
		}
		
		// Colisão entre Enemy Projectile e Player 
		if ((b1 instanceof Player || b2 instanceof Player)
				&& (b1 instanceof ProjectTiles || b2 instanceof ProjectTiles)) {
			Player p = (Player)(b1 instanceof Player?b1:b2);
			ProjectTiles pt = (ProjectTiles)(b1 instanceof ProjectTiles?b1:b2);

			if(pt.isFromEnemy()) {
				double dist = dist(pt, p);
				if(dist < p.getRaio() + pt.getRaio() * 0.8){		
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public boolean checkColission(Body other) {
		return Body.checkColission(this, other);
	}
	
	private static double dist(Body b1, Body b2) {
		double dx = b1.getPosicao().getX() - b2.getPosicao().getX();
		double dy = b1.getPosicao().getY() - b2.getPosicao().getY();
		double dist = Math.sqrt(dx * dx + dy * dy);
		
		return dist;
	}

	public abstract void draw();

	// public abstract void onColide(Body anotherBody);
}
