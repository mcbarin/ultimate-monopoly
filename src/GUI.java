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
	private GUILeftPanel leftPanel;
	
	public GUI(Board board){
		
		leftPanel = new GUILeftPanel(board,dice);
		
		mainFrame = new JFrame("BECB Monopoly");
    	Container contentPane = mainFrame.getContentPane();
		leftPanel.setLayout(null);
		contentPane.add(leftPanel);
		

	       
        //mainFrame.setResizable(false);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);  
        
		
		while(true){
			leftPanel.repaint();
		}
    	
    	
	}
	
	

}
