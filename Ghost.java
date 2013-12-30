	import java.awt.Color;
	import acm.graphics.*;

	public abstract class Ghost extends PacmanCharacter{

		
		private GArc body;
		//1 is right, 2 is up, 3 is left, and 4 is down
		private int nextDirection;
		
		public Ghost(Color color){
			super(color);
			vx = -PacmanConstants.SPEED;
			vy = 0;
			setNextDirection(1);
		}
		
		abstract void decideNextDirection(GPacman[] targets);
		
		
		public int getNextDirection() {
			return nextDirection;
		}

		public void setNextDirection(int nextDirection) {
			this.nextDirection = nextDirection;
		}
		
		public Color getColor(){
			return bodyColor;
		}
		
		public void setColor(Color color){
			bodyColor = color;
		}
		
		public GArc getBody(){
			return body;
		}
		
		public void setBody(GArc body){
			this.body = body;
		}
		
	}


