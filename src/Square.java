//status=1 just continue
//status=3 ask user msg, if says yes, then call buy(Square s,Player p, int total)
//status=4 ask user msg, if says yes, then call build(Square s,Player p, int total)
//status=5 display msg and update player position on screen
//status=10 ise result[2]'de roll once kartı bulunuyo. Bunu ekrana yansıt ve rollOnce(Player p, int card, int die)'ı çağır.(card kısmına result[2]'yi ver.)
//status=11 or 35 ask user msg, if says yes call applyCard(Square s, Player p, int cardId). cardId=status

public abstract class Square {
	String name,type;
	int id;
	int position;
	int row;
	Square next;
	Square prev;
	Square tranNext;


	public Square(String type,String name,int id, int position, int row) {
		this.type=type;
		this.name = name;
		this.id = id;
		this.position = position;
		this.row = row;
		//this.next = next;
		//this.prev = prev;
		//this.tranNext = tranNext;
	}

	public abstract String[] landOn(Player player, Board board, int total);

	public void initializeResult(String[] result){
		for (int i = 0; i < result.length; i++) {
			result[i]= "0";
		}

	}


	public String[] applyCard(Square s, Player p,int cardId){
		String[] result = new String[14];
		initializeResult(result);

		if(cardId==11){  // buy mortgaged pro
			int unmortgagePrice = (((SquareProperty)s).price/2)*(11/10);
			p.substract(unmortgagePrice);
			((SquareProperty)s).owner.deleteProperty(((SquareProperty)s));
			p.addProperty(((SquareProperty)s));
			((SquareProperty)s).isMortgaged=false;
			p.deleteCard(cardId);
			result[0]="1";
			result[1]="Player used Foreclosed Property Sale card and bought this property.";
			result[p.id+2]="-"+""+unmortgagePrice;
			return result;


		}else if(cardId==35){ // reverse
			p.addMoney(((SquareProperty)s).rent);
			((SquareProperty)s).owner.substract(((SquareProperty)s).rent);
			result[0]="1";
			result[1]="Reverse rent, owner pays the rent to the guest.";
			result[p.id+2]=""+((SquareProperty)s).rent;
			result[((SquareProperty)s).owner.id+2]="-"+""+((SquareProperty)s).rent;
			return result;
		}
		return result;
	}


	public String[] buy(Square s,Player p, int total){
		if(s.type.equals("Property")){
			return buyProperty((SquareProperty)s, p);
		}else if(s.type.equals("Utility")){
			return buyUtility((SquareUtility)s, p);
		}else{
			return buyTransit((SquareTransit)s, p,total);
		}

	}

	public String[] build(Square s,Player p,int total){
		if(s.type.equals("Property")){
			return buildToProperty((SquareProperty)s, p);
		}else{
			return buildTrainDepot((SquareTransit)s, p,total);
		}

	}

	public String[] buyTransit(SquareTransit s,Player player,int total){
		String[] result = new String[14];
		initializeResult(result);
		player.substract(s.price);
		s.owner = player;
		player.valueOfProperties +=s.price/2;
		result[0]="1"; // Success
		result[1] = player.name + " has bought the " + ""+s.name+".";
		result[player.id+2] = "-"+ ""+s.price;

		if(total%2 == 0){
			result[0]="5";
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

	public String[] buyUtility(SquareUtility s,Player p){
		String[] result = new String[14];
		initializeResult(result);
		p.utilities.add(s);
		p.substract(s.price);
		s.owner = p;
		p.valueOfProperties += s.price/2;

		result[0]="1"; // Success
		result[1] = p.name + " has bought the " + ""+s.name+".";
		result[p.id+2] = "-"+ ""+s.price;
		return result;
	}


	public String[] buyProperty(SquareProperty s,Player p){
		String[] result = new String[14];
		initializeResult(result);
		p.substract(s.price);
		p.addProperty(s);
		result[0]="1"; // Success
		result[1] = p.name + " has bought the " + ""+s.name+".";
		result[p.id+2] = "-"+ ""+s.price;
		return result;
	}

	public String[] buildTrainDepot(SquareTransit s,Player player, int total){
		String[] result = new String[14];
		initializeResult(result);
		s.trainDepot=1;
		s.rent=s.originalRent*2;
		player.substract(s.trainDepotPrice);
		player.valueOfProperties+=s.trainDepotPrice/2;
		result[0]="1"; // Success
		result[1] = player.name + " built Train Depot to " + ""+s.name+".";
		result[player.id+2] = "-"+ ""+s.price;

		if(total%2 == 0){
			result[0]="5";
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

	public String[] buildToProperty(SquareProperty s,Player p){
		String[] result = new String[14];
		initializeResult(result);
		int pr = 0;
		boolean flag = true;
		SquareProperty ss = null;

		if(s.level == 1){ //majority ownership, build only one house 
			result[1]="'"+p.name+"' built house to ";
			int ids[] = p.board.getOtherProperties(s.color); //get ids of properties of specific color
			for (int i = 0; i < ids.length; i++) {
				ss = (SquareProperty)p.board.getSquareFromBoard(ids[i]); //get SquareProperty object by id
				if(ss.owner == p){
					ss.house = 1; //build house to all owned properties
					pr = pr + ss.buildingPrice;
					ss.rent = ss.originalRent * 5;
					p.valueOfProperties+=ss.buildingPrice/2;
					result[1] = result[1] + ss.name + ", ";
				}
			}
			result[0]="1";
			result[1] = result[1] + "and lost $" +pr;
			result[p.id+2] = Integer.toString(pr);

		}else if (s.level == 2) {//monopoly

			result[1]="'"+p.name+"' built house to ";
			int ids[] = p.board.getOtherProperties(s.color); //get ids of properties of specific color
			for (int i = 0; i < ids.length; i++) {
				ss = (SquareProperty)p.board.getSquareFromBoard(ids[i]); //get SquareProperty object by id
				if(ss.house == 0){
					ss.rent = ss.originalRent*5;
					ss.house = 1; 
					pr = ss.buildingPrice;
					p.valueOfProperties+=ss.buildingPrice/2;
					result[1] = result[1] + ss.name + " ";
					flag = false;
				}
			}

			if (flag){
				for (int i = 0; i < ids.length; i++) {
					ss = (SquareProperty)p.board.getSquareFromBoard(ids[i]); //get SquareProperty object by id
					pr = pr + ss.buildingPrice;
					updateRentAccordingToHouse(ss);
					p.valueOfProperties+=ss.buildingPrice/2;
					result[1] = result[1] + ss.name + ", ";
				}
			}
			result[0]="1";
			result[1] = result[1] + "and lost $" +pr;
			result[p.id+2] = Integer.toString(pr);

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

	public String[] rollOnce(Player p, int card, int die){
		String[] result = new String[14];
		initializeResult(result);
		result[0]="1";
		if(card==die){
			result[1]=p.name+" won Roll Once Game and took $100";
			p.addMoney(100);
			result[p.id+2]="100";
		}else{
			result[1]=p.name+" lost Roll Once Game.";
		}

		return result;
	}

}

