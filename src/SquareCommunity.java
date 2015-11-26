
public class SquareCommunity extends Square {
	public int upRow,downRow,upPos,downPos;

	public SquareCommunity(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
		initializeUpDown(position, row);		
	}
	
	public void initializeUpDown(int position,int row){
		if(row==0 && position==4){
			upRow=-1; upPos=-1;downRow=1;downPos=6;
		}else if(row==1){
			upRow=0;downRow=2;
			if(position==2){
				upPos=0;downPos=4;
			}else if(position==17){
				upPos=11;downPos=23;
			}else if(position==33){
				upPos=19;downPos=47;
			}
				
			
		}else if(row==2){
			downRow=-1;downPos=-1;upRow=1;
			if(position==2){
				upPos=0;
			}else if(position==24){
				upPos=18;
			}else if(position==36){
				upPos=26;
			}else if(position==46){
				upPos=32;
			}
		}
	}
	
	@Override
	public String[] landOn(Player player, Board board, int dice) {
		// TODO Auto-generated method stub
		String[] result= board.getResultArray();
		
		result[0]="31";
		result[1]="Pull Community Card";
		
		return result;
	}

}
