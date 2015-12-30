package domain;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;



@SuppressWarnings("serial")
public class GUIPanel extends JPanel {
	
	int height = 715;
	
	
	Font font = new Font("Arial", Font.PLAIN,13);

	private BufferedImage backGround;
	private BufferedImage p;
	
	
	public int dieOne=1, dieTwo=1, dieSpeed=1;
	private BufferedImage dieOneImg;
	private BufferedImage dieTwoImg;
	private BufferedImage dieSpeedImg;
	private BufferedImage rollOnceDieImg;
	

	private BufferedImage cardImg;
	public int cardType=0;
	
	
	private ArrayList<Player> players = null;
	private Board board;
	
	private ArrayList<BufferedImage> playerIcons = new ArrayList();
	
	private int GUIPositions[][][]=new int[3][56][2];
	
	
	public GUIPanel(Board b, int dice[]) throws Exception{
		
		//Position 
		initSquarePositions();
		

		try {
			this.backGround = ImageIO.read(new File("img/board.png"));

		
		} catch (IOException ex) {}
	}
	
	
	
	public void paint(Graphics g) {

		super.paint(g);
		
	

		g.drawImage(backGround, 0, 0, height, height, null);
		if(players!=null){
			for(Player p : players){
				g.drawImage(playerIcons.get(p.id), GUIPositions[p.row][p.position][0], GUIPositions[p.row][p.position][1], 40, 40, null);
				}
		}
		
		try {
			
			if(cardType==0){
				this.cardImg = ImageIO.read(new File("img/cards.png"));
			}else if (cardType==1){
				this.cardImg = ImageIO.read(new File("img/chancecard.png"));
			}else if (cardType==2){
				this.cardImg = ImageIO.read(new File("img/comminitychest.png"));
			}
			
			if (MonopolyGame.specialConditions[0]){
					this.cardImg = ImageIO.read(new File("img/rollonce.png"));
				}
			
			this.dieOneImg = ImageIO.read(new File("img/die"+dieOne+".png"));
			this.dieTwoImg = ImageIO.read(new File("img/die"+dieTwo+".png"));
			this.dieSpeedImg = ImageIO.read(new File("img/die"+dieSpeed+".png"));
			if(dieSpeed==4){this.dieSpeedImg = ImageIO.read(new File("img/bus.png"));}else if(dieSpeed>4){
				this.dieSpeedImg = ImageIO.read(new File("img/monopoly.png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		g.drawImage(dieOneImg, 269, 269, 50, 50, null);
		g.drawImage(dieTwoImg, 332, 332, 50, 50, null);
		g.drawImage(dieSpeedImg, 395, 395, 50, 50, null);
		if(MonopolyGame.specialConditions[0])
			g.drawImage(cardImg, 725, 230, 290, 160, null);
		
		
		if (MonopolyGame.specialConditions[1]){
			try {
				this.rollOnceDieImg = ImageIO.read(new File("img/die"+GUI.rollOnceDie+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(rollOnceDieImg, 880, 294, 80, 80, null);
		}
		
		
		if (MonopolyGame.specialConditions[0]){
		    g.setFont(new Font("Arial", Font.PLAIN,80));
	    	g.setColor(Color.black);
	    	if (MonopolyGame.specialConditions[1]){g.drawString(Integer.toString(GUI.rollOnceCard), 800, 362);}
	    	else{g.drawString(Integer.toString(GUI.rollOnceCard), 842, 362);}
		}
		
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

public void setDice(int i, int j, int k){
	dieOne = i;
	dieTwo = j;
	dieSpeed = k;
}


public void initGUIPanel(Board b){


	board = b;
	players = board.getPlayers();
	
	try {
		this.backGround = ImageIO.read(new File("img/board.png"));
		
		for(int i=0;i<12;i++){
			this.p = ImageIO.read(new File("img/p"+Integer.toString(i+1)+".png"));
			playerIcons.add(this.p);
		}


	
	} catch (IOException ex) {}
}
}