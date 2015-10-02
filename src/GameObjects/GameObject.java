package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import src.ObjectId;

public abstract class GameObject {
	
	protected float x, y;
	protected ObjectId id;

	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void draw(Graphics g);
	public abstract void update(LinkedList<GameObject> object);
	
	public Rectangle getBounds(){
		return new Rectangle();
	}
	
	public Rectangle view(){
		return new Rectangle();
	}
	
	public ObjectId getId() {
		return id;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
