import java.awt.Color;

import acm.graphics.*;


public class GPacman extends GCompound{ 
	
	/** Private instance variables*/
	//Diameter of the Pacman's body
	private double size;
	private GArc body;
	//Says what frame the Pacman animation is on goes from 0-15 inclusive 
    private int frameCounter;
    
    private int direction;
    //Says whether frame number is increasing or decreasing
    private boolean increasing;
    //Variable that tells you which direction you want to turn. 1=Up 2=Down 3=Left 4=Right
	private int turnRequest=0;	
	//Color of the Pacman
	private Color bodyColor=Color.yellow;
	//x Velocity
	private double vx;
	//y Velocity
	private double vy;
	
	public GPacman(double diameter) {
        increasing = true;
        frameCounter = 0;
        direction = 4;
        size= diameter;
        body = new GArc(size, size, 45 - frameCounter * 3, 270 + frameCounter * 6);
        body.setFilled(true);
        body.setColor(bodyColor);
        add(body);
	}
	public void turnLeft(){
	        remove(body);
	        body = new GArc(size, size, 225 - frameCounter * 3, 270 + frameCounter * 6);
	        body.setFilled(true);
	        body.setColor(bodyColor);
	        add(body);
	        direction = 3;
	}
	
	public void turnRight(){
	        remove(body);
	        body = new GArc(size, size, 45 - frameCounter * 3, 270 + frameCounter * 6);
	        body.setFilled(true);
	        body.setColor(bodyColor);
	        add(body);
	        direction = 4;
	}
	
	public void turnDown(){
	        remove(body);
	        body = new GArc(size, size, 315 - frameCounter * 3, 270 + frameCounter * 6);
	        body.setFilled(true);
	        body.setColor(bodyColor);
	        add(body);
	        direction = 2;
	}
	public void turnUp(){
	        remove(body);
	        body = new GArc(size, size, 135 - frameCounter * 3, 270 + frameCounter * 6);
	        body.setFilled(true);
	        body.setColor(bodyColor);
	        add(body);
	        direction = 1;
	}
	
	public void animate(){
	        if(increasing) {
	                if(frameCounter < 15) {
	                        frameCounter++;
	                }
	                else {
	                        frameCounter = 14;
	                        increasing = false;
	                }
	        }
	        else
	                if(frameCounter > 0) {
	                        frameCounter--;
	                }
	                else
	                {
	                        frameCounter = 1;
	                        increasing = true;
	                }
	        
	        switch(direction) {
		        case 4: remove(body);
		                body = new GArc(size, size, 45 - frameCounter * 3, 270 + frameCounter * 6);
		                break;
		        case 1: remove(body);
		                body = new GArc(size, size, 135 - frameCounter * 3, 270 + frameCounter * 6);
		                break;
		        case 3: remove(body);
		                body = new GArc(size, size, 225 - frameCounter * 3, 270 + frameCounter * 6);
		                break;
		        case 2: remove(body);
		                body = new GArc(size, size, 315 - frameCounter * 3, 270 + frameCounter * 6);
		                break;
	        }
	        body.setFilled(true);
	        body.setColor(bodyColor);
	        add(body);
	}
	
	/**
	 * 
	 * @param n The turn request that you want to set.
	 */
	public void setTurnRequest(int n) {
		turnRequest=n;
	}
	/**
	 * 
	 * @return Returns turnRequest
	 */
	public int getTurnRequest() {
		return turnRequest;
	}
	/**
	 * Sets the color of Pacman's body.
	 * @param c The new color
	 */
	public void setBodyColor(Color c) {
		bodyColor=c;
	}
	/**
	 * Sets vx and vy according to the parameters.
	 * @param x New x velocity
	 * @param y New y velocity
	 */
	public void setVelocities(double x, double y) {
		vx=x;
		vy=y;
	}
	public double getXVelocity() {
		return vx;
	}
	public double getYVelocity() {
		return vy;
	}
	public int getDirection() {
		return direction;
	}
	
}
