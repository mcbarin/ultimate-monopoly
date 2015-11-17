

public class CardChance extends Card {
	int number;
	String title,description;
	Board board;

	public CardChance(int number,String title,String description,Board board){
		super("Chance",number);
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
		
		if(number == 0){
			
		} else if(number == 1){
			
		}  else if(number == 2){
			
		}  else if(number == 3){
			int amount = 0;
			amount += 25*(p.numberOfHouses+p.numberOfCabStand+p.numberOfTransitStation);
			amount += 100*(p.numberOfHotels+p.numberOfSkyscrapers);
			
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
			
		}  else if(number == 4){
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
			
		}  else if(number == 5){
			
			p.setPosition(p.position-3);
			result[0]="5"; // Must call the landOn method.
			result[1]="Go Back Three Spaces.";
			
		
		}  else if(number == 6){
			if(p.money > 150){
			
				board.payToPool(150);
				p.substract(150);
				result[0]="1";
				result[1]= "Player has paid $150 to the pool.";
				result[p.id+2]="-150";
			} else if(p.money+p.valueOfProperties>150){
				result[0]= "2";
				result[1] = "Player has to sell a property.";
			}else{
				result[0]="-1";
				result[1]= "Player is broke";
			}
		}  else if(number == 7){
			//Advance to stock exchange square. row0 pos12
			// 8-27 row2
			if(p.row==2 && p.position>7 && p.position<29){
				p.addMoney(300);
				result[0]="5";
				result[1]="Player advanced to Stock Exchange and collected $300.";
				result[p.id+2]="300";
			}else{
				result[0]="5";
				result[1]="Player advanced to Stock Exchange";	
			}
			p.row=0;        // Advancing to Stock Exchange.
			p.position=12;
			
			
		}  else if(number == 8){
			p.addMoney(150);
			result[0]= "1";
			result[1] = "Loan Matures, Player won $150 from the bank.";
			result[p.id+2] = "150";
			
			
			
		}  else if(number == 9){
			int amount = (board.getNumberOfPlayers()-1)*50;
			
			if(p.money > amount){
				for(int i=0;i<board.getNumberOfPlayers();i++){
					if(board.getPlayers().get(i).id != p.id){
						board.getPlayers().get(i).addMoney(50);
						p.substract(50);
						result[board.getPlayers().get(i).id+2] = "50";
					}
				}
				result[p.id+2] = "-"+""+amount;
				result[0]="1";
				result[1]="Player paid $50 to each player.";
			}else if(p.money+p.valueOfProperties>amount){
				result[0]="2";
				result[1]="Player must sell property.";
			}else {
				result[0]="-1";
				result[1]="Player is broke.";
				
			}
			
		}  else if(number == 10){
			
		}  else if(number == 11){
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
			
		}  else if(number == 12){
			//board.getAllPool(p);
			//tax refund row0 pos14
			
			//########## Do not change the money change after calling landOn Tax Refund.
			/*
			p.row = 0;
			p.position = 14;
			result[0]="5";
			result[1]="Player advanced to Tax Refund and Collected the pool.";
			result[p.id+2] = ""+board.pool;
			board.getAllPool(p);
			*/
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
			
		}  else if(number == 13){
			//Advance to roll one.
			// row1 pos30
			p.row=1;
			p.position= 30;
			result[0]="5";
			result[1]="Player advanced to Roll One.";
			
		}  else if(number == 14){
			
		}  else if(number == 15){
			int amount = 0;
			for(int i=0;i<p.properties.size();i++){
				if(!p.properties.get(i).isMortgaged){
					amount++;
				}
			}
			amount*=25;
			
			if(p.money > amount){
				p.substract(amount);
				result[0] = "1";
				result[1] = "Property Taxes, Player paid $25 for each unmortgaged property of its own.";
				result[p.id+2] = "-"+""+amount;
			}else if(p.money+p.valueOfProperties>amount){
				result[0]="2";
				result[1]="Player must sell property.";
				
			}else {
				result[0]="-1";
				result[1]="Player is broke.";
				
			}
			
		}  else if(number == 16){
			result[0]="5";
			result[1]="Player advanced to Black & White Cab Co.";
			p.row=2;
			p.position=22;
			
		}  else if(number == 17){
int amount = (board.getNumberOfPlayers()-1)*50;
			
			if(p.money > amount){
				for(int i=0;i<board.getNumberOfPlayers();i++){
					if(board.getPlayers().get(i).id != p.id){
						board.getPlayers().get(i).addMoney(50);
						p.substract(50);
						result[board.getPlayers().get(i).id+2] = "50";
					}
				}
				result[p.id+2] = "-"+""+amount;
				result[0]="1";
				result[1]="Player paid $50 to each player.";
			}else if(p.money+p.valueOfProperties>amount){
				result[0]="2";
				result[1]="Player must sell property.";
			}else {
				result[0]="-1";
				result[1]="Player is broke.";
				
			}
		}  else if(number == 18){
			
		}  else if(number == 19){
			result[0]="1";
			result[1]="Win the marathon, Player took a victory lap and collected the pay corner income.";
			if(p.row == 0){
				p.addMoney(250);
				result[p.id+2]="250";
			}else if(p.row == 1){
				p.addMoney(200);
				result[p.id+2]="200";
			}else if(p.row==2){
				p.addMoney(400);
				result[p.id+2]="400";
			}
			
		}  else if(number == 20){
			// All players are moving to Canal Street
			// row2 pos9
			for(int i=0;i<board.getNumberOfPlayers();i++){
				Player x =board.getPlayers().get(i);
				x.row = 2;
				x.position = 9;
			}
			result[0]="5";
			result[1]="All players moved to Canal Street.";
			
		}  else if(number == 21){
			
		}  else if(number == 22){
			p.addCard(this);
			result[0]="1";
			result[1]="Build 1 free house on any property in a monopoly you own.";
			
		}  else if(number == 23){
			p.addCard(this);
			result[0]="1";
			result[1]="Player pulled the Comped Room card.";
			
		}  else if(number == 24){
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
				p.position=((SquareChance)board.getSquareFromBoard(squareId)).downPos;
				result[0]="5";
				result[1]="Player moved to  1 track below.";
			}else if(pRow==0){
				p.row=1;
				p.position=((SquareChance)board.getSquareFromBoard(squareId)).downPos;
				result[0]="5";
				result[1]="Player moved to  1 track below.";
			}
			
		}  else if(number == 25){
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
				p.position=((SquareChance)board.getSquareFromBoard(squareId)).upPos;
				result[0]="5";
				result[1]="Player moved to  1 track above.";
			}else if(pRow==2){
				p.row=1;
				p.position=((SquareChance)board.getSquareFromBoard(squareId)).upPos;
				result[0]="5";
				result[1]="Player moved to  1 track above.";
			}
			
		} 
		
		return result;
	}
	
}
