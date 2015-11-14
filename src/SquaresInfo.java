import java.awt.Color;


public class SquaresInfo {


	int currentTurn = 0;
	int totalPlayer = 0;
	Player[] players;


	public Square[] squares = new Square[20];
	
	String[] names = {"Go", "Oriental Ave", "Community Chest", "Vermont Ave", "Connecticut Ave", "Roll Once", 
			"St. Charles Place", "Chance", "States Ave", "Virginia Ave", "Free Parking", "St. James Place", 
			"Community Chest", "Tennessee Ave", "New York Ave", "Squeeze Play", "Pacific Ave", 
			"North Carolina Ave", "Chance", "Pennsylvania Ave"};
	int[] prices = {0, 100, 0, 100, 120, 0, 140, 0, 140, 160, 0, 180, 0, 180, 200, 0, 300, 300, 0, 320}; 
	Color[] colors = {GUI.myGreen, GUI.myBlue, Color.WHITE, GUI.myBlue, GUI.myBlue, Color.WHITE, GUI.myPink, Color.WHITE,
			GUI.myPink, GUI.myPink, GUI.myWhite, GUI.myOrange, Color.WHITE,
			GUI.myOrange, GUI.myOrange, Color.WHITE, GUI.myGreen, GUI.myGreen, GUI.myGreen, GUI.myGreen};
	
	int[] rents = {0,6,0,6,8,0,10,0,10,12,0,14,0,14,16,0,26,26,0,28};
	
	int[] colorCodes = {0,1,0,1,1,0,2,0,2,2,0,3,0,3,3,0,4,4,0,4};
	
	public SquaresInfo(){

    	for(int i = 0;i < squares.length;i++){
			if(i == 0){
				squares[i] = new SquareFree(names[i], colors[i]);
			}else if(i == 2 || i == 12){
				squares[i] = new SquareCommunityChest(names[i]);
			}else if(i == 5){
				squares[i] = new SquareRollOnce(names[i]);
			}else if(i == 7 || i == 18){
				squares[i] = new SquareChance(names[i]);
			}else if(i == 10){
				squares[i] = new SquareFree(names[i], colors[i]);
			}else if(i == 15){
				squares[i] = new SquareSqueezePlay(names[i]);
			}else{
				squares[i] = new SquareHouse(names[i],prices[i], colors[i],rents[i]);
			}

    		squares[i].setID(i);
    	}
	}
	public int getTotalPlayer(){
		return totalPlayer;
	}

	public Player getPlayer(int id) {
		return players[id];
	}
	
	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player[] p) {
		this.players = p;
	}
	
	//ADDED
	public int[] getOtherHouses(SquareHouse sq){
		int pos = sq.getID();
		int[] otherHouses = {pos,-1,-1};
		int x=1;
		for(int i=1;i<20;i++){
			if(colorCodes[otherHouses[0]]==colorCodes[i]){
				otherHouses[x] = i;
				x++;
			}
		}
		return otherHouses;
	}
    
    public String getSquareName(int i){return squares[i].getName();}
    public Color getSquareColor(int i){return squares[i].getColor();}
    public int getSquarePrice(int i){return squares[i].getPrice();}
    public int getPositionX(int i){return squares[i].getPositionX();}
    public int getPositionY(int i){return squares[i].getPositionY();}
    
    public Square getSquare(int i){return squares[i];}
   
    public void setPositions(int index, int x, int y){squares[index].setPosition(x, y);}

}