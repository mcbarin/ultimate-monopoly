
public class SquareChance extends Square {
	public int upRow,downRow,upPos,downPos;

	public SquareChance(String type,String name, int id, int position, int row) {
		super(type,name, id, position, row);
		// TODO Auto-generated constructor stub
		initializeUpDown(position, row);		
	}
	
	public void initializeUpDown(int position,int row){
		if(row==0 && position==16){
			upRow=-1; upPos=-1;downRow=1;downPos=26;
		}else if(row==1){
			upRow=0;downRow=2;
			if(position==7){
				upPos=5;downPos=9;
			}else if(position==22){
				upPos=12;downPos=32;
			}else if(position==36){
				upPos=22;downPos=50;
			}
				
			
		}else if(row==2){
			downRow=-1;downPos=-1;upRow=1;
			if(position==10){
				upPos=7;
			}else if(position==21){
				upPos=15;
			}else if(position==30){
				upPos=20;
			}else if(position==54){
				upPos=0;
			}
		}
	}
	@Override
	public String[] landOn(Player player, Board board, int total) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
