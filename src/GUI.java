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
	private JLabel currentPlayer = new JLabel();
	
	private JLabel titles[] = new JLabel[13];


	public ArrayList<Square> cpFreeProperties;
	public ArrayList<Square> cpMortgagedProperties;
	
	public final static int MAX_PROPERTIES = 86;
	public JRadioButton[] userFreeProperties = new JRadioButton[MAX_PROPERTIES];
	public ButtonGroup freePropertiesButtonGroup = new ButtonGroup();
	public JPanel sellpanel = new JPanel(new GridLayout(0,1));
    JScrollPane scrollPane = new JScrollPane(sellpanel);

	private JButton buttons[] = new JButton[26];
	
	public static Color colorCodes[] = {new Color(255,255,255),new Color(0,0,0),new Color(128,128,128),new Color(170,68,0),new Color(88,12,57),
										new Color(135,165,215),new Color(239,55,120),new Color(245,128,35),new Color(212,0,0),new Color(255,204,0),
										new Color(9,135,51),new Color(40,78,161),new Color(255,170,170),new Color(128,255,128),new Color(255,230,128),
										new Color(0,128,102),new Color(128,0,51),new Color(170,136,0),new Color(255,179,128),new Color(128,0,0)};
	
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
		scrollPane.setSize(240, 100);
		sellpanel.setBackground(Color.DARK_GRAY);
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 2, 1, 1, Color.black));
		scrollPane.setAutoscrolls(true);
		scrollPane.setVisible(false);
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
        
        

        

        
        placeLabel(currentPlayer,"",240,30,1025,10,new Color(255,155,155),Color.GRAY,new Font("Verdana", Font.BOLD, 25));
        panel.add(currentPlayer);
        
        titles[0] = new JLabel();
        panel.add(titles[0]);
		

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
				if(bs.charAt(i)=='1'){
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
	
	public void setProps(){
		JButton b[] = buttons;
			
	  if(cpFreeProperties.size()>0){	
		  titles[0].setVisible(true);
		  placeLabel(titles[0],"Select a propety to sell or mortgage:",240,20,1025,45,Color.WHITE,Color.GRAY,font);
	        
		  scrollPane.setVisible(true);
		  scrollPane.setLocation(1025,70);
			for(int i = 0; i<cpFreeProperties.size();i++){
	  				userFreeProperties[i].setVisible(true);
	  				userFreeProperties[i].setText(cpFreeProperties.get(i).name);
	  				
	
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
		userFreeProperties[0].setSelected(true);
  		buttons[3].setVisible(true); buttons[3].setLocation(1025, 180);
  		buttons[8].setVisible(true); buttons[8].setLocation(1150, 180);
	  }else{buttons[3].setVisible(false); buttons[8].setVisible(false); scrollPane.setVisible(false);titles[0].setVisible(false);}
  
	}
	
	
	

}







