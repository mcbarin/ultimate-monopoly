import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class MonopolyGame {
	
	GUI gui;
	Board board;
	private JButton buttons[] = new JButton[26];
	public JComboBox<String> cpProperties = new JComboBox<String>();
	private int initialNumberofPlayers;
	//buttons = rollDice,buy,buyChance,sell,no,rollOnce,pullChance,pullCommunity,mortgage,unMortgage,yes,start,load,save

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
		else if(Board.gameStatus==0){
			int numP;
			
			
			
		}
		
		

		//RollDice
		buttons[0].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  System.out.println(Integer.toString(cpProperties.getItemCount()));
		      }
		});
		
		
		
		
		
		
		
		
		
		//Start Game
		buttons[11].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  initialNumberofPlayers = gui.getInitialNumberofPlayers();
		    	  
		    	  board = new Board(initialNumberofPlayers);
		    	  try {
		    		  gui = null;
					gui = new GUI(board,buttons);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	  
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