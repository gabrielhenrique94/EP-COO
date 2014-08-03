package entities.drawer.body.basic_body;

import interfaces.States;
import entities.states.InactiveState;

public abstract class Body{

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
	
	private static boolean checkColission(Body b1, Body b2){
		if(b1.pos.distancia(b2.pos) < (b1.raio+b2.raio)*0.8){
		//	b2.onColide(b1);
		//	b1.onColide(b2);
			return true;
		}
		return false;
	}
	
	public boolean checkColission(Body other){
		return Body.checkColission(this ,other);
	}
	
	public abstract void draw();
	
	//public abstract void onColide(Body anotherBody);
}
