package domain;

public class SquareAuction extends Square {

	public SquareAuction(String type, String name, int id, int position, int row) {
		super(type, name, id, position, row);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);

		if(board.bank.thereIsUnownedProperty()){
			result[0]="52";
			result[1]="Please pick an unowned property to auction off.";
		}else{
			result[0]="5";
			result[1]="There is no unowned properties. Go to highest rent property.";
			int[] a = board.bank.findHighestRentProperty(total);
			player.row=a[0];
			player.position=a[1];
			board.refreshGUI();
		}

		return result;
	}

}
