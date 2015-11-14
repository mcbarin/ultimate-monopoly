

// Change for active players later.

public class SquareSqueezePlay extends Square {

	public SquareSqueezePlay(String name) {
		super(name, "SquareSqueezePlay", GUI.myWhite);
	}


	public String doAction(Player player, Player players[],int firstDice,int secondDice) {
		int numOfPlayers = players.length;
		int numberOfBroke=0;
		for(int i=0;i<numOfPlayers;i++){
			if(!players[i].getIsPlaying())
				numberOfBroke++;
		}
		Player loser;
		int total = firstDice + secondDice;
		String result = player.getName() + "has won nothing. :(";
		switch (total) {
		case 5: case 6: case 7: case 8: case 9:
			//player.addMoney(50*(numOfPlayers-1));
			int amount = 50*(numOfPlayers-1-numberOfBroke);
			result="Total of two dices: "+total+"\n'"+player.getName() + "' has won "+ "$"+amount + " from other players.";
			for (int i = 0; i < numOfPlayers; i++) {
				loser = players[i];
				if (loser.getId() != player.getId() && loser.getIsPlaying())
					loser.substractMoney(50);
					player.addMoney(50);
			}
			break;
		case 3: case 4: case 10: case 11:
			//player.addMoney(100*(numOfPlayers-1));
			int amount2 = 100*(numOfPlayers-1-numberOfBroke);
			result="Total of two dices: "+total+"\n'"+player.getName() + "' has won "+ "$"+amount2 + " from other players.";
			for (int i = 0; i < numOfPlayers; i++) {
				loser = players[i];
				if (loser.getId() != player.getId() && loser.getIsPlaying())
					loser.substractMoney(100);
					player.addMoney(100);
			}
			break;
		case 2: case 12: 
			//player.addMoney(200*(numOfPlayers-1));
			int amount3 = 200*(numOfPlayers-1-numberOfBroke);
			result="Total of two dices: "+total+"\n'"+player.getName() + "' has won "+ "$"+amount3 + " from other players.";
			for (int i = 0; i < numOfPlayers; i++) {
				loser = players[i];
				if (loser.getId() != player.getId() && loser.getIsPlaying())
					loser.substractMoney(200);
					player.addMoney(200);
			}
			break;
		}
		return result;
	}

}
