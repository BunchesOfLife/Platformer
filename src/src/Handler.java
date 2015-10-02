package src;

import java.awt.Graphics;
import java.util.LinkedList;

import GameObjects.GameObject;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;
	
	public void updateGameObjects(){
		for(int i=0; i < object.size(); i++){
			tempObject = object.get(i);
			tempObject.update(object);
		}
	}
	
	public void draw(Graphics g){
		for(int i=0; i < object.size(); i++){
			tempObject = object.get(i);
			tempObject.draw(g);
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public int size(){
		return this.object.size();
	}
	
	public GameObject get(int index){
		return this.object.get(index);
	}
}
