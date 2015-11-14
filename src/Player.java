import java.awt.List;
import java.util.ArrayList;
import java.util.Random;


public class Player {

	private int money,id,position,numberOfProperties=0,numberOfCards=0,valueOfProperties=0;
	private SquareHouse[] properties = new SquareHouse[20];
	private ArrayList<Card> cards = new ArrayList<Card>();
	String name = "";
	private boolean isPlaying = true; // When player is out of money and lost all of his/her property, this var is false.
	SquaresInfo board;
	public static int NUMBEROFPLAYERS = 0;
	// Constructor of the player.
	//name alani ekledim.
	public Player(int id, String name, int money,int position,SquaresInfo board){	
		NUMBEROFPLAYERS++;
		this.id = id;
		this.name = name;
		this.money = money;
		this.position = position;	
		this.board=board;
}
	
	/*
	 * In the main, method should be like;
	 * 
	 * Square sq -> Player's arrival square
	 * String result = player.playerArrived(sq,board);
	 * if(result.equals("Buyable")){
	 * 		// ASK "Would you like to buy " + ""+sq.getName() + "?"
	 * 		var answer = playersAnswer (true or false)	
	 * 		if(answer)
	 * 			player.buySquareHouse(sq); // Type of String, so print this out.
	 * 			else	
	 * 				// Player didn't want to buy, so move on.
	 * 
	 * }
	 * else if (result.equals("HasToSell")){
	 * 		SquareHouse[] propertiesOfPlayer = player.getProperties();
	 * 		int numberOfProperties = player.getNumberOfProperties;
	 * 		// Ask which one to sell.
	 * 		int toSell = Player's Choice to sell
	 * 		player.sellPropertyPanel(propertiesOfPlayer[x-1]); // Type of String again.
	 * // x-1 because properties start from index 0.
	 * }
	 * 
	 * 
	 * */
	
	// Each time a player changes the position, this method is called.
	public String playerArrived(Square sq){
		String type = sq.getType();
		
		if(type.equals("SquareHouse")){
			// Square is house.			
			//Square current playera ait olabilir onunla ilgili degisiklik yaptim.
			int additionRent=0;
			String buyable = "Buyable";
			
			if(((SquareHouse) sq).isOwned() && ((SquareHouse) sq).getOwner().getId()!=this.id){
				//Pay the rent. If there is not enough money, add the case.
				
				// For Community Chest Card number 3: Collect $50 extra rent from the next player who lands on any of your properties.
				if(((SquareHouse) sq).getOwner().getCardWithId(3)){
					additionRent = 50;
					((SquareHouse) sq).getOwner().cardUsed(3);
				}
			
				

				if(money< (((SquareHouse) sq).getRent()+additionRent)){
					// If player has no property or value of properties is not enough to pay the rent, player is broke.
					if(numberOfProperties==0 || valueOfProperties < ((SquareHouse) sq).getRent()+additionRent){
						this.broke(); // Player is out of game.
						return "Broke";
					}else{
						// In this case, player payed the rent first, but player has to sell property, so money will be positive later.
					return "HasToSell";
					}
				}
				this.substractMoney(((SquareHouse) sq).getRent()+additionRent);
				((SquareHouse) sq).getOwner().addMoney(additionRent+((SquareHouse) sq).getRent());
				
				return "PaidRent"; // If it returns this, program should just print it.
			}else if(!((SquareHouse) sq).isOwned()) {
				if(this.getCardWithId(2)){
					buyable = "BuyWithChanceCard";
				}
				return buyable; // If it returns this, program should ask player if s/he wants to buy it or not.
			}else{
				return "Continue";
			}
			
			}else if(type.equals("SquareChance")){
				// Square is Chance Card.
				CardChance c = ((SquareChance)board.getSquare(7)).getTopCard();
				return c.doAction(this, board);			
				}else if(type.equals("SquareCommunityChest")){
						// Square is Community Chest Card.
						CardCommunity c = ((SquareCommunityChest)board.getSquare(2)).getCommunityChestCard();
						return c.doAction(this, board);
					}else if(type.equals("SquareRollOnce")){
							// Square is Roll Once Card.
							return "RollOnce"; // Returns the result of the Squeeze Player.
							}else if(type.equals("SquareSqueezePlay")){
								// Square is Squeeze Play Card.
								return "SqueezePlay";
								//return ((SquareSqueezePlay) sq).doAction(this,board);
							
								}else if(type.equals("SquareFree")){
									// Square is Free Parking or Go square.
									return "Continue";
	
								}
		//For error handling.
		return type; 
			
	}
	

	
	public boolean getCardWithId(int cardID){
		System.out.println("%%%%%%%%%%%%%%%%%%");

		boolean x=false;
		if(cards.size()==0){
			System.out.println("#################");
			return x;
		}else{
			for(int i=0;i<cards.size();i++){
			if(((CardCommunity)cards.get(i)).getNumber() == cardID){
				System.out.println("&&&&&&&&&&&&&&&&&&");
				x=true;
				return x;
			}
		}
			return x;
	}}
	
	public void removeFromArray(Object[] arr,int index,int numberOfElements){
		if(arr.length==numberOfElements){
			arr[index] = null;
			numberOfElements--;
		}else{
			for(int i=index;i<numberOfElements;i++){
				arr[index]=arr[index+1];
			}
			numberOfElements--;
		}
	}
	
	// METHODS ABOVE AND BELOW MIGHT CAUSE PROBLEM, KEEP THAT IN MIND FOR LATER. 
	
