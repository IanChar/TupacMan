import java.awt.Color;

import acm.graphics.*;


public class GPacman extends PacmanCharacter{ 
	
	/** Private instance variables*/

	private GArc body;
	//Says what frame the Pacman animation is on goes from 0-15 inclusive 
    private int frameCounter;
    //Says whether frame number is increasing or decreasing
    private boolean increasing;
	
	public GPacman(double diameter, Color color) {
		super(color);
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

}
