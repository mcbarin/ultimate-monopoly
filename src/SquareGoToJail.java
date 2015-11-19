
public class SquareGoToJail extends Square {

	public SquareGoToJail(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="5";
		result[1] = "Go to Jail";	

		player.row=1;
		player.position=0;

		return result;

	}
}
