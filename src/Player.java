import java.awt.List;
import java.util.ArrayList;

public class Player {
	public int money,id,row,position,numberOfProperties=0,numberOfCards=0,valueOfProperties=0,
			numberOfHouses=0,numberOfHotels=0,numberOfSkyscrapers=0,numberOfCabStand=0,numberOfTransitStation=0;
	public boolean isPlaying = true;
	public boolean monopolyGuyFlag=false;
	String name = "";
	public ArrayList<SquareProperty> properties = new ArrayList<SquareProperty>();
	public ArrayList<SquareTransit> trains = new ArrayList<SquareTransit>();
	public ArrayList<SquareCabCompany> cabs = new ArrayList<SquareCabCompany>();	
	public ArrayList<SquareUtility> utilities = new ArrayList<SquareUtility>();
	public ArrayList<Square> freeProperties = new ArrayList<Square>();
	public ArrayList<Square> mortgagedProperties = new ArrayList<Square>();
	public ArrayList<Card> cards = new ArrayList<Card>();
	public String allPropertiesNames = "";
	public int colorProperties[] = new int[20];
	public Board board;
	public int countJail=4; //not in jail
	public boolean reverse = false;
	public int[] shares = new int[6];
	
	// If a player has a majority ownership or monopoly, color id of the property is here.(For hurricane card.)
	public ArrayList<Integer> upgradedColors = new ArrayList<Integer>();

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
		for (int i = 0; i < 6; i++) {
			shares[i]=0;
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
	
	public void addCardDebug(int id){
		if(id<26){
			CardChance c = new CardChance(id,board.cardDescriptions[id][0],board.cardDescriptions[id][1],board);
			cards.add(c);
		}else{
			CardCommunity c = new CardCommunity(id,board.cardDescriptions[id][0],board.cardDescriptions[id][1],board);
			cards.add(c);
		}
	}
	
	public void deleteCard(int number){
		
		for(int i=0;i<cards.size();i++){
			if(cards.get(i).getNumber() == number){
				cards.remove(i);
				break;
			}
		}
		
	}
	

	public void destroy(){
		for(int i=0;i<properties.size();i++){
			properties.get(i).initializeAll();
		}
		
		for(int i=0;i<utilities.size();i++){
			utilities.get(i).initializeAll();
		}
		
		for(int i=0;i<cabs.size();i++){
			cabs.get(i).initializeAll();
		}
		for(int i=0;i<trains.size();i++){
			trains.get(i).initializeAll();
		}
		
	}
	
	public void addPropertyDebug(int id,int house){
		SquareProperty sp = (SquareProperty)board.getSquareFromBoard(id);
		if(house==5){
			sp.hotel=1;
		}else if(house==6){
			sp.skyscraper=1;
		}else{
			sp.house=house;
		}
		
		
		sp.updateRentAccordingToHouse(sp);
		

		
		addProperty(sp);

		setFreeProperties();
	}
	
	public void addProperty(SquareProperty sp){
		properties.add(sp);
		sp.setOwner(this);
		colorProperties[sp.color]++;
		numberOfProperties++;
		valueOfProperties+=sp.price;
		//check for majority ownership or monopoly.
		
		updateRentPrices(sp.color);
		setFreeProperties();
	}
	
	public void deleteUpgradedColor(int id){
		for(int i=0;i<upgradedColors.size();i++){
			if(upgradedColors.get(i)==id){
				upgradedColors.remove(i);
			}
		}
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

		setFreeProperties();
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
					SquareProperty sp = (SquareProperty)board.getSquareFromBoard(houses[i]);
					if(sp.getOwner() != null && sp.getOwner().equals(this)){
						sp.doubleRent();
						sp.level=1;
						upgradedColors.add(sp.color);
					}
				}
				
			}
			else if(colorProperties[color] < housesSameColor-1){
				int[] houses = new int[housesSameColor];
				houses = board.getOtherProperties(color);
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = (SquareProperty)board.getSquareFromBoard(houses[i]);
					sp.normalizeRent();
					sp.level=0;
					deleteUpgradedColor(sp.color);
}
			}
			else if(colorProperties[color] == housesSameColor){
				int[] houses = new int[housesSameColor];
				houses = board.getOtherProperties(color);
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = (SquareProperty)board.getSquareFromBoard(houses[i]);
					if(sp.getOwner() != null && sp.getOwner().equals(this) && sp.house==0){
						sp.TripleRent();
						sp.level=2;
						upgradedColors.add(sp.color);
					}
					
				}
			}
					
	}
		else{
			
			if(colorProperties[color] < housesSameColor){
				int[] houses = new int[housesSameColor];
				houses = board.getOtherProperties(color);
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = (SquareProperty)board.getSquareFromBoard(houses[i]);
					sp.normalizeRent();
					sp.level=0;
					deleteUpgradedColor(sp.color);
				}
			}
			else if(colorProperties[color] == housesSameColor){
				int[] houses = new int[housesSameColor];
				houses = board.getOtherProperties(color);
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = (SquareProperty)board.getSquareFromBoard(houses[i]);
					if(sp.getOwner() != null && sp.getOwner().equals(this)){
						sp.TripleRent();
						sp.level=2;
						upgradedColors.add(sp.color);
					}
					
				}}
			
		}
		setFreeProperties();
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
	
	public void setPosition(int dice){

		int old = position;
		
		if(dice%2 == 1){
			if(!reverse){
				this.position = position + dice;
			}else{ //odd dice, stay on track
				position = 22-dice;
			}
		}else {
			if(reverse){ //even dice, change track if possible
				if(dice<11){
					position = 36-dice;
					row=1;
				}else{
					position=46-dice;
					row=2;
				}
				
				reverse=false;
			}else{
			///////////////////
				int posId = position;
				if(row==1){
					posId+=24;
					}
				else if(row==2){
					posId+=64;
					
				}
				// posId : bulundugu square'in id'si
				// border: bulundugu row'un square sayisi
				Square s = board.getSquareFromBoard(posId);
				
				for(int i=0;i<dice;i++){
					s = board.getSquareFromBoard(posId);
					if(s.type.equals("Transit")){
						posId = ((SquareTransit)s).twin.id+1;
						s = board.getSquareFromBoard(posId);
					}else{
						s = board.nextSquare(posId);
						posId = s.id;
					}
				}
				
				this.position = s.position;
				this.row = s.row;
			}
			

			setFreeProperties();
			
		///////////////////
		}

		
		checkPosition(dice,old);
	}
	
	public void monopolyGuy(){
		int posId = position;
		int border = 24;
		if(row==1){
			posId+=24;
			border=40;}
		else if(row==2){
			posId+=64;
			border=56;
		}
		
		Square s = board.getSquareFromBoard(posId);
		Square firstOwned = board.getSquareFromBoard(posId);
		
		boolean transitUsed = false;
		boolean firstOwnedProperty = false;
		boolean isFound = false;
		
		for(int i=0;i<border;i++){
			s = board.getSquareFromBoard(posId);
			
			if(s.type.equals("Transit")){
			if((MonopolyGame.dieOne + MonopolyGame.dieTwo)%2 == 0){
				posId = ((SquareTransit)s).twin.id+1;
				s = board.getSquareFromBoard(posId);
				transitUsed=true;
				break;
			}else{
				s = board.nextSquare(posId);
				posId = s.id;
			}
			}
			else {

				if(s.type.equals("Property")){
					if(((SquareProperty)s).owner == null){
						// Property Square found. break
						this.position = s.position;
						this.row = s.row;
						isFound=true;
						break;
					}else if(!firstOwnedProperty && ((SquareProperty)s).owner.id != this.id){
						firstOwned = s;
						firstOwnedProperty = true;
						// If all properties are owned, this will be the next position.
					}
				}else{
					s = board.nextSquare(posId);
					posId = s.id;
				}
				

			}
		}
		
		if(transitUsed){
			if(s.row == 0){
				border = 24;
			}else if(s.row==1){
				border = 40;
			}else if(s.row == 2){
				border = 56;
			}
			firstOwnedProperty = false;
			
			for(int i=0;i<border;i++){
			
					s = board.nextSquare(posId);
					posId = s.id;
					if(s.type.equals("Property")){
						if(((SquareProperty)s).owner == null){
							// Property Square found. break
							this.position = s.position;
							this.row = s.row;
							isFound = true;
							break;
						}else if(!firstOwnedProperty && ((SquareProperty)s).owner.id != this.id){
							firstOwned = s;
							firstOwnedProperty =true;
							// If all properties are owned, this will be the next position.
						}
					}else{
						s = board.nextSquare(posId);
						posId = s.id;
					}
					
				}
			
			
			}
		
		if(!isFound){
			this.position = firstOwned.position;
			this.row = firstOwned.row;
		}
		setFreeProperties();
			
		}
		
	
	public void checkPosition(int dice,int old){
		
		if(row==0){
			if(position>23){
				position = position%24;
				}
				else if(position<0){
					position+=24;
				}
			boolean passedzero = old-position>0;
			
			if(position>=6 && passedzero){
				addMoney(250);
			}
			if(position>=6 && old < 6){
				addMoney(250);
			}
			
		}else  if(row == 1){
			
			if (position>39){
				position = position%40;
				addMoney(200);
			}	else if(position<0){
				position+=40;
			}

		}else if(row==2){
			if(position>55){
				position = position%56;
				addMoney(400);
			}else if(position<0){
				position+=56;
			}
			if(position>28 && (position-dice)<28){
				// get paid.
				if(dice%2 == 0){
					addMoney(400);
				}else{
					addMoney(300);
				}
			}
			
			
		}
	}
	
	
	public void setFreeProperties(){
		freeProperties.clear();
		mortgagedProperties.clear();
		for(int i=0; i<properties.size(); i++){
			if(properties.get(i).isMortgaged==false)
				freeProperties.add(properties.get(i));
		}
		for(int i=0; i<cabs.size(); i++){
			if(cabs.get(i).isMortgaged==false)
				freeProperties.add(cabs.get(i));
		}
		for(int i=0; i<trains.size(); i++){
			if(trains.get(i).isMortgaged==false)
				freeProperties.add(trains.get(i));
		}
		for(int i=0; i<utilities.size(); i++){
			if(utilities.get(i).isMortgaged==false)
				freeProperties.add(utilities.get(i));
		}
		
		

		for(int i=0; i<properties.size(); i++){
			if(properties.get(i).isMortgaged)
				mortgagedProperties.add(properties.get(i));
		}
		for(int i=0; i<cabs.size(); i++){
			if(cabs.get(i).isMortgaged)
				mortgagedProperties.add(cabs.get(i));
		}
		for(int i=0; i<trains.size(); i++){
			if(trains.get(i).isMortgaged)
				mortgagedProperties.add(trains.get(i));
		}
		for(int i=0; i<utilities.size(); i++){
			if(utilities.get(i).isMortgaged)
				mortgagedProperties.add(utilities.get(i));
		}

		for(int i=0; i<freeProperties.size(); i++){
			if(i==0){
				allPropertiesNames = freeProperties.get(i).name;
			}else{
				allPropertiesNames = allPropertiesNames+", "+freeProperties.get(i).name;}
		}
		for(int i=0; i<mortgagedProperties.size(); i++){
			allPropertiesNames = allPropertiesNames+", "+mortgagedProperties.get(i).name;
		}
		
	}
	
	
	
	
}
