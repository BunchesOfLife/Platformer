package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class BlockListLoader {
	
	private BufferedImage image = null;
	private LinkedList<LevelBlock> blockList = new LinkedList<LevelBlock>();
	
	public BufferedImage loadImage(File file){
		try {
			image = ImageIO.read(getClass().getResource("/" + file.getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	File currentDirectory = new File(".");
	File folder = new File(currentDirectory.getAbsolutePath() + "../../res");
	
	public void loadBlocks(){
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        blockList.add(new LevelBlock(file, false));
		    }
		}
	}
	public int size(){
		return blockList.size();
	}
	
	public LevelBlock get(int index){
		return blockList.get(index);
	}
	
	public void addLevelBlock(LevelBlock block){
		blockList.add(block);
	}
}
