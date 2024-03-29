package entities.drawer.body.basic_body;

public class Posicao {
	private double x,y;
	
	public Posicao(double x, double y) {
		this.set(x,y);
	}
	
	public Posicao() {
		this.set(0,0);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void set(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void move(double deltaX, double deltaY){
		this.x += deltaX;
		this.y += deltaY;
	}
	
	public double distancia(Posicao other){
		return Posicao.distancia(this, other);
	}
	
	//Dalhe pitagoras
	public static double distancia(Posicao pos1, Posicao pos2){
		double dx = pos1.getX()-pos2.getX();
		double dy = pos1.getY()-pos2.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}
}
