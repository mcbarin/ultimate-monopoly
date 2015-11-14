
public abstract class Square {
	String name;
	int id;
	int positionX;
	int positionY;
    int row;
    Square next;
    Square prev;
    Square tranNext;
    
	
	public Square(String name,int id, Square next, Square prev, Square tranNext, int positionX, int positionY, int row) {
		this.name = name;
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		this.row = row;
		this.next = next;
		this.prev = prev;
		this.tranNext = tranNext;
	}
	
	public abstract void landOn(Player player, Board board);

	


}

