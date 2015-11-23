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
	
	//buttons = 
	//rollDice,buy,buyChance,sell,no,
	//rollOnce,pullChance,pullCommunity,mortgage,unMortgage,
	//yes,start,load,save

	
	//Players
	private ArrayList<Player> players;
	
	private int dice[] = {dieOne, dieTwo, dieSpeed};
	
	
	private JFrame mainFrame = new JFrame("BECB Monopoly");
	private JPanel panel;

	private JTextField initalNumberofPlayers;
	private JTextArea movements,currentMessage;
	private JLabel currentPlayer;
	private JLabel piName,piProps,piMoney,  alfaName,alfaProps,alfaMoney, betaName,betaProps,betaMoney, gamaName,gamaProps,gamaMoney;
	public JComboBox<String> cpProperties;
	
	private final static int MAX_PROPERTIES = 13;
	private JRadioButton[] userProperties = new JRadioButton[MAX_PROPERTIES];
	private JRadioButton[] userMortgages = new JRadioButton[MAX_PROPERTIES];
	private ButtonGroup propertiesButtonGroup;
	private ButtonGroup mortgagesButtonGroup;
	private int propertiesHeight = -10;
	
	
	
	
	Font font = new Font("Verdana", Font.PLAIN, 12);
	
	public GUI(JButton b[], JTextField nump) throws Exception{

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
		
	
		
		panel = new GUIPanel(board,dice);
		panel.setBackground(Color.DARK_GRAY);
		
		mainFrame = new JFrame("BECB Monopoly");
    	Container contentPane = mainFrame.getContentPane();
		panel.setLayout(null);
		contentPane.add(panel);
		
		
		
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
        
        currentMessage.setText("Still, you don't have enough money to pay rent. You have to sell one more of your properties.");

        panel.add(currentMessage);
        
		

		placeButton(b[0],"Roll Dice!",735, 160, 270, 40,true);
		placeButton(b[5],MonopolyGame.rollOnceListener,"Roll Once",735, 120, 270, 40,true);
		placeButton(b[1],MonopolyGame.buyListener,"Buy",735, 200, 130, 25,true);
		placeButton(b[10],MonopolyGame.yesListener,"Yes",735, 200, 130, 25,true);
		placeButton(b[4],MonopolyGame.noListener,"No",875, 200, 130, 25,true);
		placeButton(b[2],MonopolyGame.buyChanceListener,"Buy with Cance Card",735, 20, 270, 25,true);
		

		placeButton(b[6],MonopolyGame.pullChanceListener,"Pull Chance Card",735,520, 270, 40,true);
		placeButton(b[7],MonopolyGame.pullCommunityListener,"Pull Community Chest Card",735,480, 270, 40,true);
		

		JComboBox<String> cpProperties = new JComboBox<String>();
		
		
		propertiesButtonGroup = new ButtonGroup();
	    for(int i=0; i<MAX_PROPERTIES; i++){
	    	
	    	cpProperties.addItem("asdasd");
	    	
	    	userProperties[i] = new JRadioButton("asdasd");
	    	userProperties[i].setActionCommand(Integer.toString(i));
	    	userProperties[i].setLocation(1025, propertiesHeight+20);
	    	userProperties[i].setSize(240, 20);
	    	userProperties[i].setVisible(true);
    		userProperties[i].setBackground(new Color(84,84,84));
	    	if(i%2==1)
	    		userProperties[i].setBackground(Color.DARK_GRAY);
	    	userProperties[i].setForeground(Color.WHITE);
	    	
	    	propertiesHeight+=20;
	    	
	    	propertiesButtonGroup.add(userProperties[i]);
	    	//panel.add(userProperties[i]);

	    }
	    cpProperties.setEditable(true);
	    cpProperties.setSelectedIndex(0);
	    cpProperties.setSize(240, 40);
	    cpProperties.setAutoscrolls(true);
	    cpProperties.setLocation(1025, 10);
	    panel.add(cpProperties);
	    
	    userProperties[0].setSelected(true);
	    
	    propertiesHeight+=30;
	    
		placeButton(b[3],MonopolyGame.sellListener,"Sell",1025, propertiesHeight, 115, 25,true);
		placeButton(b[8],MonopolyGame.mortgageListener,"Mortgage",1150, propertiesHeight, 115, 25,true);
		
		propertiesHeight+=30;
		
		mortgagesButtonGroup = new ButtonGroup();
	    for(int i=0; i<MAX_PROPERTIES; i++){
	    	
	    	
	    	userMortgages[i] = new JRadioButton("asdasd");
	    	userMortgages[i].setActionCommand(Integer.toString(i));
	    	userMortgages[i].setLocation(1025, propertiesHeight+20);
	    	userMortgages[i].setSize(240, 20);
	    	userMortgages[i].setVisible(true);
	    	userMortgages[i].setBackground(new Color(84,84,84));
	    	if(i%2==1)
	    		userMortgages[i].setBackground(Color.DARK_GRAY);
	    	userMortgages[i].setForeground(Color.WHITE);
	    	
	    	propertiesHeight+=20;
	    	
	    	mortgagesButtonGroup.add(userMortgages[i]);
	    	panel.add(userMortgages[i]);

	    }
	    userMortgages[0].setSelected(true);
	    
	    propertiesHeight+=30;
		
		placeButton(b[9],MonopolyGame.unMortgageListener,"UnMortgage",1025, propertiesHeight, 240, 25,true);
		
	
        //mainFrame.setResizable(false);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);  
        
      
        
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	//System.out.println("Repaints Continuously");
				panel.repaint();
            }
        };
        Timer timer = new Timer(100 ,taskPerformer);
        timer.setRepeats(true);
        timer.start();

        Thread.sleep(500);
       
    	
    	
	}
	
	
	private void placeButton(JButton button, ActionListener listener, String title, int x, int y, int width, int height, boolean visibility){
		button = new JButton(title);
		button.setLocation(x, y);
		button.setSize(width, height);
		button.setVisible(visibility);
		button.addActionListener(listener);
		panel.add(button);
	}	
	private void placeButton(JButton button, String title, int x, int y, int width, int height, boolean visibility){
		button.setText(title);
		button.setLocation(x, y);
		button.setSize(width, height);
		button.setVisible(visibility);
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
	
	public void setGUI(Player cp, String cm, String bs){
		
		
		
	}
	
	
	public void setPlayers(ArrayList<Player> p){
		this.players = p;
	}
	
	

}







