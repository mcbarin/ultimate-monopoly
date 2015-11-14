import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.util.*;
public class GUI {
	
	private int marginX;
	private int marginY;
	private int dieOne, dieTwo, dieSpeed;
	
	
	//Players
	private ArrayList<Player> player;
	
	private int dice[] = {dieOne, dieTwo, dieSpeed};
	
	
	private JFrame mainFrame;
	private GUILeftPanel leftPanel = new GUILeftPanel(player,dice);
	
	public GUI(Board board){
		
		
		
		JFrame mainFrame = new JFrame("BECB Monopoly");
    	
    	
	}
	
	

}
