import java.awt.*;


public abstract class Square {

	private String name;
	private String type;
	private Color color;
	private int positionX=0;
	private int positionY=0;
	private int id=0;
	
	//String description;
	public Square(String name, String type, Color color, int x, int y) {
		this.name = name;
		this.type = type;
		this.color = color;
		positionX = x;
		positionY = y;
		//this.description = description;
	}
	
	public Square(String name, String type, Color color/*, String description*/) {
		this.name = name;
		this.type = type;
		this.color = color;
		//this.description = description;
	}
	
	
	public String getName() {
		return name;
	}

	public int getPrice(){return 0;}
	
	public Color getColor(){return this.color;}
	
	public String getType() {
		return type;
	}
	
	public void setPosition(int x, int y){
		positionX = x;
		positionY = y;
	}	
	
	public void setID(int i){
		this.id = i;
	}	
	public int getID(){
		return this.id;
	}

	public int getPositionX(){return positionX;}
	public int getPositionY(){return positionY;}
	
	
	
}
