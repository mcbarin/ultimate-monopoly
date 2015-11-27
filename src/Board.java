import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Board {

	public int pool = 0;
	public int totalPlayer;
	public static int gameStatus = -1;
	public ArrayList<Player> players = new ArrayList<Player>();
	public Player currentPlayer;
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
	
	public void debugMode(){
		
		try{
			BufferedReader rd = new BufferedReader(new FileReader("debug.txt"));
			String line = rd.readLine();
			if(line.equals("DEBUG OFF")){
				// Do nothing
			}else if(line.equals("DEBUG ON")){
				// Debug is on.
				
				for(int i=0;i<players.size();i++){
				rd.readLine(); // empty line
				
				// next line is player id.
				line = rd.readLine();
				StringTokenizer ss = new StringTokenizer(line);
				ss.nextToken();
				int playerid = Integer.parseInt(ss.nextToken());
				Player p = players.get(playerid);
				
				// next line is "row position money"
				line = rd.readLine();
				StringTokenizer s1= new StringTokenizer(line);
				int row = Integer.parseInt(s1.nextToken());
				int position = Integer.parseInt(s1.nextToken());
				int money = Integer.parseInt(s1.nextToken());
				p.row=row;
				p.position=position;
				p.money=money;
				
				// next line is how many properties are added.
				line = rd.readLine();
				int propertyNumber = Integer.parseInt(line);				
				for(int i1=0;i1<propertyNumber;i1++){
					line = rd.readLine();
					StringTokenizer st = new StringTokenizer(line);
					st.nextToken();//property part is omitted.
					int sqid = Integer.parseInt(st.nextToken());
					int houseN = Integer.parseInt(st.nextToken());
					p.addPropertyDebug(sqid, houseN);
				}
				
				// next line is how many cards are added.
				line = rd.readLine(); 
				int cardNumber = Integer.parseInt(line);
				
				for(int i1=0;i1<cardNumber;i1++){
					line = rd.readLine();
					StringTokenizer st = new StringTokenizer(line);
					st.nextToken();//card part is omitted.
					int cardid = Integer.parseInt(st.nextToken());
					p.addCardDebug(cardid);
				}
				
			}
			}
			rd.close();	
		}
		
		catch(IOException ex){
			System.out.println("There is an error with the file." + ""+ex);
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
	
	public Square getSquareWithRowAndPosition(int row,int position){
		int x = position;
		if(row==1)
			x+=24;
		if(row==2)
			x+=64;
		
		return getSquareFromBoard(x);
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

public String[] getResultArray(){
		
		String[] result = new String[14];
		for(int i=0;i<14;i++){
			result[i]= "0";
		}
		return result;
	}
	public Square nextSquare(int id){
		int x = id;
		if(id==23){
			x=0;
		}else if(id==63){
			x=24;
		}else if(id==119){
			x=64;
		}else{
			x++;
		}
		return getSquareFromBoard(x);
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
		this.totalPlayer = totalPlayer;
		currentPlayer = players.get(0);
		initializeCards(); // Chance and Community Chest cards are initialized.

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 56; j++) {
				squares[i][j]=null;
			}
		}

		//Square(String type,String name,int id, int position, int row) 
		for (int j = 0; j <24 ; j++) {
			if (j== 0){
				squares[0][j] = new SquareSqueezePlay("SqueezePlay",names[0][j],j,j,0);
			}else if(j == 9 || j== 21 ){
				squares[0][j] = new SquareTransit("Transit",names[0][j],j,j,0);
			}else if( j == 4){
				squares[0][j] = new SquareCommunity("Community",names[0][j],j,j,0);	
			}else if(j == 16 ){
				squares[0][j] = new SquareChance("Chance",names[0][j],j,j,0);
			}else if(j == 3 || j== 15  ){
				squares[0][j] = new SquareUtility("Utility",names[0][j],j,j,0);			
			}else if(j == 6){
				squares[0][j] = new SquareGo("Go",names[0][j],j,j,0);
				//bonus
			}else if(j == 12){
				squares[0][j] = new SquareFree("Free",names[0][j],j,j,0);
				//stock exchange
			}else if(j == 14 ){
				squares[0][j] = new SquareTaxRefund("TaxRefund",names[0][j],j,j,0);
			}else if(j == 18 ){
				squares[0][j] = new SquareTunnel("Tunnel",names[0][j],j,j,0);
			}else if(j == 22){
				squares[0][j] = new SquareReverse("Reverse",names[0][j],j,j,0);
			}else{
				squares[0][j] = new SquareProperty("Property",names[0][j],j,j,0,colors[0][j],price[0][j],rent[0][j]);
			}
		}

		for (int j = 0; j <40 ; j++) {
			if (j== 0){
				squares[1][j] = new SquareGo("Go",names[1][j],j + 24,j,1);
				//go
			}else if(j == 5 || j== 15 ||j == 25 || j== 35 ){
				squares[1][j] = new SquareTransit("Transit",names[1][j],j+24,j,1);
			}else if( j == 2 || j == 17 || j == 33){
				squares[1][j] = new SquareCommunity("Community",names[1][j],j + 24,j,1);
			}else if(j == 7 || j == 22 || j == 36){
				squares[1][j] = new SquareChance("Chance",names[1][j],j + 24,j,1);	
			}else if(j == 12 || j== 28){
				squares[1][j] = new SquareUtility("Utility",names[1][j],j + 24,j,1);			
			}else if(j == 4 || j == 38){
				squares[1][j] = new SquareTax("Tax",names[1][j],j + 24,j,1);
			}else if(j == 20 ){
				squares[1][j] = new SquareFree("Free",names[1][j],j + 24,j,1);
				//free parking
			}else if(j == 30 ){
				squares[1][j] = new SquareRollOnce("RollOnce",names[1][j],j + 24,j,1);
			}else if(j == 10){
				squares[1][j] = new SquareInJail("InJail",names[1][j],j + 24,j,1);
			}else{
				squares[1][j] = new SquareProperty("Property",names[1][j],j+24,j,1,colors[1][j],price[1][j],rent[1][j]);
			}
		}

		((SquareTransit) squares[0][9]).SquareTransitTwin(9,this);
		((SquareTransit) squares[0][21]).SquareTransitTwin(21,this);
		((SquareTransit) squares[1][15]).SquareTransitTwin(39,this);
		((SquareTransit) squares[1][35]).SquareTransitTwin(59,this);

		for (int j = 0; j <56 ; j++) {
			if (j==0 || j == 5 || j == 15 || j == 48){
				squares[2][j] = new SquareFree("Free",names[2][j],j + 64 ,j,2);
				//subway(0), bus ticket(5 48), auction(15),
			}else if(j == 7 || j== 35 ){
				squares[2][j] = new SquareTransit("Transit",names[2][j],j+64,j,2);
				((SquareTransit) squares[2][j]).SquareTransitTwin(j+64,this);
			}else if( j == 2 || j== 24 || j == 36 || j == 46){
				squares[2][j] = new SquareCommunity("Community",names[2][j],j + 64,j,2);
			}else if(j == 10 || j == 21 || j == 30 || j == 54){
				squares[2][j] = new SquareChance("Chance",names[2][j],j + 64,j,2);
			}else if(j == 11 || j== 18 || j == 39 || j== 49  ){
				squares[2][j] = new SquareUtility("Utility",names[2][j],j + 64,j,2);
			}else if(j == 28 || j== 51 ){
				squares[2][j] = new SquareGo("Go",names[2][j],j + 64,j,2);
				//pay day(28) birthday(51)
			}else if(j == 14 ){
				squares[2][j] = new SquareTunnel("Tunnel",names[2][j],j + 64,j,2);	
			}else if(j == 6 || j == 22 || j == 34 || j == 50){
				squares[2][j] = new SquareCabCompany("CabCompany",names[2][j],j + 64,j,2);
			}else if(j == 42 ){
				squares[2][j] = new SquareGoToJail("GoToJail",names[2][j],j + 64,j,2);
			}else{
				squares[2][j] = new SquareProperty("Property",names[2][j],j+64,j,2,colors[2][j],price[2][j],rent[2][j]);
			}
		}

		((SquareTransit) squares[1][5]).SquareTransitTwin(29,this);
		((SquareTransit) squares[1][25]).SquareTransitTwin(49,this);
		
		//debugMode();
	}
	
	public void setCurrentPlayer(Player p){
		this.currentPlayer = p;
	}
	
	public Player getCurrentPlayer(){
		return this.currentPlayer;
	}
	
	public Player nextPlayer(){
		int npID = (this.currentPlayer.id+1)%totalPlayer;
		Player np = players.get(npID);
		if(MonopolyGame.dieOne!=MonopolyGame.dieTwo){
			while(np.isPlaying==false){
				npID = (npID+1)%totalPlayer;
				np = players.get(npID);
			}
		}
		
		this.currentPlayer = np;
		GUI.playerChange = true;
		
		return np;
	}
	

}



