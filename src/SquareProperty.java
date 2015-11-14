
public class SquareProperty extends Square  {

	Player owner;
	int color;
	int price;
	int originalRent;
	int rent;
	
	public SquareProperty(String name, int id, Square next, Square prev, Square tranNext, int positionX,
			int positionY, int row, int color, int price, int originalRent) {
		super(name, id, next, prev, tranNext, positionX, positionY, row);
		this.color = color;
		this.price = price;
		this.originalRent = originalRent;
	}

	@Override
	public String[] landOn(Player player, Board board) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public void doubleRent(){
		rent = originalRent*2;
	}
	public void TripleRent(){
		rent = originalRent*3;
	}
	public void normalizeRent(){
		rent = originalRent;
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

