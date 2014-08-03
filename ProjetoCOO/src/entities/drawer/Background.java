package entities.drawer;

public class Background {

	private double[] pointsX;
	private double[] pointsY;
	private double speed;
	private double count;

	public Background(int numPoints, double speed, double count) {
		this.pointsX = new double[numPoints];
		this.pointsY = new double[numPoints];

		this.speed = speed;
		this.count = count;
	}

	public void ramdomPoints(int boundsX, int boundsY) {
		for(int i = 0; i < pointsX.length; i++) {
			pointsX[i] = Math.random() * boundsX;
			pointsY[i] = Math.random() * boundsY;
		}
	}	
}