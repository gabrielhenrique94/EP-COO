package entities.drawer.body.dinamic_bodies;

import entities.drawer.body.basic_body.Body;

public abstract class DBody extends Body {

	/* Body para Player e Enemy */
	private double radius, explosionS, explisionE;
	private long nextShot;

	public DBody(double posX, double posY, double velX, double velY,
			double radius, double explosionS, double explosionE, long nextShot) {
		super(posX, posY, velX, velY);

		this.radius = radius;
		this.explisionE = explosionE;
		this.explosionS = explosionS;
	}

	public double getRadius() {
		return radius;
	}
	
	public double getExplosionE() {
		return explisionE;
	}
	
	public double getExplosionS() {
		return explosionS;
	}
	
	public long getNextShot() {
		return nextShot;
	}
}
