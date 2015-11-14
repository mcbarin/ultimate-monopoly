
public class SquareProperty extends Square  {

	Player owner;
	int color;
	int price;
	int originalRent;

	public SquareProperty(String name, Square next, Square prev, Square tranNext, int positionX, int positionY, int row,int color,int price) {
		super(name, next, prev, tranNext, positionX, positionY, row);
		this.color = color;
		this.price = price;
	}


	@Override
	public void landOn(Player player, Board board) {
		// TODO Auto-generated method stub

	}


	public void setOwner(Player player){
		this.owner = player; 
	}
	public int getColor(Square s){
		return this.color;
	}
	public int getPrice(Square s){
		return this.price;
	}
}

