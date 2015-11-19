//status=1 just continue
//status=3 ask user msg, if says yes, then call buy(Square s,Player p)
//status=4 ask user msg, if says yes, then call build(Square s,Player p)

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


	public String[] buy(Square s,Player p){
		if(s.type.equals("Property")){
			return buyProperty((SquareProperty)s, p);
		}else if(s.type.equals("Utility")){
			return buyUtility((SquareUtility)s, p);
		}else{
			return buyTransit((SquareTransit)s, p);
		}

	}

	public String[] build(Square s,Player p){
		if(s.type.equals("Property")){
			return buildToProperty((SquareProperty)s, p);
		}else{
			return buildTrainDepot((SquareTransit)s, p);
		}

	}

	public String[] buyTransit(SquareTransit s,Player p){
		String[] result = new String[14];
		initializeResult(result);
		p.substract(s.price);
		s.owner = p;

		result[0]="1"; // Success
		result[1] = p.name + " has bought the " + ""+s.name+".";
		result[p.id+2] = "-"+ ""+s.price;
		return result;
	}

	public String[] buyUtility(SquareUtility s,Player p){
		String[] result = new String[14];
		initializeResult(result);
		p.utilities.add(s);
		p.substract(s.price);
		s.owner = p;

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

	public String[] buildTrainDepot(SquareTransit s,Player p){
		String[] result = new String[14];
		initializeResult(result);
		s.trainDepot=1;
		s.rent=s.originalRent*2;
		p.substract(s.trainDepotPrice);
		result[0]="1"; // Success
		result[1] = p.name + " built Train Depot to " + ""+s.name+".";
		result[p.id+2] = "-"+ ""+s.price;
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
					result[1] = result[1] + ss.name + " ";
					flag = false;
				}
			}

			if (flag){
				for (int i = 0; i < ids.length; i++) {
					ss = (SquareProperty)p.board.getSquareFromBoard(ids[i]); //get SquareProperty object by id
					pr = pr + ss.buildingPrice;
					updateRentAccordingToHouse(ss);
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
		if(ss.house == 0){
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
		}else if(ss.house == 4 && ss.hotel==0){
			ss.rent = ss.originalRent*50;
			ss.hotel = 1;
		}else if(ss.house == 4 && ss.hotel==1){
			ss.rent = ss.originalRent*85;
			ss.skyscraper = 1;
		}
	}
}

