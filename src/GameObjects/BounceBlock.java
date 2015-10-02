package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import src.Framework;
import src.ObjectId;

public class BounceBlock extends GameObject {
	
	int width = Framework.unit, height = Framework.unit;

	public BounceBlock(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void draw(Graphics g) {
		Color c = new Color(0, 200, 255);
		g.setColor(c);
		g.fillRect((int)x, (int)y, width, height);

	}

	@Override
	public void update(LinkedList<GameObject> object) {}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, width, height);
	}

}
