

public class CardCommunity extends Card {
	int number;
	String title;
	String description;
	Board board;

	/**
	 * This method is the Constructor method for the CardCommunity class. 
	 * It takes the number of the card, title, description and board as an input.
	 * @param number
	 * @param title
	 * @param description
	 * @param board
	 * @requires number must be between 0 and 35.
	 * @modifies number,title,description,board
	 * @effects The new CardCommunity object with given parameters is created.
	 */
	public CardCommunity(int number,String title,String description,Board board){
		super("Community",number);
		this.number = number;
		this.title = title;
		this.description = description;
		this.board = board;
	}
	
	/**
	 * This method returns the number of the card.
	 * @requires There is no requirement for this method.
	 * @modifies This method does not modifies anything.
	 * @effects number of the card is returned to the user.
	 * @return number
	 */
	public int getNumber() {
		return number;
	}

	
	@Override
	public String toString() {
		return "CardCommunity [number=" + number + ", title=" + title + ", description=" + description + "]";
	}

	/**
	 * This method returns an empty String array.
	 * @requires There is no requirement for this method.
	 * @modifies This method does not modifies anything.
	 * @effects String array with the length of 14 is given to the user. All indexes are initialized to 0.
	 * @return It returns a string array which all elements are zero.
	 */
	public String[] getResultArray(){
		String[] result = new String[14];
		for(int i=0;i<14;i++){
			result[i]= "0";
		}
		return result;
	}
	
	/**
	 * This method takes the player as a parameter and does the action of the card to the player.
	 * After executing the card, it creates the result array and returns it to the MonopolyGame class.
	 * @param p
	 * @requires Player p must be an active player.
	 * @modifies It modifies the result array. Also depends on the card, player's some fields might be modified after the action.
	 * @effects Action of the card is applied to the player.
	 * @return result[]
	 */
	public String[] doAction(Player p){
		String[] result= getResultArray();
		
		if(number == 36){//**
			
			p.addMoney(100);
			result[0]= "1";
			result[1] = "Player inherit $100. Player collected $100 from the bank.";
			result[p.id+2] = "100";
			board.pullPushCommunity();
	
		} 
		else if(number == 37){//**
			for(int i=0;i<board.players.size();i++){
				if(p.id != board.players.get(i).id){
					p.addMoney(10);
					board.players.get(i).substract(10);
					result[board.players.get(i).id+2]="-10";
				}
			}
			int amount = board.players.size()-1;
			amount*=10;
			result[p.id+2]=""+amount;
			p.row=2;
			p.position=51;
			result[0]="5";
			result[1]="Player collected 10$ from each player and moved to the birthday gift space.";
			
			board.pullPushCommunity();
			
		}  else if(number == 38){
			
		}else if(number == 39){//**
			
			if(p.money > 50){
				
				board.payToPool(50);
				p.substract(50);
				result[0]="1";
				result[1]= "Player has paid $50 to the pool.";
				result[p.id+2]="-50";
				board.pullPushCommunity();
			} else if(p.money+p.valueOfProperties>50){
				result[0]= "2";
				result[1] = "Player has to sell a property.";
			}else{
				result[0]="-1";
				result[1]= "Player is broke";
				board.pullPushCommunity();
			}
			
		}  else if(number == 40){//**
			result[0]="26"; // Player should choose that 1. player goes to the income tax or player goes to jail.
							// only two options. than applyCard29 should be called.
							// argument boolean, if true income tax, if false jail.
			result[1]="Player should move to income tax or jail.";
			board.pullPushCommunity();
			
		}  else if(number == 41){//** AUCTION EKLENECEK
			p.row = 2;
			p.position=6;
			result[0]="5";
			result[1]="Player moved to the Checker Cab Company.";
			board.pullPushCommunity();
			
		}  else if(number == 42){
			
		
		}  else if(number == 43){//**
			
			int amount = 0;
			amount += 25*(p.numberOfCabStand+p.numberOfTransitStation);
			amount += 40*(p.numberOfHouses);
			amount += 100*(p.numberOfSkyscrapers);
			amount += 115*(p.numberOfHotels);

			if(p.money > amount){
				
				p.substract(amount);
				result[0]="1";
				result[1]="Player has paid $"+""+amount+" for making general repair for the properties.";
				result[p.id+2]="-"+""+amount;
				board.pullPushCommunity();
			}
			else if(p.money+p.valueOfProperties>amount){
				result[0]= "2";
				result[1] = "Player has to sell a property.";
			}else{
				result[0]="-1";
				result[1]= "Player is broke";
				board.pullPushCommunity();
			}
			
		} else if(number == 44){//**
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
			board.pullPushCommunity();
		}  else if(number == 45){//**
			p.row = 1;
			p.position = 10;
			result[0]="5";
			result[1]="Player went directly to jail.";
			board.pullPushCommunity();
		} else if(number == 46){
			
			
		}  else if(number == 47){
			
			
		} else if(number == 48){
	
		}  else if(number == 49){//**
			//hurricane to player's own properties.
			// player should choose a color group.
			result[0]="27";
			result[1]="PLayer should choose one color group of its own and it will be downgraded.";
			// applyCard40 should be called with color code (0-19)
			
		}  
		return result;
	}
	
	public String[] applyCard40(boolean choice,Player p){
		String[] result= getResultArray();
		
		if(choice){
			p.row = 1;
			p.position = 4;
			result[0]="5";
			result[1]="PLayer went to the income tax.";
		}else{
			p.row = 1;
			p.position = 10;
			result[0]="5";
			result[1]="Player went directly to jail.";
		}
		board.pullPushCommunity();
		return result;
	}
	
	public String[] applyCard49(Player p,int colorGroup){
		String[] result= getResultArray();
		int length = board.getNumberOfSameColor(colorGroup);
		int[] otherHouses = board.getOtherProperties(colorGroup);
		
		for(int i=0;i<length;i++){
			SquareProperty sp = (SquareProperty)board.getSquareFromBoard(otherHouses[i]);
			if(sp.owner != null && sp.owner.id == p.id){
				sp.hurricane(p);
			}
		}
		result[0]="1";
		result[1]="Properties of the chosen color group are downgraded.";
		board.pullPushCommunity();
		return result;
	}
	
}
