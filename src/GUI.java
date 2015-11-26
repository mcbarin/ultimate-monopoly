import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

public class GUI {
	

	public boolean start=true;
	
	private int dieOne, dieTwo, dieSpeed;
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
	private GUIPanel panel;

	private JTextField initalNumberofPlayers;
	private JTextArea currentMessage;
	private JLabel currentPlayer;
	
	private JComboBox<String> cpPropertiesNames = new JComboBox<String>();


	public JComboBox<String> cpMortgagedPropertiesNames = new JComboBox<String>();
	public ArrayList<Square> cpFreeProperties;
	public ArrayList<Square> cpMortgagedProperties = new ArrayList<Square>();
	
	public final static int MAX_PROPERTIES = 86;
	public JRadioButton[] userFreeProperties = new JRadioButton[MAX_PROPERTIES];
	public ButtonGroup freePropertiesButtonGroup;
	public int cpTotalFree=0,cpTotalMortgaged=0;

	private JButton buttons[] = new JButton[26];
	
	
	
	Font font = new Font("Verdana", Font.PLAIN, 12);
	
	public GUI(JButton b[], JTextField nump) throws Exception{
		buttons = b;
		initalNumberofPlayers = new JTextField();
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		
		
    	Container contentPane = mainFrame.getContentPane();
		panel.setLayout(null);
		contentPane.add(panel);
		
		initalNumberofPlayers.setSize(300, 40);
		initalNumberofPlayers.setLocation(450, 60);
		initalNumberofPlayers.setBackground(Color.GRAY);
		initalNumberofPlayers.setHorizontalAlignment(JTextField.CENTER);
		initalNumberofPlayers.setFont(new Font("Verdana", Font.BOLD, 18));
		initalNumberofPlayers.setText("Enter number of Players...");
		

		
		b[11].setText("Start Game");
		b[11].setLocation(450, 120);
		b[11].setSize(300, 40);
		b[11].setVisible(true);
		panel.add(b[11]);
        panel.add(initalNumberofPlayers);
        

        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);  
	}
	
	
	public GUI(Board board,JButton b[]) throws Exception{
		buttons = b;
		this.board = board;
		players = board.getPlayers();
		cP = board.getCurrentPlayer();
		 panel = new GUIPanel(board,dice);
		panel.setBackground(Color.DARK_GRAY);
		
		mainFrame = new JFrame("BECB Monopoly");
    	Container contentPane = mainFrame.getContentPane();
		panel.setLayout(null);
		contentPane.add(panel);
		
		
		
		
		freePropertiesButtonGroup = new ButtonGroup();
		JPanel sellpanel = new JPanel(new GridLayout(0,1));
	    for(int i = 0; i<MAX_PROPERTIES; i++){
	        userFreeProperties[i] = new JRadioButton("text" + i);
	        userFreeProperties[i].setActionCommand(Integer.toString(i));
	        userFreeProperties[i].setVisible(false);
	        userFreeProperties[i].setBackground(new Color(84,84,84));
	    	if(i%2==1)
	    		userFreeProperties[i].setBackground(Color.DARK_GRAY);
	    	userFreeProperties[i].setForeground(Color.WHITE);
	    	freePropertiesButtonGroup.add(userFreeProperties[i]);
	        sellpanel.add(userFreeProperties[i]);
	     }
	    JScrollPane scrollPane = new JScrollPane(sellpanel);
		scrollPane.setLocation(1025, 400);
		scrollPane.setSize(240, 100);
		sellpanel.setBackground(Color.DARK_GRAY);
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.black));
		scrollPane.setAutoscrolls(true);
	    panel.add(scrollPane);
		
	    placeButton(b[3],"Sell",1025, 95, 115, 25,false);
		placeButton(b[8],"Mortgage",1150, 95, 115, 25,false);
	   
		currentMessage = new JTextArea();
	    currentMessage.setLineWrap( true );
	    currentMessage.setWrapStyleWord( true );
	    currentMessage.setMargin(new Insets(10,10,10,10));
	    currentMessage.setForeground(new Color(180,255,240));
	    currentMessage.setBackground(new Color(50,50,50));
	    currentMessage.setEditable(false);
        currentMessage.setFont(new Font("Verdana", Font.PLAIN, 16));
        currentMessage.setSize(290, 160);
        currentMessage.setLocation(725, 10);
        currentMessage.setText(cP.name);
        panel.add(currentMessage);
        
        

        

        currentPlayer = new JLabel("");
        currentPlayer.setFont(new Font("Verdana", Font.BOLD, 25));
        currentPlayer.setSize(240, 40);
        currentPlayer.setLocation(1025, 10);
        currentPlayer.setForeground(new Color(255,155,155));
        panel.add(currentPlayer);
		

		placeButton(b[0],"Roll Dice!",735, 180, 270, 40,false);
		placeButton(b[5],"Roll Once",735, 180, 270, 40,false);
		placeButton(b[1],"Buy",735, 188, 130, 25,false);
		placeButton(b[10],"Yes",735, 188, 130, 25,false);
		placeButton(b[4],"No",875, 188, 130, 25,false);
		placeButton(b[2],"Buy with Cance Card",735, 188, 270, 25,false);
		

		placeButton(b[6],"Pull Chance Card",735, 180, 270, 40,false);
		b[6].setBackground(new Color(208,200,0));
		placeButton(b[7],"Pull Community Chest Card",735, 180, 270, 40,false);
		b[7].setBackground(new Color(208,200,0));
		

		placeButton(b[14],"",735, 188, 80, 25,false);
		placeButton(b[15],"",830, 188, 80, 25,false);
		placeButton(b[16],"",925, 188, 80, 25,false);
		
		
	
        //mainFrame.setResizable(false);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);  
        
      
        
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	//System.out.println("Repaints Continuously");

				players = board.getPlayers();
				cP = board.getCurrentPlayer();
				currentPlayer.setText(cP.name);


				cpFreeProperties = cP.freeProperties;
				setProps();
				panel.repaint();
				
				
			
            }
        };
        Timer timer = new Timer(100 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

        Thread.sleep(500);
       
    	
    	
	}
	

	private void placeButton(JButton button, String title, int x, int y, int width, int height, boolean visibility){
		button.setText(title);
		button.setLocation(x, y);
		button.setSize(width, height);
		panel.add(button);
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

	public void setGUI(String cm[], String bs, JButton b[]){
		setGUI(cm[1],bs,b);

		
	}
	
	public void removeSpecialConditions(){

		for(int i=0; i<26; i++){
			MonopolyGame.specialConditions[i] = false;}
	}

	public void setGUI(String cm, String bs, JButton b[]){
		this.currentMessage.setText(cm);
		int l = bs.length();
		if(l>26)
			l = 26;
		for(int i=0; i<l; i++){
				if(bs.charAt(i)=='1' || i==11 || i==3){
					b[i].setVisible(true);}
				else if(bs.charAt(i)=='0'){
					b[i].setVisible(false);
				}

		}

		for(int i=l; i<26; i++){
			if(i!=11)
					b[i].setVisible(false);
		}
		removeSpecialConditions();
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
	
	public void setProps(){
		JButton b[] = buttons;
			
			
		for(int i = 0; i<cpFreeProperties.size();i++){
  				userFreeProperties[i].setVisible(true);
  				userFreeProperties[i].setText(cpFreeProperties.get(i).name);
  				userFreeProperties[i].setActionCommand(Integer.toString(cpFreeProperties.get(i).id));
  			
    		}
		for(int i=cpFreeProperties.size(); i<MAX_PROPERTIES; i++){
				userFreeProperties[i].setVisible(false);
		}
  		buttons[3].setVisible(true); buttons[8].setVisible(true);
			
/*
			if(cpFreeProperties.size()>0){
			    cpPropertiesNames.setEditable(true);
			    cpPropertiesNames.setSelectedIndex(0);
			    cpPropertiesNames.setSize(240, 30);
			    cpPropertiesNames.setAutoscrolls(true);
			    cpPropertiesNames.setLocation(1025, 55);
				cpPropertiesNames.setBackground(new Color(102,150,0));
			    panel.add(cpPropertiesNames);
			    cpPropertiesNames.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	if(cpPropertiesNames.getSelectedIndex()>0)
			        		cpSelectedProperty = cpFreeProperties.get(cpPropertiesNames.getSelectedIndex()-1);
			          }
			        });
				
				
			}else{b[3].setVisible(false);b[8].setVisible(false);}
			
			if(cpMortgagedProperties.size()>0){
				cpMortgagedPropertiesNames.addItem("Select a property to UNmortgage.");
				cpMortgagedPropertiesNames.setEditable(true);
				cpMortgagedPropertiesNames.setSelectedIndex(0);
				cpMortgagedPropertiesNames.setSize(240, 30);
				cpMortgagedPropertiesNames.setAutoscrolls(true);
				cpMortgagedPropertiesNames.setLocation(1025, 130);
				cpMortgagedPropertiesNames.setBackground(new Color(185,0,0));
			    panel.add(cpMortgagedPropertiesNames);
			    cpMortgagedPropertiesNames.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	if(cpMortgagedPropertiesNames.getSelectedIndex()>0)
			        		cpSelectedMortgagedProperty = cpMortgagedProperties.get(cpMortgagedPropertiesNames.getSelectedIndex()-1);
			          }
			        });
			    placeButton(b[9],"UnMortgage",1025, 170, 240, 25,false);
			}else{b[9].setVisible(false);}
	*/
	}
	

}







