package domain;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

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

	/**
	 * This method is the Constructor method for the player. It creates the player object with given parameters.
	 * @param name
	 * @param money
	 * @param id
	 * @param row
	 * @param position
	 * @param board
	 * @requires money,id should be positive. row should be between 0-2 and position must be between 0-49.
	 * @modifies It modifies the all fields of the new player and initializes them.
	 * @effects A new player object with given parameters are created.
	 */
	public Player(String name,int money, int id, int row, int position,Board board) {
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

	/**
	 * This method takes a card number and checks that if player has that card or not.
	 * @param number
	 * @requires number must be between 0-49
	 * @modifies This method does not modify anything.
	 * @effects Boolean value is returned that if player has that card result is true, otherwise result is false.
	 * @return boolean result
	 */
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
	
	/**
	 * Input card is added to the player's cards ArrayList.
	 * @param c
	 * @requires Card c must be a keepable card for player.
	 * @modifies cards
	 * @effects Given card is added to the player and player may use the card later.
	 */
	public void addCard(Card c){
		cards.add(c);	
	}
	
	@Override
	public String toString() {
		return "Player [money=" + money + ", id=" + id + ", row=" + row + ", position=" + position
				+ ", numberOfProperties=" + numberOfProperties + ", numberOfCards=" + numberOfCards
				+ ", valueOfProperties=" + valueOfProperties + ", numberOfHouses=" + numberOfHouses
				+ ", numberOfHotels=" + numberOfHotels + ", numberOfSkyscrapers=" + numberOfSkyscrapers
				+ ", numberOfCabStand=" + numberOfCabStand + ", numberOfTransitStation=" + numberOfTransitStation
				+ ", isPlaying=" + isPlaying + ", name=" + name + ", cards=" + cards + ", allPropertiesNames="
				+ allPropertiesNames + ", shares=" + Arrays.toString(shares) + "]";
	}


	/**
	 * A card is created with input id and added to the player.
	 * @param id
	 * @requires Card c must be a keepable card for player.
	 * @modifies cards
	 * @effects Given card is added to the player and player may use the card later.
	 */
	public void addCardDebug(int id){
		if(id<36){
			CardChance c = new CardChance(id,board.cardDescriptions[id][0],board.cardDescriptions[id][1],board);
			cards.add(c);
		}else{
			CardCommunity c = new CardCommunity(id,board.cardDescriptions[id][0],board.cardDescriptions[id][1],board);
			cards.add(c);
		}
	}
	
	/**
	 * This method deletes the card from player's cards ArrayList.
	 * @param number
	 * @requires number must be between 0-49 and player must have the card.
	 * @modifies cards
	 * @effects A card with given number is deleted from cards.
	 */
	public void deleteCard(int number){
		
		for(int i=0;i<cards.size();i++){
			if(cards.get(i).getNumber() == number){
				cards.remove(i);
				break;
			}
		}
		
	}
	
	/**
	 * This method deletes the everything that player has. After the execution, player loses every item he/she has and
	 * player is out of game.
	 * @requires Player must be out of game.
	 * @modifies properties,utilities,cabs and trains ArrayLists
	 * @effects Player loses the items and out of the game.
	 */
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
		isPlaying = false;
		
	}
	
	/**
	 * This method takes a property id and number of house as a parameter and adds it to the player.
	 * @param id
	 * @param house
	 * @requires Square with the given id must be in the type of SquareProperty. house must be between 0-6
	 * @effects A new SquareProperty object is added to the player.
	 */
	public void addPropertyDebug(int id,int house){
		SquareProperty sp = (SquareProperty)board.getSquareFromBoard(id);
		if(house==5){
			sp.hotel=1;
			this.numberOfHotels++;
		}else if(house==6){
			sp.skyscraper=1;
			this.numberOfSkyscrapers++;
		}else{
			sp.house=house;
			this.numberOfHouses++;
		}
		
		
		sp.updateRentAccordingToHouse(sp);
		

		
		addProperty(sp);

		setFreeProperties();
	}
	
	/**
	 * This method takes a SquareProperty object and adds it to the player.
	 * @param sp
	 * @requires There is no requirement for this method.
	 * @modifies properties, colorProperties, numberOfProperties, valueOfProperties
	 * @effects A new SquareProperty object is added to the player.
	 */
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
	
	/**
	 * This method deletes the object from upgraded colors ArrayList.
	 * @param id
	 * @requires id must be between 0 and 19.
	 * @modifies upgradedColors
	 * @effects upgradedColors ArrayList is lost the given color group.
	 */
	public void deleteUpgradedColor(int id){
		for(int i=0;i<upgradedColors.size();i++){
			if(upgradedColors.get(i)==id){
				upgradedColors.remove(i);
			}
		}
	}
	
	/**
	 * This method deletes the property of the player from properties ArrayList.
	 * @param sp
	 * @requires Player must own the SquareProperty
	 * @modifies colorProperties,numberOfProperties,valueOfProperties,properties
	 * @effects Player loses the given SquareProperty object.
	 */
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

	/**
	 * This method returns an empty String array.
	 * @requires There is no requirement for this method.
	 * @modifies This method does not modifies anything.
	 * @effects String array with the length of 14 is given to the user. All indexes are initialized to 0.
	 * @return It returns a string array which all elements are zero.
	 */
	public String[] getResultArray(){
		String[] result = new String[14];
		for(int i=0;i<14;i++){
			result[i]= "0";
		}
		return result;
	}
	
	

	/**
	 * This method checks the properties of the given color group. Then, it updates the rent prices of the properties
	 * according to majority ownership or monopoly rules.
	 * @param color
	 * @requires color must be between 0-19
	 * @modifies upgradedColors
	 * @effects Rent prices of the SquareProperty object is updated to its current value.
	 */
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
				for(int i=0; i<board.getOtherProperties(color).length;i++){
					houses[i] = board.getOtherProperties(color)[i];
				}
				
				for(int i=0;i<housesSameColor;i++){
					SquareProperty sp = (SquareProperty)board.getSquareFromBoard(houses[i]);
					if(sp.getOwner() != null && sp.getOwner().equals(this) && sp.house==0){
						System.out.println(i);
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
				for(int i=0; i<board.getOtherProperties(color).length;i++){
					houses[i] = board.getOtherProperties(color)[i];
				}
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
	
	/**
	 * This method adds the given money amount to the player.
	 * @param money
	 * @requires There is no requirement for this method.
	 * @modifies money
	 * @effects money is added to the player's money.
	 */
	public void addMoney(int money){
		this.money += money;
	}
	
	/**
	 * This method substracts the given money amount to the player.
	 * @param money
	 * @requires There is no requirement for this method.
	 * @modifies money
	 * @effects money is substracted from the player's money.
	 */
	public void substract(int money){
		this.money -= money;
	}
	
	/**
	 * This method changes the row of the player.
	 * @param row
	 * @requires row must be between 0-2.
	 * @modifies row
	 * @effects row is updated to it new value.
	 */
	public void setRow(int row){
		this.row = row;
	}
	
	/**
	 * This method takes the dice value of the player, determines its new position and moves the player.
	 * @param dice
	 * @requires dice must be an integer between 3 and 16.
	 * @modifies This method modifies the player's row and position.
	 * @effects Player's position is changed according to dice value.
	 */
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
	
	/**
	 * This method moves the player according to Monopoly Guy card.
	 * @requires Player must be the current player.
	 * @modifies This method modifies the player's row and position.
	 * @effects Player's position is changed according to Monopoly Guy rules.
	 */
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
		
	/**
	 * This method takes the dice value of the player and checks if player passed any paycorner or not.
	 * If so, money of the paycorner is added to the player.
	 * @param dice
	 * @param old
	 * @requires There is no requirement for this method.
	 * @modifies money
	 * @effects Player's money is updated according to the position.
	 */
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
	/**
	 * This method takes the dice value of the player and checks if player passed any paycorner or not.
	 * If so, money of the paycorner is added to the player.
	 * @requires There is no requirement for this method.
	 * @modifies freePorperties and mortgagedProperties strings.
	 * @effects Player's properties names updated in oder to help GUI.
	 */
	
	
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

	/**
	 * This method checks whether the given player is thisplayer or not.
	 * @param player
	 * @requires There is no requirement for this method.
	 * @modifies nothing.
	 * @effects nothing.
	 */
	public boolean equals(Player p){
		if(this.id==p.id)
			return true;
		else
			return false;
	}
	
	public boolean repOk(){
		if(money<0 || id<0 || id>11 || row < 0 || row > 2 || position<0)
			return false;
		else if(properties==null || trains==null || cabs==null || utilities==null || cards == null || board==null)
			return false;
		else
			return true;
		
			
	}
	
}
