import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class GUI extends JPanel {
	private int marginX;
	private int marginY;
	private final int sqWidth = 109;
	private final int sqHeight = 109;
	private int diceOne, diceTwo;
	
	Font font = new Font("Arial", Font.PLAIN,(int)(sqWidth*0.11));

	private BufferedImage backG;
	
	private BufferedImage chanceCardBg;
	private String chanceCardText = "";
	private BufferedImage communityChestBg;
	private String communityChestText = "";
	private BufferedImage rollOnceBg;
	private String rollOnceText = "";
	
	
	private BufferedImage diceOneImg;
	private BufferedImage diceTwoImg;
	
	//Players
	private BufferedImage playerAlfa;
	private BufferedImage playerBeta;
	private BufferedImage playerGama;
	private BufferedImage playerPi;

	private int alfaPos;
	private int betaPos;
	private int gamaPos;
	private int piPos;
	
	//Colors
	public static Color myClaret = new Color(100,5,5);
	public static Color myWhite = new Color(255,255,255);
	public static Color myGreen = new Color(9,135,51);
	public static Color myOrange = new Color(170,68,0);
	public static Color myPink = new Color(238,55,119);
	public static Color myBlue = new Color(40,78,161);
	
	
	private SquaresInfo sqInfo;
	
	
	public GUI(int x, int y,SquaresInfo sqInfo){
		marginX = x;
		marginY = y;
		this.sqInfo = sqInfo;
	}

	
	
	
	public void paint(Graphics g) {

		super.paint(g);
		//Board BG
			g.setColor(myClaret);
	    	g.fillRect(0,0,getHeight(),getHeight());
	    	try {this.backG = ImageIO.read(new File("img/bg.png"));} catch (IOException ex) {}
		    g.drawImage(backG, getWidth()-104,getHeight()-43, 100, 39, null);
		    
		    
	    //Corners
	    //Bottom-Right(GO)
			sqInfo.setPositions(0,marginX+(5*(sqWidth+1)),marginY+(5*(sqHeight+1)));
			putSquare(sqInfo.getPositionX(0),sqInfo.getPositionY(0),sqWidth,sqHeight,"right",sqInfo.getSquareColor(0),sqInfo.getSquareName(0),
					makePriceTag(sqInfo.getSquarePrice(0)),g);
	    	g.setColor(myGreen);
	    	g.fillRect(marginX+(5*(sqWidth+1)),marginY+(5*(sqHeight+1)),sqWidth,sqHeight/5);
	    	g.fillRect(marginX+(5*(sqWidth+1)),marginY+(5*(sqHeight+1))+(sqHeight-sqHeight/5),sqWidth,sqHeight/5);
	    	g.fillRect(marginX+(5*(sqWidth+1))+(sqWidth-sqWidth/5),marginY+(5*(sqHeight+1)),sqWidth/5,sqHeight);
	    	g.setColor(Color.black);
	    	g.setFont(g.getFont().deriveFont(44.0f));
		    g.drawString("GO", marginX+(5*(sqWidth))+sqWidth/5+4, marginY+(5*(sqHeight+1))+sqHeight/2+4);
		    g.drawString("←", marginX+(5*(sqWidth))+sqWidth/5+17, marginY+(5*(sqHeight+1))+sqHeight/2+30);
	    	
	    	
		//Left boxes
		for(int i=0;i<5;i++){
			sqInfo.setPositions(10-i,marginX,marginY+(i*(sqHeight+1)));
			putSquare(sqInfo.getPositionX(10-i),sqInfo.getPositionY(10-i),sqWidth,sqHeight,"left",sqInfo.getSquareColor(10-i),sqInfo.getSquareName(10-i),
					makePriceTag(sqInfo.getSquarePrice(10-i)),g);
		}
		//Right boxes
		for(int i=1;i<5;i++){
			sqInfo.setPositions(15+i,marginX+(5*(sqWidth+1)),marginY+(i*(sqHeight+1)));
			putSquare(sqInfo.getPositionX(15+i),sqInfo.getPositionY(15+i),sqWidth,sqHeight,"right",sqInfo.getSquareColor(15+i),sqInfo.getSquareName(15+i),
					makePriceTag(sqInfo.getSquarePrice(15+i)),g);
		}
		//Top boxes
		for(int i=1;i<6;i++){
			sqInfo.setPositions(i+10,marginX+(i*(sqWidth+1)),marginY);
			putSquare(sqInfo.getPositionX(i+10),sqInfo.getPositionY(i+10),sqWidth,sqHeight,"top",sqInfo.getSquareColor(i+10),sqInfo.getSquareName(i+10),
					makePriceTag(sqInfo.getSquarePrice(i+10)),g);
		}
			//Bottom boxes
		for(int i=0;i<5;i++){
			sqInfo.setPositions(5-i,marginX+(i*(sqWidth+1)),marginY+(5*(sqHeight+1)));
			putSquare(sqInfo.getPositionX(5-i),sqInfo.getPositionY(5-i),sqWidth,sqHeight,"bottom",sqInfo.getSquareColor(5-i),sqInfo.getSquareName(5-i),
					makePriceTag(sqInfo.getSquarePrice(5-i)),g);
		}
    
	    
	    
	    g.drawImage(diceOneImg, 435, 145, 50, 50, null);
	    g.drawImage(diceTwoImg, 495, 145, 50, 50, null);
	    
	    
	    //Players
	    try {                
			  this.chanceCardBg = ImageIO.read(new File("img/chancecard.png"));
			  this.communityChestBg = ImageIO.read(new File("img/communitychest.png"));
			  
			  this.playerAlfa = ImageIO.read(new File("img/player_alfa.png"));
			  this.playerBeta = ImageIO.read(new File("img/player_beta.png"));
			  this.playerGama = ImageIO.read(new File("img/player_gama.png"));
			  this.playerPi = ImageIO.read(new File("img/player_pi.png"));
	       } catch (IOException ex) {}
	    
	    
	    
	    g.drawImage(chanceCardBg, sqWidth+31, 3*(sqHeight+1)+68, 250, 150, null);
	    g.drawImage(communityChestBg, sqWidth+31, 2*sqHeight+13, 250, 150, null);
	    g.setFont(g.getFont().deriveFont(16.0f));
    	g.setColor(Color.black);
    	drawStringLine(g, chanceCardText, sqWidth+85, 3*(sqHeight+1)+80);
    	drawStringLine(g, communityChestText, sqWidth+48, 2*(sqHeight+1)+46);
    	
    	if(diceTwo==0){
		    g.drawImage(rollOnceBg, sqWidth+31, sqHeight+31, 250, 60, null);
		    g.setFont(new Font("Times New Roman", Font.PLAIN,44));
	    	g.setColor(Color.black);
	    	drawStringLine(g, rollOnceText, sqWidth+220, sqHeight+23);}
		    
		    
		    g.drawImage(playerAlfa, sqInfo.getPositionX(alfaPos)+(sqWidth-48)/2, sqInfo.getPositionY(alfaPos)+(sqHeight-48)/2, 48, 48, null);
		    g.drawImage(playerBeta, sqInfo.getPositionX(betaPos)+(sqWidth-48)/2, sqInfo.getPositionY(betaPos)+(sqHeight-48)/2, 48, 48, null);
		    g.drawImage(playerGama, sqInfo.getPositionX(gamaPos)+(sqWidth-48)/2, sqInfo.getPositionY(gamaPos)+(sqHeight-48)/2, 48, 48, null);
		    g.drawImage(playerPi, sqInfo.getPositionX(piPos)+(sqWidth-48)/2, sqInfo.getPositionY(piPos)+(sqHeight-48)/2, 48, 48, null);

		}	
	
	
	public void putSquare(int mX,int mY,int w,int h,String pos,Color c,String name,String price,Graphics g){
		int fW;
		g.setFont(font);
		g.setColor(Color.white);
	    g.fillRect(mX,mY,w,h);
	    g.setColor(c);
	    if(pos.equals("left")){
	    	g.fillRect(mX+(w-w/5),mY,w/5,h);
	    	//if(mY==marginY)g.fillRect(marginX,marginY+(sqHeight-sqHeight/5),sqWidth,sqHeight/5);
	    	g.setColor(Color.black);
	    	fW = g.getFontMetrics().stringWidth(name);
		    g.drawString(name, mX +(4*w/5-fW)/2, mY+2*h/5);
	    	fW = g.getFontMetrics().stringWidth(price);
		    g.drawString(price, mX +(4*w/5-fW)/2, mY+3*h/4);
	    } else if(pos.equals("right")){
	    	g.fillRect(mX,mY,w/5,h);
	    	if(mY!=(marginY+(5*(sqHeight+1)))){
		    	g.setColor(Color.black);
		    	fW = g.getFontMetrics().stringWidth(name);
			    g.drawString(name, mX +(6*w/5-fW)/2, mY+2*h/5);
		    	fW = g.getFontMetrics().stringWidth(price);
			    g.drawString(price, mX +(6*w/5-fW)/2, mY+3*h/4);
	    	}
	    } else if(pos.equals("top")){
	    	g.fillRect(mX,mY+(h-h/5),w,h/5);
	    	g.setColor(Color.black);
	    	fW = g.getFontMetrics().stringWidth(name);
		    g.drawString(name, mX + (w-fW)/2, mY+8*h/25);
	    	fW = g.getFontMetrics().stringWidth(price);
		    g.drawString(price, mX + (w-fW)/2, mY+12*h/20);
	    } else if(pos.equals("bottom")){
	    	g.fillRect(mX,mY,w,h/5);
	    	g.setColor(Color.black);
	    	fW = g.getFontMetrics().stringWidth(name);
		    g.drawString(name, mX + (w-fW)/2, mY+13*h/25);
	    	fW = g.getFontMetrics().stringWidth(price);
		    g.drawString(price, mX + (w-fW)/2, mY+16*h/20);
	    }
	    
	    
	    // provides optimum gap for printing
	}
	
	
	
  
  public void setMargin(int x, int y){
	  marginX = x;
	  marginY = y;
  }
  
  //Set player locations
  public void locateAlfa(int p){
	  alfaPos = p;
  }
  public void locateBeta(int p){
	  betaPos = p;
  }
  public void locateGama(int p){
	  gamaPos = p;
  }
  public void locatePi(int p){
	  piPos = p;
  }
  
  public void locatePawn(int p, String d){
	  if(d.equals("Pi")) locatePi(p);
	  else if(d.equals("Alfa")) locateAlfa(p);
	  else if(d.equals("Beta")) locateBeta(p);
	  else if(d.equals("Gama")) locateGama(p);
	  
	  
  }
  
  //public static int[][] getSquarePositions(){return squarePositions;}
  
  private void drawStringLine(Graphics g, String text, int x, int y) {
      for (String line : text.split("\n"))
          g.drawString(line, x, y += g.getFontMetrics().getHeight());
  }
  public void setChanceCardText(String t){
	  this.chanceCardText = t;
  }
  public void setCommunityChestText(String t){
	  this.communityChestText = t;
  }
  
  //Dice setters
  public void setDiceOne(int x){
	  this.diceOne = x;
	  try {                
		  this.diceOneImg = ImageIO.read(new File("img/dice"+Integer.toString(x)+".png"));
   } catch (IOException ex) {
        // handle exception...
       }
	 
  }
  
  public void setDiceTwo(int x){
	  this.diceTwo = x;
	  try {                
		  this.diceTwoImg = ImageIO.read(new File("img/dice"+Integer.toString(x)+".png"));
       } catch (IOException ex) {
            // handle exception...
       }
  }  
  
  public void setRollOnce(int x){
	  this.rollOnceText = Integer.toString(x);
	  this.diceTwo = 0;
	  try {                
		  this.rollOnceBg = ImageIO.read(new File("img/rollonce.png"));
		  this.diceTwoImg = ImageIO.read(new File("img/empty.png"));
       } catch (IOException ex) {
            // handle exception...
       }
  }
  
  
  
  public String makePriceTag(int a){
	  if(a>0)
		  return "$"+Integer.toString(a);
	  else
		  return "";
  }
}