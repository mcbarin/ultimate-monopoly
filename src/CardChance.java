import java.util.ArrayList;

public class CardChance extends Card {
	int number;
	String title,description;
	Board board;

	public CardChance(int number,String title,String description,Board board){
		super("Chance",number);
		this.number = number;
		this.title = title; 
		this.description = description;
		this.board = board;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public String[] getResultArray(){
		
		String[] result = new String[14];
		for(int i=0;i<14;i++){
			result[i]= "0";
		}
		return result;
	}
	
	public String[] doAction(Player p){
		String[] result= getResultArray();
				
		if(number == 0){
			// Advance to the nearest utility.
			if(p.row==0){
				p.position=3;
				
			}else if(p.row==1){
				if(p.position==7 || p.position==36){
					if(p.position==36)
						p.addMoney(200);
					p.position=12;
				}else if(p.position==22){
					p.position=28;
				}
			}else if(p.row==2){
				if(p.position==10 || p.position==54){
					p.position=11;
				}else if(p.position==21 || p.position==30){
					if(p.position==21)
						p.addMoney(300);
					p.position=39;
				}
			}
			
			result[0]="5";
			result[1]="Player advanced to the nearest utility.";
			board.pullPushChance();
			
			
		} else if(number == 1){
			p.row = 1;
			p.position = 10;
			result[0]="5";
			result[1]="Player went directly to jail.";
			board.pullPushChance();
			
		}  else if(number == 2){
			
			if(p.row==0){
					p.position = 21;				
			}else if(p.row==1){
				if(p.position==7){
					p.position=15;
				}else if(p.position==22){
					p.position=25;
				}else if(p.position==36){
					p.position=5;
					p.addMoney(200);
				}
			}else if(p.row==2){
				if(p.position==10 || p.position==21){
					p.position=35;
					p.addMoney(300);
				}else if( p.position==30){
					p.position=35;
				}else if(p.position==54){
					p.position=7;
				}
			}
			
			result[0]="5";
			result[1]="Player went directly to nearest railroad.";
			board.pullPushChance();
			
			
		}  else if(number == 3){
			int amount = 0;
			amount += 25*(p.numberOfHouses+p.numberOfCabStand+p.numberOfTransitStation);
			amount += 100*(p.numberOfHotels+p.numberOfSkyscrapers);
			
			if(p.money > amount){
				
				p.substract(amount);
				result[0]="1";
				result[1]="Player has paid $"+""+amount+" for making general repair for the properties.";
				result[p.id+2]="-"+""+amount;
				board.pullPushChance();
				
			}
			else if(p.money+p.valueOfProperties>amount){
				result[0]= "2";
				result[1] = "Player has to sell a property.";
			}else{
				result[0]="-1";
				result[1]= "Player is broke";
				board.pullPushChance();
			}
			
		}  else if(number == 4){
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
			board.pullPushChance();
			
		}  else if(number == 5){
			
			p.position= (p.position-3);
			if(p.position<0){
				if(p.row==0)
					p.position+=24;
				else if(p.row==1){
					p.position+=40;
				}else if(p.row==2){
					p.position+=56;
				}
			}
			result[0]="5"; // Must call the landOn method.
			result[1]="Go Back Three Spaces.";
			board.pullPushChance();
			
		
		}  else if(number == 6){
			if(p.money > 150){
			
				board.payToPool(150);
				p.substract(150);
				result[0]="1";
				result[1]= "Player has paid $150 to the pool.";
				result[p.id+2]="-150";
				board.pullPushChance();
			} else if(p.money+p.valueOfProperties>150){
				result[0]= "2";
				result[1] = "Player has to sell a property.";
			}else{
				result[0]="-1";
				result[1]= "Player is broke";
				board.pullPushChance();
			}
		}  else if(number == 7){
			//Advance to stock exchange square. row0 pos12
			// 8-27 row2
			if(p.row==2 && p.position>7 && p.position<29){
				p.addMoney(300);
				result[0]="5";
				result[1]="Player advanced to Stock Exchange and collected $300.";
				result[p.id+2]="300";
				board.pullPushChance();
			}else{
				result[0]="5";
				result[1]="Player advanced to Stock Exchange";	
				board.pullPushChance();
			}
			p.row=0;        // Advancing to Stock Exchange.
			p.position=12;
			
			
		}  else if(number == 8){
			p.addMoney(150);
			result[0]= "1";
			result[1] = "Loan Matures, Player won $150 from the bank.";
			result[p.id+2] = "150";
			board.pullPushChance();
			
			
			
		}  else if(number == 9){
			int amount = (board.getNumberOfPlayers()-1)*50;
			
			if(p.money > amount){
				for(int i=0;i<board.getNumberOfPlayers();i++){
					if(board.getPlayers().get(i).id != p.id){
						board.getPlayers().get(i).addMoney(50);
						p.substract(50);
						result[board.getPlayers().get(i).id+2] = "50";
					}
				}
				result[p.id+2] = "-"+""+amount;
				result[0]="1";
				result[1]="Player paid $50 to each player.";
				board.pullPushChance();
			}else if(p.money+p.valueOfProperties>amount){
				result[0]="2";
				result[1]="Player must sell property.";
			}else {
				result[0]="-1";
				result[1]="Player is broke.";
				board.pullPushChance();
			}
			
		}  else if(number == 10){
			int posId = p.position;
			int border = 24;
			if(p.row==1){
				posId+=24;
				border=40;}
			else if(p.row==2){
				
				posId+=64;
				border=56;
			}
			
			SquareProperty first = (SquareProperty)board.getSquareFromBoard(17);
			boolean isFound=false;
			int k=0;

			for(int i=0;i<border;i++){
				Square sq = board.nextSquare(posId);
				if(posId==23)
					posId=0;
				else if(posId==63)
					posId=24;
				else if(posId==119)
					posId=64;
				else
					posId++;
				
				if(sq.type.equals("Property")){
					if(k==0){
						if(((SquareProperty)sq).owner != null && ((SquareProperty)sq).owner.id != p.id){
						first = (SquareProperty)sq;
						k++;
					}}
					if(((SquareProperty)sq).owner==null){
						p.position = (sq.position);
						isFound = true;
						break;
					}
				}
			}
			
			if(!isFound){
				p.position = (first.position);
			}
			
			result[0]="5";
			result[1]="Player advanced to the nearest Square Property.";
			board.pullPushChance();
		
			
			
		}  else if(number == 11){
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
			board.pullPushChance();
			
		}  else if(number == 12){
			//board.getAllPool(p);
			//tax refund row0 pos14
			
			//########## Do not change the money change after calling landOn Tax Refund.
			/*
			p.row = 0;
			p.position = 14;
			result[0]="5";
			result[1]="Player advanced to Tax Refund and Collected the pool.";
			result[p.id+2] = ""+board.pool;
			board.getAllPool(p);
			*/
			p.addCard(this);
			result[0]="1";
			result[1]=title+" "+description;
			board.pullPushChance();
			
		}  else if(number == 13){
			//Advance to roll one.
			// row1 pos30
			p.row=1;
			p.position= 30;
			result[0]="5";
			result[1]="Player advanced to Roll One.";
			board.pullPushChance();
			
		}  else if(number == 14){
			result[0]="8"; // For asking player id and color group.
			result[1]="Player should choose a player and color group to make a hurricane.";
			
		}  else if(number == 15){
			int amount = 0;
			for(int i=0;i<p.properties.size();i++){
				if(!p.properties.get(i).isMortgaged){
					amount++;
				}
			}
			amount*=25;
			
			if(p.money > amount){
				p.substract(amount);
				result[0] = "1";
				result[1] = "Property Taxes, Player paid $25 for each unmortgaged property of its own.";
				result[p.id+2] = "-"+""+amount;
				board.pullPushChance();
			}else if(p.money+p.valueOfProperties>amount){
				result[0]="2";
				result[1]="Player must sell property.";
				
			}else {
				result[0]="-1";
				result[1]="Player is broke.";
				board.pullPushChance();
				
			}
			
		}  else if(number == 16){
			result[0]="5";
			result[1]="Player advanced to Black & White Cab Co.";
			p.row=2;
			p.position=22;
			board.pullPushChance();
			
		}  else if(number == 17){
			int amount = (board.getNumberOfPlayers()-1)*50;
			
			if(p.money > amount){
				for(int i=0;i<board.getNumberOfPlayers();i++){
					if(board.getPlayers().get(i).id != p.id){
						board.getPlayers().get(i).addMoney(50);
						p.substract(50);
						result[board.getPlayers().get(i).id+2] = "50";
					}
				}
				result[p.id+2] = "-"+""+amount;
				result[0]="1";
				result[1]="Player paid $50 to each player.";
				board.pullPushChance();
			}else if(p.money+p.valueOfProperties>amount){
				result[0]="2";
				result[1]="Player must sell property.";
			}else {
				result[0]="-1";
				result[1]="Player is broke.";
				board.pullPushChance();
				
			}
		}  else if(number == 18){
			if(p.numberOfProperties==0){
				p.row = 1;
				p.position = 10;
				result[0]="5";
				result[1]="Player went directly to jail.";
				board.pullPushChance();
			}else{
				ArrayList<SquareProperty> pr = p.properties;
				
				for(int i=0;i<pr.size();i++){
					if(pr.get(i).isMortgaged){
						pr.remove(i);
					}
				}
				
				if(pr.size()==0){
					p.row = 1;
					p.position = 10;
					result[0]="5";
					result[1]="All of player's properties are mortgaged. Player directly went to the jail.";
					board.pullPushChance();
				}
				else{
					result[0]="24"; // Player should choose from its unmortgaged properties. Than in controller class,
					result[1]="Player should choose from its unmortgaged properties to surrender to the bank.";				
					// applyCard18 should be called.
				}
				
				
			}
			
			
		}  else if(number == 19){
			result[0]="1";
			result[1]="Win the marathon, Player took a victory lap and collected the pay corner income.";
			board.pullPushChance();
			if(p.row == 0){
				p.addMoney(250);
				result[p.id+2]="250";
			}else if(p.row == 1){
				p.addMoney(200);
				result[p.id+2]="200";
			}else if(p.row==2){
				p.addMoney(400);
				result[p.id+2]="400";
			}
			
		}  else if(number == 20){
			// All players are moving to Canal Street
			// row2 pos9
			for(int i=0;i<board.getNumberOfPlayers();i++){
				Player x =board.getPlayers().get(i);
				x.row = 2;
				x.position = 9;
			}
			result[0]="5";
			result[1]="All players moved to Canal Street.";
			board.pullPushChance();
			
		}  else if(number == 21){
			result[0]="25"; // Player should choose a cab company.
			result[1]="Player should choose a Cab Company. If it has an owner,player takes it. IF it is not owned,player will buy it.";
			// applyCard21 should be called in controller class.
			
		}  else if(number == 22){
			p.addCard(this);
			result[0]="1";
			result[1]="Build 1 free house on any property in a monopoly you own.";
			board.pullPushChance();
			
		}  else if(number == 23){
			p.addCard(this);
			result[0]="1";
			result[1]="Player pulled the Comped Room card.";
			board.pullPushChance();
			
		}  else if(number == 24){
			//Move directly to the space that 1 track below this one. If row==2 do nothing
			int pRow=p.row;
			int pPos=p.position;
			int squareId = pPos;
			if(pRow==0){
			}else if(pRow==1){
				squareId+=24;
			}else if(pRow==2){
				squareId+=64;
			}
			
			if(pRow==2){
				result[0]="1";
				result[1]="Player is on the Outer Track, do nothing.";
			}else if(pRow==1){
				p.row=2;
				p.position=((SquareChance)board.getSquareFromBoard(squareId)).downPos;
				result[0]="5";
				result[1]="Player moved to  1 track below.";
			}else if(pRow==0){
				p.row=1;
				p.position=((SquareChance)board.getSquareFromBoard(squareId)).downPos;
				result[0]="5";
				result[1]="Player moved to  1 track below.";
			}
			board.pullPushChance();
			
		}  else if(number == 25){
			//Move directly to the space that 1 track above this one. If row==0 do nothing
			int pRow=p.row;
			int pPos=p.position;
			int squareId = pPos;
			if(pRow==0){
				
			}else if(pRow==1){
				squareId+=24;
			}else if(pRow==2){
				squareId+=64;
			}
			
			if(pRow==0){
				result[0]="1";
				result[1]="Player is on the Inner Track, do nothing.";
			}else if(pRow==1){
				p.row=0;
				p.position=((SquareChance)board.getSquareFromBoard(squareId)).upPos;
				result[0]="5";
				result[1]="Player moved to  1 track above.";
			}else if(pRow==2){
				p.row=1;
				p.position=((SquareChance)board.getSquareFromBoard(squareId)).upPos;
				result[0]="5";
				result[1]="Player moved to  1 track above.";
			}
			board.pullPushChance();
		} 
		
		return result;
	}
	
	public String[] applyCard14(int id,int color){
		String[] result= getResultArray();
		
		int length = board.getNumberOfSameColor(color);
		int[] otherHouses = new int[length];
		otherHouses = board.getOtherProperties(color);
		
		Player p = board.getCurrentPlayer();
		
		for(int i=0;i<board.getPlayers().size();i++){
			if(board.getPlayers().get(i).id == id){
				p = board.getPlayers().get(i);
				break;
			}
		}
		
		Player in = board.getCurrentPlayer();
		for(int i=0;i<board.players.size();i++){
			if(id == board.players.get(i).id){
				in = board.players.get(i);
			}
		}
		
		for(int i=0;i<length;i++){
			if(((SquareProperty)board.getSquareFromBoard(otherHouses[i])).getOwner() != null && p!=null &&((SquareProperty)board.getSquareFromBoard(otherHouses[i])).getOwner().id == p.id){
				((SquareProperty)board.getSquareFromBoard(otherHouses[i])).hurricane(in);
			}
		}
		
		result[0]="1";
		result[1]="Hurricane makes a landfall!!";
		board.pullPushChance();
		return result;
	}
	
	public String[] applyCard18(SquareProperty p){
		String[] result= getResultArray();
		
		if(p.house==0 && p.hotel==0 && p.skyscraper==0){
			p.owner.deleteProperty(p);
			result[0]="1";
			result[1]="Player surrendered one of the property to the bank.";
		}else {
			p.hurricane(p.owner);
			result[0]="1";
			result[1]="Player's one of the property is downgraded.";
			board.pullPushChance();
		}
		
		return result;
	}
	
	public String[] applyCard21(Square sp,Player p){
		String[] result= getResultArray();
		SquareCabCompany sc = (SquareCabCompany)sp;
		if(sc.owner == null){
			p.row = sc.row;
			p.position = sc.position;
			result = sc.buyCabCompany(p);
			result[0] = "1";
		}else {
			p.row = sc.row;
			p.position = sc.position;
			if(sc.cabStand!=0){
				sc.owner.numberOfCabStand -= sc.cabStand;
				for(int i=0;i<sc.owner.cabs.size();i++){
					if(sc.owner.cabs.get(i).id == sc.id){
						sc.owner.cabs.remove(i);
						p.cabs.add(sc);
					}
				}
			sc.owner = p;
			p.numberOfCabStand+=sc.cabStand;
			}
			result[0]="1";
			result[1]="Player took the Cab Company from its owner.";
			board.pullPushChance();
		}
		
		return result;
	}
	
}
