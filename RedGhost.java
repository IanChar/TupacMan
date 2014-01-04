import java.awt.Color;
import acm.graphics.*;

public class RedGhost extends Ghost implements PacmanConstants{

        public RedGhost(Color color){
                super(color);
                setVelocities(-SPEED,0);
                setBody( new GArc(28,28,0,360));
                getBody().setFilled(true);
                getBody().setColor(bodyColor);
                add(getBody());
        }
        
        public void decideNextDirection(GPacman[] targets){        
                double temp = 100000;
                int tempDir = 1;
                
                for(GPacman target : targets)
                {
                        double yDif = target.getY()-getY();
                        double xDif = target.getX()-getX();
                        
                        if(Math.abs(xDif) <= temp )//&& Math.abs(xDif) > 30)
                        {
                                temp = Math.abs(xDif);
                                if(xDif > 0)
                                        tempDir = 1;
                                else
                                        tempDir = 3;
                        }
                        
                        if(Math.abs(yDif) < temp)// && Math.abs(yDif) > 30)
                        {
                                temp = Math.abs(yDif);
                                if(yDif > 0)
                                        tempDir = 4;
                                else
                                        tempDir = 2;
                        }
                }
                
                setTurnRequest(tempDir);
        }
        public void animate() {
                
        }
}
