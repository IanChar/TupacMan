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
	private static final int PACMAN_SIZE = 28;
	private static final double EPSILON = 1e-10;
	private static final int DELAY = 7;
	private static final double SPEED = 1;
	
	
	
	/**Private instance variables*/
	
	private double vx=SPEED;
	private double vy=0;
	private GPacman pacman;
	private int turnRequest=0;
	
	public void init() {
		setUpGameboard();
		addMouseListeners();
		addKeyListeners();
		startGame();
		
		while(true){
		
			move();
			changeDirection();
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
		add(pacman,WALL_SIZE+1,WALL_SIZE+1);
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
	private void changeDirection() {
		if(turnRequest==1&&canGoVertical()&&topIsClear()) {
			vx=0;
			vy=-1;
			turnRequest=0;
		}
		else if(turnRequest==2&&canGoVertical()&&bottomIsClear()) {
			vx=0;
			vy=1;
			turnRequest=0;
		}
		else if(turnRequest==3&&canGoHorizontal()&&leftIsClear()) {
			vx=-1;
			vy=0;
			turnRequest=0;
		}
		else if(turnRequest==4&&canGoHorizontal()&&rightIsClear()) {
			vx=1;
			vy=0;
			turnRequest=0;
		}
	
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
	private boolean canGoHorizontal() {
		double ycoord= pacman.getY()+PACMAN_SIZE/2;
		for(int i=0;i<NROWS;i++) {
			if((15+i*30-1)<ycoord&&ycoord<(15+i*30+1)) {
				return true;
			}
		}
		return false;
	}
	private boolean canGoVertical() {
		double xcoord= pacman.getX()+PACMAN_SIZE/2;
		for(int i=0;i<NCOLS;i++) {
			if((15+i*30-1)<=xcoord&&xcoord<=(15+i*30+1)) {
				return true;
			}
		}
		return false;
	}
	private boolean topIsClear() {
        return getElementAt(pacman.getX()+PACMAN_SIZE/2, pacman.getY()-3)==null;
	}
	private boolean bottomIsClear() {
        return getElementAt(pacman.getX()+PACMAN_SIZE/2, pacman.getY()+PACMAN_SIZE+3)==null;
	}
	private boolean	leftIsClear() {
        return getElementAt(pacman.getX()-3, pacman.getY()+PACMAN_SIZE/2)==null;
	}
	private boolean rightIsClear() {
        return getElementAt(pacman.getX()+PACMAN_SIZE+3, pacman.getY()+PACMAN_SIZE/2)==null;
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
			case KeyEvent.VK_UP:	turnRequest=1; break;
			case KeyEvent.VK_DOWN:	turnRequest=2; break;
			case KeyEvent.VK_LEFT:	turnRequest=3; break;
			case KeyEvent.VK_RIGHT:	turnRequest=4; break;
		}
	}
//	private boolean inBoundaries() {
//		return pacman.getX()>0&&(pacman.getX()+PACMAN_SIZE)<WIDTH&&pacman.getY()>0&&(pacman.getY()+PACMAN_SIZE)<HEIGHT;
//	}

}