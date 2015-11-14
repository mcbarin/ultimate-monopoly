import java.awt.*;

public class SquareHouse extends Square {

	int price;
	Player owner = null;
	int rent;
	int originalRent;

	public SquareHouse(String name, int price, Color color,int rent) {
		super(name, "SquareHouse", color);
		this.price = price;
		this.rent = rent;
		this.originalRent = rent;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public Player getOwner(){
		return owner;
		
	}
	
	public boolean isOwned(){
		if (owner == null) 
			return false;
		return true;
	}

	public int getPrice() {
		return price;
	}
	
	public int getRent() {
		return rent;
	}	
	public void setRent(int r) {
		this.rent=r;
	}
	
	public void doubleTheRent(){
		this.rent*=2;
	}
	public void normalizeRent(){
		this.rent = originalRent;
	}

}


