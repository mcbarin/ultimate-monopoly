
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
		
		Dice dice = new Dice();
		int face = dice.getFace();
		result[2]=Integer.toString(face);
		
		result[1] = player.name+" will play Roll Once. Please roll a die and wish to get '"+result[2]+"' on the face.";
		
		for (int i = 0; i < board.players.size(); i++) {
			if( board.players.get(i).hasCardWithId(41) && board.players.get(i) != player) {
				result[1] = board.players.get(i).name+" has Roll Once Card and will play the game! Please pick a Roll Once Card.";
			}
			
		}
		

		return result;
	}

}
