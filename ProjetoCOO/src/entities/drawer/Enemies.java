package entities.drawer;

import enemies.strategy.Enemy1Strategy;
import enemies.strategy.IStrategy;
import entities.drawer.body.basic_body.Posicao;
import entities.drawer.body.dinamic_bodies.DBody;
import game.gameloop.MainLoop;

public class Enemies extends DBody {
	private double angulo;
	private double velocidadeRotacao;
	private IStrategy strategy;
	private double velocidadeLinear;
	
	public double getVelocidadeLinear(){
		return velocidadeLinear;
	}
	
	public Enemies(Posicao posicao, double vel, double raio, double angulo, double velRot, Enemy1Strategy strategy) {
		super(posicao.getX(), posicao.getY(),0,0, raio, 0, 0, MainLoop.getInstance().getCurrentTime());
		this.velocidadeLinear = vel;
		this.angulo = angulo;
		this.velocidadeRotacao = velRot;
	}

	@Override
	public void draw() {
		this.getCurrentState().drawState();
	}

	public double getAngulo() {
		return angulo;
	}

	public double getVelocidadeRotacao() {
		return velocidadeRotacao;
	}

	public void setAngulo(double d) {
		this.angulo = d;
	}
	
	public void setStrategy(IStrategy strategy){
		this.strategy = strategy;
	}
	public void setVelocidadeRotacao(double velocidadeRotacao) {
		this.velocidadeRotacao = velocidadeRotacao;
	}
}
