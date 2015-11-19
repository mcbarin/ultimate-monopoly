
public class SquareTransit extends Square {

	Player owner=null;
	int price=200;
	int rent;
	int originalRent=20;
	int trainDepot = 0;
	int trainDepotPrice =100;
	public SquareTransit(String type,String name, int id, int position, int row) {
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
			if (player.money >= this.price){
				result[0]="3";
				result[1] = "Do you want to buy "+this.name+" ?";
			}else{  //if player has not enough money


			}
		}else if (owner==player && trainDepot==0){
			result[0]="4";
			result[1] = "Do you want to build Train Depot to "+this.name+" ?";

		}else if (owner != player){
			owner.addMoney(rent);
			player.substract(rent);
			result[0]="1";
			result[1] = player.name+" paid rent to "+owner.name+".";
			result[player.id+2]=Integer.toString(-1*rent);
			result[owner.id+2]=Integer.toString(rent);
		}


		//if total is odd change the track.
		if(total%2 == 0){
			if(player.position == 9){
				player.row=1;
				player.position=39-24;
			}else if(player.position == 21){
				player.row=1;
				player.position=59-24;
			}else if(player.position == 35 && player.row==2){
				player.row=1;
				player.position=49-24;
			}else if(player.position == 7){
				player.row=1;
				player.position=29-34;
			}else if(player.position == 15){
				player.row=0;
				player.position=9;
			}else if(player.position == 35){
				player.row=0;
				player.position=21;
			}else if(player.position == 5){
				player.row=2;
				player.position=71-64;
			}else if(player.position == 25){
				player.row=2;
				player.position=99-64;
			}

		}
		return result;

	}

}
