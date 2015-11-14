
public class SquareGo extends Square {

	public SquareGo(String name, int id, Square next, Square prev, Square tranNext, int positionX,
			int positionY, int row) {
		super(name, id, next, prev, tranNext, positionX, positionY, row);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String[] landOn(Player player, Board board, int total) {
		
		int amount = 300;
		
		if(this.name.equals("GO")){
			player.addMoney(amount);
		}else if(this.name.equals("BONUS")){
			player.addMoney(amount);
		}else if(this.name.equals("PAY DAY")){
			if(total % 2 == 1){
			player.addMoney(amount);
			}else{
				amount = 400;
			player.addMoney(amount);	
			}
		}
		
		
		int numPlayers = board.players.size();
		String[] result = new String[numPlayers+2];
		String msg = "'"+player.name + "' has won "+ "$"+amount;
		
		result[0]="0";
		result[1]=msg;
		result[player.id] = Integer.toString(amount);
		for (int i = 2; i < result.length; i++) {
			if(i != player.id)
				result[i] = "0";
		}
		return result;
		
		
	}
	


}
