//mortgage ekle???
public class SquareStock extends Square {

	public SquareStock(String type, String name, int id, int position, int row) {
		super(type, name, id, position, row);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="30";
		result[1]="Dividends paid. Please pick a company.";

		payDividends(board,result);

		return result;
	}


	public void payDividends(Board board,String[] result){
		Player p;
		int share;
		int amount=0;
		for (int i = 0; i < board.players.size(); i++) {
			p = board.players.get(i);
			for (int j = 0; j < 6; j++) {
				share = p.shares[j];
				if(share!=0){
					amount += board.bank.companies.get(j).getDividend(share);
				}
			}
			p.addMoney(amount);
			result[p.id+2]=Integer.toString(amount);
			amount=0;
		}
	}

	
}
