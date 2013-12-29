import java.awt.Color;

import acm.graphics.*;


public class GPacman extends GCompound{ 
	private double size;
	private GArc body;
	//Says what frame the pacman animation is on goes from 0-15 inclusive 
	private int frameCounter;
	//For direction right is 1, up is 2, left is 3, and down is 4
	private int direction;
	//Says whether frame number is increasing or decreasing
	private boolean increasing;
	
	private int turnRequest=0;	//Variable that tells you which direction you want to turn.
	
	private Color bodyColor=Color.yellow;

	
	//initializes Tupac man going to the right
	public GPacman(double diameter) {
		increasing = true;
		frameCounter = 0;
		direction = 1;
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
		direction = 1;
	}

	public void turnDown(){
		remove(body);
		body = new GArc(size, size, 315 - frameCounter * 3, 270 + frameCounter * 6);
		body.setFilled(true);
		body.setColor(bodyColor);
		add(body);
		direction = 4;
	}
	public void turnUp(){
		remove(body);
		body = new GArc(size, size, 135 - frameCounter * 3, 270 + frameCounter * 6);
		body.setFilled(true);
		body.setColor(bodyColor);
		add(body);
		direction = 2;
	}
	
	public void animate(){
		if(increasing)
			if(frameCounter < 15)
				frameCounter++;
			else 
			{
				frameCounter = 14;
				increasing = false;
			}
		else
			if(frameCounter > 0)
				frameCounter--;
			else
			{
				frameCounter = 1;
				increasing = true;
			}
		
		switch(direction)
		{case 1:
			remove(body);
			body = new GArc(size, size, 45 - frameCounter * 3, 270 + frameCounter * 6);
		
			break;
		case 2:
			remove(body);
			body = new GArc(size, size, 135 - frameCounter * 3, 270 + frameCounter * 6);
			break;
		case 3:
			remove(body);
			body = new GArc(size, size, 225 - frameCounter * 3, 270 + frameCounter * 6);
			break;
		case 4:
			remove(body);
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
	public void setBodyColor(Color c) {
		bodyColor=c;
	}


}
