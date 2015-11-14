import java.util.ArrayList;

public class Board {
	
	ArrayList<Player> players;
	Square[][] squares = new Square[3][56];
	int pool = 0;
	
	public Board(int totalPlayer) {
		players = new ArrayList<>(totalPlayer);

	}
	
	
	public int getNumberOfPlayers(){
		return players.size();
	}
	
	public void payToPool(int money){
		pool = pool + money;
	}
	
	public void getHalfPool(Player p){
		p.addMoney(Math.ceil(pool/2));
		pool = (int) (pool - Math.ceil(pool/2));
	}
	
	public void getAllPool(Player p){
		p.addMoney(pool);
		pool = 0;
	}
	
	
}
