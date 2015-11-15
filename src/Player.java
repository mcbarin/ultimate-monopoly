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
	
	/*
	 * public void initializeAll(){
	 * 		this.isMortgaged = false;
	 * 		this.setOwner(null);
	 * 		this.building=0;
	 * 		this.hotel = 0;
	 * 		this.skyscraper = 0;
	 * 		this.normalizeRent();
	 * }
	 * */
	
	public void destroy(){
		for(int i=0;i<properties.size();i++){
			properties.get(i).initializeAll();
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
		String[] result = new String[14];
		for(int i=0;i<14;i++){
			result[i]= "0";
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
	
	public void setRow(int row){
		this.row = row;
	}
	
	public void setPosition(int position){
		this.position = position;
		checkPosition();
	}
	
	public void checkPosition(){
		if(row==0){
			if(position>23){
			position = position%24;
			addMoney(250);
			}
		}else  if(row == 1){
			if (position>39){
				position = position%40;
				addMoney(200);
			}
		}else if(row==2){
			if(position>55){
				position = position%56;
			}
			
		}
	}
	
	
	
	
	
}
