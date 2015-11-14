
//Libraries



import java.awt.*;
import java.awt.event.*;

import javax.swing.*;




/**
 * @author bsefercik
 *
 */
public class Monopoly implements ActionListener {
	
	private JFrame mainFrame;

	private JButton throwDice,buyButton,buyChanceButton,sellButton,noButton,rollOnceButton;
	private JTextArea movements,currentMessage;
	private JLabel currentPlayer;
	private JLabel piName,piProps,piMoney,  alfaName,alfaProps,alfaMoney, betaName,betaProps,betaMoney, gamaName,gamaProps,gamaMoney;
	
	private final static int MAX_PROPERTIES = 20;
	private JRadioButton[] userProperties = new JRadioButton[MAX_PROPERTIES];
	private ButtonGroup propertiesButtonGroup;

	
	Dice dice = new Dice();
	
	//We can initialize dice values here.
	boolean manipulateDice = false;
	int dice1=4;
	int dice2=2;
	boolean squeezePlayOn = false;
	private int currentPlayerId = 0;
	private Player cP;
	private int initialMoney = 3200;


	Font font = new Font("Verdana", Font.PLAIN, 12);
	
	private SquaresInfo sqInfo = new SquaresInfo();
	
	private GUI sq = new GUI(15,15,sqInfo);
	
	
	//to change the id of current player.

