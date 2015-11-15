
public class SquareProperty extends Square  {

	Player owner=null;
	int color;
	int price;
	int originalRent;
	int rent;
	boolean isMortgaged;
	int house;
	int housePrice;
	boolean hotel;
	int hotelPrice;
	boolean skyscraper;
	int skyscraperPrice;

	public SquareProperty(String name, int id, int positionX,
			int positionY, int row, int color, int price, int originalRent) {
		super(name, id, positionX, positionY, row);
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
			}else{
				result[0]="1";
				result[1] = "";	
			}
		}else if(owner == player){
			String keyword = "house";
			if(house==4) keyword="hotel";
			if(hotel) keyword="skyscraper";
			
			result[0]="4";
			result[1] = "Do you want to build "+keyword+" to "+this.name+" ?";
			
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

