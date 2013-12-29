import java.awt.Color;
import acm.graphics.*;

public abstract class Ghost extends GCompound{

	private double vx;
	private double vy;
	private GArc body;
	private Color color;
	//1 is right, 2 is up, 3 is left, and 4 is down
	private int nextDirection;
	
	public Ghost(){
		vx = -PacmanConstants.SPEED;
		vy = 0;
		setNextDirection(1);
	}
	
	public void decideNextDirection(GPacman[] targets){	
	
	}
	
	
	public void setVelocities(double x, double y) {
        vx=x;
        vy=y;
	}
	
	public double getVx(){
		return vx;
	}
	
	public void setVx(double input){
		vx = input;
	}
	
	public void setVy(double input){
		vy = input;
	}
	
	public double getVy(){
		return vy;
	}

	public int getNextDirection() {
		return nextDirection;
	}

	public void setNextDirection(int nextDirection) {
		this.nextDirection = nextDirection;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public GArc getBody(){
		return body;
	}
	
	public void setBody(GArc body){
		this.body = body;
	}
	
}

