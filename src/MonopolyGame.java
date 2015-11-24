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
	
	private JButton buttons[] = new JButton[26];
	public JComboBox<String> cpProperties = new JComboBox<String>();
	private int initialNumberofPlayers;
	
	private Dice die = new Dice();
	
	//buttons = rollDice,buy,buyChance,sell,no,rollOnce,pullChance,pullCommunity,mortgage,unMortgage,
	//			yes,start,load,save, dieOne, dieTwo, dieTotal
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		new MonopolyGame();
		

		
	}
	
	public MonopolyGame() throws Exception{

		for(int i=0; i<26; i++){
			buttons[i] = new JButton();}
		
		
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
		    	  dieOne = die.getFace();
		    	  dieTwo = die.getFace();
		    	  dieSpeed = die.getFace();
		    	  gui.setDice(dieOne, dieTwo, dieSpeed);
		    	  
		    	  if(dieSpeed == 4){
		    		  gui.setGUI("You have got 'Bus'! Choose one of the numbers in order to move:", "00000000000000111", buttons);
		    		  buttons[14].setText(Integer.toString(dieOne));
		    		  buttons[15].setText(Integer.toString(dieTwo));
		    		  buttons[16].setText(Integer.toString((dieOne+dieTwo)));
		    	  }else if(dieSpeed>4){
		    		  
		    	  }else{
		    		  
		    	  }
		    	  
		    	  
		    	  
		    	  
		    	  //board.nextPlayer();
		    	  
		      }
		});
		
		
		
		//dieOne
		buttons[11].addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e)
		      {
		    	  cP.setPosition(dieOne);
		      }
		});
		
		
		
		
		//Start Game
		buttons[11].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  initialNumberofPlayers = gui.getInitialNumberofPlayers();
		    	  
		    	  board = new Board(initialNumberofPlayers);
		    	  players = board.getPlayers();
		    	  try {
		    		gui = null;
					gui = new GUI(board,buttons);
					gui.setPlayers(board.getPlayers());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	  
		    	  gui.setGUI("Buğra", "10010",buttons);
		    	  System.out.println("cont "+initialNumberofPlayers);
		      }
		});
	
	}



	public static ActionListener rollDiceListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println(Integer.toBinaryString(13).charAt(1));
	      }
	};

	public static ActionListener buyListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("buy");
	      }
	};

	public static ActionListener noListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("no");
	      }
	};

	public static ActionListener sellListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("sell");
	      }
	};

	public static ActionListener buyChanceListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("buy chance");
	      }
	};

	public static ActionListener yesListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("yesss");
	      }
	};

	public static ActionListener rollOnceListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("roll once");
	      }
	};

	public static ActionListener pullChanceListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println(Integer.toString(3/2));
	      }
	};

	public static ActionListener pullCommunityListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println(Integer.toString(3/2));
	      }
	};

	public static ActionListener mortgageListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("Mortgage");
	    	 // System.out.println(Integer.toString(gui.getPropertyIndex()));
	      }
	};

	public static ActionListener unMortgageListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("Un Mortgage");
	      }
	};

	public static ActionListener startListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("Un Mortgage");
	      }
	};

	public static ActionListener loadListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("Un Mortgage");
	      }
	};

	public static ActionListener saveListener = new ActionListener()
	{
	      public void actionPerformed(ActionEvent e)
	      {
	    	  System.out.println("Un Mortgage");
	      }
	};

}