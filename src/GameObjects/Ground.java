package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import src.ObjectId;

public class Ground extends GameObject{
	
	private float width=32, height=32;

	public Ground(float x, float y, ObjectId id) {
		super(x, y, id);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void update(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}
	
}
