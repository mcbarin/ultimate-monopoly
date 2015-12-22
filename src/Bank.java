import java.util.ArrayList;

public class Bank {
	ArrayList<Company> companies = new ArrayList<Company>();
	String names[] = {"General Radio","United Railways","National Utilities","Acme Motors","Allied Steamships","Motion Pictures"};
	ArrayList<Player> players;
	Square[][] squares;
	//boolean IsUnowned = true;

	public Bank(ArrayList<Player> players, Square[][] squares){
		for (int i = 0; i < 6; i++) {
			companies.add(new Company(names[i], i, 100+i*10));
		}
		this.players=players;
		this.squares=squares;
	}

	public int[] findHighestRentProperty(int total){
		int max=0;
		int row=2;
		int position=0;
		for (int i = 16; i < 34; i++) {
			if(squares[2][i].type.equals("Property") && ((SquareProperty)squares[2][i]).rent> max && !((SquareProperty)squares[2][i]).isMortgaged){
				max=((SquareProperty)squares[2][i]).rent;
				position=i;
			}
		}

		if(total%2 == 0){//use transit if possible
			for (int i = 0; i < 40; i++) {
				if(squares[1][i].type.equals("Property") && ((SquareProperty)squares[1][i]).rent> max && !((SquareProperty)squares[2][i]).isMortgaged){
					max=((SquareProperty)squares[1][i]).rent;
					row=1;
					position=i;
				}
			}
		}else{ //stay on current track
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
	
	public ArrayList<String> checkShareOfStocks(){
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			if(companies.get(i).share != 5 )//all shares havent sold yet. 
				options.add(companies.get(i).name);
		}

		return options;
	}

	public boolean thereIsUnownedProperty(){

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 64; j++) {
				if(squares[i][j] != null && squares[i][j].type.equals("Property") && squares[i][j].owner != null)
					return true;
			}
		}
		return false;
	}

	public ArrayList<SquareProperty> getUnownedProperties(){
		ArrayList<SquareProperty> unowned = new ArrayList<SquareProperty>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 64; j++) {
				if(squares[i][j] != null && squares[i][j].type.equals("Property") && squares[i][j].owner == null)
					unowned.add((SquareProperty) squares[i][j]);
			}
		}
		return unowned;
	}

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
			result[0]="1"; // Auction
			result[1] = p.name + " has not enough money. One share of selected company will be auction off.";
		}

		return result;
	}

	public int getIdByStockName(String name){
		for (int i = 0; i < 6; i++) {
			if(name.equals(names[i]))
				return i;
		}
		return 0;
	}

	public String[] auctionStock(String name,int[] bids){
		Player winner = null;
		//find max bid
		int max=0;
		for (int i = 0; i < bids.length; i++) {
			if(bids[i] > max && players.get(i).money >= bids[i]){ // player has the money that he offered
				max=bids[i];
				winner= players.get(i);
			}
		}

		String[] result = new String[14];
		result = winner.getResultArray();
		result[0]="1";

		int id = getIdByStockName(name);
		Company c = companies.get(id);

		if(max < c.parValue/2){ // or max==0
			result[1] = "All bids are below the half of the par value. No one wins";
		}else{
			c.share++;
			winner.substract(max);
			winner.shares[id]++;
			winner.valueOfProperties += c.parValue/2;

			result[1] = winner.name + " has bought one share of " + ""+c.name+".";
			result[winner.id+2] = "-"+ ""+max;

		}
		return result;
	}
	
	public String[] auction(SquareProperty s,int[] bids){
		Player winner = null;
		//find max bid
		int max=0;
		for (int i = 0; i < bids.length; i++) {
			if(bids[i] > max && players.get(i).money >= bids[i]){ // make sure player has the money that he offered
				max=bids[i];
				winner= players.get(i);
			}
		}

		String[] result = new String[14];
		result = winner.getResultArray();
		result[0]="1";

		int id = s.id;


		if(max < s.price/2){ // or max==0
			result[1] = "All bids are below the half of the par value. No one wins";
		}else{
			s.buyProperty(winner);
			winner.addMoney(s.price-max);

			result[1] = winner.name + " has bought " + ""+s.name+".";
			result[winner.id+2] = "-"+ ""+max;

		}
		return result;
	}
}
