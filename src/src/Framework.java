/*
 * Main framework for platformer game.
 * @author Brady Murren
 */

package src;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import GameObjects.*;

public class Framework extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "Platforming 101";
	
	private static boolean running = false;
	private Thread thread;
	
	public static int WIDTH, HEIGHT;
	public static int unit = 32;
	public static int levelLength = 10;
	public static boolean generated = false;
	public boolean chosenLevelDiff = false;
	
	public enum GameState {Intro, Playing, GameOver, GameWin, Reset, DifficultySelect, Pause};
	public static GameState gameState = GameState.Intro;
	   
   	//TODO Define instance variables for the game objects
   	
   	LevelBlock startBlock, endBlock;
   	public static BlockListLoader loader;
   	Handler handler;
   	Camera cam;
   	Random rand = new Random();
	KeyListener keyListener;
   	
   	public void init(){
   	
   		requestFocus();
   		
   		WIDTH = getWidth();
   		HEIGHT = getHeight();
   		
   		loader = new BlockListLoader();
   		loader.loadBlocks();
   		
   		startBlock = new LevelBlock(new File("/start_block.png"), true);
   		endBlock = new LevelBlock(new File("/end_block.png"), true);
   		loader.addLevelBlock(startBlock);
   		loader.addLevelBlock(endBlock);
   		
   		handler = new Handler();
   		cam = new Camera(0,0);
   		keyListener = new KeyInput(handler);
   		addKeyListener(keyListener);
   	}
	
	public void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		///////////////////////////////////
		switch(gameState){
		case Intro:
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.GREEN);
			g.setFont(new Font("TimesNewRoman", Font.ITALIC, 30));
			g.drawString(TITLE, 300, 100);
			g.setFont(new Font("TimesNewRoman", Font.PLAIN, 20));
			g.drawString("Hit ENTER to continue.", 300, 150);
			break;
		case DifficultySelect:
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.BLUE);
			g.setFont(new Font("TimesNewRoman", Font.PLAIN, 30));
			g.drawString("Choose amount of level blocks:", 100, 100);
			g.drawString(Integer.toString(levelLength), 120, 200);
		break;
		case Playing:
			//TODO Draw screen objects here
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			g2d.translate(cam.getX(), cam.getY());  //center camera on player
			
			handler.draw(g);                        //draws all game objects
			
			g2d.translate(-cam.getX(), -cam.getY());  //center camera on player
			
			g.setColor(Color.BLACK);
			g.fillRect(45, 35, 70, 25);
			g.setColor(Color.BLUE);
			for(int i=0; i < handler.size(); i++){
				if(handler.get(i).getId() == ObjectId.Player){
					String health = "Health: " + ((Player) handler.get(i)).getHealth();
					g.drawString(health, 50, 50);
				}
			}
			break;
		case GameOver:
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.RED);
			g.setFont(new Font("TimesNewRoman", Font.ITALIC, 30));
			g.drawString("YOU LOSE!", 300, 100);
			g.setFont(new Font("TimesNewRoman", Font.PLAIN, 20));
			g.drawString("Hit R to retry.", 300, 150);
			break;
		case GameWin:
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.GREEN);
			g.setFont(new Font("TimesNewRoman", Font.ITALIC, 30));
			g.drawString("YOU WIN!", 300, 100);
			g.setFont(new Font("TimesNewRoman", Font.PLAIN, 20));
			g.drawString("Hit R to play agian.", 300, 150);
			break;
		case Pause:
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.GREEN);
			g.setFont(new Font("TimesNewRoman", Font.ITALIC, 30));
			g.drawString("PAUSED", 300, 100);
			g.setFont(new Font("TimesNewRoman", Font.PLAIN, 20));
			g.drawString("Hit P to continue.", 300, 150);
			g.drawString("Controls:", 300, 200);
			g.drawString("Hit A/D or arrow keys to move left/right.", 300, 220);
			g.drawString("Hit W or up key to jump.", 300, 240);
			break;
		case Reset:
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			break;
		default:
			break;
		
		}
		///////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public void update(){
		if(gameState != GameState.Pause) {
			handler.updateGameObjects();
		}
		for(int i=0; i < handler.size(); i++){
			if(handler.get(i).getId() == ObjectId.Player){
				cam.move(handler.get(i));
				if(gameState == GameState.GameOver){
					((Player) handler.get(i)).setHealth(100);
				}
			}
		}
		if(gameState == GameState.Reset){
			removeKeyListener(keyListener);
			init();
			generated = false;
			gameState = GameState.DifficultySelect;
		}
		if(gameState == GameState.Playing){
			generateLevel(generated, levelLength);
			generated = true;
		}
	}
	
	private void generateLevel(boolean generated, int levelLength){
		if(levelLength > 0 && generated == false){
	   		int[] endCoordinates = loadImageLevel(loader.loadImage(startBlock.getFile()), 0, 0);
	   		
	   		//////////new block test code///////////
//	   		LevelBlock testBlock = new LevelBlock(new File("/moving_platforms.png"), true);
//	   		loader.addLevelBlock(testBlock);
//	   		endCoordinates[1] -= findStartCoordinates(loader.loadImage(testBlock.getFile()))[1]*unit;
//	   		endCoordinates[0] -= unit;
//	   		endCoordinates = loadImageLevel(loader.loadImage(testBlock.getFile()), endCoordinates[0], endCoordinates[1]);
	   		////////////////////////////////////////
	   		
	   		//comment out this line if testing new block
	   		endCoordinates = createLevel(loader, null, endCoordinates, null, 0, levelLength, 0);
	   		
	   		endCoordinates[1] -= findStartCoordinates(loader.loadImage(endBlock.getFile()))[1]*unit;
	   		endCoordinates[0] -= unit;
	   		
	   		loadImageLevel(loader.loadImage(endBlock.getFile()), endCoordinates[0], endCoordinates[1]);
   		}
	}
	
	private int[] createLevel(BlockListLoader blockList, LevelBlock block, int[] endCoordinates, int[] startCoordinates, int index, int levelLength, int blocksLoaded){
   		if((index+1 > levelLength) && blocksLoaded == levelLength) return endCoordinates;  //ends recursion at length of level (number of blocks requested)
   		
   		block = blockList.get(rand.nextInt(blockList.size()));  //gets random block
   		if(!block.isRendered()){
   			block.setRendered(true);  //prevents from same block rendering twice in same level (optional)
   			
   			startCoordinates = findStartCoordinates(blockList.loadImage(block.getFile()));  //gets start coordinates of block
   		
   			endCoordinates[1] -= startCoordinates[1]*unit;  //Aligns start and end points
   			endCoordinates[0] -= unit;
   		
   			//loads the block at end coordinates of previous block and retrieves new end coordinates
   			endCoordinates = loadImageLevel(blockList.loadImage(block.getFile()), endCoordinates[0], endCoordinates[1]);
   			blocksLoaded++;
   		}
   		
   		//recursive call at the new end coordinates
   		return createLevel(blockList, block, endCoordinates, startCoordinates, index+1, levelLength, blocksLoaded);
   	}
	
	private int[] loadImageLevel(BufferedImage image, int startX, int startY){
		/* Loops through each pixel in a buffered image
		 * and reads its RGB values.  It then adds a 
		 * game object according to the color at that spot on screen.
		 * Thanks to RealTutsGML on youtube for code for retrieving RGB values!
		 */
		
   		int w = image.getWidth();
   		int h = image.getHeight();
   		
   		for(int xx=0; xx < h; xx++){
   			for(int yy=0; yy < w; yy++){
   				int pixel = image.getRGB(xx, yy);
   				int red = (pixel >> 16) & 0xff;
   				int green = (pixel >> 8) & 0xff;
   				int blue = (pixel) & 0xff;
   				
   				//green (0,255,0)
   				if(red == 0 && green == 255 && blue == 0){
   					handler.addObject(new Ground(xx*unit + startX, yy*unit + startY, ObjectId.Ground));
   				}
   				
   				//blue (0,0,255)
   				if(red == 0 && green == 0 && blue == 255){
   					handler.addObject(new Player(xx*unit + startX, yy*unit + startY, handler, ObjectId.Player));
   				}
   				
   				//red (255,0,0)
   				if(red == 255 && green == 0 && blue == 0){
   					handler.addObject(new DamageBlock(xx*unit + startX, yy*unit + startY, handler, ObjectId.DamageBlock));
   				}
   				
   				//pink (255,0,255)
   				if(red == 255 && green == 0 && blue == 255){
   					handler.addObject(new End(xx*unit + startX, yy*unit + startY, ObjectId.End));
   				}
   				
   				//cyan (0,255,255)
   				if(red == 0 && green == 255 && blue == 255){
   					handler.addObject(new LevelBottom(xx*unit + startX, yy*unit + startY, ObjectId.LevelBottom));
   				}
   				
   				//RGB (0, 200, 255)
   				if(red == 0 && green == 200 && blue == 255){
   					handler.addObject(new BounceBlock(xx*unit + startX, yy*unit + startY, ObjectId.BounceBlock));
   				}
   				
   				//RGB (255, 50, 0)
   				//Moving enemy horizontally
   				if(red == 255 && green == 50 && blue == 0){
   					handler.addObject(new MovingEnemy(xx*unit + startX, yy*unit + startY, ObjectId.MovingEnemy));
   				}
   				
   				//RGB (100, 255, 100)
   				if(red == 100 && green == 255 && blue == 100){
   					handler.addObject(new MovingGround(xx*unit + startX, yy*unit + startY, ObjectId.Ground, 120, true));
   				}
   				
   				//RGB (150, 255, 150)
   				if(red == 150 && green == 255 && blue == 150){
   					handler.addObject(new MovingGround(xx*unit + startX, yy*unit + startY, ObjectId.Ground, 120, false));
   				}
   				
   				//white identifies end of block and returns new coordinates
   				if(red == 255 && green == 255 && blue == 255){
   					int[] coordinates = {(int) xx*unit + startX, (int) yy*unit + startY};
   					return coordinates;
   				}
   			}
   		}
   		//default coordinates of (0,0)
   		int[] coordinates = {(int) 0, (int) 0};
		return coordinates;
   	}

	private int[] findStartCoordinates(BufferedImage image){
		//loops through each pixel in a buffered image
		//and reads its RGB values.  It then returns the 
		//coordinates of a yellow pixel.  Every level block
		//only has one yellow pixel.
		
   		int w = image.getWidth();
   		int h = image.getHeight();
   		
   		for(int xx=0; xx < h; xx++){
   			for(int yy=0; yy < w; yy++){
   				int pixel = image.getRGB(xx, yy);
   				int red = (pixel >> 16) & 0xff;
   				int green = (pixel >> 8) & 0xff;
   				int blue = (pixel) & 0xff;
   				int coordinates[] = {(int) xx, (int)yy};
   				
   				//yellow identifies start of block
   				if(red == 255 && green == 255 && blue == 0){
   					return coordinates;
   				}
   			}
   		}
		//default coordinates of (0,0)
   		int[] coordinates = {(int) 0, (int) 0};
		return coordinates;
   	}
	
	
	public synchronized void start(){
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		
		init();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public static void main(String args[]){
		new Window(800, 600, TITLE, new Framework());
	}
}