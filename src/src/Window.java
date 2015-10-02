package src;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	public Window(int w, int h, String title, Framework framework){
		
		framework.setPreferredSize(new Dimension(w, h));
		framework.setMaximumSize(new Dimension(w, h));
		framework.setMinimumSize(new Dimension(w, h));
		
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(framework);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
		
    	framework.start();
	}
}
