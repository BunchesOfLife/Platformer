package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import src.Framework;
import src.ObjectId;

public class MovingGround extends DynamicObject{
	
	private int width = Framework.unit, height = Framework.unit;
	private boolean yDirection;
	private int time = 0;
	private int switchTimer;
	
	public MovingGround(float x, float y, ObjectId id, int switchTimer, boolean yDirection) {
		super(x, y, id);
		this.switchTimer = switchTimer;
		this.yDirection = yDirection;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect((int)x, (int)y, width, height);
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		x += dx;
		time++;
		
		if(time >= switchTimer){
			setTime(0);
			if(isYDirection()) setYDirection(false);
			else if(!isYDirection()) setYDirection(true);
		}
	
		if(isYDirection()){
			setDy(3);
		}else setDy(-3);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isYDirection() {
		return yDirection;
	}

	public void setYDirection(boolean yDirection) {
		this.yDirection = yDirection;
	}

}
