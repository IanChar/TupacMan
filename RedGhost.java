import java.awt.Color;
import acm.graphics.*;

public class RedGhost extends Ghost{

	public RedGhost(){
		setVx(-PacmanConstants.SPEED);
		setVy(0);
		setNextDirection(1);
		setBody( new GArc(28,28,0,360));
		getBody().setFilled(true);
		getBody().setColor(Color.RED);
		add(getBody());
	}
	
	public void decideNextDirection(GPacman[] targets){	
		double temp = Math.abs(targets[0].getX() - getX());
		int tempDir = 1;
		
		for(GPacman target : targets)
		{
			double yDif = target.getY()-getY();
			double xDif = target.getX()-getX();
			
			if(Math.abs(xDif) < temp && Math.abs(xDif) > 0)
			{
				temp = Math.abs(xDif);
				if(xDif > 0)
					tempDir = 1;
				else
					tempDir = 3;
			}
			
			if(Math.abs(yDif) < temp && Math.abs(yDif) > 0)
			{
				temp = Math.abs(yDif);
				if(yDif > 0)
					tempDir = 1;
				else
					tempDir = 3;
			}
		}
		
		setNextDirection(tempDir);
	}
	
}
