
public class SquareRollOnce extends Square {

	public SquareRollOnce(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="10";
		result[1] = "";

		Dice dice = new Dice();
		int face = dice.getFace();
		
		result[2]=Integer.toString(face);

		return result;
	}

}
