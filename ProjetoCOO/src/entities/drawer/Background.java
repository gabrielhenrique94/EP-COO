package entities.drawer;

import game.gameloop.MainLoop;

import java.awt.Color;

import lib.GameLib;

public class Background {

	private double[] pointsX;
	private double[] pointsY;
	private double speed;
	private double count;
	private int numPoints;
	private Color color;

	public Background(int numPoints, double speed, double count, Color color) {
		this.pointsX = new double[numPoints];
		this.pointsY = new double[numPoints];
		this.numPoints = numPoints;
		this.speed = speed;
		this.count = count;
		this.color = color;
		
		ramdomPoints(GameLib.WIDTH, GameLib.HEIGHT);
	}

	public void ramdomPoints(int boundsX, int boundsY) {
		for(int i = 0; i < pointsX.length; i++) {
			pointsX[i] = Math.random() * boundsX;
			pointsY[i] = Math.random() * boundsY;
		}
	}
	
	public void drawMe()
	{
		GameLib.setColor(this.color);
		this.count += speed * MainLoop.getInstance().getDelta();
		
		for(int i = 0; i < numPoints; i++){
			GameLib.fillRect(this.pointsX[i], (this.pointsY[i] + this.count) % GameLib.HEIGHT, 2, 2);
		}
	}
}
