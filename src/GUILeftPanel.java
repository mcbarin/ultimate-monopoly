import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
public class GUILeftPanel extends JPanel {
	
	int height = 715;
	
	
	Font font = new Font("Arial", Font.PLAIN,13);

	private BufferedImage backGround;
	private BufferedImage p;
	
	private BufferedImage diceOneImg;
	private BufferedImage dieTwoImg;
	private BufferedImage dieSpeedImg;
	
	private ArrayList<BufferedImage> playerIcons = new ArrayList();
	
	public GUILeftPanel(ArrayList<Player> player, int dice[]){
		try {
			this.backGround = ImageIO.read(new File("img/board.png"));
			
			for(int i=0;i<12;i++){
				this.p = ImageIO.read(new File("img/p"+Integer.toString(i+1)+".png"));
				playerIcons.add(this.p);
			}
		
		} catch (IOException ex) {}
	}
	
	public void paint(Graphics g) {

		super.paint(g);
		
	

		g.drawImage(backGround, 0, 0, height, height, null);
		for(int i=0;i<12;i++){
			g.drawImage(playerIcons.get(i), 40*i, 40*i, 40, 40, null);}
	}


}
