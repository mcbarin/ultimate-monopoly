//suanki kural: kareye gelince sadece o kareye ev diker. evenly ev dikmesini kontrol eder.
//majority ownership : 4 eve kadar dikebilir.
//monopoly olduktan sonra evenly hale getirmeli sonra hotel dikebilir.

public class SquareProperty extends Square  {

	Player owner=null;
	int color;
	int price;
	int originalRent;
	int rent;
	boolean isMortgaged;
	int house=0;
	int buildingPrice;
	int hotel=0;
	int skyscraper=0;
	int level=0; //1 majority 2 monopoly

	public SquareProperty(String type,String name, int id, int position, int row, int color, int price, int originalRent) {
		super(type,name, id, position, row);
		this.color = color;
		this.price = price;
		this.originalRent = originalRent;
		rent = originalRent;

		switch (this.color) {
		case 3: case 4 : case 5: case 12: case 13:
			buildingPrice = 50;
			break;
		case 0: case 6 : case 7: case 8: case 14: case 15:
			buildingPrice = 100;
			break;
		case 9: case 10 : case 16: 
			buildingPrice = 150;
			break;
		case 1: case 11 : case 17: 
			buildingPrice = 200;
			break;
		case 2: 
			buildingPrice = 215;
			break;
		case 18: 
			buildingPrice = 250;
			break;
		case 19: 
			buildingPrice = 300;
			break;
		}

	}

	@Override
	public String[] landOn(Player player, Board board , int total) {
		String[] result = new String[14];
		initializeResult(result);
		SquareProperty ss = null;
		result[0]="1";
		result[1] = "";	


		if(owner == null){
			if (player.money >= this.price){
				result[0]="3";
				result[1] = "Do you want to buy "+this.name+" ?";
			}else{  //if player has not enough money

			}

		}else if(owner == player){

			if(isMortgaged){
				result[0]="1";
				result[1] = "This property is mortgaged, can't build .";
				return result;
			}

			int props = board.getNumberOfSameColor(this.color); 
			if (props <= 2 || skyscraper==1 || level == 0){ 
				return result;
			}

			if(level== 1 && this.house<4 && player.money> buildingPrice){ //if player has more than 2 properties of same color	
				if(checkEvenly(player, board)){
					result[0]="4";
					result[1] = "Do you want to build house to "+this.name+" ?";
				}else{
					result[0]="1";
					result[1] = "Building can be done evenly.";
				}
			}else if(level == 2){ 
				if(checkEvenly(player, board)){
					result[0]="4";
					if(house<4 && player.money>buildingPrice){
						result[1] = "Do you want to build house to "+this.name+" ?";
					}else if(house==4 && player.money>buildingPrice){
						result[1] = "Do you want to build hotel to "+this.name+" ?";
					}else if(hotel==1 && player.money>buildingPrice){
						result[1] = "Do you want to build skyscraper to "+this.name+" ?";
					}

				}else{
					result[0]="1";
					result[1] = "Building can be done evenly.";
				}

			}else{ //no majority, no monopoly, can't build
				result[0]="1";
				result[1]="";
			}

		}else if(owner != player){	

			int unmortgagePrice = (price/2)*(11/10);
			//player can unmortgage this square with his card
			if(isMortgaged && player.hasCardWithId(11) && player.money>unmortgagePrice){
				result[0]="11";
				result[1] = "Do you want to use your '"+board.cardDescriptions[11][0]+"' card ?";
				//player excused from paying rent
			}else if(player.hasCardWithId(23)) {
				player.deleteCard(23);
				result[0]="1";
				result[1] = "You have '"+board.cardDescriptions[23][0]+"' card and you are excused from paying rent.";
				//player can reverse rent
			}else if(player.hasCardWithId(35)) {
				result[0]="35";
				result[1] = "Do you want to use your '"+board.cardDescriptions[35][0]+"' card ?";

				//regular pay rent process	
			}else if (isMortgaged){
				result[0]="1";
				result[1] = "This property is mortgaged, don't pay rent.";
			}else if (player.money>=rent){
				player.substract(this.rent);
				owner.addMoney(this.rent);
				result[0]="1";
				result[1] = player.name+" paid rent to "+owner.name;
				result[player.id+2]=Integer.toString(-1*rent);
				result[owner.id+2]=Integer.toString(rent);	

			}else if(player.money+player.valueOfProperties>=rent){
				result[0]="2";
				result[1]="Player can't pay rent. Player should sell property.";
			}else {
				result[0]="-1";
				result[1]="Player can't pay the rent and broke.";
			}	
		}



		return result;
		// TODO Auto-generated method stub

	}

