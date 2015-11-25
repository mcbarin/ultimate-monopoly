
public class SquareTunnel extends Square {

	public SquareTunnel(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);
		result[0]="1";
		result[1] = "Go to other side of the Tunnel";	

		if(id == 18){
			player.row=2;
			player.position=14;
		}else if(id == 78){
			player.row=0;
			player.position=18;
		}

		return result;

	}
}
