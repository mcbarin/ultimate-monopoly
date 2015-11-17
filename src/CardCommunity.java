

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
	
		} else if(number == 27){
			
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
			
		}  else if(number == 30){
			
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
			
		}  else if(number == 39){
			
		}  else if(number == 40){
			
		}  else if(number == 41){
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
		} 
		return result;
	}
	
}
