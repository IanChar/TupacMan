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

	
	//initializes Tupac man going to the right
	public GPacman(double diameter) {
		increasing = true;
		frameCounter = 0;
		direction = 1;
		size= diameter;
		body = new GArc(size, size, 45 - frameCounter * 3, 270 + frameCounter * 6);
		body.setFilled(true);
		body.setColor(Color.YELLOW);
		add(body,0,0);
	}
	public void turnLeft(){
		remove(body);
		body = new GArc(size, size, 225 - frameCounter * 3, 270 + frameCounter * 6);
		body.setFilled(true);
		body.setColor(Color.YELLOW);
		add(body);
		direction = 3;
	}
	
	public void turnRight(){
		remove(body);
		body = new GArc(size, size, 45 - frameCounter * 3, 270 + frameCounter * 6);
		body.setFilled(true);
		body.setColor(Color.YELLOW);
		add(body);
		direction = 1;
	}

	public void turnDown(){
		remove(body);
		body = new GArc(size, size, 315 - frameCounter * 3, 270 + frameCounter * 6);
		body.setFilled(true);
		body.setColor(Color.YELLOW);
		add(body);
		direction = 4;
	}
	public void turnUp(){
		remove(body);
		body = new GArc(size, size, 135 - frameCounter * 3, 270 + frameCounter * 6);
		body.setFilled(true);
		body.setColor(Color.YELLOW);
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
			body.setFilled(true);
			body.setColor(Color.YELLOW);
			add(body);
			break;
		case 2:
			remove(body);
			body = new GArc(size, size, 135 - frameCounter * 3, 270 + frameCounter * 6);
			body.setFilled(true);
			body.setColor(Color.YELLOW);
			add(body);
			break;
		case 3:
			remove(body);
			body = new GArc(size, size, 225 - frameCounter * 3, 270 + frameCounter * 6);
			body.setFilled(true);
			body.setColor(Color.YELLOW);
			add(body);
			break;
		case 4:
			remove(body);
			body = new GArc(size, size, 315 - frameCounter * 3, 270 + frameCounter * 6);
			body.setFilled(true);
			body.setColor(Color.YELLOW);
			add(body);
			break;
		}
	}


}
