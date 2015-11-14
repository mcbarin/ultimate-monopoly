import java.awt.List;
import java.util.ArrayList;

public class Player {
	private int money,id,row,position,numberOfProperties=0,numberOfCards=0,valueOfProperties=0,
			numberOfHouses=0,numberOfHotels=0,numberOfSkyscrapers=0;
	String name = "";
	private ArrayList<SquareProperty> properties = new ArrayList<SquareProperty>();
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	
	public Player(int money, int id, int row, int position, String name) {
		super();
		this.money = money;
		this.id = id;
		this.row = row;
		this.position = position;
		this.name = name;
	}
	
	
	
	
	
}
