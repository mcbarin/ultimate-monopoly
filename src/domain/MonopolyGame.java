package domain;
import java.awt.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import mp3.MP3Player;
public class MonopolyGame {
	
	private GUI gui;
	private Board board;
	private GameLS ls = new GameLS();
	
	public static int dieOne=0, dieTwo=0, dieSpeed=0;
	
	private ArrayList<Player> players;
	private Player cP;
	
	public static boolean manipulate=false; 
	
	private JButton buttons[] = new JButton[39];
	public static boolean specialConditions[] = new boolean[39];
	private ActionListener guiPublish;
	//	0-rollOnce	1-rollOnce Die	2-taxiRide	3-specialStatus	4-cabcomp	5-chance8	6-stockList		7-stockauction		8-normalauction
	private int specialStatus;
	public static int initialNumberofPlayers;
	public static int debugPlayerID=0;
	public static int debugLeft;
	
	private Dice die = new Dice();
	
	private  int totalDice;
	//buttons = rollDice,	buy,	buyChance,	sell,			4-no,			5-rollOnce,	pullChance,	pullCommunity,	mortgage,		unMortgage,
	//			10-yes,		start,	load,		save, 			dieOne,			15-dieTwo, 	dieTotal,	build,			18-taxiRide,	19-no
	//			20-yes		21-yestaxiact		22-chance21		23-hurricane	24-addPropDebug			25-debugnext	26-quit		27-sendAuction
	//			30-yesStock	31-noStock			32-sendBids		33-sellStock 	34-debugAddStock
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		new MonopolyGame();
		
		

		
	}
	
	public MonopolyGame() throws Exception{
		
		for(int i=0; i<39; i++){
			buttons[i] = new JButton();
			
					
			specialConditions[i] = false;}
		

		
		guiPublish = new ActionListener()
			{		@Override
			      public void actionPerformed(ActionEvent e)
			      {
					if(board!=null)
			    	 board.refreshGUI();
			      }};
		
		
		for(int i=0; i<39; i++){
			
			if(i!=12)
				buttons[i].addActionListener(guiPublish);
					}
		if(Board.gameStatus==-1){
			JTextField nump = new JTextField();
			gui = new GUI(new Board(2),buttons); 
			
			}
		

		//RollDice
		buttons[0].addActionListener(new ActionListener()
		{
			@Override
		      public void actionPerformed(ActionEvent e)
		      {
		    	  gui.removeSpecialConditions();
		    	  

	    		  
	    		  dieOne = gui.getDieOneF();
	    		  	gui.dieOneF.setText("0");
	    		  dieTwo = gui.getDieTwoF();
	    		  	gui.dieTwoF.setText("0");
	    		  dieSpeed = gui.getDieSpeedF();
	    		  	gui.dieSpeedF.setText("0");
	    		  	
	    		  if(dieOne>0 && dieTwo>0 && dieSpeed>0)
	    			  manipulate = true;
		    	  
		    	  if(!manipulate){
			    	  dieOne = die.getFace();
			    	  dieTwo = die.getFace();
			    	  dieSpeed = die.getFace();}
		    	  else{
		    		  
		    		 manipulate = false;
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
		    	  }else{
		    		  cP.setPosition(dieOne+dieTwo+dieSpeed);
		    		  String result[] = board.getSquareWithRowAndPosition(cP.row, cP.position).landOn(cP, board, totalDice);
			    	  play(Integer.parseInt(result[0]), result);
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

		//SellButton
		buttons[3].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String result[] = board.getSquareFromBoard(Integer.parseInt(gui.freePropertiesButtonGroup.getSelection().getActionCommand())).sellSquare(cP);
	
		    	  play(Integer.parseInt(result[0]),result);
		    	  System.out.println("Selected Radio Button: " + gui.freePropertiesButtonGroup.getSelection().getActionCommand());
		    	 
		    	  
		      }
		});

		//MortgageButton
		buttons[8].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String result[] = board.getSquareFromBoard(Integer.parseInt(gui.freePropertiesButtonGroup.getSelection().getActionCommand())).mortgage(cP);
	
		    	  play(Integer.parseInt(result[0]),result);
		    	  System.out.println("Selected Radio Button: " + gui.freePropertiesButtonGroup.getSelection().getActionCommand());
		    	 
		    	  
		      }
		});

		//UNMortgageButton
		buttons[9].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String result[] = board.getSquareFromBoard(Integer.parseInt(gui.mortgagedPropertiesButtonGroup.getSelection().getActionCommand())).unmortgage(cP);
	
		    	  play(Integer.parseInt(result[0]),result);
		    	  System.out.println("Selected Radio Button: " + gui.freePropertiesButtonGroup.getSelection().getActionCommand());
		    	 
		    	  
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
		    	  debugLeft = initialNumberofPlayers;
		    	  Board.gameStatus = 0;
		    	  
		    	  board = new Board(initialNumberofPlayers);
		    	  players = board.getPlayers();

		    		gui.initGUI(board);
					gui.setPlayers(board.getPlayers());
					
					
					board.addressGUI(gui);

			  	
		  		
		    
		    	  gui.setGUI("Let's play!", "",buttons);
		    	  System.out.println("cont "+initialNumberofPlayers);
		      }
		});


		
		
		//Quit Game
		buttons[26].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {

		    	  
		    	  
		    	  Board.gameStatus = -1;
		    	  gui.refresh();
		      }
		});

		
		
		


		//LOADButton
		buttons[12].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {

		    	  debugLeft = 0;
		    	  Board.gameStatus = 0;
		    	  board = ls.loadGame(Integer.parseInt(gui.savedGamesGroup.getSelection().getActionCommand()));
		    	  initialNumberofPlayers = board.players.size();
		    	  players = board.getPlayers();
		    	  
		    	  gui.initGUI(board);
		    	  gui.setPlayers(board.getPlayers());
		    	  gui.setGUI("Let's play!", "",buttons);
		    	  board.addressGUI(gui);
		    	  gui.refresh();
		    	  debugLeft = -1;
		    	  gui.refresh();
		    	  //System.out.println("Selected Radio Button: " + Integer.parseInt(gui.savedGamesGroup.getSelection().getActionCommand()));
		    	  //String[] a = {"asd","asdasd"};
		    	  //play(50,a);
		    	  
		    	  gui.refresh();
		    	  
		    	  
		    	  
		      }
		});


		
		//stock buy button
		buttons[30].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  //System.out.println("Selected Radio Button: " + gui.eCompsGroup.getSelection().getActionCommand());		    	  
		    	  String[] result = board.bank.buyStock(gui.eCompsGroup.getSelection().getActionCommand(), cP);
		    	  play(Integer.parseInt(result[0]),result);
		      }
		  });

		
		//stock add debug button
		buttons[34].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String nu = gui.debugStocksNum.getText();
		    	  int n = 0;
		    	  if(nu.equals("#"))
		    		  n=1;
		    	  else if(Integer.parseInt(nu)>(6-board.bank.companies.get(board.bank.getIdByStockName(gui.debugStocksButtonGroup.getSelection().getActionCommand())).share))
		    		  n=(6-board.bank.companies.get(board.bank.getIdByStockName(gui.debugStocksButtonGroup.getSelection().getActionCommand())).share);
		    	  else if(Integer.parseInt(nu)<1)
		    		  n=1;
		    	  else
			    	  n = Integer.parseInt(nu);
		    		  
		    	  //System.out.println("Selected Radio Button: " + gui.debugStocksButtonGroup.getSelection().getActionCommand() + gui.debugStocksNum.getText());		    	  
		    	  board.bank.addStock(gui.debugStocksButtonGroup.getSelection().getActionCommand(), n, board.getPlayers().get(initialNumberofPlayers-debugLeft));
		    	  
		    	  gui.debugStocksNum.setText("#");
		    	  gui.debugStocksButtonGroup.clearSelection();
		    	  gui.refresh();
		      }
		  });
		
		//stock no button
		buttons[31].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String[] result = {"51","One share of "+gui.eCompsGroup.getSelection().getActionCommand()+" will be auction off."};
		    	  specialConditions[7]=true;
		    	  play(51,result);
		      }
		  });
		
		//stock bids button
		buttons[32].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {	
		    	  int[] bids = new int[12];
		    	  Arrays.fill(bids, 0);
		    	  for(int i=0; i<board.players.size(); i++){
		    		  bids[i] = Integer.parseInt(gui.auctionBids[i].getText());
		    	  }
		    	  String[] result = board.bank.auctionStock(gui.eCompsGroup.getSelection().getActionCommand(), bids);
		    	  play(1,result);
		      }
		  });
		
		//stock sell button
		buttons[33].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String[] result = board.bank.sellStock(gui.userStocksButtonGroup.getSelection().getActionCommand(), cP);
		    	  play(0,result);
		      }
		  });
		  		

		
		//property no button
		buttons[4].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  gui.setGUI("There will be an auction for "+board.getSquareWithRowAndPosition(cP.row, cP.position).name+":", "",buttons);

		    	  specialConditions[8] = true;
		    	  gui.refresh();
		      }
		  });
		
		//property auction button
		buttons[27].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  Square sq = board.getSquareWithRowAndPosition(cP.row, cP.position);

			    	  specialConditions[8] = true;
			    	  int[] bids = new int[12];
			    	  Arrays.fill(bids, 0);
			    	  for(int i=0; i<board.players.size(); i++){
			    		  bids[i] = Integer.parseInt(gui.auctionBids[i].getText());
			    	  }
			    	  
			    	  String[] result = board.bank.auction(sq, bids);
			    	  play(1,result);
			    	  gui.refresh();

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
		    	  String result[] = board.peekChanceCard().doAction(cP);
		    	  int s = Integer.parseInt(result[0]);
		    	  if(s==25){
		    		  gui.setGUI(result,"",buttons);
		    		  specialConditions[4] = true;
		    	  }else if(s==8){
		    		  gui.setGUI(result,"",buttons);
		    		  specialConditions[5] = true;
		    	  }else{
		    		  play(1,result);
		    	  }
		    	 
		      }
		});
		
		//pull community
		buttons[7].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String result[] = board.peekChanceCard().doAction(cP);
		    	  int s = Integer.parseInt(result[0]);
		    	  if(s==25){
		    		  gui.setGUI(result,"",buttons);
		    		  specialConditions[4] = true;
		    	  }else if(s==8){
		    		  gui.setGUI(result,"",buttons);
		    		  specialConditions[5] = true;
		    	  }else{
		    		  play(1,result);
		    	  }
		    	 
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
		    		  play(Integer.parseInt(result[0]),result);
		    	  }
		    	 
		      }
		});
		
		//taxiRideAction
		buttons[22].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  //String result[] = board.peekChance().applyCard21(board.getSquareFromBoard(Integer.parseInt(gui.cabGroup.getSelection().getActionCommand())), cP);
		    
		    		 // result = board.getSquareWithRowAndPosition(cP.row, cP.position).landOn(cP, board, totalDice);
		    		  //play(Integer.parseInt(result[0]),result);
		    	 
		      }
		});
		
		//addPropDebug
		buttons[24].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {

		    	  //MP3Player.play("bip.mp3");
		    	  
		    	  board.getPlayers().get(initialNumberofPlayers-debugLeft).addPropertyDebug(Integer.parseInt(gui.dPropsGroup.getSelection().getActionCommand()), Integer.parseInt(gui.dPropsTypeGroup.getSelection().getActionCommand()));
		    	  gui.debugProps();
		    	  gui.refresh();
		    	  // System.out.println("debo: " + gui.dPropsGroup.getSelection().getActionCommand()+" "+gui.dPropsTypeGroup.getSelection().getActionCommand());
		    	 
		      }
		});
		
		//debug next player
		buttons[25].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  if(gui.getRow().equals("R"))
		    		  board.getPlayers().get(initialNumberofPlayers-debugLeft).row = 1;
		    	  else
		    		  board.getPlayers().get(initialNumberofPlayers-debugLeft).row=Integer.parseInt(gui.getRow());
		    	  gui.row.setText("R");
		    	  
		    	  if(gui.getPosition().equals("S"))
			    	  board.getPlayers().get(initialNumberofPlayers-debugLeft).position = 0;
		    	  else
			    	  board.getPlayers().get(initialNumberofPlayers-debugLeft).position=Integer.parseInt(gui.getPosition());
		    	  gui.position.setText("S");		    	  
		    	  
		    	  if(gui.getMoney().equals("$"))
			    	  board.getPlayers().get(initialNumberofPlayers-debugLeft).money=3200;
		    	  else
		    		  board.getPlayers().get(initialNumberofPlayers-debugLeft).money=Integer.parseInt(gui.getMoney());
		    	  gui.money.setText("$");
		    	  debugLeft--;
		    	  
		    	  if(debugLeft<0)
						gui.setGUI("1","",buttons);
		    	  
		    	  gui.refresh();
		      }
		});
		
		//hurricane
		buttons[23].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  String a = gui.hurGroup.getSelection().getActionCommand();
		    	 String result[] = board.peekChanceCard().applyCard14(Integer.parseInt(a.substring(0, a.indexOf("-"))), Integer.parseInt(a.substring(a.indexOf("-")+1, a.length())));
		    
		    		 // result = board.getSquareWithRowAndPosition(cP.row, cP.position).landOn(cP, board, totalDice);
		    		  play(Integer.parseInt(result[0]),result);
		    	  System.out.println(a.substring(a.indexOf("-")+1, a.length()));
		    	 
		      }
		});
		
		//Save Game
		buttons[13].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  ls.saveGame(board, gui.getSaveName());
		    	  
		    	 
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
		buttons[19].addActionListener(nolistener);
		

		

		
		while(true){
			if(board!=null && board.currentPlayer!=null){
				cP = board.currentPlayer;
				players = board.players;
			}
		}
			
	}
	
	private void play(int resultStatus, String result[]){
		
	

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
			case 31:
				gui.setGUI(result, "00000001", buttons);
				break;
			case 50:
				gui.setGUI(result, "00000000000000000000000000000011", buttons);
				specialConditions[6] = true;
				
				break;
			case 51:
				gui.setGUI(result, "000000000000000000000000000000001", buttons);
				specialConditions[7] = true;
				
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
	    		play(specialStatus,result);
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
			case 31:
				gui.setGUI(result, "00000001", buttons);
				break;
			default:
				gui.setGUI(result[1]+" Next player!", "1", buttons);
				board.nextPlayer();
				break;
				
		}
		
		
		
		
		
	}
	

	

}