    public Monopoly(){
    	Player pi = new Player(0,"Pi",initialMoney,0,sqInfo);
    	Player alfa = new Player(1,"Alfa",initialMoney,0,sqInfo);
    	Player beta = new Player(2,"Beta",initialMoney,0,sqInfo);
    	Player gama = new Player(3,"Gama",initialMoney,0,sqInfo);
    	Player players[] = {pi,alfa,beta,gama}; 
    	sqInfo.setPlayers(players);
    	//To set the initial positions.
    	
    	//Set locations in GUI.
		sq.locatePi(pi.getPosition());
		sq.locateAlfa(alfa.getPosition());
		sq.locateBeta(beta.getPosition());
		sq.locateGama(gama.getPosition());

		alfa.addProperty((SquareHouse)sqInfo.getSquare(1));
		alfa.addProperty((SquareHouse)sqInfo.getSquare(4));
		alfa.addProperty((SquareHouse)sqInfo.getSquare(6));
		alfa.addProperty((SquareHouse)sqInfo.getSquare(8));
		beta.addProperty((SquareHouse)sqInfo.getSquare(3));
		

		((SquareCommunityChest)sqInfo.getSquare(2)).createCommunityChestCards();
		((SquareChance)sqInfo.getSquare(7)).createChanceCards();

    	
    	//initSquares() has to be above setGUI();
    	
    	
    	setGUI();
    	
		movements.insert(pi.getPropertiesNames()+"\n", 0);
		movements.insert(alfa.getPropertiesNames()+"\n", 0);
		movements.insert(beta.getPropertiesNames()+"\n", 0);
		movements.insert(gama.getPropertiesNames()+"\n", 0);
		
    	throwDice.addActionListener(new ActionListener(){

        	 public void actionPerformed(ActionEvent evt){
        		throwDice.setEnabled(false);
        		if(!manipulateDice){
        			dice1 = dice.getFace();
        			dice2 = dice.getFace();
        			}else{
        				manipulateDice = false;
        			}
        		sq.setDiceOne(dice1);
         		sq.setDiceTwo(dice2);
         		
         		System.out.println(Integer.toString(dice1)+Integer.toString(dice2));
         		if(!squeezePlayOn){
         			cP.setPosition(cP.getPosition()+dice1+dice2);
         			sq.locatePawn(cP.getPosition(), cP.getName());}
         		String currEvent = cP.playerArrived(sqInfo.getSquare(cP.getPosition()));

         		
         		//currEvent = cP.playerArrived(sqInfo.getSquare(cP.getPosition()),sqInfo);

         		if(currEvent.equals("Buyable")){
	          		currentMessage.setText("Do you want to buy '"+sqInfo.getSquareName(cP.getPosition())+"' for $"+sqInfo.getSquarePrice(cP.getPosition())+"?");
	    		
	          		buyButton.setVisible(true);
	          		noButton.setVisible(true);

    				
    			}else if(currEvent.equals("BuyWithChanceCard")){
	          		currentMessage.setText("Do you want to buy '"+sqInfo.getSquareName(cP.getPosition())+"' for $"+sqInfo.getSquarePrice(cP.getPosition())+"?");
	          		buyButton.setVisible(true);
	          		noButton.setVisible(true);
	          		buyChanceButton.setVisible(true);
    				
    			}else if(currEvent.equals("HasToSell")){
	          		currentMessage.setText("You don't have enough money to pay rent. You have to sell one of your properties.");
	          		throwDice.setVisible(false);
	          		sellButton.setVisible(true);
	          		SquareHouse[] props = cP.getProperties();
	          		int propsMarginTop = 0;
	          		for(int i = 0; i<cP.getNumberOfProperties();i++){
	          			propsMarginTop = 136+18*i;
	          			userProperties[i].setVisible(true);
	        	    	userProperties[i].setLocation(703, propsMarginTop);
	          			userProperties[i].setText(props[i].getName());
	        	    	userProperties[i].setActionCommand(Integer.toString(props[i].getID()));
	          		}
	          		sellButton.setLocation(703, propsMarginTop+25);
    				
    			}else if(squeezePlayOn){
    				squeezePlayOn = false;
    				String resultT = ((SquareSqueezePlay) sqInfo.getSquare(cP.getPosition())).doAction(cP,players,dice1,dice2);
	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
    				resultT+="\nNow "+cP.getName()+"'s turn! Throw dice!";
	          		throwDice.setEnabled(true);
    				currentMessage.setText(resultT);
    			}else if(currEvent.equals("SqueezePlay")){
    				squeezePlayOn = true;
    				currentMessage.setText("It's 'Squeeze Play' square! Time to throw dice!");
	          		throwDice.setEnabled(true);
    			}else if(currEvent.equals("Continue")){	
    				currentMessage.setText("Throw dice!");
	          		throwDice.setEnabled(true);
	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
    			}else if(currEvent.equals("Broke")){	
    				currentMessage.setText("'"+cP.getName()+"' is broken and out of the game.");
	          		throwDice.setEnabled(true);
	          			currentPlayerId=(currentPlayerId+1)%4;
    			}else if(currEvent.equals("PaidRent")){
					currentMessage.setText(cP.getName()+" has paid $"+Integer.toString(((SquareHouse)sqInfo.getSquare(cP.getPosition())).getRent())+" for rent. Next player!");
	          		throwDice.setEnabled(true);
	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
				}else if(currEvent.equals("RollOnce")){
					currentMessage.setText("It's 'Roll Once' for "+cP.getName()+"!");
					rollOnceButton.setVisible(true);
					
				}else if(currEvent.equals("Chance3")){
					throwDice.setEnabled(true);
					currentMessage.setText("Chance Card: "+cP.getName()+" has paid $50 to other players.");
	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
					
				}else if(currEvent.equals("Chance1")){
					throwDice.setEnabled(true);
					currentMessage.setText("Chance Card: "+cP.getName()+" has been advanced to St. Charles Place! Paid $"+((SquareHouse)sqInfo.getSquare(6)).getRent()+" for rent.");
         			cP.setPosition(6);
         			cP.substractMoney(((SquareHouse)sqInfo.getSquare(6)).getRent());
         			((SquareHouse)sqInfo.getSquare(6)).getOwner().addMoney(((SquareHouse)sqInfo.getSquare(6)).getRent());
         			sq.locatePawn(cP.getPosition(), cP.getName());
	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
					
				}else if(currEvent.equals("Chance2_1")){
					throwDice.setEnabled(true);
					currentMessage.setText("Chance Card: '"+cP.getName()+"' has been advanced to Squeeze Play Square and won $200 by passing GO.");
         			cP.setPosition(15);
         			cP.addMoney(200);
         			sq.locatePawn(cP.getPosition(), cP.getName());
	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
				}else if(currEvent.equals("Chance2_2")){
					throwDice.setEnabled(true);
					currentMessage.setText("Chance Card: '"+cP.getName()+"' has been advanced to Squeeze Play Square.");
         			cP.setPosition(15);
         			sq.locatePawn(cP.getPosition(), cP.getName());
	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
				}else if(currEvent.equals("Chance4")){
					throwDice.setEnabled(true);
					currentMessage.setText("Chance Card: '"+cP.getName()+"' has been advanced to GO Square and won $200.");
         			cP.setPosition(0);
         			//cP.addMoney(200);
         			sq.locatePawn(cP.getPosition(), cP.getName());
	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
				}else if(currEvent.equals("Community1")){
					throwDice.setEnabled(true);
					currentMessage.setText("Community Chest: '"+cP.getName()+"' has been received Consultancy Fee and gained $25.");
         			cP.addMoney(25);
	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
				}else if(currEvent.equals("Community2")){
					throwDice.setEnabled(true);
					currentMessage.setText("Community Chest: '"+cP.getName()+"' has won Bargain Business Card: When you land on an unowned propert you want, buy it for only $100.");
	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
				}else if(currEvent.equals("Community3")){
					throwDice.setEnabled(true);
					currentMessage.setText("Community Chest: '"+cP.getName()+"' has won Renovation Success Card: Collect $50 extra rent from the next player who lands on any of your properties.");

	          		if(dice1!=dice2)
	          			currentPlayerId=(currentPlayerId+1)%4;
				}
         		
        		//currentPlayerId=(currentPlayerId+1)%4;
         		sq.repaint();
        		//plyr.repaint();

        	 }});
    	
    	buyButton.addActionListener(new ActionListener(){

	       	 public void actionPerformed(ActionEvent evt){
	       		String resultT = cP.buySquareHouse((SquareHouse)sqInfo.getSquare(cP.getPosition()));
	       		if(resultT.equals("NoMoney")){
	       			currentMessage.setText(cP.getName()+" doesn't have enough money. :( Next player!");
	       		}else if(resultT.equals("Other")){
	       			currentMessage.setText("Someone else owns this place.");
	       		}else if(resultT.equals("Bought")){
	       			currentMessage.setText(cP.getName()+" has bought "+sqInfo.getSquare(cP.getPosition()).getName()+". Next player!");
	       		}
	       		if(dice1!=dice2)
          			currentPlayerId=(currentPlayerId+1)%4;
          		buyButton.setVisible(false);
          		noButton.setVisible(false);
          		buyChanceButton.setVisible(false);
          		throwDice.setEnabled(true);
         		sq.repaint();
	       		 
	       	 }
       	 });
    	
    	buyChanceButton.addActionListener(new ActionListener(){

	       	 public void actionPerformed(ActionEvent evt){
	       		String resultT = cP.buyWithCard((SquareHouse)sqInfo.getSquare(cP.getPosition()));
	       		if(resultT.equals("NoMoney")){
	       			currentMessage.setText(cP.getName()+" doesn't have enough money. :( Next player!");
	       		}else if(resultT.equals("Other")){
	       			currentMessage.setText("Someone else owns this place.");
	       		}else if(resultT.equals("Bought")){
	       			currentMessage.setText(cP.getName()+" has bought "+sqInfo.getSquare(cP.getPosition()).getName()+". Next player!");
	       		}
	       		if(dice1!=dice2)
          			currentPlayerId=(currentPlayerId+1)%4;
          		buyButton.setVisible(false);
          		noButton.setVisible(false);
          		buyChanceButton.setVisible(false);
          		throwDice.setEnabled(true);
         		sq.repaint();
	       		 
	       	 }
       	 });
    	
    	sellButton.addActionListener(new ActionListener(){

	       	 public void actionPerformed(ActionEvent evt){
	          	sellButton.setVisible(false);

          		for(int i = 0; i<cP.getNumberOfProperties();i++)
          			userProperties[i].setVisible(false);
          		
	       		String resultT = cP.sellPropertyPanel((SquareHouse)sqInfo.getSquare(Integer.parseInt(propertiesButtonGroup.getSelection().getActionCommand())));
	       		if(resultT.equals("Sold") && cP.playerArrived(sqInfo.getSquare(cP.getPosition())).equals("HasToSell")){
		          		currentMessage.setText("Still, you don't have enough money to pay rent. You have to sell one more of your properties.");
		          		throwDice.setVisible(false);
		          		sellButton.setVisible(true);
		          		SquareHouse[] props = cP.getProperties();
		          		int propsMarginTop = 0;
		          		for(int i = 0; i<cP.getNumberOfProperties();i++){
		          			propsMarginTop = 136+18*i;
		          			userProperties[i].setVisible(true);
		        	    	userProperties[i].setLocation(703, propsMarginTop);
		          			userProperties[i].setText(props[i].getName());
		        	    	userProperties[i].setActionCommand(Integer.toString(props[i].getID()));
		          		}
		          		sellButton.setLocation(703, propsMarginTop+25);
	    				
	    		}else{
	    			currentMessage.setText("'"+cP.getName()+"' has sold '"+sqInfo.getSquareName(Integer.parseInt(propertiesButtonGroup.getSelection().getActionCommand()))+"' and paid the rent.");
		       		if(dice1!=dice2){
		          		currentPlayerId=(currentPlayerId+1)%4;}
		       		
	          		noButton.setVisible(false);
	          		throwDice.setEnabled(true);
	          		throwDice.setVisible(true);
	          		System.out.println("Selected Radio Button: " + propertiesButtonGroup.getSelection().getActionCommand());
	    		}
         		sq.repaint();
	       		 
	       	 }
       	 });
    	
    	rollOnceButton.addActionListener(new ActionListener(){

	       	 public void actionPerformed(ActionEvent evt){
	       		
         		rollOnceButton.setVisible(false);
         		throwDice.setEnabled(true);
	       		int dicer = dice.getFace();
    			int diceOnce = dice.getFace();
    			String resultT = cP.rollOncePlay(dicer, diceOnce);
    			currentMessage.setText(resultT+"\n Next player!");
        		sq.setDiceOne(dicer);
         		sq.setRollOnce(diceOnce);

         		if(dice1!=dice2)
          			currentPlayerId=(currentPlayerId+1)%4;
        		sq.repaint();
	       	 }
      	 });
    	while(true){
    		cP = players[currentPlayerId];
    		currentPlayer.setText(cP.getName());
    		
    		if(!cP.getIsPlaying())
      			currentPlayerId=(currentPlayerId+1)%4;
        	piMoney.setText("$"+Integer.toString(pi.getMoney()));
        	piProps.setText(pi.getPropertiesNames());
        	piProps.setToolTipText(pi.getPropertiesNames());
        	alfaMoney.setText("$"+Integer.toString(alfa.getMoney()));
    		alfaProps.setText(alfa.getPropertiesNames());
    		alfaProps.setToolTipText(alfa.getPropertiesNames());
        	betaMoney.setText("$"+Integer.toString(beta.getMoney()));
        	betaMoney.setToolTipText("$"+Integer.toString(beta.getMoney()));
    		betaProps.setText(beta.getPropertiesNames());
        	gamaMoney.setText("$"+Integer.toString(gama.getMoney()));
        	gamaMoney.setToolTipText("$"+Integer.toString(gama.getMoney()));
        	gamaProps.setText(gama.getPropertiesNames());
    		}
    		/*if(cP.getIsPlaying()){
    			if(cP.playerArrived(sqInfo.getSquare(cP.getPosition()),sqInfo).equals("Buyable")){
	          		currentMessage.setText("Do you want to buy '"+sqInfo.getSquareName(cP.getPosition())+"' for $"+sqInfo.getSquarePrice(cP.getPosition())+"?");
	    			buyButton.setText("Buy");
	          		buyButton.setVisible(true);
	          		noButton.setVisible(true);
	          		throwDice.setEnabled(false);
	          		sq.repaint();
    				
    			}else if(cP.playerArrived(sqInfo.getSquare(cP.getPosition()),sqInfo).equals("Continue")){
    				currentMessage.setText("Please throw dices!");
	          		throwDice.setEnabled(true);
	          		sq.repaint();
    			}
    		}
    		*/
    		
    		
    	
    	
    }
   
