
public class SquareCabCompany extends Square {

	Player owner=null;
	int price=300;
	int rent=30;
	int originalRent=30;
	int cabStand = 0;
	int cabStandPrice =150;
	boolean isMortgaged=false;

	public SquareCabCompany(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="1";
		result[1] = "";	

		if(owner == null){
			if (player.money >= this.price){ //else if player has not enough money, do nothing
				result[0]="3";
				result[1] = "Do you want to buy "+this.name+" ?";
			}
			
		}else if (owner==player){
			if(isMortgaged){
				result[0]="1";
				result[1] = "This cab company is mortgaged, can't build .";
				if(owner.money>=20){
					result[0]="9";
					result[1] += "Do you want to take a Taxi Ride for $20 ?";
				}
			}else if (cabStand==0 && owner.money>=cabStandPrice){
				result[0]="4";
				result[1] = "Do you want to build Cab Stand to "+this.name+" ?";
			}else if (cabStand==1 && owner.money>=20){ 
				result[0]="9";
				result[1] = "Do you want to take a Taxi Ride for $20 ?";
			}
			
		}else if (owner != player){
			if (isMortgaged){
				result[0]="1";
				result[1] = "This property is mortgaged, don't pay rent.";
				if (player.money >= 50){
					result[0]="9";
					result[1] += " Do you want to take a Taxi Ride for $50 ?";
				}
				return result;
			}

			int size = owner.cabs.size()-1;
			if(player.money>=rent*(2^size)){
				owner.addMoney(rent*(2^size));
				player.substract(rent*(2^size));
				result[0]="1";
				result[1] = player.name + " paid rent.";
				result[player.id+2]=Integer.toString(-1*rent*(2^size));
				result[owner.id+2]=Integer.toString(rent*(2^size));

				if (player.money >= 50){
					result[0]="9"; // Success
					result[1] += " Do you want to take a Taxi Ride for $50 ?";
				}

			}else if(player.money+player.valueOfProperties>rent){
				result[0]="2";
				result[1]="Player can't pay rent. Player should sell property.";
			}else{
				result[0]="-1";
				result[1]="Player can't pay the rent and broke.";
			}


		}

		return result;
	}

	public void initializeAll(){
		this.owner = null;
		this.price=300;
		this.rent=30;
		this.originalRent=30;
		this.cabStand = 0;
		this.cabStandPrice =150;
		this.isMortgaged=false;
	}


	public String[] buyCabCompany(Player p){
		String[] result = new String[14];
		initializeResult(result);
		p.cabs.add(this);
		p.substract(this.price);
		this.owner = p;
		p.valueOfProperties += this.price/2;
		result[0]="1";
		result[1] = p.name + " has bought the " + ""+this.name+".";
		result[p.id+2] = "-"+ ""+this.price;

		if (p.money >= 20){
			result[0]="9"; // Success
			result[1] += " Do you want to take a Taxi Ride for $20 ?";
		}
		return result;
	}

	public String[] buildCabStand(Player player){
		String[] result = new String[14];
		initializeResult(result);
		cabStand=1;
		rent=rent*2;
		player.substract(cabStandPrice);
		player.valueOfProperties+=cabStandPrice/2;
		result[0]="1"; // Success
		result[1] = player.name + " built Cab Stand to " + ""+name+".";
		result[player.id+2] = "-"+ ""+cabStandPrice;

		if (player.money >= 20){
			result[0]="9"; 
			result[1] += " Do you want to take a Taxi Ride for $20 ?";
		}
		return result;
	}

	public String[] sellCabStand(Player p){
		String[] result = new String[14];
		initializeResult(result);
		result[0]="0";

		if(owner != p){
			result[1]="This cab stand doesn't belong to you!";
			return result;
		}else if (isMortgaged){
			result[1]="This cab stand is mortgaged already";
			return result;
		}else if(cabStand==1){
			cabStand=0;
			result[1]=p.name+" sold cabStand for $75";
			p.addMoney(75);
			rent=originalRent;
			result[p.id+2]="75";
		}else if (cabStand==0){
			result[1]="You don't have any cabStand here";
		}

		return result;
	}

	public String[] sellCabCompany(Player p){
		String[] result = new String[14];
		initializeResult(result);
		result[0]="0";
		if(owner != p){
			result[1]="This cab stand doesn't belong to you!";
			return result;
		}else if (isMortgaged){
			result[1]="This cab stand is mortgaged already";
			return result;
		}else if(cabStand==1){
			result[1]="You should sell Cab Stand first.";
			return result;
		}else if(cabStand==0){
			initializeAll();
			p.addMoney(price/2);
			result[1]=p.name+" sold "+ this.name+" for $"+price/2;
			result[p.id+2]=Integer.toString(price/2);
			p.cabs.remove(this);
		}
		return result;
	}

}
