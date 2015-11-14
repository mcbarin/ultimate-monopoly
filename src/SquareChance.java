import java.util.Queue;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// There are 2 Chance Square in board. But these cards Array should be used from only one of them.
// So, call the createChanceCards() method from only one of them,
// And every time player comes to Chance Square, use that square's variables.
// Same rule is valid for also SquareCommunityChestClass.

// #############################################
// IMPORTANT NOTE ABOVE
// #############################################
// Cards should be initialized from the first chance square: id: 7

// There will be 8 Chance cards. These cards will be shuffled at first, then every card will be the last after used. FIFO

public class SquareChance extends Square {
	Queue<CardChance> deck = new LinkedList<CardChance>();
	String[] descriptions = {"","Advance to St. Charles Place","Advance to Squeeze Play, if you pass “Go”, collect $200 from the bank.","You are elected as the Chairperson. Pay each player $50.","Advance to “Go”, collect $200."};
	public SquareChance(String name) {
		super(name, "SquareChance", GUI.myWhite);
		// TODO Auto-generated constructor stub
	}
	
	
	public CardChance getChanceCard(){		
		CardChance c = deck.poll();
		deck.add(c);
		return c;
	}		
	
	public void swipeChanceCards(){		
		CardChance c = deck.poll();
		deck.add(c);
	}	
	
	public CardChance getTopCard(){		
		CardChance c = deck.peek();
		return c;
	}
	
	public void createChanceCards(){

		CardChance c1 = new CardChance(1,descriptions[1]);
		CardChance c2 = new CardChance(2,descriptions[2]);
		CardChance c3 = new CardChance(3,descriptions[3]);
		CardChance c4 = new CardChance(4,descriptions[4]);
	deck.add(c1);
	deck.add(c1);
	deck.add(c2);
	deck.add(c2);
	deck.add(c3);
	deck.add(c3);
	deck.add(c4);
	deck.add(c4);
	
	Collections.shuffle((List<?>) deck);
	
	}
	}
	
	
	
