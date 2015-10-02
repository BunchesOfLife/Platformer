package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import src.Handler;
import src.ObjectId;

public class DamageBlock extends GameObject {
	
	private int width=32, height=32;
	Handler handler;

	public DamageBlock(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	@Override
	public void update(LinkedList<GameObject> object) {
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}
}