	public boolean checkEvenly(Player player, Board board){
		SquareProperty ss = null;
		int ids[] = board.getOtherProperties(this.color); 
		for (int i = 0; i < ids.length; i++) {
			ss = (SquareProperty)board.getSquareFromBoard(ids[i]);
			if(ss.owner == player && ss.house>this.house){
				return false;
			}
		}
		return true;
	}

	public String[] buyProperty(Player p){
		String[] result = new String[14];
		initializeResult(result);
		p.substract(price);
		p.addProperty(this);
		result[0]="1"; // Success
		result[1] = p.name + " has bought the " + ""+name+".";
		result[p.id+2] = "-"+ ""+price;
		return result;
	}



	public String[] buildToProperty(Player p){
		String[] result = new String[14];
		initializeResult(result);

		if(level == 1){
			updateRentAccordingToHouse(this);
			p.substract(buildingPrice);
			p.valueOfProperties+=buildingPrice/2;

			result[0]="1";
			result[1]= p.name+" built house to "+this.name+" and lost $"+buildingPrice;
			result[p.id+2] = Integer.toString(-1*buildingPrice);

		}else if (level == 2) {//monopoly

			updateRentAccordingToHouse(this);
			p.substract(buildingPrice);
			p.valueOfProperties+=buildingPrice/2;

			String keyword="house";
			if(hotel==1) keyword="hotel";
			if(skyscraper==1) keyword="skyscraper";
			result[0]="1";
			result[1]= p.name+" built "+keyword+" to "+this.name+" and lost $"+buildingPrice;
			result[p.id+2] = Integer.toString(-1*buildingPrice);

		}

		return result;
	}

	public void updateRentAccordingToHouse(SquareProperty ss){
		//		assuming with 1 house rent will be x5
		//						2 house x15
		//						3 house x30
		//						4 house x40
		//						hotel x50
		//						sky x85

		if(ss.hotel==1){
			ss.rent = ss.originalRent*85;
			ss.hotel=0;
			ss.skyscraper = 1;
		}else if(ss.house == 0){
			ss.rent = ss.originalRent*5;
			ss.house = ss.house + 1; //build house to all owned properties
		}else if(ss.house == 1){
			ss.rent = ss.originalRent*15;
			ss.house = ss.house + 1; //build house to all owned properties
		}else if(ss.house == 2){
			ss.rent = ss.originalRent*30;
			ss.house = ss.house + 1;
		}else if(ss.house == 3){
			ss.rent = ss.originalRent*40;
			ss.house = ss.house + 1;
		}else if(ss.house == 4){
			ss.rent = ss.originalRent*50;
			ss.house=0;
			ss.hotel = 1;
		}
	}



	public String[] mortgageProperty(Player p){
		this.isMortgaged=true;
		String[] result = new String[14];
		initializeResult(result);
		p.addMoney(price/2);
		result[0]="1";
		result[1]="Player mortgaged the property.";
		result[p.id+2]=""+(price/2);
		return result;
	}

	public String[] unmortgageProperty(Player p){
		int unmortgagePrice = (price/2)*(11/10);
		String[] result = new String[14];
		initializeResult(result);
		if(p.money>unmortgagePrice){
			p.substract(unmortgagePrice);
			isMortgaged=false;
			result[0]="1";
			result[1]="Player unmortgaged the property.";
			result[p.id+2]="-"+""+unmortgagePrice;
			return result;
		}else {
			result[0]="5";
			result[1]="Player can not unmortgage this property. Money is not enough.";
			return result;
		}
	}





	public void initializeAll(){
		this.isMortgaged = false;
		this.setOwner(null);
		this.house=0;
		this.hotel = 0;
		this.skyscraper = 0;
		this.normalizeRent();
		this.level=0;
	}

	public void doubleRent(){
		rent = originalRent*2;
	}
	public void TripleRent(){
		rent = originalRent*3;
	}
	public void normalizeRent(){
		rent = originalRent;
	}
	public Player getOwner(){
		return this.owner;
	}
	public void setOwner(Player player){
		this.owner = player; 
	}
	public int getColor(Square s){
		return this.color;
	}
	public int getPrice(Square s){
		return this.price;
	}
}

