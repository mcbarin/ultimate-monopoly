
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
	
	public String[] doAction(Player p){
		String[] result= new String[20];
		
		
		return result;
	}
	
}
