package domain;

public class SquareFree extends Square {

	public SquareFree(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] landOn(Player player, Board board, int dice) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="1";
		result[1]="Free Square.";
	
		return result;
		// TODO Auto-generated method stub
		
	}
}
