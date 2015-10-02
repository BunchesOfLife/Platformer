package src;

import java.io.File;

public class LevelBlock {
	
	File file;
	public boolean rendered = false;
	
	public LevelBlock(File file, boolean rendered) {
		this.file = file;
		this.rendered = rendered;
	}
	
	public File getFile() {
		return file;
	}

	public void setPath(File file) {
		this.file = file;
	}

	public boolean isRendered() {
		return rendered;
	}
	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}
}
