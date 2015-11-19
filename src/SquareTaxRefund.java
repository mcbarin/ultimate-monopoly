
public class SquareTaxRefund extends Square {

	public SquareTaxRefund(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="1";
		result[1] = "Collect %50 from pool";	
		
		return null;
		// TODO Auto-generated method stub
		
	}

}
