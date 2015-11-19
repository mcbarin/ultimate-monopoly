//suanki kural: majority ownership ise o renkten sahip oldukarına sadece 1 ev dikebilir.aynı anda hepsine diker.
//monopoly olduktan sonra, o renkten herhangi bi yere gelince ev olmayan yere de bi ev diker. Sonra her geldiğinde aynı anda hepsine birer ev daha diker.
//bunu sonra oyuncuya kac ev dikmek istersin diye sorup değiştirebiliriz.
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

		if(owner == null){
			if (player.money >= this.price){
				result[0]="3";
				result[1] = "Do you want to buy "+this.name+" ?";
			}else{  //if player has not enough money
				result[0]="1";
				result[1] = "";	
			}

		}else if(owner == player){
			int props = board.getNumberOfSameColor(this.color); 
			if (props <= 2){ //if 2 properties of same color can't build house
				result[0]="1";
				result[1]="";
				return result;
			}

			String keyword = "house";
			if(house==4) keyword="hotel";
			if(hotel==1) keyword="skyscraper";
			int pr = 0;
			int ids[] = board.getOtherProperties(this.color); //get ids of properties of specific color
			for (int i = 0; i < ids.length; i++) {
				ss = (SquareProperty)board.getSquareFromBoard(ids[i]); //get SquareProperty object by id
				if(ss.owner == player){
					pr  += ss.buildingPrice;
				}
			}


			if(level== 1 && this.house==1){ //if player has more than 2 properties of same color
				result[0]="1";
				result[1] = "Majority ownership, you must own missing property to build more.";					
			}else if(level != 0 && player.money>=pr){ //if player has more than 2 properties of same color
				result[0]="4";
				result[1] = "Do you want to build "+keyword+" to "+this.name+" ?";
			}else{ //player has less than 2 properties of same color, can't build
				result[0]="1";
				result[1]="";
			}

		}else if(owner != player){	
			if(isMortgaged && player.hasCardWithId(11)){
				return applyCard(player, 11);
			}else if(player.hasCardWithId(34)){
				return applyCard(player, 34);
			}else if(player.hasCardWithId(35)){
				return applyCard(player, 35);
			}
			
			if (player.money < this.rent){
				result[0]="-1";
				result[1] = player.name+" is not able to pay rent. BANKRUPTCY!";
			}else{
				player.substract(this.rent);
				owner.addMoney(this.rent);
				result[0]="1";
				result[1] = player.name+" paid rent to "+owner.name;
				result[player.id+2]=Integer.toString(-1*rent);
				result[owner.id+2]=Integer.toString(rent);
			}	
		}



		return result;
		// TODO Auto-generated method stub

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
	
	public String[] applyCard(Player p,int cardId){
		String[] result = new String[14];
		initializeResult(result);
		
		if(cardId==11){  // buy mortgaged pro
			int unmortgagePrice = (price/2)*(11/10);
			if(p.money>unmortgagePrice){
				p.substract(unmortgagePrice);
				owner.deleteProperty(this);
				p.addProperty(this);
				this.isMortgaged=false;
				p.deleteCard(cardId);
				result[0]="1";
				result[1]="Player used Foreclosed Property Sale card and bought this property.";
				result[p.id+2]="-"+""+unmortgagePrice;
				return result;
			}else{
				result[0]="1";
				result[1]="Player can not use the card. Game moves on.";
				return result;
			}
		}else if(cardId==34){ // 1/2
			if(p.money>rent/2){
				p.substract(rent/2);
				p.deleteCard(cardId);
				result[0]="1";
				result[1]="Player used the card and payed only half of the rent.";
				result[p.id+2]="-"+""+(rent/2);
				return result;
			}else if(p.money+p.valueOfProperties>rent/2){
				result[0]="2";
				result[1]="Player can't pay rent. Player should sell property.";
				return result;
			}else {
				result[0]="-1";
				result[1]="Player can't pay the rent and broke.";
				return result;
			}
			
		}else if(cardId==35){ // reverse
			p.addMoney(rent);
			owner.substract(rent);
			result[0]="1";
			result[1]="Reverse rent, owner pays the rent to the guest.";
			result[p.id+2]=""+rent;
			result[owner.id+2]="-"+""+rent;
			return result;
		}
		return result;
	}
	



	public void initializeAll(){
		this.isMortgaged = false;
		this.setOwner(null);
		this.house=0;
		this.hotel = 0;
		this.skyscraper = 0;
		this.normalizeRent();
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

