
public class SquareProperty extends Square  {

	Player owner;
	int color;
	int price;
	int originalRent;

	public SquareProperty(String name, int id, Square next, Square prev, Square tranNext, int positionX,
			int positionY, int row) {
		super(name, id, next, prev, tranNext, positionX, positionY, row);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void landOn(Player player, Board board) {
		// TODO Auto-generated method stub

	}

	public Player getOwner(){
		return this.owner;
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

