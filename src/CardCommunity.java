

public class CardCommunity extends Card {
	int number;
	String title;
	String description;
	Board board;

	public CardCommunity(int number,String title,String description,Board board){
		super("Community",number);
		this.number = number;
		this.title = title;
		this.description = description;
		this.board = board;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	public String[] getResultArray(){
		String[] result = new String[14];
		for(int i=0;i<14;i++){
			result[i]= "0";
		}
		return result;
	}
	
	public String[] doAction(Player p){
		String[] result= getResultArray();
		
		if(number == 26){
			
			p.addMoney(100);
			result[0]= "1";
			result[1] = "Player inherit $100. Player collected $100 from the bank.";
			result[p.id+2] = "100";
	
		} 
		else if(number == 27){
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
			result[1]="Player collected 10$ from each player and moved to the birthdat gift space.";
			
			
			
		}  else if(number == 28){
			
			if(p.money > 50){
				
				board.payToPool(50);
				p.substract(50);
				result[0]="1";
				result[1]= "Player has paid $50 to the pool.";
				result[p.id+2]="-50";
			} else if(p.money+p.valueOfProperties>50){
				result[0]= "2";
				result[1] = "Player has to sell a property.";
			}else{
				result[0]="-1";
				result[1]= "Player is broke";
			}
			
		}  else if(number == 29){
			result[0]="26"; // Player should choose that 1. player goes to the income tax or player goes to jail.
							// only two options. than applyCard29 should be called.
							// argument boolean, if true income tax, if false jail.
			result[1]="Player should move to income tax or jail.";
			
		}  else if(number == 30){
			p.row = 2;
			p.position=6;
			result[0]="5";
			result[1]="Player moved to the Checker Cab Company.";
			
			
		}  else if(number == 31){
			//Move directly to the space that 1 track below this one. If row==2 do nothing
			int pRow=p.row;
			int pPos=p.position;
			int squareId = pPos;
			if(pRow==0){
			}else if(pRow==1){
				squareId+=24;
			}else if(pRow==2){
				squareId+=64;
			}
			
			if(pRow==2){
				result[0]="1";
				result[1]="Player is on the Outer Track, do nothing.";
			}else if(pRow==1){
				p.row=2;
				p.position=((SquareCommunity)board.getSquareFromBoard(squareId)).downPos;
				result[0]="5";
				result[1]="Player moved to  1 track below.";
			}else if(pRow==0){
				p.row=1;
				p.position=((SquareCommunity)board.getSquareFromBoard(squareId)).downPos;
				result[0]="5";
				result[1]="Player moved to  1 track below.";
			}
		
		}  else if(number == 32){
			//Move directly to the space that 1 track above this one. If row==0 do nothing
			int pRow=p.row;
			int pPos=p.position;
			int squareId = pPos;
			if(pRow==0){
				
			}else if(pRow==1){
				squareId+=24;
			}else if(pRow==2){
				squareId+=64;
			}
			
			if(pRow==0){
				result[0]="1";
				result[1]="Player is on the Inner Track, do nothing.";
			}else if(pRow==1){
				p.row=0;
				p.position=((SquareCommunity)board.getSquareFromBoard(squareId)).upPos;
				result[0]="5";
				result[1]="Player moved to  1 track above.";
			}else if(pRow==2){
				p.row=1;
				p.position=((SquareCommunity)board.getSquareFromBoard(squareId)).upPos;
				result[0]="5";
				result[1]="Player moved to  1 track above.";
			}
	
		}  else if(number == 33){
			
			if(p.numberOfHouses == 0){
				result[0]="1";
				result[1]="Player has no house.";
			}else {
				for(int i=0;i<p.properties.size();i++){
					if(p.properties.get(i).house != 0){
						p.properties.get(i).sellBuilding(p);
						p.properties.get(i).updateRentAccordingToHouse(p.properties.get(i));
						break;
					}
				}
				result[0]="1";
				result[1]="City condemned one house of the player.";
			}
			
			
		}  else if(number == 34){
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
			
		}  else if(number == 35){
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
			
		}  else if(number == 36){
			
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
				
			}
			else if(p.money+p.valueOfProperties>amount){
				result[0]= "2";
				result[1] = "Player has to sell a property.";
			}else{
				result[0]="-1";
				result[1]= "Player is broke";
			}
			
		}  else if(number == 37){
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
			
		}  else if(number == 38){
			p.row = 1;
			p.position = 10;
			result[0]="5";
			result[1]="Player went directly to jail.";
			
		}  else if(number == 39){
			// 0 12
			if(p.row == 2 && p.position>7 && p.position<28){
				p.addMoney(300);
			}
			p.position=12;
			p.row = 0;
			result[0]="5";
			result[1]="Player advanced to stock exchange.";
			result[p.id+2]="300";
			
		}  else if(number == 40){
			//hurricane to player's own properties.
			// player should choose a color group.
			result[0]="27";
			result[1]="PLayer should choose one color group of its own and it will be downgraded.";
			// applyCard40 should be called with color code (0-19)
			
		}  else if(number == 41){
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
		} 
		return result;
	}
	
	public String[] applyCard29(boolean choice,Player p){
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
		
		return result;
	}
	
	public String[] applyCard40(Player p,int colorGroup){
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
		return result;
	}
	
}
