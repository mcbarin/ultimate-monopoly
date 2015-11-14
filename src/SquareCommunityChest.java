import java.util.Queue;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// Same thing with the chance square is valid for community chest square.
// CommunityChestCards should be initialized for first community chest square: id:2


public class SquareCommunityChest extends Square {
	Queue<CardCommunity> deck = new LinkedList<CardCommunity>();
	String[] descriptions = {"","Receive Consultancy Fee: Collect $25 from the bank."," Bargain Business: When you land on an unowned propert you want, buy it for only $100. Keep until needed.","Renovation Success: Collect $50 extra rent from the next player who lands on any of your properties."};
	public SquareCommunityChest(String name) {
		super(name, "SquareCommunityChest", GUI.myWhite);
		// TODO Auto-generated constructor stub
	}

	
	public CardCommunity getCommunityChestCard(){		
			CardCommunity c = deck.poll();
			if(c.getNumber()==1){
				deck.add(c);
			}
			return c;
	}
	
	public void deleteCard(int id){
		CardCommunity c = new CardCommunity(id,descriptions[id]);
		deck.remove(c);
	}
	
	public String[] getDescriptions() {
		return descriptions;
	}


	public void addCommunityChestCard(CardCommunity c){
		deck.add(c);
	}
	
	public void createCommunityChestCards(){
		
		CardCommunity c1 = new CardCommunity(1,descriptions[1]);
		CardCommunity c2= new CardCommunity(2,descriptions[2]);
		CardCommunity c3= new CardCommunity(3,descriptions[3]); 
		
		deck.add(c1);
		deck.add(c1);
		deck.add(c2);
		deck.add(c2);
		deck.add(c3);
		deck.add(c3);
		Collections.shuffle((List<?>) deck);
		
		
		}
		
	

	

}
