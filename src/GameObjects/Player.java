package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import src.Framework;
import src.Handler;
import src.ObjectId;
import src.Framework.GameState;

public class Player extends DynamicObject{
	
	private int width = Framework.unit, height = Framework.unit*2, health = 100;
	private Handler handler;

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public void update(LinkedList<GameObject> object){
		
		x += dx;
		dy += gravity;
		y += dy;
		
		if(dy > MAX_SPEED){
			dy = MAX_SPEED;
		}
		
		collide(object);
		
		if(health <= 0){
			Framework.gameState = GameState.GameOver;
		}
		
	}
	
	private void collide(LinkedList<GameObject> object){
		
		for(int i=0; i < handler.size(); i++){
			
			GameObject tempObject = handler.get(i);
			
			if(tempObject.getId() == ObjectId.Ground){
				if(getBoundsBot().intersects(tempObject.getBounds())){
					dy = 0;
					setJumping(false);
					setFalling(false);
					y = tempObject.getY() - height;
				}else
					setFalling(true);
				
				if(getBoundsTop().intersects(tempObject.getBounds())){
					dy = 0;
					y = tempObject.getY() + 32;
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() - width;
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX() + 32;
				}
			}
			
			if(tempObject.getId() == ObjectId.MovingEnemy){
				if(getBoundsBot().intersects(tempObject.getBounds())){
					setHealth(health - 5);
					setDy(-10);
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())){
					setHealth(health - 5);
					setDy(8);
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					setHealth(health - 5);
					setDx(-8);
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())){
					setHealth(health - 5);
					setDx(8);
				}
			}
			
			if(tempObject.getId() == ObjectId.DamageBlock){
				if(getBoundsBot().intersects(tempObject.getBounds())){
					setHealth(health - 5);
					setDy(-10);
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())){
					setHealth(health - 5);
					setDy(8);
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					setHealth(health - 5);
					setDx(-8);
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())){
					setHealth(health - 5);
					setDx(8);
				}
			}
			
			if(tempObject.getId() == ObjectId.BounceBlock){
				if(getBoundsBot().intersects(tempObject.getBounds())){
					setDy(-15);
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())){
					setDy(8);
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds()) 
						&& !getBoundsBot().intersects(tempObject.getBounds()) &&
						!getBoundsTop().intersects(tempObject.getBounds())){
					setDx(-8);
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())
						&& !getBoundsBot().intersects(tempObject.getBounds()) &&
						!getBoundsTop().intersects(tempObject.getBounds())){
					setDx(8);
				}
			}
			
			if(tempObject.getId() == ObjectId.LevelBottom){
				if(getBoundsBot().intersects(tempObject.getBounds())){
					Framework.gameState = GameState.GameOver;
				}
			}
			
			if(tempObject.getId() == ObjectId.End){
				if(getBoundsBot().intersects(tempObject.getBounds())){
					Framework.gameState = GameState.GameWin;
				}
			}
		}
	}
	
	public Rectangle getBoundsBot(){
		return new Rectangle((int)x+(width/2)-(width/4), (int)y+(height/2), width/2, height/2);
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle((int)x+(width/2-(width/4)), (int)y, width/2, height/2);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle((int)x+width/2, (int)y+5, width/2, height-10);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle((int)x, (int)y+5, width/2, height-10);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
