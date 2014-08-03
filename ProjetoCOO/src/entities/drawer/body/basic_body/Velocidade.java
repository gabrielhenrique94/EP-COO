package entities.drawer.body.basic_body;

public class Velocidade {
	private double x,y;
	
	public Velocidade(double x , double y){
		this.set(x,y);
	}
	
	public Velocidade(){
		this.set(0, 0);
	}
	
	public void set(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void acelera(double deltaX, double deltaY){
		this.x += deltaX;
		this.y += deltaY;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
