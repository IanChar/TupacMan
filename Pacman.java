import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Pacman extends GraphicsProgram implements PacmanConstants{
        
        /**Private instance variables*/
        private GPacman pacman1;
        private GPacman pacman2;
        private int pacman1score=0;
        private int pacman2score=0;
        private GLabel scoreboard;
        private GPacman[] pacmen = new GPacman[2];
        private Ghost[] ghosts = new Ghost[5];
        
        public static void main(String[] args) { 
                 new Pacman().start(args); 
                 } 
        
        public void init() {
                setUpGameboard();
                addMouseListeners();
                addKeyListeners();
                startGame();
                
                while(true){
                        move(pacman1);
                        move(pacman2);
                        for(Ghost ghost : ghosts)
                        {
                        	move(ghost);
                        }
                        
                        changeDirection(pacman1);
                        changeDirection(pacman2);
                        for(Ghost ghost : ghosts)
                        	changeDirection(ghost);
                        
                        adjustForCollision(pacman1);
                        adjustForCollision(pacman2);
                        for(Ghost ghost : ghosts)
                        	adjustForCollision(ghost);
                        
                        eatDots();
                        teleport(pacman1);
                        teleport(pacman2);
                        updateScoreBoard();
                        pause(DELAY);
                }
                
        }
        private void adjustForCollision(GPacman pacman) {
                if(getObjectinFront(pacman) instanceof GRect) {
                        if(pacman.getXVelocity()>0) {
                                pacman.move(-1, 0);
                        }
                        else if(pacman.getXVelocity()<0) {
                                pacman.move(1, 0);
                        }
                        else if(pacman.getYVelocity()>0) {
                                pacman.move(0, -1);
                        }
                        else if(pacman.getYVelocity()<0) {
                                pacman.move(0, 1);
                        }
                }
        }
        
        private void adjustForCollision(Ghost ghost) {
            if(getObjectinFront(ghost) instanceof GRect) {
                    if(ghost.getVx()>0) {
                            ghost.move(-1, 0);
                    }
                    else if(ghost.getVx()<0) {
                            ghost.move(1, 0);
                    }
                    else if(ghost.getVy()>0) {
                            ghost.move(0, -1);
                    }
                    else if(ghost.getVy()<0) {
                            ghost.move(0, 1);
                    }
            }
    }
    
        
        private void setUpGameboard() {
                setBackground(BACKGROUND_COLOR);
                initiatePacman();
                
                addWalls();
                initiateGhosts();
                addScoreBoard();
        }
        
        private void initiatePacman() {
                pacman1=new GPacman(PACMAN_SIZE);
                pacman1.setVelocities(SPEED,(double)0);
                add(pacman1,WALL_SIZE+1,WALL_SIZE+1);
                
                pacman2=new GPacman(PACMAN_SIZE);
                pacman2.setBodyColor(new Color(157,74,0));
                pacman2.turnLeft();
                pacman2.setVelocities(-SPEED, 0);
                add(pacman2,PacmanConstants.WIDTH-2*WALL_SIZE+1,PacmanConstants.HEIGHT-2*WALL_SIZE+1);
                
                pacmen[0] = pacman1;
                pacmen[1] = pacman2;
                
        }
        
        private void initiateGhosts(){
        	ghosts[0] = new RedGhost();
        	for(Ghost ghost : ghosts)
        	{
        		add(ghost, WALL_SIZE *9, WALL_SIZE * 7);
        	}
        }
        
        
        private void addWalls() {
                boolean[][] wallsArray={{true, true, true, true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                                                                {true, false, false, false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,true},
                                                                {true, false, true, true,false,true,true,true,false,true,false,true,true,true,false,true,true,false,true},
                                                                {true, false, false, false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,true},
                                                                {true, false, true, true,false,true,false,true,true,true,true,true,false,true,false,true,true,false,true},
                                                                {true, false, false, false,false,true,false,false,false,true,false,false,false,true,false,false,false,false,true},
                                                                {true, true, true, true,false,true,true,true,false,true,false,true,true,true,false,true,true,true,true},
                                                                {false, false, false, true,false,true,false,false,false,false,false,false,false,true,false,true,false,false,false},
                                                                {true, true, true, true,false,true,false,true,true,true,true,true,false,true,false,true,true,true,true},
                                                                {false, false, false, false,false,false,false,true,false,false,false,true,false,false,false,false,false,false,false},
                                                                {true, true, true, true,false,true,false,true,true,true,true,true,false,true,false,true,true,true,true},
                                                                {false, false, false, true,false,true,false,false,false,false,false,false,false,true,false,true,false,false,false},
                                                                {true, true, true, true,false,true,false,true,true,true,true,true,false,true,false,true,true,true,true},
                                                                {true, false, false, false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,true},
                                                                {true, false, true, true,false,true,true,true,false,true,false,true,true,true,false,true,true,false,true},
                                                                {true, false, false, true,false,false,false,false,false,false,false,false,false,false,false,true,false,false,true},
                                                                {true, true, false, true,false,true,false,true,true,true,true,true,false,true,false,true,false,true,true},
                                                                {true, false, false, false,false,true,false,false,false,true,false,false,false,true,false,false,false,false,true},
                                                                {true, false, true, true,true,true,true,true,false,true,false,true,true,true,true,true,true,false,true},
                                                                {true, false, false, false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                                                                {true, true, true, true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true}};
                for(int row=0; row<NROWS; row++) {
                        for(int col=0;col<NCOLS; col++) {
                                if(wallsArray[row][col]) {
                                        buildWall(row, col);
                                }
                                else {
                                        buildDot(row, col);
                                }
                        }
                }
        }
        private void buildDot(int r, int c) {
                int xcoord= c*WALL_SIZE+(WALL_SIZE-SMALL_DOT_SIZE)/2;
                int ycoord= r*WALL_SIZE+(WALL_SIZE-SMALL_DOT_SIZE)/2;
                GOval dot = new GOval(SMALL_DOT_SIZE, SMALL_DOT_SIZE);
                dot.setFilled(true);
                dot.setColor(Color.WHITE);
                add(dot,xcoord,ycoord);
        }
        private void buildWall(int r, int c) {
                int xcoord= c*WALL_SIZE;
                int ycoord= r*WALL_SIZE;
                GRect wall = new GRect(WALL_SIZE,WALL_SIZE);
                wall.setColor(Color.BLUE);
                wall.setFilled(true);
                wall.setFillColor(new Color(28, 70, 222));
                add(wall,xcoord,ycoord);
        }

        private void changeDirection(GPacman pacman) {
                int turnRequest=pacman.getTurnRequest();
                if(turnRequest==1&&canGoVertical(pacman.getX()+PACMAN_SIZE/2)&&topIsClear(pacman)) {
                        pacman.setVelocities(0, -SPEED);
                        turnRequest=0;
                }
                else if(turnRequest==2&&canGoVertical(pacman.getX()+PACMAN_SIZE/2)&&bottomIsClear(pacman)) {
                        pacman.setVelocities(0, SPEED);
                        turnRequest=0;
                }
                else if(turnRequest==3&&canGoHorizontal(pacman.getY()+PACMAN_SIZE/2)&&leftIsClear(pacman)) {
                        pacman.setVelocities(-SPEED, 0);
                        turnRequest=0;
                }
                else if(turnRequest==4&&canGoHorizontal(pacman.getY()+PACMAN_SIZE/2)&&rightIsClear(pacman)) {
                        pacman.setVelocities(SPEED, 0);
                        turnRequest=0;
                }
        }
        
        private void changeDirection(Ghost ghost) {
            int turnRequest= ghost.getNextDirection();
            if(turnRequest==1&&canGoVertical(ghost.getX()+PACMAN_SIZE/2)&&topIsClear(ghost)) {
                    ghost.setVelocities(0, -SPEED);
                    turnRequest=0;
            }
            else if(turnRequest==2&&canGoVertical(ghost.getX()+PACMAN_SIZE/2)&&bottomIsClear(ghost)) {
                    ghost.setVelocities(0, SPEED);
                    turnRequest=0;
            }
            else if(turnRequest==3&&canGoHorizontal(ghost.getY()+PACMAN_SIZE/2)&&leftIsClear(ghost)) {
                    ghost.setVelocities(-SPEED, 0);
                    turnRequest=0;
            }
            else if(turnRequest==4&&canGoHorizontal(ghost.getY()+PACMAN_SIZE/2)&&rightIsClear(ghost)) {
                    ghost.setVelocities(SPEED, 0);
                    turnRequest=0;
            }
    }

        private void move(GPacman pacman) {
                if(pacman.getYVelocity()<0) {
                        pacman.turnUp();
                }
                else if(pacman.getYVelocity()>0) {
                        pacman.turnDown();
                }
                else if(pacman.getXVelocity()>0) {
                        pacman.turnRight();
                }
                else if(pacman.getXVelocity()<0) {
                        pacman.turnLeft();
                }
                pacman.animate();
                pacman.move(pacman.getXVelocity(), pacman.getYVelocity());
        
                                
        }
       
        private void move(Ghost ghost) {
            
            ghost.move(ghost.getX(), ghost.getY());
        }
            
        private void startGame() {
                GLabel prompt = new GLabel("CLICK TO START GAME");
                prompt.setFont("Sans_Serif-26");
                prompt.setColor(Color.red);
                add(prompt, getWidth()/2-prompt.getWidth()/2,getHeight()/2-prompt.getAscent()/2);
                waitForClick();
                remove(prompt);
        }
        private void eatDots() {
                GObject collision1 = getObjectinFront(pacman1);
                if(collision1 instanceof GOval) {
                        remove(collision1);
                        pacman1score++;
                }
                GObject collision2= getObjectinFront(pacman2);
                if(collision2 instanceof GOval) {
                        remove(collision2);
                        pacman2score++;
                }
        }

        private boolean canGoHorizontal(double ycoord) {

                for(int i=0;i<NROWS;i++) {
                        if((15+i*30-1)<ycoord&&ycoord<(15+i*30+1)) {
                                return true;
                        }
                }
                return false;
        }
        private boolean canGoVertical(double xcoord) {

                for(int i=0;i<NCOLS;i++) {
                        if((15+i*30-1)<=xcoord&&xcoord<=(15+i*30+1)) {
                                return true;
                        }
                }
                return false;
        }
        /**
         * Method tells you if the space above pacman is clear or not.
         * @param pacman The Pacman for which it checks if top is clear.
         * @return True if top is clear, false otherwise.
         */
        private boolean topIsClear(GPacman pacman) {
        return getElementAt(pacman.getX()+PACMAN_SIZE/2, pacman.getY()-3)==null;
        }
        private boolean bottomIsClear(GPacman pacman) {
        return getElementAt(pacman.getX()+PACMAN_SIZE/2, pacman.getY()+PACMAN_SIZE+3)==null;
        }
        private boolean        leftIsClear(GPacman pacman) {
        return getElementAt(pacman.getX()-3, pacman.getY()+PACMAN_SIZE/2)==null;
        }
        private boolean rightIsClear(GPacman pacman) {
        return getElementAt(pacman.getX()+PACMAN_SIZE+3, pacman.getY()+PACMAN_SIZE/2)==null;
        }
        
        private boolean topIsClear(Ghost ghost) {
            return getElementAt(ghost.getX()+PACMAN_SIZE/2, ghost.getY()-3)==null;
            }
        private boolean bottomIsClear(Ghost ghost) {
            return getElementAt(ghost.getX()+PACMAN_SIZE/2, ghost.getY()+PACMAN_SIZE+3)==null;
            }
        private boolean leftIsClear(Ghost ghost) {
            return getElementAt(ghost.getX()-3, ghost.getY()+PACMAN_SIZE/2)==null;
            }
        private boolean rightIsClear(Ghost ghost) {
            return getElementAt(ghost.getX()+PACMAN_SIZE+3, ghost.getY()+PACMAN_SIZE/2)==null;
            }
        /**
         * Gets the GObject in front of pacman. Returns null if nothing is there.
         * @param pacman The GPacman object of which it gets the object in front
         * @return The object in front of pacman.
         */
        private GObject getObjectinFront(GPacman pacman) {
                if(pacman.getXVelocity()>0) {
                        return getElementAt(pacman.getX()+PACMAN_SIZE+1, pacman.getY()+PACMAN_SIZE/2);
                }
                else if(pacman.getXVelocity()<0) {
                        return getElementAt(pacman.getX()-1, pacman.getY()+PACMAN_SIZE/2);
                }
                else if(pacman.getYVelocity()>0) {
                        return getElementAt(pacman.getX()+PACMAN_SIZE/2, pacman.getY()+PACMAN_SIZE+1);
                }
                else if(pacman.getYVelocity()<0) {
                        return getElementAt(pacman.getX()+PACMAN_SIZE/2, pacman.getY()-1);
                }
                return null;
        }
        
        private GObject getObjectinFront(Ghost ghost) {
            if(ghost.getVx()>0) {
                    return getElementAt(ghost.getX()+PACMAN_SIZE+1, ghost.getY()+PACMAN_SIZE/2);
            }
            else if(ghost.getVx()<0) {
                    return getElementAt(ghost.getX()-1, ghost.getY()+PACMAN_SIZE/2);
            }
            else if(ghost.getVy()>0) {
                    return getElementAt(ghost.getX()+PACMAN_SIZE/2, ghost.getY()+PACMAN_SIZE+1);
            }
            else if(ghost.getVy()<0) {
                    return getElementAt(ghost.getX()+PACMAN_SIZE/2, ghost.getY()-1);
            }
            return null;
    }
        /**
         * Method: teleport()
         * 
         * When Pacman travels off-screen either to the left or right, this method teleports Pacman to the other side of 
         * the screen. 
         * @param pacman The GPacman object to be considered for teleportation.
         */
        private void teleport(GPacman pacman) {
                if(pacman.getX()+PACMAN_SIZE/2<0) {
                        pacman.setLocation(PacmanConstants.WIDTH-PACMAN_SIZE/2, pacman.getY());
                }
                else if(pacman.getX()+PACMAN_SIZE/2>PacmanConstants.WIDTH) {
                        pacman.setLocation(-PACMAN_SIZE/2, pacman.getY());
                }
        }
        private void addScoreBoard() {
                scoreboard = new GLabel("Pacman 1: "+pacman1score+ "    Pacman 2: "+pacman2score);
                scoreboard.setFont("Sans_Serif-22");
                scoreboard.setColor(Color.WHITE);

                add(scoreboard,0,scoreboard.getAscent());
        }
        private void updateScoreBoard() {
                remove(scoreboard); 
                scoreboard = new GLabel("Pacman 1: "+pacman1score+ "    Pacman 2: "+pacman2score);
                scoreboard.setFont("Sans_Serif-22");
                scoreboard.setColor(Color.WHITE);
                add(scoreboard,0,scoreboard.getAscent());
                
        }
        public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                        case KeyEvent.VK_UP:        pacman1.setTurnRequest(1); break;
                        case KeyEvent.VK_DOWN:        pacman1.setTurnRequest(2); break;
                        case KeyEvent.VK_LEFT:        pacman1.setTurnRequest(3); break;
                        case KeyEvent.VK_RIGHT:        pacman1.setTurnRequest(4); break;
                        case KeyEvent.VK_W:        pacman2.setTurnRequest(1); break;
                        case KeyEvent.VK_S:        pacman2.setTurnRequest(2); break;
                        case KeyEvent.VK_A:        pacman2.setTurnRequest(3); break;
                        case KeyEvent.VK_D:        pacman2.setTurnRequest(4); break;
                }
        }
        
//        private boolean inBoundaries() {
//                return pacman1.getX()>0&&(pacman1.getX()+PACMAN_SIZE)<WIDTH&&pacman1.getY()>0&&(pacman1.getY()+PACMAN_SIZE)<HEIGHT;
//        }

