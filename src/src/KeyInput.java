package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import GameObjects.DynamicObject;
import GameObjects.GameObject;
import src.Framework.GameState;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean aPressed = false;
	private boolean dPressed = false;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		switch(Framework.gameState){
		case Intro:
			if(key == KeyEvent.VK_ENTER){
				Framework.gameState = GameState.DifficultySelect;
			}
			break;
		case DifficultySelect:
			if(key == KeyEvent.VK_ENTER && Framework.levelLength > 0){
				Framework.gameState = GameState.Playing;
			}
			if(key == KeyEvent.VK_UP && Framework.levelLength <= Framework.loader.size()-3){
				Framework.levelLength++;
			}
			else if(key == KeyEvent.VK_DOWN && Framework.levelLength > 1){
				Framework.levelLength--;
			}
			break;
		case Playing:
			for(int i=0; i < handler.object.size(); i++){
				
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ObjectId.Player){
					
					if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){ 
						((DynamicObject) tempObject).setDx(5);
						dPressed = true;
					}else 
						dPressed = false;
					
					if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT){ 
						((DynamicObject) tempObject).setDx(-5);
						aPressed = true;
					}else 
						aPressed = false;
					
					if((key == KeyEvent.VK_W  || key == KeyEvent.VK_UP) && !((DynamicObject) tempObject).isJumping()){
						((DynamicObject) tempObject).setDy(-12);
						((DynamicObject) tempObject).setJumping(true);
					}
				}
			}
			if(key == KeyEvent.VK_P){
				Framework.gameState = GameState.Pause;
			}
			break;
		case Pause:
			if(key == KeyEvent.VK_P){
				Framework.gameState = GameState.Playing;
			}
			break;
		case GameOver:
			if(key == KeyEvent.VK_R){
				Framework.gameState = GameState.Reset;
			}
			break;
		case GameWin:
			if(key == KeyEvent.VK_R){
				Framework.gameState = GameState.Reset;
			}
			break;
		default:
			if(key == KeyEvent.VK_ESCAPE){
				System.exit(1);
			}
			break;
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i=0; i < handler.object.size(); i++){
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player){
				
				if((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && aPressed == false) ((DynamicObject) tempObject).setDx(0);
				if((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && dPressed == false) ((DynamicObject) tempObject).setDx(0);
				
			}
			
		}
		
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
	}
}
