
public class CardChance extends Card {
	int number;
	String description;
	SquaresInfo board;
	
	/*
 	1-    Advance to St. Charles Place
	2-    Advance to Squeeze Play, if you pass “Go”, collect $200 from the bank.
	3-    You are elected as the Chairperson. Pay each player $50.
	4-    Advance to “Go”, collect $200.
	  */
	
	public CardChance(int number,String description){
		this.number = number;
		this.description = description;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	// This should return string.
	public String doAction(Player p, SquaresInfo board){
		this.board = board;
		if(number==1){
			return "Chance1";
			
		}else if(number==2){ // SHOULD RUN THE PLAYERARRIVED METHOD AGAIN
			if(p.getPosition()>15){
				return "Chance2_1";
			}
			else{
				return "Chance2_2";
			}
		}else if(number==3){
			// Player pays each active player 50 $.
			// CHECK FOR ACTIVE PLAYERS
			//########################
			// IMPORTANT
			//########################
			int numOfPlayers = Player.NUMBEROFPLAYERS-1;
			int moneyWillBePayed = numOfPlayers*50; // Ask if there is an inactive player or not later.DON'T FORGET.
			if(p.getMoney()<moneyWillBePayed){
				// Asks player to sell properties.
				// CHECK LATER.
				if(p.getValueOfProperties() + p.getMoney() > moneyWillBePayed){

					// Player's money is payed. Player should sell property. Player's money is negative now.
					// So after the sell, player's money should be checked because it should be positive.
					return "HasToSell";
				}
				else {
					p.broke();
					return "Broke";
				}
			}
			else {
				
				for (int j = 0; j < Player.NUMBEROFPLAYERS; j++) {				

					if (board.getPlayer(j).getIsPlaying()){
						board.getPlayer(j).addMoney(50);
						p.substractMoney(50);
				}}
				((SquareChance)board.getSquare(7)).swipeChanceCards();
				return "Chance3";
				
			}
		}else if(number==4){
			p.setPosition(0);
			p.addMoney(200);
			return "Chance4";
					
		}
		return "Error in CardChance Class."; // Just in case.
	}
	
	
}
