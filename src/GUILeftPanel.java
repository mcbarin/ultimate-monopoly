import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
public class GUILeftPanel extends JPanel {
	

	Font font = new Font("Arial", Font.PLAIN,13);
	
	private BufferedImage backGround;
	
	private BufferedImage diceOneImg;
	private BufferedImage dieTwoImg;
	private BufferedImage dieSpeedImg;
	
	private ArrayList<BufferedImage> playerIcons;
	
	public GUILeftPanel(ArrayList<Player> player, int dice[]){
		
		
	}
	
	public void paint(Graphics g) {

		super.paint(g);
	}

}
