import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
public class MonopolyGame {
	
	private GUI gui;
	private Board board;
	
	public static int dieOne, dieTwo, dieSpeed;
	
	private ArrayList<Player> players;
	private Player cP;
	
	public static boolean manipulate=true; 
	
	private JButton buttons[] = new JButton[26];
	public static boolean specialConditions[] = new boolean[26];
	//	0-rollOnce	1-rollOnce Die
	private int initialNumberofPlayers;
	
	private Dice die = new Dice();
	private  int totalDice;
	//buttons = rollDice,	buy,	buyChance,	sell,	no,		rollOnce,	pullChance,	pullCommunity,mortgage,unMortgage,
	//			yes,		start,	load,		save, 	dieOne,	dieTwo, 	dieTotal,	build
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		new MonopolyGame();
		
		

		
	}
	
	public MonopolyGame() throws Exception{
		String[][] names = {{"SQUEEZE PLAY","THE EMBARCADERO","FISHERMAN'S WHARF","TELEPHONE COMPANY","COMMUNITY CHEST","BEACON STREET","BONUS","BOYLSTON STREET","NEWBURY STREET","PENNSYLVANIA RAILROAD","FIFTH AVENUE","MADISON AVENUE","STOCK EXCHANGE","WALL STREET","TAX REFUND","GAS COMPANY","CHANCE","FLORIDA AVENUE","HOLLAND TUNNEL","MIAMI AVENUE","BISCAYNE AVENUE","SHORT LINE","REVERSE DIRECTION","LOMBARD STREET","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
				{"GO","MEDITERRANEAN AVENUE","COMMUNITY CHEST","BALTIC AVENUE","INCOME TAX","READING RAILROAD","ORIENTAL AVENUE","CHANCE","VERMONT AVENUE","CONNECTICUT AVENUE","IN JAIL","ST. CHARLES PLACE","ELECTRIC COMPANY","STATES AVENUE","VIRGINIA AVENUE","PENNSYLVANIA RAILROAD","ST. JAMES PLACE","COMMUNITY CHEST","TENNESSE AVENUE","NEW YORK AVENUE","FREE PARKING","KENTUCKY AVENUE","CHANCE","INDIANA AVENUE","ILLINOIS AVENUE","B&O RAILROAD","ATLANTIC AVENUE","VENTNOR AVENUE","WATER WORKS","MARVIN GARDENS","ROLL ONCE","PACIFIC AVENUE","NORTH CAROLINA AVENUE","COMMUNITY CHEST","PENNSYLVANIA AVENUE","SHORT LINE","CHANCE","PARK PLACE","LUXURY TAX","BOARDWALK","","","","","","","","","","","","","","","",""},
				{"SUBWAY","LAKE STREET","COMMUNITY CHEST", "NICOLLET AVENUE","HENNEPIN AVENUE", "BUS TICKET","CHECKER CAB CO.", "READING RAILROAD","ESPLANADE AVENUE","CANAL STREET","CHANCE","CABLE COMPANY","MAGAZINE STREET","BOURBON STREET","HOLLAND TUNNEL","AUCTION","KATY FREEWAY","WESTHEIMER ROAD","INTERNET SERVICE PROVIDER","KIRBY DRIVE","CULLEN BOULEVARD","CHANCE","BLACK & WHITE CAB CO.","DEKALB AVENUE","COMMUNITY CHEST","ANDREW YOUNG INTL BOULEVARD","DECATUR STREET","PEACHTREE STREET","PAY DAY","RANDOLPH STREET","CHANCE","LAKE SHORE DRIVE","WACKER DRIVE","MICHIGAN AVENUE","YELLOW CAB CO.","B&O RAILROAD","COMMUNITY CHEST","SOUTH TEMPLE","WEST TAMPLE","TRASH COLLECTOR","NORTH TEMPLE","TEMPLE SQUARE","GO TO JAIL","SOUTH STREET","BROAD STREET","WALNUT STREET","COMMUNITY CHEST","MARKET STREET","BUS TICKET","SEWAGE SYSTEM","UTE CAB CO.","BIRTHDAY GIFT","MULHOLLAND DRIVE","VENTURA BOULEVARD","CHANCE","RODEO DRIVE"}};

		int[][] colors = {{64,0,0,64,64,1,64,1,1,64,2,2,64,2,64,64,64,3,64,3,3,64,64,0,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64},
				{64,4,64,4,64,64,5,64,5,5,64,6,64,6,6,64,7,64,7,7,64,8,64,8,8,64,9,9,64,9,64,10,10,64,10,64,64,11,64,11,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64},
				{64,12,64,12,12,64,64,64,13,13,64,64,13,13,64,64,14,14,64,14,14,64,64,15,64,15,15,15,64,16,64,16,16,16,64,64,64,17,17,64,17,17,64,18,18,18,64,18,64,64,64,64,19,19,64,19}};
		
		for(int i=0;i<3;i++){
			for(int j=0;j<56;j++){
				System.out.println(colors[i][j]+" - "+names[i][j]);
			}
		}
		for(int i=0; i<26; i++){
			buttons[i] = new JButton();
			specialConditions[i] = false;}
		
		
		if(Board.gameStatus==-1){
			JTextField nump = new JTextField();
			gui = new GUI(buttons, nump); 
			Board.gameStatus = 0;
			}
		

		//RollDice
		buttons[0].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  gui.removeSpecialConditions();
		    	  
		    	  if(!manipulate){
			    	  dieOne = die.getFace();
			    	  dieTwo = die.getFace();
			    	  dieSpeed = die.getFace();}
		    	  else{
		    		 manipulate = false;
		    		 dieOne = 2;
			    	 dieTwo = 2;
			    	 dieSpeed = 2;
		    	  }
		    	  totalDice = dieOne+dieTwo;
		  			if(dieSpeed<4) totalDice+=dieSpeed;	
		    	  gui.setDice(dieOne, dieTwo, dieSpeed);
		    	  
		    	  if(dieSpeed == 4){
		    		  gui.setGUI("You have got 'Bus'! Choose one of the numbers in order to move:", "00000000000000111", buttons);
		    		  buttons[14].setText(Integer.toString(dieOne));
		    		  buttons[15].setText(Integer.toString(dieTwo));
		    		  buttons[16].setText(Integer.toString((dieOne+dieTwo)));
		    	  }else if(dieSpeed>4){
		    		  cP.setPosition(dieOne+dieTwo);		
		    		  cP.monopolyGuyFlag = true;
		    	  }else{
		    		  cP.setPosition(dieOne+dieTwo+dieSpeed);
			    	  play();
		    	  }
		    	  
		    	  
		      }
		});

		//BuyButton
		buttons[1].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position).buy(cP, totalDice);
		    	  if(result[0].equals("1")){
		    		  board.nextPlayer();
		    		  gui.setGUI(result[1]+" Next player!","1",buttons);
		    	  }else if(result[0].equals("9")){
		    		  gui.setGUI(result[1],"1",buttons);
		    	  }
		    	 
		    	  
		      }
		});


		//RllOnce Button
		buttons[5].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  GUI.rollOnceDie = die.getFace();
		    	  String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position).rollOnce(cP, GUI.rollOnceCard,GUI.rollOnceDie);
	    		  
		    	  
		    	  board.nextPlayer();
	    		  gui.setGUI(result[1]+" Next player!","1",buttons);
		    	  specialConditions[0]=true;
		    	  specialConditions[1]=true;
		      }
		});

		//BuyButton
		buttons[17].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position).build(cP, totalDice);
		    	  if(result[0].equals("1")){
		    		  board.nextPlayer();
		    		  gui.setGUI(result[1]+" Next player!","1",buttons);
		    	  }else if(result[0].equals("5")){
		    		  board.nextPlayer();
		    		  gui.setGUI(result[1] + " Next player!","1",buttons);
		    	  }
		    	 
		    	  
		      }
		});
		
		
		
		//dieOne
		buttons[14].addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){cP.setPosition(dieOne); gui.setGUI("", "", buttons); play();}});
		buttons[15].addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){cP.setPosition(dieTwo); gui.setGUI("", "", buttons); play();}});
		buttons[16].addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){cP.setPosition(dieOne+dieTwo); gui.setGUI("", "", buttons); play();}});
		
		
		
		
		//Start Game
		buttons[11].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  initialNumberofPlayers = gui.getInitialNumberofPlayers();
		    	  
		    	  board = new Board(initialNumberofPlayers);
		    	  players = board.getPlayers();

			  		players.get(0).row=1;
			  		players.get(0).position=30;
		  		
		    	  try {
		    		gui = null;
					gui = new GUI(board,buttons);
					gui.setPlayers(board.getPlayers());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	  
		    	  gui.setGUI("Let's play!", "1",buttons);
		    	  System.out.println("cont "+initialNumberofPlayers);
		      }
		});
		
		//Sell
		buttons[3].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  System.out.println("Selected Radio Button: " + gui.freePropertiesButtonGroup.getSelection().getActionCommand());
		      }
		});
		
		
		buttons[4].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {

				board.nextPlayer();
				gui.setGUI("Next player!", "1", buttons);
		  		
		      }
		});
		
		
		

		
		while(true){
			if(board!=null && board.currentPlayer!=null){
				cP = board.currentPlayer;
				players = board.players;
			}
		}
			
	}
	
	private void playMonopolyGuy(){}
	
	private void play(){
		
		
		int resultStatus;
		
		String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position).landOn(cP, board, totalDice);
		
		resultStatus = Integer.parseInt(result[0]);
		
		switch(resultStatus){
			case 0:
				gui.setGUI(result, "1", buttons);
			break;
			case 3:
				gui.setGUI(result[1], "01011", buttons);
			break;
			case 4:
				gui.setGUI(result[1], "01011", buttons);
				break;
			case 10:
				gui.setGUI(result, "000001", buttons);
				GUI.rollOnceCard = Integer.parseInt(result[2]);
				specialConditions[0] = true;
				break;
			default:
				gui.setGUI(result[1]+" Next player!", "1", buttons);
				board.nextPlayer();
				break;
				
		}
		
		
		
		
		
	}
	

	

}