package src;

import GameObjects.GameObject;

public class Camera {
	int x, y;
	
	public Camera(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void move(GameObject player){
		x = (int) (Framework.WIDTH/2 - player.getX());
		y = (int) (Framework.HEIGHT/2 - player.getY());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
