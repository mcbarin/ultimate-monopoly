package domain;
import java.util.ArrayList;

public class Bank {

	ArrayList<Company> companies = new ArrayList<Company>();
	String names[] = {"General Radio","United Railways","National Utilities","Acme Motors","Allied Steamships","Motion Pictures"};
	ArrayList<Player> players;
	Square[][] squares;
	//boolean IsUnowned = true;

	/**
	 * This method is the Constructor method of the bank. This method initializes the companies and takes players and squares from the board.
	 * @param players
	 * @param squares
	 * @requires There is no requirement for this method.
	 * @modifies companies, players, squares
	 * @effects Bank is created and ready for the game.
	 */
	public Bank(ArrayList<Player> players, Square[][] squares){
		for (int i = 0; i < 6; i++) {
			companies.add(new Company(names[i], i, 100+i*10));
		}
		this.players=players;
		this.squares=squares;
	}

	//////////////////////////////////AUCTION///////////////////////////////////////
	/**
	 * This method checks that there is unowned property in the bank or not
	 * @requires There is no requirement for this method. 
	 * @modifies This method does not modify anything.
	 * @effects True is returned if there exits at least one unowned property, otherwise false is returned.
	 * @return boolean value
	 */
	public boolean thereIsUnownedProperty(){
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 64; j++) {
				if(squares[i][j] != null && squares[i][j].type.equals("Property") && squares[i][j].owner == null){
					return true;}
			}
		}
		return false;
	}


	/**
	 * This method returns all unowned properties to show to the user all options.
	 * @requires There is no requirement for this method. 
	 * @modifies This method does not modify anything.
	 * @effects Unowned properties will be returned as a list.
	 * @return Returns the ArrayList of SquareProperty objects whose owner null.
	 */
	public ArrayList<SquareProperty> getUnownedProperties(){
		ArrayList<SquareProperty> unowned = new ArrayList<SquareProperty>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 56; j++) {
				if(squares[i][j] != null && squares[i][j].type.equals("Property") && squares[i][j].owner == null)
					unowned.add((SquareProperty) squares[i][j]);
			}
		}
		return unowned;
	}
	/**
	 * This method takes total number of dice thrown and finds the highest-rent property within the TRANSIT STATION rules. 
	 * @param total
	 * @requires total must be the sum of numbers in dice, Mr. Monopoly and bus are counted as zero. 
	 * @modifies This method does not modifies anything.
	 * @effects Highest-rent property's location in the board will be returned.
	 * @return Returns the array of integers with row and position of Square
	 */
	public int[] findHighestRentProperty(int total){
		int max=0;
		int row=2; 
		int position=0; 
		//search until transit station
		for (int i = 16; i < 34; i++) {
			if(squares[2][i].type.equals("Property") && ((SquareProperty)squares[2][i]).rent> max && !((SquareProperty)squares[2][i]).isMortgaged){
				max=((SquareProperty)squares[2][i]).rent;
				position=i;
			}
		}

		if(total%2 == 0){//use transit if possible
			//search through upper track
			for (int i = 0; i < 40; i++) {
				if(squares[1][i].type.equals("Property") && ((SquareProperty)squares[1][i]).rent> max && !((SquareProperty)squares[1][i]).isMortgaged){
					max=((SquareProperty)squares[1][i]).rent;
					row=1;
					position=i;
				}
			}
		}else{ //stay on current track
			//search through current track
			for (int i = 37; i < 56; i++) {
				if(squares[2][i].type.equals("Property") && ((SquareProperty)squares[2][i]).rent> max && !((SquareProperty)squares[2][i]).isMortgaged){
					max=((SquareProperty)squares[2][i]).rent;
					position=i;
				}
			}
		}
		int[] a = {row,position};
		return a;
	}


	/**
	 * This method takes a property and bids from all players; finds highest bid; and sells the property to the player with highest bid.
	 * @param s
	 * @param bids
	 * @requires s must be unowned property, bids must be in the same order of players.
	 * @modifies s, winner
	 * @effects Selected property has sold to the player who makes the highest bid if max bid is greater than half of the actual price of property and player has the money that he offered, else nothing sold.
	 * @return String array which includes status and message for GUI
	 */
	public String[] auction(Square s,int[] bids){
		Player winner = null;
		String[] result = new String[14];
		result = getResultArray();
		result[0]="1";
		result[1]="";
		//find max bid
		int max=0;
		for (int i = 0; i < players.size(); i++) {
			if(bids[i] > max && players.get(i).money >= bids[i]){ // make sure player has the money that he offered
				max=bids[i];
				winner= players.get(i);
			}
			if(players.get(i).money < bids[i])
				result[1]+=players.get(i).name+" offered a bid higher than his money. ";

		}


		if(s.type.equals("Property")){
			if(max < ((SquareProperty)s).price/2){ // or max==0
				result[1] += "Bids are below the half of the price. No one wins.";
			}else{
				((SquareProperty)s).buyProperty(winner);
				winner.addMoney(((SquareProperty)s).price-max); //

				result[1] += winner.name + " has bought " + ""+s.name+" for $"+max+".";
				result[winner.id+2] = "-"+ ""+max;

			}
		}else if (s.type.equals("Transit")){
			if(max < ((SquareTransit)s).price/2){ // or max==0
				result[1] += "Bids are below the half of the price. No one wins.";
			}else{
				winner.substract(max);
				((SquareTransit)s).owner = winner;
				((SquareTransit)s).twin.owner = winner;
				winner.valueOfProperties +=((SquareTransit)s).price/2;
				winner.trains.add(((SquareTransit)s));
				winner.trains.add(((SquareTransit)s).twin);

				result[1] += winner.name + " has bought " + ""+s.name+" for $"+max+".";
				result[winner.id+2] = "-"+ ""+max;

			}
		}else if(s.type.equals("CabCompany")){
			if(max < ((SquareCabCompany)s).price/2){ // or max==0
				result[1] += "Bids are below the half of the price. No one wins.";
			}else{
				((SquareCabCompany)s).buyCabCompany(winner);
				winner.addMoney(((SquareCabCompany)s).price-max); //
				
				result[1] += winner.name + " has bought " + ""+s.name+" for $"+max+".";
				result[winner.id+2] = "-"+ ""+max;
			}
		}else if (s.type.equals("Utility")){
			if(max < ((SquareUtility)s).price/2){ // or max==0
				result[1] += "Bids are below the half of the price. No one wins.";
			}else{
				((SquareUtility)s).buyUtility(winner);
				winner.addMoney(((SquareUtility)s).price-max); //
				
				result[1] += winner.name + " has bought " + ""+s.name+" for $"+max+".";
				result[winner.id+2] = "-"+ ""+max;
			}
			
		}

		winner.board.refreshGUI();
		winner.setFreeProperties();
		return result;
	}


	///////////////////////////////////STOCK EXCHANGE/////////////////////////////////////
	/**
	 * This method returns the list of names of companies that have at least one unsold share.
	 * @requires There is no requirement for this method. 
	 * @modifies This method does not modifies anything.
	 * @effects Names of Company objects whose share fields less than 5 will be returned.
	 * @return Returns the ArrayList of String of Company names.
	 */
	public ArrayList<String> checkShareOfStocks(){
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			if(companies.get(i).share < 6 )//all shares haven't sold yet. 
				options.add(companies.get(i).name);
		}

		return options;
	}


	/**
	 * This method takes a company name and player; and sells one share of that company to the player.
	 * @param name
	 * @param p
	 * @requires name must be one of the company names that have unsold share(s)
	 * @modifies p, c
	 * @effects One share of the company with given name has sold to the player if player has enough money, else nothing sold.
	 * @return String array which includes status and message for GUI
	 */
	public String[] buyStock(String name, Player p){
		String[] result = new String[14];
		result = p.getResultArray();

		int id = getIdByStockName(name);
		Company c = companies.get(id);

		if(p.money >= c.parValue){ //check player has enough money
			c.share++;
			p.substract(c.parValue);
			p.shares[id]++;
			p.valueOfProperties += c.parValue/2;
			result[0]="1"; // Success
			result[1] = p.name + " has bought one share of " + ""+c.name+".";
			result[p.id+2] = "-"+ ""+c.parValue;
		}else{
			result[0]="51"; // Auction
			result[1] = p.name + " has not enough money. One share of "+c.name+" will be auction off.";
		}

		return result;
	}
	
	

	public void addStock(String name, int n, Player p){

		int id = getIdByStockName(name);
		Company c = companies.get(id);
			c.share+=n;
			p.shares[id]+=n;
	}
	

	public String[] sellStock(String name, Player p){
		String[] result = new String[14];
		result = p.getResultArray();

		int id = getIdByStockName(name);
		Company c = companies.get(id);
		
		c.share--;
		p.addMoney(c.parValue);
		p.shares[id]--;

		p.valueOfProperties -= c.parValue/2;

		result[0]="0"; // Success
		result[1] = p.name + " has sold one share of " + ""+c.name+".";

		result[p.id+2] = "+"+c.parValue;

		return result;
	}


	/**
	 * This method takes a company name and bids from all players; finds highest bid; and sells one share of that company to the player with highest bid.
	 * @param name
	 * @param bids
	 * @requires name must be one of the company names that have unsold share(s), bids must be in the same order of players.
	 * @modifies c, winner
	 * @effects One share of the company with given name has sold to the player who makes the highest bid if max bid is greater than half of the par value and player has the money that he offered, else nothing sold.
	 * @return String array which includes status and message for GUI
	 */
	public String[] auctionStock(String name,int[] bids){
		Player winner =null;
		String[] result = new String[14];
		result = getResultArray();
		result[0]="1";
		result[1]="";
		//find max bid
		int max=0;
		for (int i = 0; i < players.size(); i++) {
			if(bids[i] > max && (players.get(i).money >= bids[i])){ // player has the money that he offered
				max=bids[i];
				winner= players.get(i);
			}
			if(players.get(i).money < bids[i])
				result[1]+=players.get(i).name+" offered a bid higher than his money. ";

		}



		int id = getIdByStockName(name);
		Company c = companies.get(id);

		if(max < c.parValue/2){ // or max==0
			result[1] += "Bids are below the half of the par value. No one wins.";
		}else{
			c.share++;
			winner.substract(max);
			winner.shares[id]++;
			winner.valueOfProperties += c.parValue/2;

			result[1] += winner.name + " has bought one share of " + ""+c.name+" for $"+max+".";
			result[winner.id+2] = "-"+ ""+max;

		}
		return result;
	}


	/**
	 * This method gives the ID of the stock belonging to given name.
	 * @param name
	 * @requires no requirement.
	 * @modifies nothing.
	 * @effects nothing.
	 * @return integer which is the IDof the stock
	 */
	public int getIdByStockName(String name){
		for (int i = 0; i < 6; i++) {
			if(name.equals(names[i]))
				return i;
		}
		return 0;
	}



	public boolean repOk(){
		if(this==null || companies==null || players==null || squares == null)
			return false;
		else
			return true;
	}

	public String[] getResultArray(){
		String[] result = new String[14];
		for(int i=0;i<14;i++){
			result[i]= "0";
		}
		return result;
	}
}
