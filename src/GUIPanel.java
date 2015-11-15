import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;



public class GUIPanel extends JPanel {
	
	int height = 715;
	
	
	Font font = new Font("Arial", Font.PLAIN,13);

	private BufferedImage backGround;
	private BufferedImage p;
	
	private BufferedImage diceOneImg;
	private BufferedImage dieTwoImg;
	private BufferedImage dieSpeedImg;
	
	private ArrayList<BufferedImage> playerIcons = new ArrayList();
	
	private int GUIPositions[][][]=new int[3][56][2];
	
	
	public GUIPanel(Board board, int dice[]) throws Exception{
		
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
			g.drawImage(playerIcons.get(i), GUIPositions[2][44][0], GUIPositions[2][44][1], 40, 40, null);}
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
			
		}	
		

		GUIPositions[1][0][0] = 562;
		GUIPositions[1][0][1] = 562;
		GUIPositions[1][1][0] = 500;
		GUIPositions[1][1][1] = 562;
		for(int i=2;i<40;i++){
			if(i<=9){
				GUIPositions[1][i][0] = 500-(i-1)*40;
				GUIPositions[1][i][1] = 562;
			}else if(i<=11){
				GUIPositions[1][10][0] = 112;
				GUIPositions[1][10][1] = 562;
				GUIPositions[1][11][0] = 112;
				GUIPositions[1][11][1] = 500;
			}else if(i<=19){
				GUIPositions[1][i][0] = 112;
				GUIPositions[1][i][1] = 500-(i-11)*40;
			}else if(i<=21){
				GUIPositions[1][20][0] = 112;
				GUIPositions[1][20][1] = 112;
				GUIPositions[1][21][0] = 176;
				GUIPositions[1][21][1] = 112;
			}else if(i<=29){
				GUIPositions[1][i][0] = 176+(i-21)*40;
				GUIPositions[1][i][1] = 112;
			}else if(i<=31){
				GUIPositions[1][30][0] = 562;
				GUIPositions[1][30][1] = 112;
				GUIPositions[1][31][0] = 562;
				GUIPositions[1][31][1] = 175;
			}else if(i<=39){
				GUIPositions[1][i][0] = 562;
				GUIPositions[1][i][1] = 175+(i-31)*40;
			}
		
	}
		

		GUIPositions[2][0][0] = 648;
		GUIPositions[2][0][1] = 648;
		GUIPositions[2][1][0] = 586;
		GUIPositions[2][1][1] = 648;
		for(int i=2;i<56;i++){
			if(i<=13){
				GUIPositions[2][i][0] = 586-(i-1)*41;
				GUIPositions[2][i][1] = 648;
			}else if(i<=15){
				GUIPositions[2][14][0] = 26;
				GUIPositions[2][14][1] = 648;
				GUIPositions[2][15][0] = 26;
				GUIPositions[2][15][1] = 586;
			}else if(i<=27){
				GUIPositions[2][i][0] = 26;
				GUIPositions[2][i][1] = 586-(i-15)*41;
			}else if(i<=29){
				GUIPositions[2][28][0] = 26;
				GUIPositions[2][28][1] = 26;
				GUIPositions[2][29][0] = 89;
				GUIPositions[2][29][1] = 26;
			}else if(i<=41){
				GUIPositions[2][i][0] = 89+(i-29)*41;
				GUIPositions[2][i][1] = 26;
			}else if(i<=43){
				GUIPositions[2][42][0] = 648;
				GUIPositions[2][42][1] = 26;
				GUIPositions[2][43][0] = 648;
				GUIPositions[2][43][1] = 90;
			}else if(i<=50){
				GUIPositions[2][i][0] = 648;
				GUIPositions[2][i][1] = 88+(i-43)*42;
			}else if(i<=55){
				GUIPositions[2][i][0] = 648;
				GUIPositions[2][i][1] = 382+(i-50)*41;
			}
		
		}
	}

	



public void movePlayerTimer() throws Exception{
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //...Perform a task...

            	GUIPositions[2][44][0]=0;
            	GUIPositions[2][44][1]=0;
            }
        };
        Timer timer = new Timer(1000 ,taskPerformer);
        timer.setRepeats(false);
        timer.start();

        Thread.sleep(500);
    }


}