	public void cardUsed(int id){

		int x=0;
		for(int i=0;i<cards.size();i++){
			if(((CardCommunity)cards.get(i)).getNumber() == id){
				x=i;
				break;
			}
		}
		
		((SquareCommunityChest)board.getSquare(2)).addCommunityChestCard(((CardCommunity)cards.get(x)));
		cards.remove(x);
		numberOfCards--;
	}
	
	// In the main, if return value is "HasToSell", 
	// player's properties and number of properties should've called and listed to the player.
	// When player choose which property to sell, function below should be called.
	public String sellPropertyPanel(SquareHouse sh){
		String result = "";
		String nameOfProperty = sh.getName();		
		this.addMoney(sh.getPrice());
		for(int i=0;i<numberOfProperties;i++){
			if(properties[i].getName().equals(nameOfProperty)){
				sh.setOwner(null);
				removeFromArray(properties, i, numberOfProperties);
			}
		}
		normalizeRents(sh);
		numberOfProperties--;
		valueOfProperties-=((SquareHouse) sh).getPrice();
		result = "Sold";
		return result;
	}
	
	// When BUY button is pressed, this function will be called.
	// This function returns the console output.
	public String buySquareHouse(SquareHouse p){
		if(p.isOwned()){
			String result = "Other";
			return result;
		} else if (money < p.getPrice()) {
			String result = "NoMoney" ;
			
			return result;
		} else {
			money -= p.getPrice();
			this.addProperty((SquareHouse)p);
			checkForTriple(p); // Checking if the rent should be doubled or not.
			String result = "Bought";
			return result;
		}
	}
	
	public void checkForTriple(SquareHouse sq){
		int[] otherHouses = board.getOtherHouses(sq);
		if(((SquareHouse)board.getSquare(otherHouses[0])).getOwner().getId()==this.id 
				&& ((SquareHouse)board.getSquare(otherHouses[1])).getOwner().getId()==this.id 
				&& ((SquareHouse)board.getSquare(otherHouses[2])).getOwner().getId()==this.id){
			
			// Rents are doubled.
			((SquareHouse)board.getSquare(otherHouses[0])).doubleTheRent();
			((SquareHouse)board.getSquare(otherHouses[1])).doubleTheRent();
			((SquareHouse)board.getSquare(otherHouses[2])).doubleTheRent();

		}
	}
	
	public void normalizeRents(SquareHouse sq){
		int[] otherHouses = board.getOtherHouses(sq);
		for(int i=0;i<3;i++){
			((SquareHouse)board.getSquare(otherHouses[i])).normalizeRent();
		}
	}
	
	public String buyWithCard(SquareHouse p){
		String result;
		if(p.isOwned()){
			result = "Other";
		} else if (money < 100) {
			result = "NoMoney" ;
		
		} else {
			this.substractMoney(100);
			this.addProperty(p);
			this.cardUsed(2);
			result = "Bought";
		}
		return result;
	}
	
	public void broke(){
		this.isPlaying = false;
		NUMBEROFPLAYERS--;
	}

	// Player owns the property.
	public void addProperty(SquareHouse sq){
		properties[numberOfProperties] = sq;
		numberOfProperties++;
		sq.setOwner(this);
		valueOfProperties+=sq.getPrice();
	}
	// Player owns the card.
	public void addCard(CardCommunity cc){
		cards.add(cc);
		numberOfCards++;
	}
	
	public void addCardManipulate(int id,SquaresInfo board){
		CardCommunity cc = new CardCommunity(id,((SquareCommunityChest)board.getSquare(2)).getDescriptions()[id]);
		cards.add(cc);
		numberOfCards++;
		((SquareCommunityChest)board.getSquare(2)).deleteCard(id);		
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}

	public String rollOncePlay(int player,int rollOnceCard){
		String result;
		if(player == rollOnceCard){
			// Player wins 100$.
			this.addMoney(100);	
			result = player +" is equal to "+rollOnceCard+". "+this.getName()+" has won $100.";
		}
		else{
			result = player +" is not equal to "+rollOnceCard+". "+this.getName()+" has won no money.";
		}
		return result;
	}
	
	public void checkPosition(){
		if(position >= 20){
			position -= 20;
			money+=200;		
		}
	}
	
	public int getFace() {
		Random rand = new Random();
		int face = 1+rand.nextInt(6);
		return face;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public void addMoney(int money){
		this.money+=money;
		
	}
	public void substractMoney(int money){
		this.money-=money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
		checkPosition(); // Checks if the player passed the GO square or not.
	}

	public SquareHouse[] getProperties() {
		return properties;
	}
	
	public int getNumberOfProperties(){
		return numberOfProperties;
	}

	public String getPropertiesNames(){
		String r = "";
		for(int i=0; i<numberOfProperties; i++){
			if(i>0)
				r+=", ";
			r+=properties[i].getName();
		}
		return r;
	}
	public void setProperties(SquareHouse[] properties) {
		this.properties = properties;
	}


	public boolean getIsPlaying(){
		return isPlaying;
	}
	
	public void setIsPlaying(boolean isPlaying){
		this.isPlaying = isPlaying;
	}
	
	public int getValueOfProperties() {
		return valueOfProperties;
	}

	public void setValueOfProperties(int valueOfProperties) {
		this.valueOfProperties = valueOfProperties;
	}
	
	public String getName(){
		return name;
	}
	

	public int getNumberOfCards() {
		return numberOfCards;
	}

	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}
	
	
}
