package domain;

public class SquareUtility extends Square {

	int price=150;
	int rent;
	boolean isMortgaged=false;

	public SquareUtility(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);

		if(owner == null){
			if (player.money >= this.price){
				result[0]="3";
				result[1] = "Do you want to buy "+this.name+" for $"+price+"?";
			}else{  //if player has not enough money
				result[0]="1";
				result[1] = "";	
			}
		}else if (owner.id==player.id){

			result[0]="1";
			result[1] = "";	

		}else if (owner.id != player.id){
			if (isMortgaged){
				result[0]="1";
				result[1] = "This utility is mortgaged, don't pay rent.";
				return result;
			}

			if(owner.utilities.size() == 1)
				rent = total*4;
			if(owner.utilities.size() == 2)
				rent = total*10;
			if(owner.utilities.size() == 3)
				rent = total*20;
			if(owner.utilities.size() == 4)
				rent = total*40;
			if(owner.utilities.size() == 5)
				rent = total*80;
			if(owner.utilities.size() == 6)
				rent = total*100;
			if(owner.utilities.size() == 7)
				rent = total*120;
			if(owner.utilities.size() == 8)
				rent = total*150;

			if(player.money>=rent){
				owner.addMoney(rent);
				player.substract(rent);
				result[0]="1";
				result[1] = player.name+" paid rent to "+owner.name+".";
				result[player.id+2]=Integer.toString(-1*rent);
				result[owner.id+2]=Integer.toString(rent);
			}else if(player.money+player.valueOfProperties>rent){
				result[0]="2";
				result[1]="Player can't pay rent. Player should sell property.";
			}else{
				result[0]="-1";
				result[1]="Player can't pay the rent and broke.";
			}
		}
		player.setFreeProperties();
		return result;
		// TODO Auto-generated method stub

	}

	public String[] buyUtility(Player p){
		String[] result = new String[14];
		initializeResult(result);
		p.utilities.add(this);
		p.substract(this.price);
		this.owner = p;
		p.valueOfProperties += this.price/2;

		result[0]="1"; // Success
		result[1] = p.name + " has bought the " + ""+this.name+".";
		result[p.id+2] = "-"+ ""+this.price;
		p.setFreeProperties();
		return result;
	}


	public void initializeAll(){
		this.owner=null;
		this.price=150;
		this.rent = 0;
		this.isMortgaged=false;
	}
	
	

	public String[] sellUtility(Player p){
		String[] result = new String[14];
		initializeResult(result);
		result[0]="0";
		
		if(owner.id != p.id){
			result[1]="This utility doesn't belong to you!";
			return result;
		}else if (isMortgaged){
			result[1]="This utility is mortgaged already";
			return result;
		}else{
			
			p.addMoney(price/2);
			result[1]=p.name+" sold "+ this.name+" for $"+price/2;
			result[p.id+2]=Integer.toString(price/2);
			p.utilities.remove(this);
			initializeAll();
		}
		p.setFreeProperties();
		return result;
	}

}
