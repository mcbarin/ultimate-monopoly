
public class CardCommunity extends Card {
	int number;
	String description;
	public CardCommunity(int number,String description){
		this.number = number;
		this.description = description;
	}
	public int getNumber(){
		return number;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	

	public String doAction(Player p, SquaresInfo board){
		if(number == 1){
			return "Community1";
		}else if(number ==2){
			p.addCard(this);
			return "Community2";
		}else if(number ==3){
			p.addCard(this);
			return "Community3";
		}
		return type;	// For error handling
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