    public static void main(String[] args){
    		
    	new Monopoly();
    	
    }
   
    public void setGUI(){
    	
    	mainFrame = new JFrame("BECB Monopoly");
    	sq.setLayout(null);
    	Container contentPane = mainFrame.getContentPane();

    	sq.setBackground(Color.DARK_GRAY);
       
       // mainFrame.setContentPane(contentPane);
        throwDice = new JButton("Throw Dice");
        throwDice.setLocation(750, 245);
        throwDice.setSize(200, 40);
        throwDice.setToolTipText("You must throw the dices to make your next move, dude!");
        throwDice.addActionListener(this);
        
        

        
        // Buy or Sell BUtton
        buyButton = new JButton("Buy");
        buyButton.setLocation(703, 130);
        buyButton.setSize(133, 24);
        buyButton.setToolTipText("Do you want to buy?");
        buyButton.setVisible(false);
        buyButton.addActionListener(this);
        
        buyChanceButton = new JButton("Buy with Chance Card");
        buyChanceButton.setLocation(703, 166);
        buyChanceButton.setSize(278, 24);
        buyChanceButton.setVisible(false);
        buyChanceButton.addActionListener(this);
        
        sellButton = new JButton("Sell Selected Property");
        sellButton.setLocation(703, 166);
        sellButton.setSize(278, 24);
        sellButton.setVisible(true);
        sellButton.addActionListener(this);
        
        noButton = new JButton("No");
        noButton.setLocation(848, 130);
        noButton.setSize(133, 24);
        noButton.setToolTipText("No?");
        noButton.setVisible(false);
        noButton.addActionListener(this);
        

        rollOnceButton = new JButton("Roll Once!");
        rollOnceButton.setLocation(750, 290);
        rollOnceButton.setSize(200, 40);
        rollOnceButton.setVisible(false);
        rollOnceButton.addActionListener(this);
        

        currentPlayer = new JLabel("");
        currentPlayer.setFont(new Font("Verdana", Font.BOLD, 25));
        currentPlayer.setSize(278, 50);
        currentPlayer.setLocation(703, 0);
        currentPlayer.setHorizontalAlignment(0);
        currentPlayer.setForeground(new Color(255,155,155));

        piName = new JLabel("Pi");
        piName.setFont(new Font("Verdana", Font.BOLD, 16));
        piName.setSize(100, 20);
        piName.setLocation(703, 340);
        piName.setForeground(GUI.myWhite);
        piMoney = new JLabel("$");
        piMoney.setFont(new Font("Verdana", Font.BOLD, 16));
        piMoney.setSize(100, 20);
        piMoney.setLocation(803, 340);
        piMoney.setForeground(Color.gray);
        piProps = new JLabel("");
        piProps.setFont(new Font("Verdana", Font.BOLD, 13));
        piProps.setSize(278, 30);
        piProps.setLocation(703, 354);
        piProps.setForeground(GUI.myGreen);

        alfaName = new JLabel("Alfa");
        alfaName.setFont(new Font("Verdana", Font.BOLD, 16));
        alfaName.setSize(100, 20);
        alfaName.setLocation(703, 385);
        alfaName.setForeground(GUI.myWhite);
        alfaMoney = new JLabel("$");
        alfaMoney.setFont(new Font("Verdana", Font.BOLD, 16));
        alfaMoney.setSize(100, 20);
        alfaMoney.setLocation(803, 385);
        alfaMoney.setForeground(Color.gray);
        alfaProps = new JLabel("");
        alfaProps.setFont(new Font("Verdana", Font.BOLD, 13));
        alfaProps.setSize(278, 30);
        alfaProps.setLocation(703, 399);
        alfaProps.setForeground(GUI.myGreen);

        betaName = new JLabel("Beta");
        betaName.setFont(new Font("Verdana", Font.BOLD, 16));
        betaName.setSize(100, 20);
        betaName.setLocation(703, 430);
        betaName.setForeground(GUI.myWhite);
        betaMoney = new JLabel("$");
        betaMoney.setFont(new Font("Verdana", Font.BOLD, 16));
        betaMoney.setSize(100, 20);
        betaMoney.setLocation(803, 430);
        betaMoney.setForeground(Color.gray);
        betaProps = new JLabel("");
        betaProps.setFont(new Font("Verdana", Font.BOLD, 13));
        betaProps.setSize(278, 30);
        betaProps.setLocation(703, 444);
        betaProps.setForeground(GUI.myGreen);

        gamaName = new JLabel("Gama");
        gamaName.setFont(new Font("Verdana", Font.BOLD, 16));
        gamaName.setSize(100, 20);
        gamaName.setLocation(703, 475);
        gamaName.setForeground(GUI.myWhite);
        gamaMoney = new JLabel("$");
        gamaMoney.setFont(new Font("Verdana", Font.BOLD, 16));
        gamaMoney.setSize(100, 20);
        gamaMoney.setLocation(803, 475);
        gamaMoney.setForeground(Color.gray);
        gamaProps = new JLabel("");
        gamaProps.setFont(new Font("Verdana", Font.BOLD, 13));
        gamaProps.setSize(278, 30);
        gamaProps.setLocation(703, 484);
        gamaProps.setForeground(GUI.myGreen);
        
        currentMessage = new JTextArea();
        UIManager.put("TextArea.margin", new Insets(10,10,10,10));  
	    currentMessage.setLineWrap( true );
	    currentMessage.setWrapStyleWord( true );
	    currentMessage.setForeground(new Color(180,255,240));
	    currentMessage.setBackground(Color.DARK_GRAY);
	    currentMessage.setEditable(false);
        currentMessage.setFont(new Font("Verdana", Font.PLAIN, 16));
        currentMessage.setSize(278, 80);
        currentMessage.setLocation(703, 50);
 
	    movements= new JTextArea();
	    movements.setLocation(703, 550);
	    movements.setSize(278,80);
	    movements.setFont(font);
	    movements.setLineWrap( true );
	    movements.setWrapStyleWord( true );
	    movements.setForeground(new Color(120,120,120));
	    movements.setBackground(new Color(50,50,50));
	    movements.setEditable(false);
	    movements.setVisible(false);
    
    
        //Props Radios
	    propertiesButtonGroup = new ButtonGroup();
	    for(int i=0; i<MAX_PROPERTIES; i++){
	    	
	    	userProperties[i] = new JRadioButton("asdasd");
	    	userProperties[i].setActionCommand(Integer.toString(i));
	    	userProperties[i].setLocation(703, 0);
	    	userProperties[i].setSize(278, 18);
	    	userProperties[i].setVisible(false);
    		userProperties[i].setBackground(new Color(84,84,84));
	    	if(i%2==1)
	    		userProperties[i].setBackground(Color.DARK_GRAY);
	    	userProperties[i].setForeground(GUI.myWhite);
	    	propertiesButtonGroup.add(userProperties[i]);
	    	sq.add(userProperties[i]);

	    }
	    userProperties[0].setSelected(true);
        sellButton = new JButton("Sell Selected Property");
        sellButton.setLocation(703, 0);
        sellButton.setSize(278, 24);
        sellButton.setVisible(false);
        sellButton.addActionListener(this);
        

	    sq.add(piName);
	    sq.add(piMoney);
	    sq.add(piProps);
	    sq.add(alfaName);
	    sq.add(alfaMoney);
	    sq.add(alfaProps);
	    sq.add(betaName);
	    sq.add(betaMoney);
	    sq.add(betaProps);
	    sq.add(gamaName);
	    sq.add(gamaMoney);
	    sq.add(gamaProps);
	    
	    
	    sq.add(movements);
        sq.add(throwDice);
        sq.add(currentPlayer);
        sq.add(currentMessage);
        sq.add(buyButton);
        sq.add(buyChanceButton);
        sq.add(noButton);
        sq.add(sellButton);
        sq.add(rollOnceButton);
        
        

       contentPane.add(sq);

       //sq.validate();
       
        mainFrame.setResizable(false);
        mainFrame.setMinimumSize(new Dimension(1000,718));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);  
        
        
        
    }
    
    
    
    
    public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if(action.equals("No")) {
      		buyButton.setVisible(false);
      		noButton.setVisible(false);
      		buyChanceButton.setVisible(false);
    		throwDice.setEnabled(true);
    		currentMessage.setText("Throw dice!");

      		currentPlayerId = (currentPlayerId+1)%4;
      		sq.repaint();
			
		}
		
	}


   
}


	