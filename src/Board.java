import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Board {

	public int pool = 0;
	public ArrayList<Player> players;
	public Square[][] squares = new Square[3][56];
	public String[][] names = {{"SQUEEZE PLAY","THE EMBARCADERO","FISHERMAN'S WHARF","TELEPHONE COMPANY","COMMUNITY CHEST","BEACON STREET","BONUS","BOYLSTON STREET","NEWBURY STREET","PENNSYLVANIA RAILROAD","FIFTH AVENUE","MADISON AVENUE","STOCK EXCHANGE","WALL STREET","TAX REFUND","GAS COMPANY","CHANCE","FLORIDA AVENUE","HOLLAND TUNNEL","MIAMI AVENUE","BISCAYNE AVENUE","SHORT LINE","REVERSE DIRECTION","LOMBARD STREET","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""},
			{"GO","MEDITERRANEAN AVENUE","COMMUNITY CHEST","BALTIC AVENUE","INCOME TAX","READING RAILROAD","ORIENTAL AVENUE","CHANCE","VERMONT AVENUE","CONNECTICUT AVENUE","IN JAIL","ST. CHARLES PLACE","ELECTRIC COMPANY","STATES AVENUE","VIRGINIA AVENUE","PENNSYLVANIA RAILROAD","ST. JAMES PLACE","COMMUNITY CHEST","TENNESSE AVENUE","NEW YORK AVENUE","FREE PARKING","KENTUCKY AVENUE","CHANCE","INDIANA AVENUE","ILLINOIS AVENUE","B&O RAILROAD","ATLANTIC AVENUE","VENTNOR AVENUE","WATER WORKS","MARVIN GARDENS","ROLL ONCE","PACIFIC AVENUE","NORTH CAROLINA AVENUE","COMMUNITY CHEST","PENNSYLVANIA AVENUE","SHORT LINE","CHANCE","PARK PLACE","LUXURY TAX","BOARDWALK","","","","","","","","","","","","","","","",""},
			{"SUBWAY","LAKE STREET","COMMUNITY CHEST", "NICOLLET AVENUE","HENNEPIN AVENUE", "BUS TICKET","CHECKER CAB CO.", "READING RAILROAD","ESPLANADE AVENUE","CANAL STREET","CHANCE","CABLE COMPANY","MAGAZINE STREET","BOURBON STREET","HOLLAND TUNNEL","AUCTION","KATY FREEWAY","WESTHEIMER ROAD","INTERNET SERVICE PROVIDER","KIRBY DRIVE","CULLEN BOULEVARD","CHANCE","BLACK & WHITE CAB CO.","DEKALB AVENUE","COMMUNITY CHEST","ANDREW YOUNG INTL BOULEVARD","DECATUR STREET","PEACHTREE STREET","PAY DAY","RANDOLPH STREET","CHANCE","LAKE SHORE DRIVE","WACKER DRIVE","MICHIGAN AVENUE","YELLOW CAB CO.","B&O RAILROAD","COMMUNITY CHEST","SOUTH TEMPLE","WEST TAMPLE","TRASH COLLECTOR","NORTH TEMPLE","TEMPLE SQUARE","GO TO JAIL","SOUTH STREET","BROAD STREET","WALNUT STREET","COMMUNITY CHEST","MARKET STREET","BUS TICKET","SEWAGE SYSTEM","UTE CAB CO.","BIRTHDAY GIFT","MULHOLLAND DRIVE","VENTURA BOULEVARD","CHANCE","RODEO DRIVE"}};

	public int[][] colors = {{64,0,0,64,64,1,64,1,1,64,2,2,64,2,64,64,64,3,64,3,3,64,64,0,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64},
			{64,4,64,4,64,64,5,64,5,5,64,6,64,6,6,64,7,64,7,7,64,8,64,8,8,64,9,9,64,9,64,10,10,64,10,64,64,11,64,11,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64},
			{64,12,64,12,12,64,64,64,13,13,64,64,13,13,64,64,14,14,64,14,14,64,64,15,64,15,15,15,64,16,64,16,16,16,64,64,64,17,17,64,17,17,64,18,18,18,64,18,64,64,64,64,19,19,64,19}};

	public int[][] price = {{-64,210,250,150,-64,330,-64,330,380,200,430,430,-64,500,-64,150,-64,130,-64,130,150,200,-64,210,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64},
			{-64,60,-64,60,-64,200,100,-64,100,120,-64,140,150,140,160,200,180,-64,180,200,-64,220,-64,220,240,200,260,260,150,280,-64,300,300,-64,320,200,-64,350,-64,400,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64},
			{-64,30,-64,30,60,-64,300,200,90,90,-64,150,120,120,-64,-64,150,150,150,180,180,-64,300,210,-64,210,240,240,-64,270,-64,270,300,300,300,200,-64,330,330,150,360,360,-64,390,390,420,-64,420,-64,150,300,-64,450,480,-64,510}};

	public int[][] rent = {{-64,21,25,15,-64,33,-64,33,38,20,43,43,-64,50,-64,15,-64,13,-64,13,15,20,-64,21,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64},
			{-64,6,-64,6,-64,20,10,-64,10,12,-64,14,15,14,16,20,18,-64,18,20,-64,22,-64,22,24,20,26,26,15,28,-64,30,30,-64,32,20,-64,35,-64,40,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64,-64},
			{-64,3,-64,3,6,-64,30,20,9,9,-64,15,12,12,-64,-64,15,15,15,18,18,-64,30,21,-64,21,24,24,-64,27,-64,27,30,30,30,20,-64,33,33,15,36,36,-64,39,39,42,-64,42,-64,15,30,-64,45,48,-64,51}};

	public String[][] cardDescriptions = {{"Advance to the Nearest Utility","If unowned,you may buy it from the Bank. If owned,roll dice."},{"Go to Jail","Go directly to Jail. Do not pass any Pay Corner. Do not collect any money."},{"Advance to the Nearest Railroad","If unowned, you may buy it from the Bank. If owned, pay the owner twice the rent otherwise due."},
			{"Make General Repairs to all your properties.","$25 per House, Cab Stand, and Transit Station. $100per Hotel and Skyscraper."},{"Get Out of Jail Free!",""},{"Go Back Three (3) Spaces",""},{"School Fees","Pay the Pool $150."},{"Advance to the Stock Exchange","If you pass Pay Day, collect $300 from the Bank."},{"Loan Matures!","Collect $150 from the Bank."},{"You are elected as the Chairperson","Pay each player $50."},{"Occupy","If unowned,you may buy it from the Bank, or put it up for Auction. If owned, pay the owner the normal rent due."},{"Foreclosed Property Sale!","Foreclose on any opponent's mortgaged property. Pay the mortgage value to the bank to claim the property."},
			{"Excellent Accounting","Advance to Tax Refund. Collect ALL of the Pool."},{"Get Rollin'","Advance to Roll Once.Roll the dice."},{"Hurricane makes landfall!","Remove 1 House from each property in any player's 1 color group.(Downgrade Skyscrapers to Hotels; Hotels to 4 houses.)"},{"Property Taxes","Pay $25 to the Pool for each unmortgaged property you own."},{"HEY! TAXI!!","Advance to Black & White Cab Co. If unowned, you may buy it from the Bank. If owned, pay the owner the normal rent due."},{"Social Media Fail!","Someone posting to your company's official online presence made you look bad. Pay each other player $50 to restore good PR."},{"Assets Seized!","Surrender any one undeveloped, unmortgaged property -or any one building to the Bank. If you do not own property -go directly to Jail."},{"Win the Marathon!","Take a victory lap around the board (on your current Track) and collect the Pay Corner income from the Bank."},
			{"MARDI GRAS!","Everyone has to see the parade of Rex, King of Carnival. All players must move directly to Canal Street."},{"Taxi Wars are not Fare!","Take any 1 Cab Company from any player. If none are owned, purchase your choice from the bank. If you pass a Pay Corner, collect your income."},{"Zero Dollars Down!","Built 1 FREE house on any property in a monopoly you own."},{"Comped Room","The next time you land on anyone else's property, you are excused from paying rent."},{"Changing Lanes","Move directly to the space that is 1 Track below this one. If you are on the Outer Track, do nothing."},{"Changing Lanes","Move directly to the space that is 1 Track below this one. If you are on the Inner Track, do nothing."},{"You Inherit $100","Collect $100 from the Bank."},{"Happy Birthday!","Collect $10 from each player, and move to the Birthday Gift space and follow the instructions."},
			{"Doctor's Fee","Pay $50 to the Pool."},{"April 15, Taxes Due!","Move directly to Income Tax, (do not pass any Pay Corner, do not collect any money), and pay the fine -OR- go to directly to Jail."},{"A Moving Experience","Move to Any Transportation Property(Railroad or Cab Co.). If unowned, you may purchase it. If owned, pay rent."},{"Changing Lanes","Move directly to the space that is 1 Track below this one. If you are on the Outer Track, do nothing."},{"Changing Lanes","Move directly to the space that is 1 Track below this one. If you are on the Inner Track, do nothing."},{"House Condemned","The city condemns one of your houses. Sell one house back to the Bank at 1/2 the price you paid for it. (Houses only. If you donnot own any houses, do nothing.)"},{"Special Online Pricing","The next time you land on anyone else's railroad, only pay 1/2 the rent."},{"REVERSE RENT!","Collect the rent due when you land on another player's property."},
			{"Assessed for Street Repairs","$25 per Cab Stand & Transit Station, $40 per House, $115 per Hotel, and $100 per Skyscraper."},{"Get Out of Jail Free!",""},{"Go to Jail!","Go directly to Jail. Do not pass any Pay Corner. Do not collect any money."},{"Advance to the Stock Exchange","If you pass Pay Day collect $300."},{"Tornado Hits!","Remove one House from each property in any 1 of your color groups. (Downgrade Skyscrapers to Hotels; Hotels to 4 houses.)"},{"Roll 3!",""}};

	Queue<CardCommunity> communityDeck = new LinkedList<CardCommunity>();
	Queue<CardChance> chanceDeck = new LinkedList<CardChance>();
	
	String[] playerNames = {"Alpha","Beta","Gamma","Delta","Epsilon","Zeta","Eta","Theta","Iota","Kappa","Lambda","Pi"};
	
	
	public void initializePlayers(int totalPlayer){
		for(int i=0;i<totalPlayer;i++){
			Player p = new Player(playerNames[i],3200,i,1,0,this);
			players.add(p);
		}
	}
	
	public void initializeCards(){
		for(int i=0;i<42;i++){
			if(i<26){//ChanceCards
				CardChance c = new CardChance(i,cardDescriptions[i][0],cardDescriptions[i][1],this);
				chanceDeck.add(c);	
				Collections.shuffle((List<?>) chanceDeck);
			}else{//CommunityChestCards
				CardCommunity c = new CardCommunity(i,cardDescriptions[i][0],cardDescriptions[i][1],this);
				communityDeck.add(c);
				Collections.shuffle((List<?>) communityDeck);
			}
		}
	}
	
	public CardChance peekChance(){

		CardChance c = chanceDeck.peek();
		return c;
	}
	
	public CardCommunity peekCommunity(){

		CardCommunity c = communityDeck.peek();
		return c;
	}
	
	public void pullPushChance(){
		CardChance c = chanceDeck.poll();
		chanceDeck.add(c);
	}
	
	public void pullPushCommunity(){
		CardCommunity c = communityDeck.poll();
		communityDeck.add(c);
	}
	
	
	public int[] getOtherProperties(int color) {
		// TODO Auto-generated method stub
		int length = getNumberOfSameColor(color);
		int[] result = new int[length];
		int x=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<56;j++){
				if(colors[i][j]==color){
					result[x] = squares[i][j].id;
					x++;
				}
			}
		}
		return result;

	}

	public Square getSquareFromBoard(int id) {
		// TODO Auto-generated method stub
		for(int i=0;i<3;i++){
			for(int j=0;j<56;j++){
				if(squares[i][j]!=null && squares[i][j].id==id){
					return (squares[i][j]);
				}
			}
		}
		return null;
	}

	public int getNumberOfSameColor(int color) {
		// TODO Auto-generated method stub
		int result=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<56;j++){
				if(colors[i][j]==color){
					result++;
				}
			}
		}
		return result;
	}

	public ArrayList<Player> getPlayers(){
		return players;
	}

	public int getNumberOfPlayers(){
		return players.size();
	}

	public void payToPool(int money){
		pool = pool + money;
	}

	public void getHalfPool(Player p){
		p.addMoney((int)Math.ceil(pool/2));
		pool = (int) (pool - Math.ceil(pool/2));
	}

	public void getAllPool(Player p){
		p.addMoney(pool);
		pool = 0;
	}

	public Board(int totalPlayer) {

		// Initialization Processes
		initializePlayers(totalPlayer);
		initializeCards(); // Chance and Community Chest cards are initialized.

		for (int j = 25; j < 56; j++) {
			names[0][j] = ""; colors[0][j] = 64; price[0][j] = -64; rent [0][j] = -64; squares[0][j] = null;
		}
		for (int j = 41; j < 56; j++) {
			names[1][j] = ""; colors[1][j] = 64; price[1][j] = -64; rent [1][j] = -64; squares[1][j] = null;
		}

		for(int i = 0;i < 3;i++){
			for (int j = 0; j <56 ; j++) {
				if(i == 0){
					if (j== 0){
						squares[i][j] = new SquareSqueezePlay("SqueezePlay",names[i][j],j,j,i);
						//sqeeze play
					}else if(j == 9 || j== 21 ){
						squares[i][j] = new SquareTransit("Transit",names[i][j],j,j,i);
						//transit
					}else if( j == 4){
						squares[i][j] = new SquareCommunity("Community",names[i][j],j,j,i);
						//community chest	
					}else if(j == 16 ){
						squares[i][j] = new SquareChance("Chance",names[i][j],j,j,i);
						//chance	
					}else if(j == 3 || j== 15  ){
						squares[i][j] = new SquareUtility("Utility",names[i][j],j,j,i);
						//	utility				
					}else if(j == 6){
						squares[i][j] = new SquareGo("Go",names[i][j],j,j,i);
						//go	
					}else if(j == 12){
						squares[i][j] = new SquareFree("Free",names[i][j],j,j,i);
						//free
					}else if(j == 14 ){
						squares[i][j] = new SquareTaxRefund("TaxRefund",names[i][j],j,j,i);
						//tax refund	
					}else if(j == 18 ){
						squares[i][j] = new SquareTunnel("Tunnel",names[i][j],j,j,i);
						//tunnel	
					}else if(j == 22){
						squares[i][j] = new SquareReverse("Reverse",names[i][j],j,j,i);
						//reverse
					}else{
						squares[i][j] = new SquareProperty("Property",names[i][j],j,j,i,colors[i][j],price[i][j],rent[i][j]);
						//Property
					}
				}else if(i == 1){
					if (j== 0){
						squares[i][j] = new SquareGo("Go",names[i][j],j + 24,j,i);
						//go
					}else if(j == 5 || j== 15 || j == 25 || j == 35 ){
						squares[i][j] = new SquareCommunity("Community",names[i][j],j + 24,j,i);
						//transit
					}else if( j == 2 || j == 17 || j == 33){
						squares[i][j] = new SquareCommunity("Community",names[i][j],j + 24,j,i);
						//community chest	
					}else if(j == 7 || j == 22 || j == 36){
						squares[i][j] = new SquareChance("Chance",names[i][j],j + 24,j,i);
						//chance	
					}else if(j == 12 || j== 28  ){
						squares[i][j] = new SquareUtility("Utility",names[i][j],j + 24,j,i);
						//	utility				
					}else if(j == 4 || j == 38){
						squares[i][j] = new SquareTax("Tax",names[i][j],j + 24,j,i);
						//tax	
					}else if(j == 20 ){
						squares[i][j] = new SquareFree("Free",names[i][j],j + 24,j,i);
						//free
					}else if(j == 30 ){
						squares[i][j] = new SquareRollOnce("RollOnce",names[i][j],j + 24,j,i);
						//roll once	
					}else if(j == 10){
						squares[i][j] = new SquareInJail("InJail",names[i][j],j + 24,j,i);
						//in jail
					}else{
						squares[i][j] = new SquareProperty("Property",names[i][j],j+24,j,i,colors[i][j],price[i][j],rent[i][j]);
						//Propriety
					}
				}else if(i == 2){
					if (j== 0 || j == 5 || j == 15 || j == 48){
						squares[i][j] = new SquareSqueezePlay("SqueezePlay",names[i][j],j + 64 ,j,i);
						//free and 0 = subway
					}else if(j == 7 || j== 35 ){
						squares[i][j] = new SquareCommunity("Community",names[i][j],j + 64,j,i);
						//transit
					}else if( j == 2 || j== 24 || j == 36 || j == 46){
						squares[i][j] = new SquareCommunity("Community",names[i][j],j + 64,j,i);
						//community chest	
					}else if(j == 10 || j == 21 || j == 30 || j == 54){
						squares[i][j] = new SquareChance("Chance",names[i][j],j + 64,j,i);
						//chance	
					}else if(j == 11 || j== 18 || j == 39 || j== 49  ){
						squares[i][j] = new SquareUtility("Utility",names[i][j],j + 64,j,i);
						//	utility				
					}else if(j == 28 || j== 51 ){
						squares[i][j] = new SquareGo("Go",names[i][j],j + 64,j,i);
						//go and birthday
					}else if(j == 14 ){
						squares[i][j] = new SquareTunnel("Tunnel",names[i][j],j + 64,j,i);
						//tunnel	
					}else if(j == 6 || j == 22 || j == 34 || j == 50){
						squares[i][j] = new SquareCabCompany("CabCompany",names[i][j],j + 64,j,i);
						//CAB CO.
					}else if(j == 42 ){
						squares[i][j] = new SquareGoToJail("GoToJail",names[i][j],j + 64,j,i);
						// go to jail	
					}else{
						squares[i][j] = new SquareProperty("Property",names[i][j],j+64,j,i,colors[i][j],price[i][j],rent[i][j]);
						//Property
					}}}}
	}

}



