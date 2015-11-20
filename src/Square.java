//status=1 just continue
//status=2 player has to sell something + update player's position on screen
//status=3 ask user msg, if says yes, then call buy(Player p, int total)
//status=4 ask user msg, if says yes, then call build(Player p, int total)
//status=5 display msg and update player position on screen
//status=10 ise result[2]'de roll once kartı bulunuyo. Bunu ekrana yansıt ve rollOnce(Player p, int card, int die)'ı çağır.(card kısmına result[2]'yi ver.)
//status=11 or 35 ask user msg, if says yes call applyCard(Square s, Player p, int cardId). cardId=status

public abstract class Square {
	String name,type;
	int id;
	int position;
	int row;


	public Square(String type,String name,int id, int position, int row) {
		this.type=type;
		this.name = name;
		this.id = id;
		this.position = position;
		this.row = row;
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


	public String[] buy(Player p, int total){
		if(this.type.equals("Property")){
			return ((SquareProperty)this).buyProperty(p);
		}else if(this.type.equals("Utility")){
			return ((SquareUtility)this).buyUtility(p);
		}else{
			return ((SquareTransit)this).buyTransit(p,total);
		}

	}

	public String[] build(Player p,int total){
		if(this.type.equals("Property")){
			return ((SquareProperty)this).buildToProperty(p);
		}else{
			return ((SquareTransit)this).buildTrainDepot(p,total);
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

