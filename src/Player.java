import java.awt.List;
import java.util.ArrayList;

public class Player {
	public int money,id,row,position,numberOfProperties=0,numberOfCards=0,valueOfProperties=0,
			numberOfHouses=0,numberOfHotels=0,numberOfSkyscrapers=0,numberOfCabStand=0,numberOfTransitStation=0;
	public boolean isPlaying = true;
	String name = "";
	public ArrayList<SquareProperty> properties = new ArrayList<SquareProperty>();
	public ArrayList<Card> cards = new ArrayList<Card>();
	public int[] colorProperties = new int[20];
	public Board board;
	
	public Player(String name,int money, int id, int row, int position,Board board) {
		super();
		this.name = name;
		this.money = money;
		this.id = id;
		this.row = row;
		this.position = position;
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
		colorProperties[sp.color]++;
		numberOfProperties++;
		valueOfProperties+=sp.price;
		//check for majority ownership or monopoly.
		updateRentPrices(sp.color);
	}
	
	public void deleteProperty(SquareProperty sp){
		if(sp.getOwner().equals(this)){
			
			sp.setOwner(null);
			colorProperties[sp.color]--;
			numberOfProperties--;
			valueOfProperties-=sp.price;
			for(int i=0;i<properties.size();i++){
				if(properties.get(i).id == sp.id){
					properties.remove(i);
					updateRentPrices(sp.color);
					break;
				}
			}
			
		}
	}

	public String[] getResultArray(){
		int x = board.getNumberOfPlayers() +2;
		String[] result = new String[x];
		for(int i=0;i<x;i++){
			result[i]= "0";
		}
		return result;
	}
	
	public String[] buyProperty(SquareProperty sp){
		String[] result = getResultArray();
		if(sp.getOwner()==null){
		if(this.money > sp.price){
			this.substract(sp.price);
			this.addProperty(sp);
			result[0]="1"; // Success
			result[1] = name + " has bought the " + ""+sp.name+".";
			result[this.id+2] = "-"+ ""+sp.price;
		}
		else {
			result[0] = "0";
			result[1] = "Player does not have enough money.";
			
		}
		}
		return result;
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
						sp.doubleRent();
					}
				}
				
			}
			else if(colorProperties[color] < housesSameColor-1){
				int[] houses = new int[housesSameColor];
				houses = board.getOtherProperties(color);
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = board.getSquareFromBoard(houses[i]);
					sp.normalizeRent();
				}
			}
			else if(colorProperties[color] == housesSameColor){
				int[] houses = new int[housesSameColor];
				houses = board.getOtherProperties(color);
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = board.getSquareFromBoard(houses[i]);
					if(sp.getOwner() != null && sp.getOwner().equals(this)){
						sp.TripleRent();
					}
					
				}
			}
					
	}
		else{
			
			if(colorProperties[color] < housesSameColor){
				int[] houses = new int[housesSameColor];
				houses = board.getOtherProperties(color);
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = board.getSquareFromBoard(houses[i]);
					sp.normalizeRent();
				}
			}
			else if(colorProperties[color] == housesSameColor){
				int[] houses = new int[housesSameColor];
				houses = board.getOtherProperties(color);
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = board.getSquareFromBoard(houses[i]);
					if(sp.getOwner() != null && sp.getOwner().equals(this)){
						sp.TripleRent();
					}
					
				}}
			
		}
	}
	
	public void addMoney(int money){
		this.money += money;
	}
	
	public void substract(int money){
		this.money -= money;
	}
	
	
	
	
	
}
