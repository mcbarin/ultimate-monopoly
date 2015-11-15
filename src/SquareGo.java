
public class SquareGo extends Square {

	public SquareGo(String name, int id, int positionX, int positionY, int row) {
		super(name, id, positionX, positionY, row);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String[] landOn(Player player, Board board, int total) {

		int amount = 300;
		String msg = "'"+player.name + "' has won "+ "$"+amount;

		if(this.name.equals("GO")){
			player.addMoney(amount);
		}else if(this.name.equals("BONUS")){
			amount=50;
			player.addMoney(amount);
		}else if(this.name.equals("PAY DAY")){
			if(total % 2 == 1){
				player.addMoney(amount);
			}else{
				amount = 400;
				player.addMoney(amount);	
			}
		}else if(this.name.equals("BIRTHDAY GIFT")){
			amount = 100;
			msg = "'"+player.name + "' has won "+ "$"+amount+" as birthday gift";
			player.addMoney(amount);
		}


		String[] result = new String[14];
		initializeResult(result);

		result[0]="1";
		result[1]=msg;
		result[player.id+2] = Integer.toString(amount);

		return result;


	}



}
