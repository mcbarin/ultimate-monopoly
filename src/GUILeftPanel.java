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
	
	private int GUIPositions[][][]=new int[3][56][2];
	
	
	public GUILeftPanel(Board board, int dice[]){
		
		//Position 
		initSquarePositions();
		
		
		
		
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
			g.drawImage(playerIcons.get(i), GUIPositions[1][i][0], GUIPositions[1][i][1], 40, 40, null);}
	}
	
	private void initSquarePositions(){
		GUIPositions[0][0][0] = 480;
		GUIPositions[0][0][1] = 480;
		GUIPositions[0][1][0] = 416;
		GUIPositions[0][1][1] = 480;
		for(int i=2;i<24;i++){
			if(i<=5){
				GUIPositions[0][i][0] = 416-(i-1)*40;
				GUIPositions[0][i][1] = 480;
			}else if(i==6){
				GUIPositions[0][i][0] = 196;
				GUIPositions[0][i][1] = 480;
			}else if(i==7){
				GUIPositions[0][i][0] = 196;
				GUIPositions[0][i][1] = 416;
			}else if(i<=11){
				GUIPositions[0][i][0] = 196;
				GUIPositions[0][i][1] = 416-(i-7)*40;
			}else if(i<=13){
				GUIPositions[0][12][0] = 196;
				GUIPositions[0][12][1] = 198;
				GUIPositions[0][13][0] = 258;
				GUIPositions[0][13][1] = 198;
			}else if(i<=17){
				GUIPositions[0][i][0] = 258+(i-13)*40;
				GUIPositions[0][i][1] = 198;
			}else if(i<=19){
				GUIPositions[0][18][0] = 480;
				GUIPositions[0][18][1] = 198;
				GUIPositions[0][19][0] = 480;
				GUIPositions[0][19][1] = 258;
			}else if(i<=23){
				GUIPositions[0][i][0] = 480;
				GUIPositions[0][i][1] = 258+(i-19)*40;
			}
			
			
			

			GUIPositions[1][0][0] = 562;
			GUIPositions[1][0][1] = 562;
			GUIPositions[1][1][0] = 500;
			GUIPositions[1][1][1] = 562;
			for(i=2;i<24;i++){
				if(i<=5){
					GUIPositions[0][i][0] = 416-(i-1)*40;
					GUIPositions[0][i][1] = 480;
				}else if(i==6){
					GUIPositions[0][i][0] = 196;
					GUIPositions[0][i][1] = 480;
				}else if(i==7){
					GUIPositions[0][i][0] = 196;
					GUIPositions[0][i][1] = 416;
				}else if(i<=11){
					GUIPositions[0][i][0] = 196;
					GUIPositions[0][i][1] = 416-(i-7)*40;
				}else if(i<=13){
					GUIPositions[0][12][0] = 196;
					GUIPositions[0][12][1] = 198;
					GUIPositions[0][13][0] = 258;
					GUIPositions[0][13][1] = 198;
				}else if(i<=17){
					GUIPositions[0][i][0] = 258+(i-13)*40;
					GUIPositions[0][i][1] = 198;
				}else if(i<=19){
					GUIPositions[0][18][0] = 480;
					GUIPositions[0][18][1] = 198;
					GUIPositions[0][19][0] = 480;
					GUIPositions[0][19][1] = 258;
				}else if(i<=23){
					GUIPositions[0][i][0] = 480;
					GUIPositions[0][i][1] = 258+(i-19)*40;
				}
			
		}
	}


}
