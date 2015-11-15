
public class SquareFree extends Square {

	public SquareFree(String name, int id, int positionX, int positionY, int row) {
		super(name, id, positionX, positionY, row);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] landOn(Player player, Board board, int dice) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="1";
		result[1]="";
	
		return result;
		// TODO Auto-generated method stub
		
	}
}
