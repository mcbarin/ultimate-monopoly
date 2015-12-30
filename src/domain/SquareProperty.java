package domain;
//suanki kural: kareye gelince sadece o kareye ev diker. evenly ev dikmesini kontrol eder.
//majority ownership : 4 eve kadar dikebilir.
//monopoly olduktan sonra evenly hale getirmeli sonra hotel dikebilir.




public class SquareProperty extends Square  {
	

	public int price;
	public int originalRent;
	public int rent;
	public boolean isMortgaged=false;
	public int house=0;
	public int buildingPrice;
	public int hotel=0;
	public int skyscraper=0;
	public int level=0; //1 majority 2 monopoly

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
		result[0]="1";
		result[1] = "";	


		if(owner == null){
			if (player.money >= this.price){
				result[0]="3";
				result[1] = "Do you want to buy "+this.name+" ?";
			}else{  //if player has not enough money

			}

		}else if(owner.id == player.id){

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
					if(house<4 && player.money>buildingPrice){
						result[1] = "Do you want to build house to "+this.name+" ?";
					}else if(house==4 && player.money>buildingPrice){
						result[1] = "Do you want to build hotel to "+this.name+" ?";
					}
					result[0]="4";
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

		}else if(owner.id != player.id){	

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
			if(ss.owner.id == player.id && ss.house>this.house){
				return false;
			}
		}
		return true;
	}

	public String[] buyProperty(Player p){
		String[] result = new String[14];
		initializeResult(result);
		owner=p;
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

			if(house==4){ house=0; hotel=1;}
			else if(house<4){house+=1; p.numberOfHouses++;}
			updateRentAccordingToHouse(this);
			p.substract(buildingPrice);
			p.valueOfProperties+=buildingPrice/2;

			String keyword="house";
			if(hotel==1) keyword="hotel";

			result[0]="1";
			result[1]= p.name+" built "+keyword+" to "+this.name+" and lost $"+buildingPrice;
			result[p.id+2] = Integer.toString(-1*buildingPrice);

		}else if (level == 2) {//monopoly


			if(hotel==1){hotel=0; p.numberOfHotels--; skyscraper=1; p.numberOfSkyscrapers++;}
			else if(house==4){ house=0; p.numberOfHouses-=4; hotel=1; p.numberOfHotels++;}
			else if(house<4){house+=1; p.numberOfHouses++;}
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

		if(ss.skyscraper==1){
			ss.rent = ss.originalRent*85;
		}else if(ss.hotel == 1){
			ss.rent = ss.originalRent*50;
		}else if(ss.house == 1){
			ss.rent = ss.originalRent*5;
		}else if(ss.house == 2){
			ss.rent = ss.originalRent*15;
		}else if(ss.house == 3){
			ss.rent = ss.originalRent*30;
		}else if(ss.house == 4){
			ss.rent = ss.originalRent*40;

		}
	}



	


	public String[] sellBuilding(Player p){
		String[] result = new String[14];
		initializeResult(result);
		result[0]="0";

		if(owner.id != p.id){
			result[1]="This property doesn't belong to you!";
			return result;
		}else if (isMortgaged){
			result[1]="This property is mortgaged already";
			return result;
		}else if(skyscraper==1){
			skyscraper=0;
			hotel=1;
			result[1]=p.name+" sold skyscraper for $"+buildingPrice/2;
		}else if(hotel==1){
			hotel=0;
			p.numberOfHotels--;
			house=4;
			p.numberOfHouses+=4;
			result[1]=p.name+" sold hotel for $"+buildingPrice/2;
		}else if(house==0){
			result[1]="You don't have any building here.";
			return result;
		}else if (house<4){
			house-=1;
			p.numberOfHouses--;
			result[1]=p.name+" sold house for $"+buildingPrice/2;
		}

		p.addMoney(buildingPrice/2);
		updateRentAccordingToHouse(this);
		result[p.id+2]=Integer.toString(buildingPrice/2);

		return result;
	}

	public String[] sellProperty(Player p){
		String[] result = new String[14];
		initializeResult(result);
		result[0]="0";
		
		if(owner.id != p.id){
			result[1]="This property doesn't belong to you!";
			return result;
		}else if(isMortgaged){
			result[1]="This property is mortgaged already";
			return result;
		}else if(house==0 && hotel==0 && skyscraper==0){
			
			p.addMoney(price/2);
			result[1]=p.name+" sold "+ this.name+" for $"+price/2;
			result[p.id+2]=Integer.toString(buildingPrice/2);
			p.deleteProperty(this);
			initializeAll();
		}

		p.setFreeProperties();
		return result;
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

	public void hurricane(Player p){
		if(skyscraper==1){
			skyscraper=0;
			p.numberOfSkyscrapers--;
			hotel=1;
			p.numberOfHotels++;
		}else if(hotel==1){
			hotel=0;
			p.numberOfHotels--;
			house=4;
			p.numberOfHouses+=4;
		}else if(house==0){
			return;
		}else if (house<4){
			house-=1;
			p.numberOfHouses--;
		}
		updateRentAccordingToHouse(this);
	}
	
	public void doubleRent(){
		rent = originalRent*2;
	}
	public void TripleRent(){
		this.rent = originalRent*3;
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
	
	public void setHouse(int n){
		house=n;
		updateRentAccordingToHouse(this);
	}
	public void setHotel(int n){
		hotel=n;
		updateRentAccordingToHouse(this);
	}
	public void setSkyscraper(int n){
		skyscraper=n;
		updateRentAccordingToHouse(this);
	}
	
	public String getName(){
		return name;
	}

}

