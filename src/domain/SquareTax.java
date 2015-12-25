package domain;

public class SquareTax extends Square {

	public SquareTax(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="1";
		int amount=0;

		if (id == 28){ //INCOME TAX
			if((player.valueOfProperties + player.money)/10 < 200) 
				amount = (player.valueOfProperties + player.money)/10;//pay % 10 of assets
			amount=200; //or $200

			player.substract(amount);
			board.pool +=amount;
			result[1] =player.name+" paid $"+amount+" as Income Tax.";

		}else if (this.id == 62){ //LUXUARY TAX
			amount=750;
			player.substract(amount);
			board.pool +=amount;
			result[1] =player.name+" paid $"+amount+" as Luxuary Tax.";
		}

		result[player.id+2]=Integer.toString(-1*amount);
		return result;

	}
}
