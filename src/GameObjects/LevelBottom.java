package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import src.ObjectId;

public class LevelBottom extends GameObject{
	
	int width = 32, height = 32;

	public LevelBottom(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void draw(Graphics g) {}

	@Override
	public void update(LinkedList<GameObject> object) {
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	
}
