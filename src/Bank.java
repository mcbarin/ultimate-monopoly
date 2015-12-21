import java.util.ArrayList;

public class Bank {
	ArrayList<Company> companies = new ArrayList<Company>();
	String names[] = {"General Radio","United Railways","National Utilities","Acme Motors","Allied Steamships","Motion Pictures"};
	ArrayList<Player> players;

	public Bank(ArrayList<Player> players){
		for (int i = 0; i < 6; i++) {
			companies.add(new Company(names[i], i, 100+i*10));
		}
		this.players=players;
	}

	public ArrayList<String> checkShareOfStocks(){
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			if(companies.get(i).share != 5 )//all shares havent sold yet. 
				options.add(companies.get(i).name);
		}

		return options;
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

		if(max < c.parValue){ // or max==0
			result[1] = "All bids are below the half of the par value. No one wins";
		}else{
			c.share++;
			winner.substract(max);
			winner.shares[id]++;
			winner.valueOfProperties += c.parValue/2;

			result[1] = winner.name + " has bought one share of " + ""+c.name+".";
			result[winner.id+2] = "-"+ ""+c.parValue;

		}
		return result;
	}
}
