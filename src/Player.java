import java.awt.List;
import java.util.ArrayList;

public class Player {
	public int money,id,row,position,numberOfProperties=0,numberOfCards=0,valueOfProperties=0,
			numberOfHouses=0,numberOfHotels=0,numberOfSkyscrapers=0;
	public boolean isPlaying = true;
	String name = "";
	public ArrayList<SquareProperty> properties = new ArrayList<SquareProperty>();
	public ArrayList<Card> cards = new ArrayList<Card>();
	public int[] colorProperties = new int[20];
	public Board board;
	
	public Player(int money, int id, int row, int position, String name,Board board) {
		super();
		this.money = money;
		this.id = id;
		this.row = row;
		this.position = position;
		this.name = name;
		this.board = board;
		initialize();
	}
	
	private void initialize() {
		// TODO Auto-generated method stub
		for(int i=0;i<20;i++){
			colorProperties[i]=0;
		}
	}

	public boolean hasCardWithId(int number){
		boolean result = false;
		
		for(int i=0; i<cards.size();i++){
			if(cards.get(i).getNumber() == number){
				result = true;
				break;
			}
		}
		
		return result;
		
	}
	
	public void addCard(Card c){
		cards.add(c);	
	}
	
	public void deleteCard(int number){
		
		for(int i=0;i<cards.size();i++){
			if(cards.get(i).getNumber() == number){
				cards.remove(i);
				break;
			}
		}
		
	}
	
	public void addProperty(SquareProperty sp){
		properties.add(sp);
		sp.setOwner(this);
		colorProperties[sp.getColor()]++;
		numberOfProperties++;
		valueOfProperties+=sp.getPrice();
		//check for majority ownership or monopoly.
		updateRentPrices(sp.getColor());
	}

	
	
	public void updateRentPrices(int color){
		int housesSameColor = board.getNumberOfSameColor(color); // total number of the properties in that color.
		if(housesSameColor!=2){
			// Majority Ownership could be applied
			if (colorProperties[color]== housesSameColor-1){
				int[] houses = new int[housesSameColor];
				houses = board.getOtherProperties(color);
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = board.getSquareFromBoard(houses[i]);
					if(sp.getOwner() != null && sp.getOwner().equals(this)){
						sp.doubleTheRent();
					}
				}
				
			}
		}else {
			
			if(colorProperties[color] == housesSameColor){
				int[] houses = new int[housesSameColor];
				houses = board.getOtherProperties(color);
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = board.getSquareFromBoard(houses[i]);
					if(sp.getOwner() != null && sp.getOwner().equals(this)){
						sp.tripleTheRent();
					}
					
				}
			}
			
		}
		
	}
	
	
	
	
	
	
}
