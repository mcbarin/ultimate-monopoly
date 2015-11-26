//mrmonopoly dahil degil 
public class SquareReverse extends Square {

	public SquareReverse(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="5";
		result[1]="";

		if(!player.reverse){
			player.reverse=true;
			result[0]="1";
			result[1]="Play next turn in reverse direction.";
		}


		return result;
		// TODO Auto-generated method stub

	}

}
