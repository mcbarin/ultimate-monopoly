
public class CardCommunity extends Card {
	int number;
	String description;
	Board board;

	public CardCommunity(int number,String description,Board board){
		super("Community",number);
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
		
		}  else if(number == 32){
	
		}  else if(number == 33){
			
		}  else if(number == 34){
			
		}  else if(number == 35){
			
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
			
		}  else if(number == 38){
			
		}  else if(number == 39){
			
		}  else if(number == 40){
			
		}  else if(number == 41){
			
		} 
		return result;
	}
	
}
