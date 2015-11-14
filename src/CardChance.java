
public class CardChance extends Card {
	int number;
	String description;
	Board board;

	public CardChance(int number,String description,Board board){
		super("Chance",number);
		this.number = number;
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
		int x = board.getNumberOfPlayers() +2;
		String[] result = new String[x];
		for(int i=0;i<x;i++){
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
			else if(p.money+p.valueOfProperties>150){
				result[0]= "2";
				result[1] = "Player has to sell a property.";
			}else{
				result[0]="-1";
				result[1]= "Player is broke";
			}
			
		}  else if(number == 4){
			
		}  else if(number == 5){
			""
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
			
		}  else if(number == 8){
			
		}  else if(number == 9){
			
		}  else if(number == 10){
			
		}  else if(number == 11){
			
		}  else if(number == 12){
			
		}  else if(number == 13){
			
		}  else if(number == 14){
			
		}  else if(number == 15){
			
		}  else if(number == 16){
			
		}  else if(number == 17){
			
		}  else if(number == 18){
			
		}  else if(number == 19){
			
		}  else if(number == 20){
			
		}  else if(number == 21){
			
		}  else if(number == 22){
			
		}  else if(number == 23){
			
		}  else if(number == 24){
			
		}  else if(number == 25){
			
		} 
		
		return result;
	}
	
}
