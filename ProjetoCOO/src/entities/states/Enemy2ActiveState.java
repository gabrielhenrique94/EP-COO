package entities.states;

import java.awt.Color;

import lib.GameLib;
import game.gameloop.MainLoop;
import game.gamerule.GameRule;
import interfaces.States;
import entities.drawer.Enemies;
import entities.drawer.body.basic_body.Posicao;
import entities.drawer.body.basic_body.Velocidade;

public class Enemy2ActiveState implements States {
	private Enemies enemy;

	public Enemy2ActiveState(Enemies enemy) {
		this.enemy = enemy;
	}

	@Override
	public void doState() {
		/* verificando se inimigo saiu da tela */
		if (enemy.getPosicao().getX() < -10
				|| enemy.getPosicao().getX() > GameLib.WIDTH + 10) {
			((GameRule) MainLoop.getInstance().getRules()).removeBody(enemy);
		} else {

			boolean shootNow = false;
			double previousY = enemy.getPosicao().getY();
			System.out.printf("{\n\tx:%f,\n\ty:%f,\n\tv:%f\n\tdelta:%d,\n\tRV:%f,\n\tangle:%f\n}\n",
					enemy.getPosicao().getX(),enemy.getPosicao().getY(),
					enemy.getVelocidadeLinear(),MainLoop.getInstance().getDelta(),enemy.getVelocidadeRotacao(),enemy.getAngulo());
			enemy.getPosicao().move(
					enemy.getVelocidadeLinear() * Math.cos(enemy.getAngulo())
							* MainLoop.getInstance().getDelta(),
					enemy.getVelocidadeLinear() * Math.sin(enemy.getAngulo())
							* MainLoop.getInstance().getDelta()*(-1.0));

			enemy.setAngulo(enemy.getAngulo() + enemy.getVelocidadeRotacao()
					* MainLoop.getInstance().getDelta());

			double threshold = GameLib.HEIGHT * 0.30;

			if (previousY < threshold && enemy.getPosicao().getY() >= threshold) {

				if (enemy.getPosicao().getX() < GameLib.WIDTH / 2)
					enemy.setVelocidadeRotacao(0.003);
				else
					enemy.setVelocidadeRotacao(-0.003);
			}

			if (enemy.getVelocidadeRotacao() > 0
					&& Math.abs(enemy.getAngulo() - 3 * Math.PI) < 0.05) {

				enemy.setVelocidadeRotacao(0.0);
				enemy.setAngulo(3 * Math.PI);
				shootNow = true;
			}

			if (enemy.getVelocidadeRotacao() < 0
					&& Math.abs(enemy.getAngulo()) < 0.05) {

				enemy.setVelocidadeRotacao(0.0);
				enemy.setAngulo(0.0);
				shootNow = true;
			}

			if (shootNow) {

				double[] angles = { Math.PI / 2 + Math.PI / 8, Math.PI / 2,
						Math.PI / 2 - Math.PI / 8 };

				for (int k = 0; k < angles.length; k++) {

					double a = angles[k] + Math.random() * Math.PI / 6 - Math.PI / 12;
					double vx = Math.cos(a);
					double vy = Math.sin(a);
					((GameRule) MainLoop.getInstance().getRules())
							.createEnemyProjectile(new Posicao(enemy.getPosicao()
									.getX(), enemy.getPosicao().getY()),
									new Velocidade(vx * 0.30, vy * 0.30),2.0, true);
				}
			}
		}
	}

	@Override
	public void drawState() {
		GameLib.setColor(Color.MAGENTA);
		GameLib.drawDiamond(enemy.getPosicao().getX(), enemy.getPosicao().getY(), enemy.getRaio());
	}

}
