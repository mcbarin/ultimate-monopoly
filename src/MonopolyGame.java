import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
public class MonopolyGame {
	
	private GUI gui;
	private Board board;
	
	public static int dieOne=0, dieTwo=0, dieSpeed=0;
	
	private ArrayList<Player> players;
	private Player cP;
	
	public static boolean manipulate=true; 
	
	private JButton buttons[] = new JButton[26];
	public static boolean specialConditions[] = new boolean[26];
	//	0-rollOnce	1-rollOnce Die	2-taxiRide	3-specialStatus
	private int specialStatus;
	private int initialNumberofPlayers;
	
	private Dice die = new Dice();
	private  int totalDice;
	//buttons = rollDice,	buy,	buyChance,	sell,	4-no,		5-rollOnce,	pullChance,	pullCommunity,	mortgage,		unMortgage,
	//			10-yes,		start,	load,		save, 	dieOne,		15-dieTwo, 	dieTotal,	build,			18-taxiRide,	19-no
	//			20-yes		21-yestaxiact
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		new MonopolyGame();
		
		

		
	}
	
	public MonopolyGame() throws Exception{
		
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
		    		 dieOne = 3;
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
		    	  specialConditions[3] = true;
		    	  specialStatus = Integer.parseInt(result[0]);
		    	  play();
		    	 
		    	  
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
		    	  specialConditions[3] = true;
		    	  specialStatus = Integer.parseInt(result[0]);
		    	  play();
		    	 
		    	  
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

			  		players.get(0).row=2;
			  		players.get(0).position=3;
		  		
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
		    	 /* String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position);
		    	  
		    	  specialConditions[3] = truee;
		    	  specialStatus = Integer.parseInt(result[0]);
		    	  play();*/
		    	  System.out.println("Selected Radio Button: " + gui.freePropertiesButtonGroup.getSelection().getActionCommand());
		      }
		});
		
		//Take a TaxiRide
		buttons[18].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position).taxiRide(cP, gui.taxiRideGroup.getSelection().getActionCommand());
		    	  
		    	  specialConditions[3] = true;
		    	  specialStatus = Integer.parseInt(result[0]);
		    	  play();
		    	 
		    	  System.out.println("Taxi Ride: " + gui.taxiRideGroup.getSelection().getActionCommand());
		      }
		});
		
		//applyCard
		buttons[20].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position).applyCard(board.getSquareWithRowAndPosition(cP.row, cP.position), cP, specialStatus);
		    	  
		    	  specialConditions[3] = true;
		    	  specialStatus = Integer.parseInt(result[0]);
		    	  play();
		    	 
		      }
		});
		
		//pull chance
		buttons[6].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position).applyCard(board.getSquareWithRowAndPosition(cP.row, cP.position), cP, specialStatus);
		    	  
		    	  specialConditions[3] = true;
		    	  specialStatus = Integer.parseInt(result[0]);
		    	  play();
		    	 
		      }
		});
		
		//taxiRideAction
		buttons[21].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position).taxiRideAction(cP, specialStatus);
		    	  
		    	  System.out.println(result[0]);
		    	  if(result[0].equals("5")){
		    		  result = board.getSquareWithRowAndPosition(cP.row, cP.position).landOn(cP, board, totalDice);
		    		  play2(Integer.parseInt(result[0]),result);
		    	  }
		    	 
		      }
		});
		
		//No button
		ActionListener nolistener = new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	
				board.nextPlayer();
				gui.setGUI("Next player!", "1", buttons);
		  		
		      }
		};
		buttons[4].addActionListener(nolistener);
		buttons[19].addActionListener(nolistener);
		
		
		

		
		while(true){
			if(board!=null && board.currentPlayer!=null){
				cP = board.currentPlayer;
				players = board.players;
			}
		}
			
	}
	
	private void play2(int resultStatus, String result[]){
		
		
		

		switch(resultStatus){
			case 0:
				gui.setGUI(result, "1", buttons);
			break;
			case 1:
	    		board.nextPlayer();
	    		gui.setGUI(result[1]+" Next player!","1",buttons);
				break;
			case 3:
				gui.setGUI(result[1], "01011", buttons);
			break;
			case 4:
				gui.setGUI(result[1], "01011", buttons);
				break;
			case 5:
				System.out.println(specialStatus);
			case 9:
				gui.setGUI(result, "00000000000000000011", buttons);
				specialConditions[2] = true;
				break;
			case 10:
				gui.setGUI(result, "000001", buttons);
				GUI.rollOnceCard = Integer.parseInt(result[2]);
				specialConditions[0] = true;
				break;
			case 11: case 35:
				specialConditions[3] = true;
				specialStatus = resultStatus;
				gui.setGUI(result, "000010000000000000011", buttons);
				break;
			case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
				specialConditions[3] = true;
				specialStatus = resultStatus;
				
				gui.setGUI(result, "0000100000000000000001", buttons);
				
				break;
			case 30:
				gui.setGUI(result, "0000001", buttons);
				break;
				
			default:
				gui.setGUI(result[1]+" Next player!", "1", buttons);
				board.nextPlayer();
				break;
		}
		}
	
	private void play(){
		
		
		int resultStatus;
		
		String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position).landOn(cP, board, totalDice);
		
		if(!specialConditions[3]){
			resultStatus = Integer.parseInt(result[0]);
			//resultStatus = 9;
		}else{
			specialConditions[3] = false;
			resultStatus = specialStatus;
		}
		
		switch(resultStatus){
			case 0:
				gui.setGUI(result, "1", buttons);
			break;
			case 1:
	    		board.nextPlayer();
	    		gui.setGUI(result[1]+" Next player!","1",buttons);
				break;
			case 3:
				gui.setGUI(result[1], "01011", buttons);
			break;
			case 4:
				gui.setGUI(result[1], "01011", buttons);
				break;
			case 5:
				result = board.getSquareWithRowAndPosition(cP.row, cP.position).landOn(cP, board, totalDice);
				specialConditions[3] = true;
				specialStatus = Integer.parseInt(result[0]);
				System.out.println(specialStatus);
				gui.setGUI(result[1], "01011", buttons);
	    		play2(specialStatus,result);
			case 9:
				gui.setGUI(result, "00000000000000000011", buttons);
				specialConditions[2] = true;
				break;
			case 10:
				gui.setGUI(result, "000001", buttons);
				GUI.rollOnceCard = Integer.parseInt(result[2]);
				specialConditions[0] = true;
				break;
			case 11: case 35:
				specialConditions[3] = true;
				specialStatus = resultStatus;
				gui.setGUI(result, "000010000000000000011", buttons);
				break;
			case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19:
				specialConditions[3] = true;
				specialStatus = resultStatus;
				
				gui.setGUI(result, "0000100000000000000001", buttons);
				
				break;
			case 25:
				gui.setGUI(result, "0000100000000000000001", buttons);
				break;
			case 30:
				gui.setGUI(result, "0000001", buttons);
				break;
			default:
				gui.setGUI(result[1]+" Next player!", "1", buttons);
				board.nextPlayer();
				break;
				
		}
		
		
		
		
		
	}
	

	

}