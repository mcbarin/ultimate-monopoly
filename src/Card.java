

public class Card {
	private String type = "";
	public int number;

	// For chance card, "Chance"; for community chest card, "Community".

	public int getNumber() {
		return number;
	}

	public Card(String type, int number){
		this.type = type;
		this.number = number;		
	}
	
	public String getType() {
		return type;
	}


}
