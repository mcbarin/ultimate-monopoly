package domain;
import java.awt.*;
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
	private String[] loadGameLines;
	
	
	public final static int MAX_PROPERTIES = 86;
	public JRadioButton[] userFreeProperties = new JRadioButton[MAX_PROPERTIES];
	public ButtonGroup freePropertiesButtonGroup = new ButtonGroup();
	public JPanel sellpanel = new JPanel(new GridLayout(0,1));
    JScrollPane scrollPane = new JScrollPane(sellpanel);
    

	public JRadioButton[] userMortgagedProperties = new JRadioButton[MAX_PROPERTIES];
	public ButtonGroup mortgagedPropertiesButtonGroup = new ButtonGroup();
	public JPanel unmortgagepanel = new JPanel(new GridLayout(0,1));
    JScrollPane scrollPaneUnmortgage = new JScrollPane(unmortgagepanel);

    

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

	int c[] = new int[20];
    
	private JButton buttons[];
	
	public static Color colorCodes[] = {new Color(255,255,255),new Color(0,0,0),new Color(128,128,128),new Color(170,68,0),new Color(88,12,57),
										new Color(135,165,215),new Color(239,55,120),new Color(245,128,35),new Color(212,0,0),new Color(255,204,0),
										new Color(9,135,51),new Color(40,78,161),new Color(255,170,170),new Color(128,255,128),new Color(255,230,128),
										new Color(0,128,102),new Color(128,0,51),new Color(170,136,0),new Color(255,179,128),new Color(128,0,0)};
	
	Font font = new Font("Verdana", Font.PLAIN, 12);
	

	
	public GUI(Board board,JButton b[]) throws Exception{
		buttons = b;
		for(int i=0; i<39; i++){
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
			 auctionBids[i] = new JTextField();
			 auctionNames[i] = new JLabel();
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
				playerNames[i].setSize(100, 40);
				playerNames[i].setLocation(50,i*75+5);
				playerNames[i].setFont(new Font("Verdana", Font.BOLD, 20));
				playerStatsPanel.add(playerNames[i]);

				//playerMoneys[i] = new JLabel("$"+Integer.toString(players.get(i).money).toUpperCase());
				playerMoneys[i] = new JLabel("$");
				playerMoneys[i].setSize(200, 40);
				playerMoneys[i].setLocation(150,i*75+2);
				playerMoneys[i].setFont(new Font("Lucida Calligraphy", Font.BOLD, 24));
				playerStatsPanel.add(playerMoneys[i]);

				//playerPropertiesNames[i] = new JLabel(players.get(i).allPropertiesNames.toLowerCase());
				//playerPropertiesNames[i].setToolTipText(players.get(i).allPropertiesNames);
				playerPropertiesNames[i] = new JLabel("");
				playerPropertiesNames[i].setToolTipText("");
				playerPropertiesNames[i].setSize(540, 40);
				playerPropertiesNames[i].setLocation(0,i*75+35);
				playerPropertiesNames[i].setFont(new Font("Arial", Font.PLAIN, 16));
				playerPropertiesNames[i].setBorder(BorderFactory.createMatteBorder(0, 0, 2,0, Color.black));
				playerStatsPanel.add(playerPropertiesNames[i]);
			}
			
			playerStatsPanel.setSize(5700,800);
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
			
	
			placeButton(b[0],"Roll Dice!",735, 180, 270, 40,false);
			placeButton(b[5],"Roll Once",735, 180, 270, 40,false);
			placeButton(b[1],"Buy",735, 188, 130, 25,false);
			placeButton(b[10],"Yes",735, 188, 130, 25,false);
			placeButton(b[20],"Yes",735, 188, 130, 25,false);
			placeButton(b[21],"Yes",735, 188, 130, 25,false);
			placeButton(b[22],"Go to Company",735, 280, 270, 25,false);
			placeButton(b[4],"No",875, 188, 130, 25,false);
			placeButton(b[2],"Buy with Cance Card",735, 188, 270, 25,false);
			
	
			placeButton(b[6],"Pull Chance Card",735, 180, 270, 40,false);
			b[6].setBackground(new Color(208,200,0));
			placeButton(b[7],"Pull Community Chest Card",735, 180, 270, 40,false);
			b[7].setBackground(new Color(208,200,0));
	
			placeButton(b[14],"",735, 188, 80, 25,false);
			placeButton(b[15],"",830, 188, 80, 25,false);
			placeButton(b[16],"",925, 188, 80, 25,false);
			
			

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
		    dPropsScrollPane.setLocation(725, 170);
		    dPropsScrollPane.setSize(540, 200);
		    //dProps[0].setSelected(true);
		    dPropsPanel.setBackground(Color.DARK_GRAY);
		    dPropsScrollPane.setBorder(BorderFactory.createMatteBorder(0, 0,0, 0, Color.black));
		    dPropsScrollPane.setAutoscrolls(true);
		    dPropsScrollPane.setVisible(false);
		    
		    for(int i=0;i<5;i++){
				dPropsType[i] = new JRadioButton("SkyScraper");
				dPropsType[i].setLocation(725 + i*120,375);
				dPropsType[i].setSize(120, 25);
				dPropsType[i].setVisible(false);
				dPropsType[i].setBackground(Color.DARK_GRAY);
				dPropsType[i].setForeground(Color.WHITE);

				panel.add(dPropsType[i]);
				dPropsTypeGroup.add(dPropsType[i]);
		    }
		    dPropsType[0].setSelected(true);
		    dPropsType[0].setText("None");
		    dPropsType[0].setActionCommand("0");
		    dPropsType[1].setText("House");
		    dPropsType[1].setActionCommand("1");
		    dPropsType[2].setText("Hotel");
		    dPropsType[2].setActionCommand("5");
		    dPropsType[3].setText("SkyScraper");
		    dPropsType[3].setActionCommand("6");
			placeButton(b[24],"Add Property",725, 405, 540, 25,false);
			placeButton(b[25],"Next Player",725, 450, 540, 50,false);
		    
			panel.add(dPropsScrollPane);
			
			

	        placeLabel(titles[6],"asdasdsad",540,50,725,13,new Color(255,255,255),Color.GRAY,new Font("Verdana", Font.BOLD, 18));
	        titles[6].setHorizontalAlignment(JLabel.CENTER);
	        titles[6].setVisible(false);
	        panel.add(titles[6]);
	        
	        


			row.setSize(80, 40);
			row.setLocation(910, 65);
			row.setBackground(Color.GRAY);
			row.setHorizontalAlignment(JTextField.CENTER);
			row.setFont(new Font("Verdana", Font.BOLD, 18));
			row.setVisible(false);
			row.setText("1");

			position.setSize(80, 40);
			position.setLocation(1010, 65);
			position.setBackground(Color.GRAY);
			position.setHorizontalAlignment(JTextField.CENTER);
			position.setFont(new Font("Verdana", Font.BOLD, 18));
			position.setText("0");
			position.setVisible(false);

			money.setSize(180, 40);
			money.setLocation(910, 110);
			money.setBackground(Color.GRAY);
			money.setHorizontalAlignment(JTextField.CENTER);
			money.setFont(new Font("Verdana", Font.BOLD, 18));
			money.setText("3200");
			money.setVisible(false);

			dieOneF.setSize(80, 40);
			dieOneF.setLocation(880, 510);
			dieOneF.setBackground(Color.GRAY);
			dieOneF.setHorizontalAlignment(JTextField.CENTER);
			dieOneF.setFont(new Font("Verdana", Font.BOLD, 18));
			dieOneF.setText("0");

			dieTwoF.setSize(80, 40);
			dieTwoF.setLocation(970, 510);
			dieTwoF.setBackground(Color.GRAY);
			dieTwoF.setHorizontalAlignment(JTextField.CENTER);
			dieTwoF.setFont(new Font("Verdana", Font.BOLD, 18));
			dieTwoF.setText("0");

			dieSpeedF.setSize(80, 40);
			dieSpeedF.setLocation(1060, 510);
			dieSpeedF.setBackground(Color.GRAY);
			dieSpeedF.setHorizontalAlignment(JTextField.CENTER);
			dieSpeedF.setFont(new Font("Verdana", Font.BOLD, 18));
			dieSpeedF.setText("0");
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
		b[26].setFont(new Font("Arial", Font.BOLD, 18));
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


			for(int i=0; i<39; i++){
				buttons[i].setVisible(false);
			}
			
			for(int i=0;i<13;i++){
				 titles[i].setVisible(false);
			}
			


			setGUI("","",buttons);
			titles[12].setVisible(true);
			initalNumberofPlayers.setVisible(true);
			buttons[11].setVisible(true);

			eCompsScrollPane.setVisible(false);
			buttons[30].setVisible(false);
			buttons[31].setVisible(false);


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

			titles[12].setVisible(false);
			titles[11].setVisible(false);

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
			}for(int i=players.size(); i<12; i++){
				playerNames[i].setVisible(false);
				playerMoneys[i].setVisible(false);
				playerPropertiesNames[i].setVisible(false);
				playerPics[i].setVisible(false);}
					
				if(MonopolyGame.debugLeft==0){
			
				    currentMessage.setVisible(true);
					currentPlayer.setText(cP.name);
					currentPlayer.setVisible(true);
					currentPlayer.setIcon(new ImageIcon("img/p"+Integer.toString(cP.id+1)+".png"));
					titles[2].setText("$"+Integer.toString(cP.money));
					titles[2].setVisible(true);
			        buttons[0].setVisible(true);
			
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

			
					MonopolyGame.debugLeft--;
				}
				else if(MonopolyGame.debugLeft<0){
				    row.setVisible(false);
				    position.setVisible(false);
				    money.setVisible(false);
				    
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
						
				    

				    
				    if(MonopolyGame.specialConditions[7]){
						buttons[32].setVisible(true);
						for(int i=0;i<board.players.size();i++){
							auctionBids[i].setVisible(true);
							auctionNames[i].setVisible(true);
						}
						
						buttons[32].setLocation(776, 182+27*board.players.size());
						
				    }else{
						buttons[32].setVisible(false);

						for(int i=0;i<12;i++){
							auctionBids[i].setVisible(false);
							auctionNames[i].setVisible(false);}
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
					
					
					
			
				}else{
			
					setGUI("","",buttons);
			        titles[6].setVisible(true);
			        titles[6].setText(board.players.get(MonopolyGame.initialNumberofPlayers-MonopolyGame.debugLeft).name);
					debugProps();
					buttons[24].setVisible(true);
					buttons[25].setVisible(true);
				    
				    if(MonopolyGame.debugLeft==1){
			
				        dieOneF.setVisible(true);
				        dieTwoF.setVisible(true);
				        dieSpeedF.setVisible(true);
				    }
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

	public int getRow(){
		int a = Integer.parseInt(row.getText()); 
		return a;
	}
	public int getPosition(){
		int a = Integer.parseInt(position.getText()); 
		return a;
	}
	public int getMoney(){
		int a = Integer.parseInt(money.getText()); 
		return a;
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
		if(l>39)
			l = 39;
		for(int i=0; i<l; i++){
				if(bs.charAt(i)=='1'){
					b[i].setVisible(true);}
				else if(bs.charAt(i)=='0'){
					b[i].setVisible(false);
				}

		}

		for(int i=l; i<39; i++){
			if(i!=11)
					b[i].setVisible(false);
		}
		
		if(bs.length()>0 && bs.charAt(0)=='1'){
			saveName.setVisible(true); b[13].setVisible(true); b[26].setVisible(true);
		}else{
				saveName.setVisible(false); b[13].setVisible(false); b[26].setVisible(false);
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
  				userFreeProperties[i].setText(cpFreeProperties.get(i).name.toLowerCase());
  				

  				userFreeProperties[i].setForeground(Color.WHITE);
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

		y = starty+170;
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
	
	  }else{buttons[9].setVisible(false); scrollPaneUnmortgage.setVisible(false);titles[1].setVisible(false);}
	  
	  
  
	  
	}
	
	
	
	public void debugProps(){

		int abc = 0;
	    for(int i = 0; i<120; i++){
	    	dProps[i] = null;
	    	Square sq = board.getSquareFromBoard(i);
	    	
	    	if(sq.type=="Property" || sq.type=="Transit" || sq.type=="Utility" || sq.type=="CabCompany"){
	    		

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