package domain;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import java.util.*;

public class GUI {
	

	public boolean start=true;
	
	private int dieOne=1, dieTwo=1, dieSpeed=1;
	public static int rollOnceCard, rollOnceDie;
	
	//buttons = 
	//rollDice,buy,buyChance,sell,no,
	//rollOnce,pullChance,pullCommunity,mortgage,unMortgage,
	//yes,start,load,save

	
	//Players
	private ArrayList<Player> players;
	private Player cP;
	public static boolean playerChange;
	private Board board;
	
	private int dice[] = {dieOne, dieTwo, dieSpeed};
	
	
	private JFrame mainFrame = new JFrame("BECB Monopoly");
	Container contentPane = mainFrame.getContentPane();

	private GUIPanel panel = new GUIPanel(board,dice);

	private JTextField initalNumberofPlayers;
	private JTextArea currentMessage = new JTextArea();
	private JLabel currentPlayer = new JLabel();
	
	private JLabel titles[] = new JLabel[13];


	public ArrayList<Square> cpFreeProperties;
	public ArrayList<Square> cpMortgagedProperties;
	
	

	public JRadioButton[] savedGamesElt = new JRadioButton[13];
	public ButtonGroup savedGamesGroup = new ButtonGroup();
	
	
	public final static int MAX_PROPERTIES = 86;
	public JRadioButton[] userFreeProperties = new JRadioButton[MAX_PROPERTIES];
	public ButtonGroup freePropertiesButtonGroup = new ButtonGroup();
	public JPanel sellpanel = new JPanel(new GridLayout(0,1));
    JScrollPane scrollPane = new JScrollPane(sellpanel);
    

	public JRadioButton[] userMortgagedProperties = new JRadioButton[MAX_PROPERTIES];
	public ButtonGroup mortgagedPropertiesButtonGroup = new ButtonGroup();
	public JPanel unmortgagepanel = new JPanel(new GridLayout(0,1));
    JScrollPane scrollPaneUnmortgage = new JScrollPane(unmortgagepanel);

    

	public JRadioButton[] userStocks = new JRadioButton[6];
	public ButtonGroup userStocksButtonGroup = new ButtonGroup();
	public JPanel userStockspanel = new JPanel(new GridLayout(0,1));
    JScrollPane userStocksscrollPane = new JScrollPane(userStockspanel);
    
    
    
	public JRadioButton[] debugStocks = new JRadioButton[6];
	public ButtonGroup debugStocksButtonGroup = new ButtonGroup();
	public JPanel debugStockspanel = new JPanel(new GridLayout(0,1));
	public JTextField debugStocksNum= new JTextField();
    

	public JRadioButton[] taxiRideDestinations = new JRadioButton[9];
	public ButtonGroup taxiRideGroup = new ButtonGroup();
	public JPanel taxiRidePanel = new JPanel(new GridLayout(0,1));
    JScrollPane scrollPaneTaxiRide = new JScrollPane(taxiRidePanel);
    

	public JRadioButton[] cabDestinations = new JRadioButton[4];
	public ButtonGroup cabGroup = new ButtonGroup();
	public JPanel cabPanel = new JPanel(new GridLayout(0,1));
    JScrollPane cabScrollPane = new JScrollPane(cabPanel);


	public JRadioButton[] hurElt = new JRadioButton[20];
	public ButtonGroup hurGroup = new ButtonGroup();
	public JPanel hurPanel = new JPanel(new GridLayout(0,1));
    JScrollPane hurScrollPane = new JScrollPane(hurPanel);

	public JRadioButton[] dProps = new JRadioButton[120];
	public ButtonGroup dPropsGroup = new ButtonGroup();
	public JPanel dPropsPanel = new JPanel(new GridLayout(0,1));
    JScrollPane dPropsScrollPane = new JScrollPane(dPropsPanel);
	public JRadioButton[] dPropsType = new JRadioButton[5];
	public ButtonGroup dPropsTypeGroup = new ButtonGroup();
	public JTextField row = new JTextField();
	public JTextField position = new JTextField();
	public JTextField money = new JTextField();
	
	public JTextField dieOneF = new JTextField();
	public JTextField dieTwoF = new JTextField();
	public JTextField dieSpeedF = new JTextField();
	public JTextField saveName = new JTextField();
	public JTextField houseNum = new JTextField();
	public JTextField debugCards = new JTextField();
	
	

	public JTextField[] auctionBids = new JTextField[12];
    public JLabel auctionNames[] = new JLabel[12];
	public JRadioButton[] eComps = new JRadioButton[6];
	public ButtonGroup eCompsGroup = new ButtonGroup();
	public JPanel eCompsPanel = new JPanel(new GridLayout(0,1));
    public JScrollPane eCompsScrollPane = new JScrollPane(eCompsPanel);
    
    

	public JPanel playerStatsPanel = new JPanel();
    JScrollPane scrollPanePlayerStats = new JScrollPane(playerStatsPanel);
    JLabel playerPics[] = new JLabel[12];
    JLabel playerNames[] = new JLabel[12];
    JLabel playerMoneys[] = new JLabel[12];
    JLabel playerPropertiesNames[] = new JLabel[12];
    JLabel playerStocksNames[] = new JLabel[12];
    JLabel playerCardsNames[] = new JLabel[12];
    JLabel statTitle[][] = new JLabel[12][3];

	int c[] = new int[20];
    
	private JButton buttons[];
	
	public static Color colorCodes[] = {new Color(255,255,255),new Color(0,0,0),new Color(128,128,128),new Color(170,68,0),new Color(88,12,57),
										new Color(135,165,215),new Color(239,55,120),new Color(245,128,35),new Color(212,0,0),new Color(255,204,0),
										new Color(9,135,51),new Color(40,78,161),new Color(255,170,170),new Color(128,255,128),new Color(255,230,128),
										new Color(0,128,102),new Color(128,0,51),new Color(170,136,0),new Color(255,179,128),new Color(128,0,0)};
	
	Font font = new Font("Verdana", Font.PLAIN, 12);
	

	
	public GUI(Board board,JButton b[]) throws Exception{
		buttons = b;
		for(int i=0; i<52; i++){
			buttons[i].setVisible(false);
		}
		
		
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);
		contentPane.add(panel);
		
		for(int i=0;i<13;i++){
			 titles[i] = new JLabel();
		}
		
