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
        private Ghost[] ghosts = new Ghost[5];
        private GPacman[] players = new GPacman[2];
        
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
                        move(ghosts[0]);
                        
                        ghosts[0].decideNextDirection(players);
                        
                        changeDirection(pacman1);
                        changeDirection(pacman2);
                        changeDirection(ghosts[0]);
               
                        adjustForCollision(pacman1);
                        adjustForCollision(pacman2);
                        adjustForCollision(ghosts[0]);

                        eatDots();
                        teleport(pacman1);
                        teleport(pacman2);
                        updateScoreBoard();
                        pause(DELAY);
                }
                
        }
        private void setUpGameboard() {
                setBackground(BACKGROUND_COLOR);
                initiatePacman();
                addWalls();
                initiateGhosts();
                addScoreBoard();
        }
        
        private void adjustForCollision(PacmanCharacter character) {
                if(getObjectinFront(character) instanceof GRect) {
                        if(character.getVx()>0) {
                                character.move(-1, 0);
                        }
                        else if(character.getVx()<0) {
                                character.move(1, 0);
                        }
                        else if(character.getVy()>0) {
                                character.move(0, -1);
                        }
                        else if(character.getVy()<0) {
                                character.move(0, 1);
                        }
                }
        }

        private void initiatePacman() {
                pacman1=new GPacman(PACMAN_SIZE, Color.YELLOW);
                pacman1.setVelocities(SPEED,(double)0);
                add(pacman1,WALL_SIZE+1,WALL_SIZE+1);
                
                pacman2=new GPacman(PACMAN_SIZE, new Color(157,74,0));

                pacman2.turnLeft();
                pacman2.setVelocities(-SPEED, 0);
                add(pacman2,PacmanConstants.WIDTH-2*WALL_SIZE+1,PacmanConstants.HEIGHT-2*WALL_SIZE+1);
                
                players[0] = pacman1;
                players[1] = pacman2;
                
        }
        private void initiateGhosts(){
            Ghost red = new RedGhost(Color.RED);
            
            ghosts[0]=red;
            add(ghosts[0], WALL_SIZE *9, WALL_SIZE * 7);
               
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
        /**
         * Changes the direction of travel for character depending on the turnRequest. Only changes the x and y velocities
         * if the character's turn is legal. i.e. The direction must not be blocked and must be aligned with the grids.
         * @param character
         */
        private void changeDirection(PacmanCharacter character) {
                int turnRequest=character.getTurnRequest();
                if(turnRequest==1&&canGoVertical(character.getX()+PACMAN_SIZE/2)&&topIsClear(character)) {
                        character.setVelocities(0, -SPEED);
                        turnRequest=0;
                }
                else if(turnRequest==2&&canGoVertical(character.getX()+PACMAN_SIZE/2)&&bottomIsClear(character)) {
                        character.setVelocities(0, SPEED);
                        turnRequest=0;
                }
                else if(turnRequest==3&&canGoHorizontal(character.getY()+PACMAN_SIZE/2)&&leftIsClear(character)) {
                        character.setVelocities(-SPEED, 0);
                        turnRequest=0;
                }
                else if(turnRequest==4&&canGoHorizontal(character.getY()+PACMAN_SIZE/2)&&rightIsClear(character)) {
                        character.setVelocities(SPEED, 0);
                        turnRequest=0;
                }
        }
        /**
         * First determines the direction that the character shall face depending on its velocities, then moves the
         * character.
         * @param character The PacmanCharacter object to be moved.
         */
        private void move(PacmanCharacter character) {
                if(character.getVy()<0) {
                        character.turnUp();
                }
                else if(character.getVy()>0) {
                        character.turnDown();
                }
                else if(character.getVx()>0) {
                        character.turnRight();
                }
                else if(character.getVx()<0) {
                        character.turnLeft();
                }
                character.animate();
                character.move(character.getVx(), character.getVy());
                
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
         * Method tells you if the space above the object is clear or not.
         * @param obj The GObject for which it checks if top is clear.
         * @return True if top is clear, false otherwise.
         */
        private boolean topIsClear(GObject obj) {
        return getElementAt(obj.getX()+PACMAN_SIZE/2, obj.getY()-3)==null;
        }
        private boolean bottomIsClear(GObject obj) {
        return getElementAt(obj.getX()+PACMAN_SIZE/2, obj.getY()+PACMAN_SIZE+3)==null;
        }
        private boolean        leftIsClear(GObject obj) {
        return getElementAt(obj.getX()-3, obj.getY()+PACMAN_SIZE/2)==null;
        }
        private boolean rightIsClear(GObject obj) {
        return getElementAt(obj.getX()+PACMAN_SIZE+3, obj.getY()+PACMAN_SIZE/2)==null;
        }
        /**
         * Gets the GObject in front of character. Returns null if nothing is there.
         * @param character The PacmanCharacter object of which it gets the object in front
         * @return The object in front of character.
         */
        private GObject getObjectinFront(PacmanCharacter character) {
                if(character.getVx()>0) {
                        return getElementAt(character.getX()+PACMAN_SIZE+1, character.getY()+PACMAN_SIZE/2);
                }
                else if(character.getVx()<0) {
                        return getElementAt(character.getX()-1, character.getY()+PACMAN_SIZE/2);
                }
                else if(character.getVy()>0) {
                        return getElementAt(character.getX()+PACMAN_SIZE/2, character.getY()+PACMAN_SIZE+1);
                }
                else if(character.getVy()<0) {
                        return getElementAt(character.getX()+PACMAN_SIZE/2, character.getY()-1);
                }
                return null;
        }
        
        /**
         * Method: teleport()
         * 
         * When a character travels off-screen either to the left or right, this method teleports the character to the other side of 
         * the screen. 
         * @param obj The PacmanCharacter object to be considered for teleportation.
         */
        private void teleport(PacmanCharacter obj) {
                if(obj.getX()+PACMAN_SIZE/2<0) {
                        obj.setLocation(PacmanConstants.WIDTH-PACMAN_SIZE/2, obj.getY());
                }
                else if(obj.getX()+PACMAN_SIZE/2>PacmanConstants.WIDTH) {
                        obj.setLocation(-PACMAN_SIZE/2, obj.getY());
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


}
