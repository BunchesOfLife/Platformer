package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import src.ObjectId;

public class End extends GameObject{
	
	private int width = 96, height = 96;

	public End(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillOval((int)x, (int)y, width, height);
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}

}
