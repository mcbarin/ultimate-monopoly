import java.util.ArrayList;

public class Board {

	ArrayList<Player> players;
	Square[][] squares = new Square[3][56];
	int pool = 0;

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


		players = new ArrayList<>(totalPlayer);

		String[][] names = {{"SQUEEZE PLAY","THE EMBARCADERO","FISHERMAN'S WHARF","TELEPHONE COMPANY","COMMUNITY CHEST","BEACON STREET","BONUS","BOYLSTON STREET","NEWBURY STREET","PENNSYLVANIA RAILROAD","FIFTH AVENUE","MADISON AVENUE","STOCK EXCHANGE","WALL STREET","TAX REFUND","GAS COMPANY","CHANCE","FLORIDA AVENUE","HOLLAND TUNNEL","MIAMI AVENUE","BISCAYNE AVENUE","SHORT LINE","REVERSE DIRECTION","LOMBARD STREET"},
				{"GO","MEDITERRANEAN AVENUE","COMMUNITY CHEST","BALTIC AVENUE","INCOME TAX","READING RAILROAD","ORIENTAL AVENUE","CHANCE","VERMONT AVENUE","CONNECTICUT AVENUE","IN JAIL","ST. CHARLES PLACE","ELECTRIC COMPANY","STATES AVENUE","VIRGINIA AVENUE","PENNSYLVANIA RAILROAD","ST. JAMES PLACE","COMMUNITY CHEST","TENNESSE AVENUE","NEW YORK AVENUE","FREE PARKING","KENTUCKY AVENUE","CHANCE","INDIANA AVENUE","ILLINOIS AVENUE","B&O RAILROAD","ATLANTIC AVENUE","VENTNOR AVENUE","WATER WORKS","MARVIN GARDENS","ROLL ONCE","PACIFIC AVENUE","NORTH CAROLINA AVENUE","COMMUNITY CHEST","PENNSYLVANIA AVENUE","SHORT LINE","CHANCE","PARK PLACE","LUXURY TAX","BOARDWALK"},
				{"SUBWAY","LAKE STREET","COMMUNITY CHEST", "NICOLLET AVENUE","HENNEPIN AVENUE", "BUS TICKET","CHECKER CAB CO.", "READING RAILROAD","ESPLANADE AVENUE","CANAL STREET","CHANCE","CABLE COMPANY","MAGAZINE STREET","BOURBON STREET","HOLLAND TUNNEL","AUCTION","KATY FREEWAY","WESTHEIMER ROAD","INTERNET SERVICE PROVIDER","KIRBY DRIVE","CULLEN BOULEVARD","CHANCE","BLACK & WHITE CAB CO.","DEKALB AVENUE","COMMUNITY CHEST","ANDREW YOUNG INTL BOULEVARD","DECATUR STREET","PEACHTREE STREET","PAY DAY","RANDOLPH STREET","CHANCE","LAKE SHORE DRIVE","WACKER DRIVE","MICHIGAN AVENUE","YELLOW CAB CO.","B&O RAILROAD","COMMUNITY CHEST","SOUTH TEMPLE","WEST TAMPLE","TRASH COLLECTOR","NORTH TEMPLE","TEMPLE SQUARE","GO TO JAIL","SOUTH STREET","BROAD STREET","WALNUT STREET","COMMUNITY CHEST","MARKET STREET","BUS TICKET","SEWAGE SYSTEM","UTE CAB CO.","BIRTHDAY GIFT","MULHOLLAND DRIVE","VENTURA BOULEVARD","CHANCE","RODEO DRIVE"}};

		int [][] colors = {{64,0,0,64,64,1,64,1,1,64,2,2,64,2,64,64,64,3,64,3,3,64,64,0},
				{64,4,64,4,64,64,5,64,5,5,64,6,64,6,6,64,7,64,7,7,64,8,64,8,8,64,9,9,64,9,64,10,10,64,10,64,64,11,64,11},
				{64,12,64,12,12,64,64,64,13,13,64,64,13,13,64,64,14,14,64,14,14,64,64,15,64,15,15,15,64,16,64,16,16,16,64,64,64,17,17,64,17,17,64,18,18,18,64,18,64,64,64,64,19,19,64,19}};


		int [][] price = {{-64,210,250,150,-64,330,-64,330,380,200,430,430,-64,500,-64,150,-64,130,-64,130,150,200,-64,210},
				{-64,60,-64,60,-64,200,100,-64,100,120,-64,140,150,140,160,200,180,-64,180,200,-64,220,-64,220,240,200,260,260,150,280,-64,300,300,-64,320,200,-64,350,-64,400},
				{-64,30,-64,30,60,-64,300,200,90,90,-64,150,120,120,-64,-64,150,150,150,180,180,-64,300,210,-64,210,240,240,-64,270,-64,270,300,300,300,200,-64,330,330,150,360,360,-64,390,390,420,-64,420,-64,150,300,-64,450,480,-64,510}};

