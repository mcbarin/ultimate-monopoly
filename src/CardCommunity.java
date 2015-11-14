
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
		int x = board.getNumberOfPlayers() +2;
		String[] result = new String[x];
		for(int i=0;i<x;i++){
			result[i]= "0";
		}
		return result;
	}
	
	public String[] doAction(Player p){
		String[] result= getResultArray();
		
		
		return result;
	}
	
}
