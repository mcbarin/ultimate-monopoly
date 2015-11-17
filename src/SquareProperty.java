
public class SquareProperty extends Square  {

	Player owner=null;
	int color;
	int price;
	int originalRent;
	int rent;
	boolean isMortgaged;
	int house;
	int housePrice;
	int hotel;
	int hotelPrice;
	int skyscraper;
	int skyscraperPrice;
	int level=0; //1 majority 2 monopoly

	public SquareProperty(String type,String name, int id, int position, int row, int color, int price, int originalRent) {
		super(type,name, id, position, row);
		this.color = color;
		this.price = price;
		this.originalRent = originalRent;
		rent = originalRent;
	}

	@Override
	public String[] landOn(Player player, Board board , int total) {
		String[] result = new String[14];
		initializeResult(result);

		if(owner == null){
			if (player.money >= this.price){
				result[0]="3";
				result[1] = "Do you want to buy "+this.name+" ?";
			}else{  //if player has not enough money
				result[0]="1";
				result[1] = "";	
			}
			
		}else if(owner == player){
			String keyword = "house";
			if(house==4) keyword="hotel";
			if(hotel==1) keyword="skyscraper";
			int pr = 0;
			int ids[] = board.getOtherProperties(this.color); //get ids of properties of specific color
			for (int i = 0; i < ids.length; i++) {
				SquareProperty ss = (SquareProperty)board.getSquareFromBoard(ids[i]); //get SquareProperty object by id
				if(ss.owner == player){
					if (ss.house == 4) pr += ss.hotelPrice;
					else if (ss.hotel == 1) pr += ss.skyscraperPrice;
					else pr  += ss.housePrice;
				}
			}
			
			int props = board.getNumberOfSameColor(this.color); 
			if (props <= 2){ //if 2 properties of same color can't build house
				result[0]="1";
				result[1]="";
			}else if(level!= 0 && player.money>=pr){ //if player has more than 2 properties of same color
				result[0]="4";
				result[1] = "Do you want to build "+keyword+" to "+this.name+" ?";
			}else{ //player has less than 2 properties of same color, can't build
				result[0]="1";
				result[1]="";
			}

		}else if(owner != player){	
			if (player.money < this.rent){
				result[0]="-1";
				result[1] = "'"+player.name+"' is not able to pay rent. BANKRUPTCY!";
			}else{
				player.substract(this.rent);
				owner.addMoney(this.rent);
				result[0]="1";
				result[1] = "'"+player.name+"' paid rent.";
				result[player.id+2]=Integer.toString(-1*rent);
			}	
		}



		return result;
		// TODO Auto-generated method stub

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