		for(int i=0;i<12;i++){
			 playerNames[i] = new JLabel();
			 playerMoneys[i] = new JLabel();
			 playerPics[i] = new JLabel();
			 playerPropertiesNames[i] = new JLabel();
			 playerStocksNames[i] = new JLabel();
			 playerCardsNames[i] = new JLabel();
			 auctionBids[i] = new JTextField();
			 auctionNames[i] = new JLabel();
			 
			 for(int j=0;j<statTitle[i].length;j++){
				 statTitle[i][j] = new JLabel();
			 }
		}
			
			
		    for(int i = 0; i<MAX_PROPERTIES; i++){
		        userFreeProperties[i] = new JRadioButton("text" + i);
		        userFreeProperties[i].setActionCommand(Integer.toString(i));
		        userFreeProperties[i].setVisible(false);
		    	userFreeProperties[i].setForeground(Color.WHITE);
		    	userFreeProperties[i].setBackground(Color.GRAY);
		    	freePropertiesButtonGroup.add(userFreeProperties[i]);
		        sellpanel.add(userFreeProperties[i]);
		     }
			scrollPane.setSize(240, 100);
			sellpanel.setBackground(Color.DARK_GRAY);
			userFreeProperties[0].setSelected(true);
			scrollPane.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.black));
			scrollPane.setAutoscrolls(true);
			scrollPane.setVisible(false);
			panel.add(scrollPane);
		
		    placeButton(b[3],"Sell",1025, 95, 115, 25,false);
			placeButton(b[8],"Mortgage",1150, 95, 115, 25,false);
			
			for(int i=0; i<12; i++){
				
				playerPics[i] = new JLabel();
				playerPics[i].setIcon(new ImageIcon("img/p"+Integer.toString(i+1)+".png"));
				playerPics[i].setSize(40, 40);
				playerPics[i].setLocation(5,i*75+5);
				playerStatsPanel.add(playerPics[i]);

				//playerNames[i] = new JLabel(players.get(i).name.toUpperCase());
				playerNames[i] = new JLabel("");
				playerNames[i].setLocation(50,i*75+5);
				playerNames[i].setFont(new Font("Calibri", Font.BOLD, 20));
				playerNames[i].setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, Color.black));
				playerStatsPanel.add(playerNames[i]);
				


				//playerMoneys[i] = new JLabel("$"+Integer.toString(players.get(i).money).toUpperCase());
				playerMoneys[i] = new JLabel("$");
				playerMoneys[i].setLocation(150,i*75+2);
				playerMoneys[i].setFont(new Font("Calibri", Font.BOLD, 18));
				playerMoneys[i].setForeground(new Color(45,128,0));
				playerStatsPanel.add(playerMoneys[i]);
				
				statTitle[i][0].setText("Properties:");
				statTitle[i][0].setFont(new Font("Calibri", Font.BOLD, 18));
				statTitle[i][0].setForeground(new Color(160,0,67));
				playerStatsPanel.add(statTitle[i][0]);

				//playerPropertiesNames[i] = new JLabel(players.get(i).allPropertiesNames.toLowerCase());
				//playerPropertiesNames[i].setToolTipText(players.get(i).allPropertiesNames);
				playerPropertiesNames[i] = new JLabel("");
				playerPropertiesNames[i].setToolTipText("");
				playerPropertiesNames[i].setLocation(0,i*75+35);
				playerPropertiesNames[i].setFont(new Font("Calibri", Font.PLAIN, 18));
				playerStatsPanel.add(playerPropertiesNames[i]);
				
				statTitle[i][1].setText("Stocks:");
				statTitle[i][1].setFont(new Font("Calibri", Font.BOLD, 18));
				statTitle[i][1].setForeground(new Color(160,0,67));
				playerStatsPanel.add(statTitle[i][1]);
				
				
				playerStocksNames[i] = new JLabel("");
				playerStocksNames[i].setToolTipText("");
				playerStocksNames[i].setLocation(0,i*75+35);
				playerStocksNames[i].setFont(new Font("Calibri", Font.PLAIN, 18));
				playerStatsPanel.add(playerStocksNames[i]);
				
				statTitle[i][2].setText("Cards:");
				statTitle[i][2].setFont(new Font("Calibri", Font.BOLD, 18));
				statTitle[i][2].setForeground(new Color(160,0,67));
				playerStatsPanel.add(statTitle[i][2]);
				
				
				playerCardsNames[i] = new JLabel("");
				playerCardsNames[i].setToolTipText("");
				playerCardsNames[i].setLocation(0,i*75+35);
				playerCardsNames[i].setFont(new Font("Calibri", Font.PLAIN, 18));
				playerStatsPanel.add(playerCardsNames[i]);
			}
			
			playerStatsPanel.setSize(6500,74);
			scrollPanePlayerStats.setSize(540, 74);
			scrollPanePlayerStats.setLocation(725, 630);
			scrollPanePlayerStats.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.black));
			scrollPanePlayerStats.setVisible(false);
			panel.add(scrollPanePlayerStats);
			
			
			
			
			

			
			
			//Taxi Ride List
			///////////////////////////////
			String taxiRideNames[] = {"FREE PARKING", "PENNSYLVANIA RAILROAD" , "SHORT LINE", "READING RAILROAD", "B&O RAILROAD", "CHECKER CAB CO." , "BLACK & WHITE CAB CO." , "YELLOW CAB CO." ,"UTE CAB CO."};
				
			for(int i=0; i<taxiRideNames.length;i++){
				taxiRideDestinations[i] = new JRadioButton(taxiRideNames[i]);
				taxiRideDestinations[i].setActionCommand(taxiRideNames[i]);
				taxiRideDestinations[i].setForeground(Color.WHITE);
				if(i%2==0){taxiRideDestinations[i].setBackground(Color.DARK_GRAY);
				}else{taxiRideDestinations[i].setBackground(Color.GRAY);}
				
				taxiRideGroup.add(taxiRideDestinations[i]);
				taxiRidePanel.add(taxiRideDestinations[i]);
			}
			scrollPaneTaxiRide.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
			taxiRidePanel.setBackground(Color.DARK_GRAY);
			taxiRideDestinations[0].setSelected(true);
			scrollPaneTaxiRide.setAutoscrolls(true);
			scrollPaneTaxiRide.setVisible(false);
			scrollPaneTaxiRide.setLocation(725, 170);
			scrollPaneTaxiRide.setSize(290, 100);
			panel.add(scrollPaneTaxiRide);
			placeButton(b[18],"Take a Cab",735,280, 130, 25,false);
			placeButton(b[19],"No",875, 280, 130, 25,false);
			
			
			
			
			
			
			
			//Stock Companies List
			///////////////////////////////

			for(int i=0; i<6;i++){
				Company compa = board.bank.companies.get(i);
				eComps[i] = new JRadioButton(compa.name);
				eComps[i].setActionCommand(compa.name);
				eComps[i].setForeground(Color.WHITE);
				eComps[i].setVisible(false);
						
				if(i%2==0){eComps[i].setBackground(Color.DARK_GRAY);
				}else{eComps[i].setBackground(Color.GRAY);}
				
				eCompsGroup.add(eComps[i]);
				eCompsPanel.add(eComps[i]);
			}
			eCompsScrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
			eCompsPanel.setBackground(Color.DARK_GRAY);
			eComps[0].setSelected(true);
			eCompsScrollPane.setAutoscrolls(true);
			eCompsScrollPane.setVisible(false);
			eCompsScrollPane.setLocation(725, 170);
			eCompsScrollPane.setSize(290, 100);
			panel.add(eCompsScrollPane);
			placeButton(b[30],"Buy",735,280, 130, 25,false);
			placeButton(b[31],"No",875, 280, 130, 25,false);
			
			


			for(int i=0; i<12;i++){
				auctionNames[i].setText(board.playerNames[i]);
				auctionNames[i].setSize(128, 24);
				auctionNames[i].setLocation(725, 180+27*i);
				auctionNames[i].setFont(new Font("Arial", Font.BOLD, 15));
				auctionNames[i].setHorizontalAlignment(JLabel.RIGHT);
				auctionNames[i].setForeground(Color.WHITE);
				auctionNames[i].setVisible(false);
				auctionBids[i].setSize(48, 24);
				auctionBids[i].setHorizontalAlignment(JLabel.CENTER);
				auctionBids[i].setBorder(null);
				auctionBids[i].setLocation(858, 180+27*i);
				auctionBids[i].setText("0");
				auctionBids[i].setVisible(false);
				panel.add(auctionNames[i]);
				panel.add(auctionBids[i]);
						
			}
			

			placeButton(b[32],"Send Bids",875, 280, 130, 25,false);
			
			

			placeButton(b[27],"Send Bids",875, 280, 130, 25,false);
			placeButton(b[36],"Send Bids",875, 280, 130, 25,false);
			
			
			//////////////////////////////////////////
			
			
			
			
			
			
			
			
			
			//UNMortgage Pane
		    for(int i = 0; i<MAX_PROPERTIES; i++){
		    	userMortgagedProperties[i] = new JRadioButton("text" + i);
		    	userMortgagedProperties[i].setActionCommand(Integer.toString(i));
		    	userMortgagedProperties[i].setVisible(false);
		    	userMortgagedProperties[i].setForeground(Color.WHITE);
		    	mortgagedPropertiesButtonGroup.add(userMortgagedProperties[i]);
		    	unmortgagepanel.add(userMortgagedProperties[i]);
		     }
		    scrollPaneUnmortgage.setSize(240, 100);
		    userMortgagedProperties[0].setSelected(true);
		    unmortgagepanel.setBackground(Color.DARK_GRAY);
			scrollPaneUnmortgage.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.black));
			scrollPaneUnmortgage.setAutoscrolls(true);
			scrollPaneUnmortgage.setVisible(false);
			panel.add(scrollPaneUnmortgage);
		
			placeButton(b[9],"UNMortgage",105, 95, 240, 25,false);
			
			
			
			
			
			//SellStock Pane
		    for(int i = 0; i<6; i++){
		    	userStocks[i] = new JRadioButton("text" + i);
		    	userStocks[i].setActionCommand(board.bank.names[i]);
		    	userStocks[i].setVisible(false);
		    	userStocks[i].setForeground(Color.WHITE);
		    	userStocksButtonGroup.add(userStocks[i]);
		    	userStockspanel.add(userStocks[i]);
		     }
		    userStocksscrollPane.setSize(240, 100);
		    userStocks[0].setSelected(true);
		    userStockspanel.setBackground(Color.DARK_GRAY);
		    userStocksscrollPane.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.black));
		    userStocksscrollPane.setAutoscrolls(true);
		    userStocksscrollPane.setVisible(false);
			panel.add(userStocksscrollPane);
		
			placeButton(b[33],"Sell Share of Stock",105, 95, 240, 25,false);
			
			
	
			
			//cabbo
			////////////////////////////////
			int ids[] = {70,86,99,114};
		    for(int i = 0; i<4; i++){
		    	Square sq = board.getSquareFromBoard(ids[i]);
		    	cabDestinations[i] = new JRadioButton(sq.name);
		    	cabDestinations[i].setActionCommand(Integer.toString(ids[i]));
		    	cabDestinations[i].setVisible(true);
		    	cabDestinations[i].setForeground(Color.WHITE);
		    	cabDestinations[i].setBackground(Color.DARK_GRAY);
		    	cabGroup.add(cabDestinations[i]);
		    	cabPanel.add(cabDestinations[i]);
		     }
		    cabScrollPane.setLocation(725, 170);
		    cabScrollPane.setSize(290, 100);
		    cabDestinations[0].setSelected(true);
		    cabPanel.setBackground(Color.DARK_GRAY);
		    cabScrollPane.setBorder(BorderFactory.createMatteBorder(0, 0,0, 0, Color.black));
		    cabScrollPane.setAutoscrolls(true);
		    cabScrollPane.setVisible(false);
			panel.add(cabScrollPane);
		
			//////////////////////////////////
			
			
			//hurro
			////////////////////////////////
		    for(int i = 0; i<20; i++){
		    	hurElt[i] = new JRadioButton("");
		    	hurElt[i].setVisible(false);
		    	hurGroup.add(hurElt[i]);
		    	hurPanel.add(hurElt[i]);
		     }
		    hurScrollPane.setLocation(725, 170);
		    hurScrollPane.setSize(290, 100);
		    hurElt[0].setSelected(true);
		    hurPanel.setBackground(Color.DARK_GRAY);
		    hurScrollPane.setBorder(BorderFactory.createMatteBorder(0, 0,0, 0, Color.black));
		    hurScrollPane.setAutoscrolls(true);
		    hurScrollPane.setVisible(false);
			placeButton(b[23],"Hurricane",735, 280, 270, 25,false);
			panel.add(hurScrollPane);
		
			//////////////////////////////////

		    currentMessage.setLineWrap( true );
		    currentMessage.setWrapStyleWord( true );
		    currentMessage.setMargin(new Insets(10,10,10,10));
		    currentMessage.setForeground(new Color(180,255,240));
		    currentMessage.setBackground(new Color(50,50,50));
		    currentMessage.setEditable(false);
		    currentMessage.setVisible(false);
	        currentMessage.setFont(new Font("Verdana", Font.PLAIN, 16));
	        currentMessage.setSize(290, 160);
	        currentMessage.setLocation(725, 10);
	        if(cP!=null)
	        currentMessage.setText(cP.name);
	        panel.add(currentMessage);
	        
	        
	
	        
	
	        currentPlayer.setIcon(new ImageIcon("img/p1.png"));
	        currentPlayer.setSize(40, 40);
	        currentPlayer.setLocation(1025, 5);
	        currentPlayer.setVisible(false);
	        panel.add(currentPlayer);
	        //cp money

	        placeLabel(titles[2],"",240,24,1025,13,new Color(255,255,255),Color.GRAY,new Font("Verdana", Font.PLAIN, 18));
	        titles[2].setHorizontalAlignment(JLabel.RIGHT);
	        panel.add(titles[2]);
	        
	        //mortgage
	        panel.add(titles[0]);
	        //unmortgage
	        panel.add(titles[1]);
	        //sellstock
	        panel.add(titles[3]);
			
	
			placeButton(b[0],"Roll Dice!",735, 180, 270, 40,false);
			

			placeButton(b[37],"Play Monopoly Guy",735, 180, 270, 24,false);
			
			placeButton(b[5],"Roll Once",735, 180, 270, 40,false);
			placeButton(b[1],"Buy",735, 188, 130, 25,false);
			placeButton(b[10],"Yes",735, 188, 130, 25,false);
			placeButton(b[20],"Yes",735, 188, 130, 25,false);
			placeButton(b[21],"Yes",735, 188, 130, 25,false);
			placeButton(b[22],"Go to Company",735, 280, 270, 25,false);
			placeButton(b[4],"No",875, 188, 130, 25,false);
			placeButton(b[2],"Buy with Cance Card",735, 188, 270, 25,false);		

			placeButton(b[28],"Take $100",735, 180, 270, 25,false);
			placeButton(b[29],"Go to the Nearest Cab Company",735, 211, 270, 25,false);
			
			placeButton(b[6],"Pull Chance Card",735, 180, 270, 40,false);
			b[6].setBackground(new Color(208,200,0));
			placeButton(b[7],"Pull Community Chest Card",735, 180, 270, 40,false);
			b[7].setBackground(new Color(208,200,0));
	
			placeButton(b[14],"",735, 188, 80, 25,false);
			placeButton(b[15],"",830, 188, 80, 25,false);
			placeButton(b[16],"",925, 188, 80, 25,false);
			
			
			

			placeButton(b[38],"Build",735,180, 130, 25,false);
			placeButton(b[39],"No",875, 180, 130, 25,false);

			//////////////////////////////////////////////////////
			//////////////////////////////////////////////////////
			//Very Beginning of the Game
			placeLabel(titles[12],"Start a Brand New Game:",240,24,735,18,new Color(255,255,255),Color.GRAY,new Font("Verdana", Font.PLAIN, 18));
			titles[12].setVisible(false);
			panel.add(titles[12]);
			initalNumberofPlayers = new JTextField();
			initalNumberofPlayers.setSize(300, 40);
			initalNumberofPlayers.setLocation(735, 60);
			initalNumberofPlayers.setBackground(Color.GRAY);
			initalNumberofPlayers.setHorizontalAlignment(JTextField.CENTER);
			initalNumberofPlayers.setFont(new Font("Verdana", Font.BOLD, 18));
			initalNumberofPlayers.setText("Enter number of Players...");
			

			
			b[11].setText("Start Game");
			b[11].setLocation(1045, 60);
			b[11].setSize(200, 40);
			b[11].setVisible(true);
			initalNumberofPlayers.setVisible(false);
			b[11].setVisible(false);
			panel.add(b[11]);
	        panel.add(initalNumberofPlayers);
	        
	        

			placeLabel(titles[11],"or Choose a Saved Game to Play:",400,24,735,118,new Color(255,255,255),Color.GRAY,new Font("Verdana", Font.PLAIN, 18));
			titles[11].setVisible(false);
			panel.add(titles[11]);
			
			
		    for(int i = 0; i<13; i++){
		        savedGamesElt[i] = new JRadioButton("lorem ipsum");
		        savedGamesElt[i].setFont(new Font("Arial", Font.BOLD, 15));
		        savedGamesElt[i].setActionCommand(Integer.toString(i));
		        savedGamesElt[i].setVisible(false);
		        savedGamesElt[i].setForeground(new Color(199,199,0));
		        if(i%2==0){
		        	savedGamesElt[i].setBackground(new Color(62,62,62));
		        }else{
		        	savedGamesElt[i].setBackground(new Color(75,75,75));
		        }
		        

	        	//savedGamesElt[i].setBackground(new Color(218-i*13,218-i*13,218-i*13));
		        savedGamesElt[i].setSize(510, 32);
		        savedGamesElt[i].setLocation(735, 162 + i*32);
		    	savedGamesGroup.add(savedGamesElt[i]);
		        panel.add(savedGamesElt[i]);
		     }
			

			b[12].setText("Load Game");
			b[12].setLocation(1045, 589);
			b[12].setSize(200, 40);
			b[12].setVisible(false);
			panel.add(b[12]);
			
			
			
			
			
			
			
			
			///////////////////////////////////////////
			//DEBUG
			//////////////////////////////////////////
			

			////////////////////////////////
		    //dProps[0].setSelected(true);
		    dPropsPanel.setBackground(Color.DARK_GRAY);
		    dPropsScrollPane.setBorder(BorderFactory.createMatteBorder(0, 0,0, 0, Color.black));
		    dPropsScrollPane.setAutoscrolls(true);
		    dPropsScrollPane.setVisible(false);
		    
		    for(int i=0;i<5;i++){
				dPropsType[i] = new JRadioButton("SkyScraper");
				dPropsType[i].setLocation(1095,44 + i*32);
				dPropsType[i].setSize(120, 32);
				dPropsType[i].setVisible(false);
				dPropsType[i].setBackground(Color.DARK_GRAY);
				dPropsType[i].setForeground(Color.WHITE);
				if(i==1)
					dPropsType[i].addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){houseNum.setEnabled(true);}});
				else
			    	dPropsType[i].addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){houseNum.setEnabled(false);}});
				panel.add(dPropsType[i]);
				dPropsTypeGroup.add(dPropsType[i]);
		    }
		    dPropsType[0].setSelected(true);
		    dPropsType[0].setText("None");
		    dPropsType[0].setActionCommand("0");
		    dPropsType[1].setText("House");
		    dPropsType[1].setSize(61, 32);
		    dPropsType[1].setActionCommand("1");
		    dPropsType[2].setText("Hotel");
		    dPropsType[2].setActionCommand("5");
		    dPropsType[3].setText("SkyScraper");
		    dPropsType[3].setActionCommand("6");
			placeButton(b[24],"Add Property",725, 182, 540, 24,false);
			placeButton(b[25],"Next Player",725, 500, 540, 48,false);
		    
			houseNum.setSize(40, 26);
			houseNum.setLocation(1160, 78);
			houseNum.setBackground(new Color(75,75,75));
			houseNum.setForeground(Color.WHITE);
			houseNum.setHorizontalAlignment(JTextField.CENTER);
			houseNum.setFont(new Font("Verdana", Font.BOLD, 16));
			houseNum.setVisible(false);
			houseNum.setEnabled(false);
			houseNum.setText("#");
			panel.add(houseNum);
			panel.add(dPropsScrollPane);
			

			debugCards.setSize(500, 32);
			debugCards.setLocation(745, 426);
			debugCards.setBackground(new Color(75,75,75));
			debugCards.setForeground(Color.WHITE);
			debugCards.setHorizontalAlignment(JTextField.CENTER);
			debugCards.setFont(new Font("Verdana", Font.BOLD, 16));
			debugCards.setVisible(false);
			debugCards.setText("Enter card ID numbers...");
			panel.add(debugCards);
			

			//DebugStock Pane
		    for(int i = 0; i<6; i++){
		    	debugStocks[i] = new JRadioButton("text" + i);
		    	debugStocks[i].setActionCommand(board.bank.names[i]);
		    	debugStocks[i].setVisible(true);
		    	debugStocks[i].setSize(360,26);
		    	debugStocks[i].setForeground(new Color(199,199,0));
		        if(i%2==1)
		        	debugStocks[i].setBackground(new Color(62,62,62));
		        else
		        	debugStocks[i].setBackground(new Color(75,75,75));
		        
		    	debugStocksButtonGroup.add(debugStocks[i]);
		    	debugStockspanel.add(debugStocks[i]);
		     }
		    debugStockspanel.setSize(360, 156);
		    debugStockspanel.setLocation(725, 216);
		    debugStockspanel.setBackground(Color.DARK_GRAY);
			panel.add(debugStockspanel);
			
			debugStocksNum.setSize(90, 40);
			debugStocksNum.setLocation(1145, 274);
			debugStocksNum.setBackground(new Color(75,75,75));
			debugStocksNum.setForeground(Color.WHITE);
			debugStocksNum.setHorizontalAlignment(JTextField.CENTER);
			debugStocksNum.setFont(new Font("Verdana", Font.BOLD, 18));
			debugStocksNum.setVisible(true);
			debugStocksNum.setText("#");
			panel.add(debugStocksNum);
			placeButton(b[34],"Add Shares of Stock",725,382, 540, 24,false);
			panel.add(b[34]);
			
			
			
			

	        placeLabel(titles[6],"asdasdsad",100,20,725,10,new Color(255,255,255),Color.GRAY,new Font("Verdana", Font.BOLD, 16));
	        titles[6].setHorizontalAlignment(JLabel.CENTER);
	        titles[6].setVisible(false);
	        panel.add(titles[6]);
			row.setSize(60, 24);
			row.setLocation(825, 8);
			row.setBackground(Color.GRAY);
			row.setHorizontalAlignment(JTextField.CENTER);
			row.setFont(new Font("Verdana", Font.BOLD, 16));
			row.setVisible(false);
			row.setText("R");
			position.setSize(60, 24);
			position.setLocation(895, 8);
			position.setBackground(Color.GRAY);
			position.setHorizontalAlignment(JTextField.CENTER);
			position.setFont(new Font("Verdana", Font.BOLD, 16));
			position.setText("S");
			position.setVisible(false);
			money.setSize(120, 24);
			money.setLocation(965,8);
			money.setBackground(Color.GRAY);
			money.setHorizontalAlignment(JTextField.CENTER);
			money.setFont(new Font("Verdana", Font.BOLD, 16));
			money.setText("$");
			money.setVisible(false);


			placeButton(b[35],"Go Auction",735, 275, 270, 25,false);
			
			dieOneF.setBackground(Color.GRAY);
			dieOneF.setHorizontalAlignment(JTextField.CENTER);
			dieOneF.setFont(new Font("Verdana", Font.BOLD, 15));
			dieTwoF.setBackground(Color.GRAY);
			dieTwoF.setHorizontalAlignment(JTextField.CENTER);
			dieTwoF.setFont(new Font("Verdana", Font.BOLD, 15));
			dieSpeedF.setBackground(Color.GRAY);
			dieSpeedF.setHorizontalAlignment(JTextField.CENTER);
			dieSpeedF.setFont(new Font("Verdana", Font.BOLD, 15));
	        dieOneF.setVisible(false);
	        dieTwoF.setVisible(false);
	        dieSpeedF.setVisible(false);
			
			panel.add(row);
			panel.add(position);
			panel.add(money);
			panel.add(dieOneF);
			panel.add(dieTwoF);
			panel.add(dieSpeedF);
			//////////////////////////////////
		
			
	        
	      
		// saveGame Begin


		saveName.setSize(270, 30);
		saveName.setLocation(725, 590);
		saveName.setBackground(Color.GRAY);
		saveName.setHorizontalAlignment(JTextField.CENTER);
		saveName.setFont(new Font("Verdana", Font.BOLD, 14));
		saveName.setVisible(true);
		saveName.setText("Save Name");
		saveName.setVisible(false);
		panel.add(saveName);
		placeButton(b[13],"Save Game",1005, 590, 135, 30,false);
		placeButton(b[26],"X",1235, 590, 30, 30,false);
		b[26].setFont(new Font("Calibri", Font.BOLD, 18));
		b[26].setFocusPainted(false);
		b[26].setBackground(new Color(169,0,0));
		b[26].setForeground(Color.WHITE);
		b[26].setBorder(null);
		b[26].setMargin(new Insets(0,0,0,0));
        
    	
		
    	
        //mainFrame.setResizable(false);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);  
    	
    	refresh();
	}
	public void refresh(){
		if(Board.gameStatus==-1){
			
			
			for(int i=0; i<52; i++){
				buttons[i].setVisible(false);
			}
			
			for(int i=0;i<13;i++){
				 titles[i].setVisible(false);
			}
			scrollPane.setVisible(false);
			scrollPaneUnmortgage.setVisible(false);
			userStocksscrollPane.setVisible(false);
			scrollPaneTaxiRide.setVisible(false);
			cabScrollPane.setVisible(false);
			hurScrollPane.setVisible(false);
			debugStockspanel.setVisible(false);
			dPropsScrollPane.setVisible(false);
			buttons[34].setVisible(false);
		    debugStockspanel.setVisible(false);
		    debugStocksNum.setVisible(false);
			setGUI("","",buttons);
			titles[12].setVisible(true);
			initalNumberofPlayers.setVisible(true);
			buttons[11].setVisible(true);

			debugCards.setVisible(false);
			eCompsScrollPane.setVisible(false);
			

			buttons[32].setVisible(false);

			for(int i=0;i<12;i++){
				auctionBids[i].setVisible(false);
				auctionNames[i].setVisible(false);}

			currentPlayer.setVisible(false);
			titles[2].setVisible(false);
			currentMessage.setVisible(false);
	
			scrollPanePlayerStats.setVisible(false);
			
			String lines[];
			BufferedReader rd;
			int ito = 0;
			try {
				rd = new BufferedReader(new FileReader("./saved_games/info.txt"));
				lines =  readLineArray(rd);
				if(!lines[3].equals("na")){
					buttons[12].setVisible(true);
					titles[11].setVisible(true);}
				
			    for(int i = 0; i<(lines.length-3); i++){
			    	if(lines[i+3]!=null && !lines[i+3].equals("na")){
			    		savedGamesElt[i].setVisible(true);
			    		savedGamesElt[i].setText(lines[i+3]);
			    		ito++;
			    	}else{
			    		savedGamesElt[i].setVisible(false);
			    	}
			    }

		        savedGamesElt[(Integer.parseInt(lines[2])+12)%13].setSelected(true);
		        

		        buttons[12].setLocation(1045, 172 + ito*32);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    
			
		}else{

			buttons[34].setVisible(false);
		    debugStockspanel.setVisible(false);
		    debugStocksNum.setVisible(false);	
			titles[12].setVisible(false);
			titles[11].setVisible(false);

			debugCards.setVisible(true);
		    for(int i = 0; i<13; i++){
		        savedGamesElt[i].setVisible(false);}
			
			initalNumberofPlayers.setVisible(false);
			buttons[11].setVisible(false);
			buttons[12].setVisible(false);
			buttons[26].setVisible(true);
		    row.setVisible(true);
		    position.setVisible(true);
		    money.setVisible(true);
	
		    scrollPanePlayerStats.setVisible(true);
			
			
			players = board.getPlayers();
			cP = board.getCurrentPlayer();
			scrollPanePlayerStats.setVisible(true);

			

			

			
			
			
			for(int i=0; i<players.size(); i++){
				playerNames[i].setText(players.get(i).name.toUpperCase());
				playerMoneys[i].setText("$"+Integer.toString(players.get(i).money).toUpperCase());
				playerPropertiesNames[i].setText(players.get(i).allPropertiesNames.toLowerCase());
				playerStocksNames[i].setText(players.get(i).allStocksNames.toLowerCase());
				playerCardsNames[i].setText(players.get(i).allCardsNames.toLowerCase());
				
				
				
				for(int j=0; j<statTitle[i].length;j++){
					statTitle[i][j].setVisible(true);
				}
			}for(int i=players.size(); i<12; i++){
				playerNames[i].setVisible(false);
				playerMoneys[i].setVisible(false);
				playerPropertiesNames[i].setVisible(false);
				playerStocksNames[i].setVisible(false);
				playerCardsNames[i].setVisible(false);
				playerPics[i].setVisible(false);
				
				for(int j=0; j<statTitle[i].length;j++){
					statTitle[i][j].setVisible(false);
				}
				
			}
	
			    debugStockspanel.setVisible(true);
			    
				if(MonopolyGame.debugLeft==0){
				    debugStockspanel.setVisible(false);
			
				    currentMessage.setVisible(true);
					currentPlayer.setText(cP.name);
					currentPlayer.setVisible(true);
					currentPlayer.setIcon(new ImageIcon("img/p"+Integer.toString(cP.id+1)+".png"));
					titles[2].setText("$"+Integer.toString(cP.money));
					titles[2].setVisible(true);
			        buttons[0].setVisible(true);
			        buttons[37].setVisible(true);
			
			        dieOneF.setVisible(false);
			        dieTwoF.setVisible(false);
			        dieSpeedF.setVisible(false);
			
			        titles[6].setVisible(false);
				    dPropsScrollPane.setVisible(false);
				    row.setVisible(false);
				    position.setVisible(false);
				    money.setVisible(false);
				    
				    for(int i=0;i<5;i++){
				    	dPropsType[i].setVisible(false);}
				    buttons[24].setVisible(false);
				    buttons[25].setVisible(false);
				    saveName.setVisible(true); buttons[13].setVisible(true);


					setGUI("Everything is ready. Let the game begin!","1",buttons);
					MonopolyGame.debugLeft--;
				}
				else if(MonopolyGame.debugLeft<0){

					debugCards.setVisible(false);
				    row.setVisible(false);
				    position.setVisible(false);
				    money.setVisible(false);
				    dPropsScrollPane.setVisible(false);
				    debugStockspanel.setVisible(false);

					houseNum.setVisible(false);
				    if(MonopolyGame.specialConditions[6]){
						eCompsScrollPane.setVisible(true);
						buttons[30].setVisible(true);
						buttons[31].setVisible(true);
						for(int i=0;i<6;i++){
							eComps[5-i].setVisible(true);
							Company compa = board.bank.companies.get(i);
							if(compa.share<6){
								eComps[5-i].isSelected();}
							else {
								eComps[i].setEnabled(false);
								//eComps[i].s
							}
						}
				    }else{
						eCompsScrollPane.setVisible(false);
						buttons[30].setVisible(false);
						buttons[31].setVisible(false);
						for(int i=0;i<6;i++)
							eComps[i].setVisible(false);
				    }
				    
				    
				    
				    if(MonopolyGame.specialConditions[7] || MonopolyGame.specialConditions[8] || MonopolyGame.specialConditions[11]){
						for(int i=0;i<board.players.size();i++){
							auctionBids[i].setVisible(true);
							auctionNames[i].setVisible(true);
						}
				    }else{
							for(int i=0;i<12;i++){
								auctionBids[i].setVisible(false);
								auctionBids[i].setText("0");
								auctionNames[i].setVisible(false);}
					    }

				    
				    if(MonopolyGame.specialConditions[7]){
						buttons[32].setVisible(true);
						
						buttons[32].setLocation(776, 182+27*board.players.size());
						
				    }else{
						buttons[32].setVisible(false);

				    }

				    
				    if(MonopolyGame.specialConditions[8]){
						buttons[27].setVisible(true);
						buttons[27].setLocation(776, 182+27*board.players.size());
				    }else{
						buttons[27].setVisible(false);
				    }

				    
				    if(MonopolyGame.specialConditions[11]){
						buttons[36].setVisible(true);
						buttons[36].setLocation(776, 182+27*board.players.size());
				    }else{
						buttons[36].setVisible(false);
				    }

				    //Birthday Gift
				    if(MonopolyGame.specialConditions[9]){
						buttons[28].setVisible(true);
						buttons[29].setVisible(true);
						
				    }else{
						buttons[28].setVisible(false);
						buttons[29].setVisible(false);
				    }
					
					

				    
				    
					currentPlayer.setIcon(new ImageIcon("img/p"+Integer.toString(cP.id+1)+".png"));
					titles[2].setText("$"+Integer.toString(cP.money));
			
					cpFreeProperties = cP.freeProperties;
					cpMortgagedProperties = cP.mortgagedProperties;
					setProps(45);
					
					
			
					if(MonopolyGame.specialConditions[2]){
						buttons[18].setVisible(true);
						buttons[19].setVisible(true);
						scrollPaneTaxiRide.setVisible(true);
					}else{scrollPaneTaxiRide.setVisible(false);}
					
					
					if(MonopolyGame.specialConditions[4]){
						buttons[22].setVisible(true);
						cabScrollPane.setVisible(true);
					}else{cabScrollPane.setVisible(false);
					buttons[22].setVisible(false);}
					
					
					
					if(MonopolyGame.specialConditions[5]){
						listHurricane();
					}else{hurScrollPane.setVisible(false);
					buttons[23].setVisible(false);}
					
					
					if(MonopolyGame.specialConditions[10]){
						debugProps();
					    dPropsScrollPane.setLocation(725, 170);
					    dPropsScrollPane.setSize(290, 100);
					    houseNum.setVisible(false);
					    dPropsScrollPane.setVisible(true);
						buttons[34].setVisible(false);
					    debugStockspanel.setVisible(false);
					    debugStocksNum.setVisible(false);
				    	houseNum.setVisible(false);
					    for(int i=0;i<5;i++){
					    	dPropsType[i].setVisible(false);}
					    
					    buttons[35].setVisible(true);
				    	
					}else{
					    dPropsScrollPane.setVisible(false);
					    buttons[35].setVisible(false);
					}
					
					

					
					
			
				}else{
			
					setGUI("","",buttons);
			        titles[6].setVisible(true);
			        titles[6].setText(board.players.get(MonopolyGame.initialNumberofPlayers-MonopolyGame.debugLeft).name);
					debugProps();
				    dPropsScrollPane.setLocation(725, 44);
				    dPropsScrollPane.setSize(360, 128);
					buttons[24].setVisible(true);
					buttons[25].setVisible(true);
				    /*
				    if(MonopolyGame.debugLeft==1){
			
				        dieOneF.setVisible(true);
				        dieTwoF.setVisible(true);
				        dieSpeedF.setVisible(true);
				        
						dieOneF.setSize(48, 24);
						dieOneF.setLocation(1095, 8);
						dieOneF.setText("0");
						dieTwoF.setSize(48, 24);
						dieTwoF.setLocation(1153, 8);
						dieTwoF.setText("0");
						dieSpeedF.setSize(48, 24);
						dieSpeedF.setLocation(1211, 8);
						dieSpeedF.setText("0");
				    }*/
					//MonopolyGame.debugLeft--;
					
					
					//System.out.println(MonopolyGame.debugLeft);
				}

		}
				
				panel.repaint();
				
				
	
		
	}

	
	
	
	
	private void placeButton(JButton button, String title, int x, int y, int width, int height, boolean visibility){
		button.setText(title);
		button.setLocation(x, y);
		button.setSize(width, height);
		button.setFocusPainted(false);
		panel.add(button);
	}
	
	public void placeLabel(JLabel l, String t, int w, int h, int x, int y, Color f, Color b, Font font){
		l.setText(t);
		l.setFont(font);
        l.setSize(w, h);
        l.setLocation(x, y);
        l.setBackground(b);
        l.setForeground(f);
	}
	
	
	public int getInitialNumberofPlayers(){
		int a = 2;
		if (initalNumberofPlayers.getText().contains("[a-zA-Z]+") == false && initalNumberofPlayers.getText().length() > 2) {
		    a = 2; 
		}else{
			a = Integer.parseInt(initalNumberofPlayers.getText()); 
		
			if(a<2){a=2;}else if(a>12){a = 12;}
		}
		return a;
		
	}	

	public String getRow(){
		return row.getText();
	}
	public String getPosition(){
		return position.getText();
	}
	public String getMoney(){
		return money.getText();
	}
	public int getDieOneF(){
		int a = Integer.parseInt(dieOneF.getText()); 
		return a;
	}
	public int getDieTwoF(){
		int a = Integer.parseInt(dieTwoF.getText()); 
		return a;
	}
	public int getDieSpeedF(){
		int a = Integer.parseInt(dieSpeedF.getText()); 
		return a;
	}
	public String getSaveName(){
		return saveName.getText(); 
	}

	public void removeSpecialConditions(){

		for(int i=0; i<26; i++){
			MonopolyGame.specialConditions[i] = false;}
	}

	public void setGUI(String cm, String bs, JButton b[]){
		this.currentMessage.setText(cm);
		int l = bs.length();
		if(l>52)
			l = 52;
		for(int i=0; i<l; i++){
				if(bs.charAt(i)=='1'){
					b[i].setVisible(true);}
				else if(bs.charAt(i)=='0'){
					b[i].setVisible(false);
				}

		}

		for(int i=l; i<52; i++){
			if(i!=11)
					b[i].setVisible(false);
		}
		
		if(bs.length()>0 && bs.charAt(0)=='1' && !MonopolyGame.specialConditions[1]){
			saveName.setVisible(true); b[13].setVisible(true); b[26].setVisible(true);

			
	        dieOneF.setVisible(true); dieTwoF.setVisible(true);  dieSpeedF.setVisible(true);
			dieOneF.setSize(48, 24);
			dieOneF.setLocation(788, 230);
			dieOneF.setText("0");
			dieTwoF.setSize(48, 24);
			dieTwoF.setLocation(846, 230);
			dieTwoF.setText("0");
			dieSpeedF.setSize(48, 24);
			dieSpeedF.setLocation(904, 230);
			dieSpeedF.setText("0");
		}else{
			saveName.setVisible(false); b[13].setVisible(false); b[26].setVisible(false);
			dieOneF.setVisible(false); dieTwoF.setVisible(false);  dieSpeedF.setVisible(false);
			}
		
		removeSpecialConditions();
	}

	public void setGUI(String cm[], String bs, JButton b[]){
		setGUI(cm[1],bs,b);

		
	}
	
	
	public void setPlayers(ArrayList<Player> p){
		this.players = p;
	}
	
	public void setDice(int i, int j, int k){
		dieOne = i;
		dieOne = i;
		dieTwo = j;
		dieSpeed = k;
		panel.setDice(i, j, k);
	}
	
	public void setProps(int starty){
		int y = starty;
		
  if(cpFreeProperties.size()>0){	
	  titles[0].setVisible(true);
	  placeLabel(titles[0],"Select a propety to sell or mortgage:",240,20,1025,starty,Color.WHITE,Color.GRAY,font);
        
	  scrollPane.setVisible(true);
	  scrollPane.setLocation(1025,starty+25);
		for(int i = 0; i<cpFreeProperties.size();i++){
  				userFreeProperties[i].setVisible(true);
  				userFreeProperties[i].setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
  				

  				String title = cpFreeProperties.get(i).name.toLowerCase();
  				if(cpFreeProperties.get(i).type=="Property"){
	  				int house = ((SquareProperty)cpFreeProperties.get(i)).house;
	  				if(house==1)
	  					title+=" ➀";
	  				else if(house==2)
	  					title+=" ➁";
	  				else if(house==3)
	  					title+=" ➂";
	  				else if(house==4)
	  					title+=" ➃";
	  				
	  				if(((SquareProperty)cpFreeProperties.get(i)).hotel==1)
	  					title+=" Ⓗ";
	  				
	  				if(((SquareProperty)cpFreeProperties.get(i)).skyscraper==1)
	  					title+=" Ⓢ";
  					
  				
  				}

  				userFreeProperties[i].setForeground(Color.WHITE);
  				userFreeProperties[i].setText(title);
  				if(cpFreeProperties.get(i).color<20 && cpFreeProperties.get(i).color>-1){
  					Color sqColor = colorCodes[cpFreeProperties.get(i).color];
  	  				userFreeProperties[i].setBackground(sqColor);
  	  				if(sqColor.getBlue()>128 && sqColor.getGreen()>128 && sqColor.getRed()>128){
  	  					userFreeProperties[i].setForeground(Color.BLACK);
  	  				}
  				}else{
  					userFreeProperties[i].setBackground(Color.DARK_GRAY);
  				}
  				userFreeProperties[i].setActionCommand(Integer.toString(cpFreeProperties.get(i).id));
  			
    		}
		for(int i=cpFreeProperties.size(); i<MAX_PROPERTIES; i++){
				userFreeProperties[i].setVisible(false);
		}
		buttons[3].setVisible(true); buttons[3].setLocation(1025, starty+135);
		buttons[8].setVisible(true); buttons[8].setLocation(1150, starty+135);

		y +=170;
	  }else{buttons[3].setVisible(false); buttons[8].setVisible(false); 
	  scrollPane.setVisible(false);titles[0].setVisible(false);}
  
  
	
	
  if(cpMortgagedProperties.size()>0){	
	  titles[1].setVisible(true);
	  placeLabel(titles[1],"Select a propety to UNmortgage:",240,20,1025,y,Color.WHITE,Color.GRAY,font);
        
	  scrollPaneUnmortgage.setVisible(true);
	  scrollPaneUnmortgage.setLocation(1025,y+25);
		for(int i = 0; i<cpMortgagedProperties.size();i++){
			userMortgagedProperties[i].setVisible(true);
  				userMortgagedProperties[i].setText(cpMortgagedProperties.get(i).name.toLowerCase());
  				userMortgagedProperties[i].setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
  				

  				userMortgagedProperties[i].setForeground(Color.WHITE);
  				if(cpMortgagedProperties.get(i).color<20 && cpMortgagedProperties.get(i).color>-1){
  					Color sqColor = colorCodes[cpMortgagedProperties.get(i).color];
  					userMortgagedProperties[i].setBackground(sqColor);
  	  				if(sqColor.getBlue()>128 && sqColor.getGreen()>128 && sqColor.getRed()>128){
  	  				userMortgagedProperties[i].setForeground(Color.BLACK);
  	  				}
  				}else{
  					userMortgagedProperties[i].setBackground(Color.DARK_GRAY);
  				}
  				userMortgagedProperties[i].setActionCommand(Integer.toString(cpMortgagedProperties.get(i).id));
  			
    		}
		for(int i=cpMortgagedProperties.size(); i<MAX_PROPERTIES; i++){
			userMortgagedProperties[i].setVisible(false);
		}
		buttons[9].setVisible(true); buttons[9].setLocation(1025, y+135);

		y+=170;
  }else{buttons[9].setVisible(false); scrollPaneUnmortgage.setVisible(false);titles[1].setVisible(false);}
  
  
	
	
  if(cP.hasStock()){	
	  titles[3].setVisible(true);
	  placeLabel(titles[3],"Select a Share of Stock:",240,20,1025,y,Color.WHITE,Color.GRAY,font);
        
	  userStocksscrollPane.setVisible(true);
	  userStocksscrollPane.setLocation(1025,y+25);
	  int abc = 0;
		for(int i = 0; i<6;i++){
			
			if(cP.shares[i]>0){
				userStocks[abc].setVisible(true);
				userStocks[abc].setFont(new Font("Segoe UI Symbol", Font.PLAIN, 13));
				userStocks[abc].setText(board.bank.names[i]+" - "+Integer.toString(cP.shares[i])+"/6");
				userStocks[abc].setActionCommand(board.bank.names[i]);
				userStocks[abc].setForeground(Color.WHITE);
				userStocks[abc].setBackground(Color.GRAY);
				if(abc%2==1)
					userStocks[abc].setBackground(Color.DARK_GRAY);
					
				abc++;
			}
  			
    	}
		for(int i=abc; i<6; i++){
			userStocks[i].setVisible(false);
		}
		buttons[33].setVisible(true); buttons[33].setLocation(1025, y+135);

  	}else{buttons[33].setVisible(false); userStocksscrollPane.setVisible(false);titles[3].setVisible(false);}
	  
	  
}
	
	
	
	public void debugProps(){
		dPropsPanel.removeAll();
		int abc = 0;
	    for(int i = 0; i<120; i++){
	    	dProps[i] = null;
	    	Square sq = board.getSquareFromBoard(i);
	    	
	    	if((MonopolyGame.specialConditions[10] && sq.owner==null && sq.type=="Property") || (!MonopolyGame.specialConditions[10] &&sq.owner==null && ( sq.type=="Property" || sq.type=="Transit" || sq.type=="Utility" || sq.type=="CabCompany"))){
		    	dProps[abc] = new JRadioButton(sq.name);
	    		if(sq.owner==null){
			    	dProps[abc].setActionCommand(Integer.toString(i));
			    	dProps[abc].setVisible(true);
			    	dProps[abc].setForeground(Color.WHITE);
		    		dProps[abc].setBackground(Color.DARK_GRAY);
			    	if(sq.color<20 && sq.color>-1){
			    		dProps[abc].setBackground(colorCodes[sq.color]);
	  	  				if(colorCodes[sq.color].getBlue()>128 && colorCodes[sq.color].getGreen()>128 && colorCodes[sq.color].getRed()>128)
	  	  					dProps[abc].setForeground(Color.BLACK);
	  	  				}
	    		}else{
			    	dProps[abc].setVisible(false);
	    		}
		    	dPropsGroup.add(dProps[abc]);
		    	dPropsPanel.add(dProps[abc]);
		    	abc++;
	    	}
	     }
	    for(int i=0;i<4;i++){
	    	dPropsType[i].setVisible(true);}
	    dPropsScrollPane.setVisible(true);
	    

		buttons[34].setVisible(true);
	    debugStockspanel.setVisible(true);
	    debugStocksNum.setVisible(true);
    	houseNum.setVisible(true);
    	

    	
	    

		
		
		for(int i = 0; i<6; i++){
	    	
	    	if(board.bank.companies.get(i).share<6){
	    		debugStocks[i].setText(board.bank.names[i]+" - "+Integer.toString(6-board.bank.companies.get(i).share)+" available");
	    		debugStocks[i].setEnabled(true);
	    	}else{
	    		debugStocks[i].setText(board.bank.names[i]+ " - not available");
	    		debugStocks[i].setEnabled(false);
	    	}
	    	
	    	debugStocks[i].setActionCommand(board.bank.names[i]);
	    	
	    }
	    
	    
	    
	    
	    
	}
	
	
	
	public void listHurricane(){
		int a=0;
		for(int i=0;i<20;i++){
			c[i] = 0;
		}
		for(int i=0;i<players.size();i++){
			for(int j=0; j<players.get(i).upgradedColors.size(); j++){
				if(c[players.get(i).upgradedColors.get(j)]==0){
					hurElt[a].setText(players.get(i).name);
					hurElt[a].setBackground(colorCodes[players.get(i).upgradedColors.get(j)]);
					hurElt[a].setVisible(true);
	
					hurElt[a].setActionCommand(Integer.toString(players.get(i).upgradedColors.get(j))+"-"+Integer.toString(players.get(i).id));
					a++;
					c[players.get(i).upgradedColors.get(j)]=1;
				}
			}
			
		}
		
		hurScrollPane.setVisible(true);
		buttons[23].setVisible(true);
		
	}
	
	
	public void initGUI(Board b){
		this.board = b;
		players = board.getPlayers();
		cP = board.getCurrentPlayer();
		
		panel.initGUIPanel(this.board);
		
	}
	
	
	private String[] readLineArray(BufferedReader rd) {
		String[] result = null;
		String[] t = new String[1000];
		int count = 0;
		// Your code starts here
	try{
		while(true){
			String line = rd.readLine();
			if(line == null) break;
			t[count] = line;
			count++;
		}
		result = new String[count];
		for(int i=0;i<count;i++){
			result[i] = t[i];
		}
	} catch (IOException e) {
	}
		// Your code ends here
		return result;
	}

}