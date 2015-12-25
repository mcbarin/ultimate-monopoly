package domain;

public class SquareBirthday extends Square {

	public SquareBirthday(String type, String name, int id, int position, int row) {
		super(type, name, id, position, row);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] landOn(Player player, Board board, int total) {

		String[] result = new String[14];
		initializeResult(result);
		result[0]="29";
		result[1]="Please pick one of these.";
		
		return result;
		
	}

	public String[] birthdayAction(int choice, Player player, Board board){
		
		String[] result = new String[14];
		initializeResult(result);
		result[0]="1";
		
		if (choice == 1){
			player.addMoney(100);
			result[1] = player.name + " has won 100$ as birthday gift";
			result[player.id+2] = "100";
		}else if (choice == 2){
			player.position=6;
			board.refreshGUI();
			result[1]="";
			if(board.getSquareFromBoard(70).owner == null && player.money >= ((SquareCabCompany)board.getSquareFromBoard(70)).price){
				result[0]="5";
			}
			
		}
		return result;
		
	}
}
