
public class SquareSqueezePlay extends Square {

	public SquareSqueezePlay(String name, int id, int positionX, int positionY, int row) {
		super(name, id, positionX, positionY, row);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String[] landOn(Player player, Board board,int total) {
		int numPlayers = board.players.size(); // Number of active players
		String[] result = new String[14];
		initializeResult(result);
		String msg = "";
		Player loser; 
		int amount = 0;
		int loseAmount = 0;
		
		Dice dice = new Dice();
		int die1 = dice.getFace();
		int die2 = dice.getFace();
		int die = die1 + die2;	
		
		switch (die) {
		case 5: case 6: case 7: case 8: case 9:
			loseAmount=50;
			amount = 50*(numPlayers-1);
			msg="Total of two dices: "+die+"\n'"+player.name + "' has won "+ "$"+amount + " from other players.";
			for (int i = 0; i < numPlayers; i++) {
				loser = board.players.get(i);
				if (loser.id != player.id)
					loser.substract(50);
			}
			player.addMoney(amount);
			break;
		case 3: case 4: case 10: case 11:
			loseAmount=100;
			amount = 100*(numPlayers-1);
			msg="Total of two dices: "+die+"\n'"+player.name+ "' has won "+ "$"+amount + " from other players.";
			for (int i = 0; i < numPlayers; i++) {
				loser = board.players.get(i);
				if (loser.id != player.id)
					loser.substract(100);
			}
			player.addMoney(amount);
			break;
		case 2: case 12: 
			loseAmount=200;
			amount = 200*(numPlayers-1);
			msg="Total of two dices: "+die+"\n'"+player.name + "' has won "+ "$"+amount + " from other players.";
			for (int i = 0; i < numPlayers; i++) {
				loser = board.players.get(i);
				if (loser.id != player.id)
					loser.substract(200);
			}
			player.addMoney(amount);
			break;
		}
		result[0]="1";
		result[1]=msg;
		result[player.id+2] = Integer.toString(amount);
		for (int i = 2; i < numPlayers+2; i++) {
			if(i != player.id)
				result[i] = Integer.toString(-1 *loseAmount);
		}
		return result;
	}
		
}

