import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class MonopolyGame {
	
	GUI gui;
	private JButton buttons[] = new JButton[26];
	public JComboBox<String> cpProperties = new JComboBox<String>();
	//buttons = rollDice,buy,buyChance,sell,no,rollOnce,pullChance,pullCommunity,mortgage,unMortgage,yes,start,load,save

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		new MonopolyGame();
		

		
	}
	
	public MonopolyGame() throws Exception{

		for(int i=0; i<26; i++){
			buttons[i] = new JButton();}
		
		
		gui = new GUI(new Board(0),buttons,cpProperties);
		
		//RollDice
		buttons[0].addActionListener(new ActionListener()
		{
		      public void actionPerformed(ActionEvent e)
		      {
		    	  System.out.println(Integer.toString(cpProperties.getItemCount()));
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