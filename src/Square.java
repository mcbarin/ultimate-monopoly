
public abstract class Square {
	String name;
	int id;
	int positionX;
	int positionY;
    int row;
    Square next;
    Square prev;
    Square tranNext;
    
	
	public Square(String name,int id, int positionX, int positionY, int row) {
		this.name = name;
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		this.row = row;
		//this.next = next;
		//this.prev = prev;
		//this.tranNext = tranNext;
	}
	
	public abstract String[] landOn(Player player, Board board, int total);

	public void initializeResult(String[] result){
		for (int i = 0; i < result.length; i++) {
			result[i]= "0";
		}
		
	}
	


}

