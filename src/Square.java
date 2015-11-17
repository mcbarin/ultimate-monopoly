
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




	public String[] buyProperty(SquareProperty s,Player p){
		String[] result = new String[14];
		initializeResult(result);
		p.substract(s.price);
		p.addProperty(s);
		result[0]="1"; // Success
		result[1] = name + " has bought the " + ""+s.name+".";
		result[this.id+2] = "-"+ ""+s.price;
		return result;
	}


	public String[] build(SquareProperty s,Player p){
		String[] result = new String[14];
		initializeResult(result);
		int pr = 0;
		
		if(s.house<4){
			result[1]="'"+p.name+"' built house to ";
			int ids[] = p.board.getOtherProperties(s.color); //get ids of properties of specific color
			for (int i = 0; i < ids.length; i++) {
				SquareProperty ss = (SquareProperty)p.board.getSquareFromBoard(ids[i]); //get SquareProperty object by id
				if(ss.owner == p){
					ss.house = ss.house + 1; //build house to all owned properties
					pr = pr + ss.housePrice;
					updateRentAccordingToHouse(ss,ss.house);
					result[1] = result[1] + ss.name + ", ";
				}
			}
			result[0]="1";
			result[1] = result[1] + "and lost $" +pr;
			result[p.id+2] = Integer.toString(pr);

		}else if(s.house==4){
			
		}


		return result;
	}


	
	public void updateRentAccordingToHouse(SquareProperty ss, int house){
		
		
		
	}
}

