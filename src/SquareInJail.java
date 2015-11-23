
public class SquareInJail extends Square {

	public SquareInJail(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] landOn(Player player, Board board, int total) {
		String[] result = new String[14];
		initializeResult(result);

		if(player.countJail == 4){
			player.countJail = 3;
			result[0]="1";
			result[1]="";
		}else if (player.countJail < 4 ){
			if(player.hasCardWithId(4))
				result[0]="21";
			result[0]="20";
			result[1]="Remaining turns in jail : "+player.countJail+". What do you want to do ?";
		}

		return result;
	}

	//"Try to throw double", "Pay $50 to bank", "Use 'Get Out of Jail Card'" 
	public String[] getOutJail(Player p, int choice){
		String[] result = new String[14];
		initializeResult(result);
		result[0]="1";
		result[1]="";

		if(choice == 1){
			Dice dice = new Dice();
			int die1 = dice.getFace();
			int die2 = dice.getFace();
			int total=die1+die2;

			if(die1==die2){
				p.countJail=4;
				result[0]="23";
				result[1]=p.name+" threw double. Move according to dice.";
				result[2]=Integer.toString(total);

			}else{
				p.countJail -=1;
				if(p.countJail==0) p.countJail=4; 
				result[0]="1";
				result[1]=p.name+ " failed to throw double.";
			}

		}else if(choice == 2){
			p.substract(50);
			p.countJail=4;
			result[0]="0";
			result[p.id+2]="-50";
		}else{
			result[0]="0";
			p.deleteCard(4);
			p.countJail=4;
		}

		return result;
	}
}