		int [][] rent = {{-64,21,25,15,-64,33,-64,33,38,20,43,43,-64,50,-64,15,-64,13,-64,13,15,20,-64,21},
				{-64,6,-64,6,-64,20,10,-64,10,12,-64,14,15,14,16,20,18,-64,18,20,-64,22,-64,22,24,20,26,26,15,28,-64,30,30,-64,32,20,-64,35,-64,40},
				{-64,3,-64,3,6,-64,30,20,9,9,-64,15,12,12,-64,-64,15,15,15,18,18,-64,30,21,-64,21,24,24,-64,27,-64,27,30,30,30,20,-64,33,33,15,36,36,-64,39,39,42,-64,42,-64,15,30,-64,45,48,-64,51}};

		//name,id,x,y,row

		for(int i = 0;i < 3;i++){
			for (int j = 0; j <56 ; j++) {
				if(i == 0){
					if (j== 0){
						squares[i][j] = new SquareSqueezePlay(names[i][j],j,0,0,i);
						//sqeeze play
					}else if(j == 9 || j== 21 ){
						squares[i][j] = new SquareTransit(names[i][j],j,0,0,i);
						//transit
					}else if( j == 4){
						squares[i][j] = new SquareCommunity(names[i][j],j,0,0,i);
						//community chest	
					}else if(j == 16 ){
						squares[i][j] = new SquareChance(names[i][j],j,0,0,i);
						//chance	
					}else if(j == 3 || j== 15  ){
						squares[i][j] = new SquareUtility(names[i][j],j,0,0,i);
						//	utility				
					}else if(j == 6){
						squares[i][j] = new SquareGo(names[i][j],j,0,0,i);
						//go	
					}else if(j == 12){
						squares[i][j] = new SquareFree(names[i][j],j,0,0,i);
						//free
					}else if(j == 14 ){
						squares[i][j] = new SquareTaxRefund(names[i][j],j,0,0,i);
						//tax refund	
					}else if(j == 18 ){
						squares[i][j] = new SquareTunnel(names[i][j],j,0,0,i);
						//tunnel	
					}else if(j == 22){
						squares[i][j] = new SquareReverse(names[i][j],j,0,0,i);
						//reverse
					}else{
						squares[i][j] = new SquareProperty(names[i][j],j,0,0,i,colors[i][j],price[i][j],rent[i][j]);
						//Property
					}
				}else if(i == 1){
					if (j== 0){
						squares[i][j] = new SquareGo(names[i][j],j + 24,0,0,i);
						//go
					}else if(j == 5 || j== 15 || j == 25 || j == 35 ){
						squares[i][j] = new SquareCommunity(names[i][j],j + 24,0,0,i);
						//transit
					}else if( j == 2 || j == 17 || j == 33){
						squares[i][j] = new SquareCommunity(names[i][j],j + 24,0,0,i);
						//community chest	
					}else if(j == 7 || j == 22 || j == 36){
						squares[i][j] = new SquareChance(names[i][j],j + 24,0,0,i);
						//chance	
					}else if(j == 12 || j== 28  ){
						squares[i][j] = new SquareUtility(names[i][j],j + 24,0,0,i);
						//	utility				
					}else if(j == 4 || j == 38){
						squares[i][j] = new SquareTax(names[i][j],j + 24,0,0,i);
						//tax	
					}else if(j == 20 ){
						squares[i][j] = new SquareFree(names[i][j],j + 24,0,0,i);
						//free
					}else if(j == 30 ){
						squares[i][j] = new SquareRollOnce(names[i][j],j + 24,0,0,i);
						//roll once	
					}else if(j == 10){
						squares[i][j] = new SquareInJail(names[i][j],j + 24,0,0,i);
						//in jail
					}else{
						squares[i][j] = new SquareProperty(names[i][j],j+24,0,0,i,colors[i][j],price[i][j],rent[i][j]);
						//Propriety
					}
				}else if(i == 2){
					if (j== 0 || j == 5 || j == 15 || j == 48){
						squares[i][j] = new SquareSqueezePlay(names[i][j],j + 64 ,0,0,i);
						//free and 0 = subway
					}else if(j == 7 || j== 35 ){
						squares[i][j] = new SquareCommunity(names[i][j],j + 64,0,0,i);
						//transit
					}else if( j == 2 || j== 24 || j == 36 || j == 46){
						squares[i][j] = new SquareCommunity(names[i][j],j + 64,0,0,i);
						//community chest	
					}else if(j == 10 || j == 21 || j == 30 || j == 54){
						squares[i][j] = new SquareChance(names[i][j],j + 64,0,0,i);
						//chance	
					}else if(j == 11 || j== 18 || j == 39 || j== 49  ){
						squares[i][j] = new SquareUtility(names[i][j],j + 64,0,0,i);
						//	utility				
					}else if(j == 28 || j== 51 ){
						squares[i][j] = new SquareGo(names[i][j],j + 64,0,0,i);
						//go and birthday
					}else if(j == 14 ){
						squares[i][j] = new SquareTunnel(names[i][j],j + 64,0,0,i);
						//tunnel	
					}else if(j == 6 || j == 22 || j == 34 || j == 50){
						squares[i][j] = new SquareCabCompany(names[i][j],j + 64,0,0,i);
						//CAB CO.
					}else if(j == 42 ){
						squares[i][j] = new SquareGoToJail(names[i][j],j + 64,0,0,i);
						// go to jail	
					}else{
						squares[i][j] = new SquareProperty(names[i][j],j+64,0,0,i,colors[i][j],price[i][j],rent[i][j]);
						//Propriety
					}}}}
	}


}



