
public class SquareTransit extends Square {

	Player owner=null;
	int price=200;
	int rent=20;
	int originalRent=20;
	int trainDepot = 0;
	int trainDepotPrice =100;
	SquareTransit twin; //when selling you must initialize twin too.

	public SquareTransit(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
	}

	public void SquareTransitTwin(int id, Board board ){
		if (this.id == 29)
			twin=(SquareTransit) board.getSquareFromBoard(71);
		if (this.id == 71)
			twin=(SquareTransit) board.getSquareFromBoard(29);
		if (this.id == 49)
			twin=(SquareTransit) board.getSquareFromBoard(99);
		if (this.id == 99)
			twin=(SquareTransit) board.getSquareFromBoard(49);
		if (this.id == 9)
			twin=(SquareTransit) board.getSquareFromBoard(39);
		if (this.id == 39)
			twin=(SquareTransit) board.getSquareFromBoard(9);
		if (this.id == 59)
			twin=(SquareTransit) board.getSquareFromBoard(21);
		if (this.id == 21)
			twin=(SquareTransit) board.getSquareFromBoard(59);

	}

	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="1";
		result[1] = "";	

		if(owner == null){
			if (player.money >= this.price){
				result[0]="3";
				result[1] = "Do you want to buy "+this.name+" ?";
				return result;
			}else{  //if player has not enough money


			}
		}else if (owner==player && trainDepot==0){ //else trainDepot==1 do nothing
			result[0]="4";
			result[1] = "Do you want to build Train Depot to "+this.name+" ?";
			return result;

		}else if (owner != player){

			if(player.hasCardWithId(34)) {
				if(player.money>rent/2){
					player.substract(rent/2);
					player.deleteCard(34);
					result[0]="1";
					result[1] = "You have '"+board.cardDescriptions[34][0]+"' card and you are using half rent.";
					result[player.id+2]="-"+""+(rent/2);
					return result;
				}else if(player.money+player.valueOfProperties>rent/2){
					result[0]="2";
					result[1]="Player can't pay rent. Player should sell property.";
					return result;
				}else {
					result[0]="-1";
					result[1]="Player can't pay the rent and broke.";
					return result;
				}
			}


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
				return result;
			}else{
				result[0]="-1";
				result[1]="Player can't pay the rent and broke.";
				return result;
			}
		}


		result[0]=checkEven(player, total);

		return result;

	}

	public String checkEven(Player player, int total){
		//if total is even change the track.
		if(total%2 == 0){
			if(this.id == 9){
				player.row=1;
				player.position=39-24;
			}else if(this.id == 21){
				player.row=1;
				player.position=59-24;
			}else if(this.id == 99){
				player.row=1;
				player.position=49-24;
			}else if(this.id == 71){
				player.row=1;
				player.position=29-34;
			}else if(this.id == 39){
				player.row=0;
				player.position=9;
			}else if(this.id == 59){
				player.row=0;
				player.position=21;
			}else if(this.id == 29){
				player.row=2;
				player.position=71-64;
			}else if(this.id== 49){
				player.row=2;
				player.position=99-64;
			}
			return "5";
		}
		return "1";
	}



	public String[] buyTransit(Player player,int total){
		String[] result = new String[14];
		initializeResult(result);
		player.substract(price);
		owner = player;
		twin.owner = player;
		player.valueOfProperties +=price/2;
		player.trains.add(this);
		player.trains.add(this.twin);
		result[0]="1"; // Success
		result[1] = player.name + " has bought the " + ""+name+".";
		result[player.id+2] = "-"+ ""+price;

		result[0]=checkEven(player, total);
		return result;
	}


	public String[] buildTrainDepot(Player player, int total){
		String[] result = new String[14];
		initializeResult(result);
		trainDepot=1;
		twin.trainDepot=1;
		rent=originalRent*2;
		twin.rent=rent;
		player.substract(trainDepotPrice);
		player.valueOfProperties+=trainDepotPrice/2;
		result[0]="1"; // Success
		result[1] = player.name + " built Train Depot to " + ""+name+".";
		result[player.id+2] = "-"+ ""+price;

		result[0]=checkEven(player, total);

		return result;
	}
}
