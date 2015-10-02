package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import src.Framework;
import src.ObjectId;

public class MovingEnemy extends DynamicObject{
	
	int width = Framework.unit, height = Framework.unit;
	private double time = 0;
	
	public MovingEnemy(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void draw(Graphics g) {
		Color c = new Color(255,50,0);
		g.setColor(c);
		g.fillRect((int)x, (int)y, width, height);
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
		x += dx;
		time+=0.02;
		setDx((float) (Math.sin(time)*3));
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}

	public double getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
