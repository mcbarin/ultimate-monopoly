
public abstract class Square {
	String name;
	int color;
	int positionX;
	int positionY;
    int row;
    Square next;
    Square prev;
    Square tranNext;
	
	public Square(String name, Square next, Square prev, Square tranNext, int color, int positionX, int positionY, int row) {
		this.name = name;
	}
	
	public abstract void landOn(Player player, Player[] players);

}

