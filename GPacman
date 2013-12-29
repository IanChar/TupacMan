import java.awt.Color;

import acm.graphics.*;


public class GPacman extends GCompound{ 
	private double size;
	private GArc body;
	public GPacman(double diameter) {
		size= diameter;
		body = new GArc(size, size, 30, 300);
		body.setFilled(true);
		body.setColor(Color.YELLOW);
		add(body,0,0);
	}
	public void turnLeft(){
		remove(body);
		body = new GArc(size, size, 210, 300);
		body.setFilled(true);
		body.setColor(Color.YELLOW);
		add(body);
	}
	public void turnRight(){
		remove(body);
		body = new GArc(size, size, 30, 300);
		body.setFilled(true);
		body.setColor(Color.YELLOW);
		add(body);
	}

	public void turnDown(){
		remove(body);
		body = new GArc(size, size, 300, 300);
		body.setFilled(true);
		body.setColor(Color.YELLOW);
		add(body);
	}
	public void turnUp(){
		remove(body);
		body = new GArc(size, size, 120, 300);
		body.setFilled(true);
		body.setColor(Color.YELLOW);
		add(body);
	}


}
