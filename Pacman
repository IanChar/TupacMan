import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Pacman extends GraphicsProgram {
	
	/**Constants*/
	public static final int APPLICATION_WIDTH = 570;
	public static final int APPLICATION_HEIGHT = 630;

	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;
	private static final int NROWS=21;
	private static final int NCOLS=19;
	
	private static final int SMALL_DOT_SIZE=10;
	private static final int WALL_SIZE=30;
	private static final int PACMAN_SIZE = 24;
	private static final double EPSILON = 1e-10;
	private static final int DELAY = 6;
	private static final double SPEED = 1;
	
	
	
	/**Private instance variables*/
	
	private double vx=SPEED;
	private double vy=0;
	private GPacman pacman;
//	private boolean[][] wallsArray;
	
	public void init() {
		setUpGameboard();
		addMouseListeners();
		addKeyListeners();
		startGame();
		
		while(true){
			move();
			adjustForCollision();
			eatDots();
			pause(DELAY);
		}
		
	}
	private void adjustForCollision() {
		if(getObjectinFront() instanceof GRect) {
			if(vx>0) {
				pacman.move(-1, 0);
			}
			else if(vx<0) {
				pacman.move(1, 0);
			}
			else if(vy>0) {
				pacman.move(0, -1);
			}
			else if(vy<0) {
				pacman.move(0, 1);
			}
		}
	}
	private void setUpGameboard() {
		setBackground(Color.black);
		pacman=new GPacman(PACMAN_SIZE);
		add(pacman,WALL_SIZE+3,WALL_SIZE+3);
		addWalls();
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
	private void move() {
		if(vy<0) {
			pacman.turnUp();
		}
		else if(vy>0) {
			pacman.turnDown();
		}
		else if(vx>0) {
			pacman.turnRight();
		}
		else if(vx<0) {
			pacman.turnLeft();
		}
		pacman.move(vx, vy);
		
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
		GObject collision = getObjectinFront();
		if(collision instanceof GOval) {
			remove(collision);
		}
	}
//	private GObject getObjectAtCorners() {
//		GObject object = getElementAt(pacman.getX()+PACMAN_SIZE/2, pacman.getY()-1);	//Top
//		if(object == null) {
//			object = getElementAt(pacman.getX()-1, pacman.getY()+PACMAN_SIZE/2);	//Left
//			if(object == null) {
//				object = getElementAt(pacman.getX()+PACMAN_SIZE+1, pacman.getY()+PACMAN_SIZE/2);	//Right
//				if(object==null) {
//					object = getElementAt(pacman.getX()+PACMAN_SIZE/2, pacman.getY()+PACMAN_SIZE+1);	//Bottom
//				}
//			}
//		}
//		return object;
//	}
	private boolean leftIsClear() {
		return true;
		
	}
	private boolean topIsClear() {
		return getElementAt(pacman.getX()-1, pacman.getY()-1.5)==null&&getElementAt(pacman.getX()+PACMAN_SIZE+1, pacman.getY()-1.5)==null;
	}
	private boolean rightIsClear() {
		return getElementAt(pacman.getX()+PACMAN_SIZE+1.5, pacman.getY()-1)==null&&getElementAt(pacman.getX()+PACMAN_SIZE+1.5, pacman.getY()+PACMAN_SIZE+1)==null;
	}
	private boolean bottomIsClear() {
		return getElementAt(pacman.getX()-1, pacman.getY()+PACMAN_SIZE+1.5)==null&&getElementAt(pacman.getX()+PACMAN_SIZE+1, pacman.getY()+PACMAN_SIZE+1.5)==null;
	}
	
	private GObject getObjectinFront() {
		if(vx>0) {
			return getElementAt(pacman.getX()+PACMAN_SIZE+1, pacman.getY()+PACMAN_SIZE/2);
		}
		else if(vx<0) {
			return getElementAt(pacman.getX()-1, pacman.getY()+PACMAN_SIZE/2);
		}
		else if(vy>0) {
			return getElementAt(pacman.getX()+PACMAN_SIZE/2, pacman.getY()+PACMAN_SIZE+1);
		}
		else if(vy<0) {
			return getElementAt(pacman.getX()+PACMAN_SIZE/2, pacman.getY()-1);
		}
		return null;
	
	}
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:	if(topIsClear()){vx=0;vy=-SPEED; break;} 
									else{break;}
			case KeyEvent.VK_DOWN:	if(bottomIsClear()){vx=0; vy=SPEED; break;}
									else{break;}
			case KeyEvent.VK_LEFT:	if(leftIsClear()){vx=-SPEED; vy=0; break;}
									else{break;}
			case KeyEvent.VK_RIGHT:	if(rightIsClear()){vx=SPEED; vy=0; break;} 
									else{break;}
		}
	}
//	private boolean inBoundaries() {
//		return pacman.getX()>0&&(pacman.getX()+PACMAN_SIZE)<WIDTH&&pacman.getY()>0&&(pacman.getY()+PACMAN_SIZE)<HEIGHT;
//	}

}
