package entities.drawer;

import enemies.strategy.EnemyStrategy;
import entities.drawer.body.basic_body.Posicao;
import entities.drawer.body.basic_body.Velocidade;
import entities.drawer.body.dinamic_bodies.DBody;
import game.gameloop.MainLoop;

public class Enemies extends DBody {
	private double angulo;
	private double velocidadeRotacao;
	private EnemyStrategy strategy;
	private double velocidadeLinear;
	public double getVelocidadeLinear(){
		return velocidadeLinear;
	}
	
	public Enemies(Posicao posicao, double vel, double raio,EnemyStrategy strategy) {
		super(posicao.getX(), posicao.getY(),0,0, raio, 0, 0, MainLoop.getInstance().getCurrentTime());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
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
}
