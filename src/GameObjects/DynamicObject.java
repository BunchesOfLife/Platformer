package GameObjects;

import java.awt.Rectangle;

import src.ObjectId;

public abstract class DynamicObject extends GameObject{
	
	protected float dx=0, dy=0, gravity = 0.5f;
	private boolean jumping = false;
	private boolean falling = true;
	protected final int MAX_SPEED = 50;

	public DynamicObject(float x, float y, ObjectId id) {
		super(x, y, id);
	}
	
	public Rectangle getBounds(){
		return new Rectangle();
	}
	
	public Rectangle view(){
		return new Rectangle();
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
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

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}
	
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
}
