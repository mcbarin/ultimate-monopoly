import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class GameLS {
	
     SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy H:mm:ss");
     String date = sdf.format(new Date());
     Board board;
   
   
    //public static void main(String[] args) {saveGame("bugracan");}
    public GameLS(){}
    public void setBoard(Board b){this.board = b;}
    
    public void saveGame(Board board, String n){
    	String name = "Saved Game";
    	if(!n.equals("") && !n.equals("Save Name"))
    		name = n;
   

	  try{
	     //root element
	     Element game = new Element("game");
	     Document doc = new Document(game);			
	
	     //currentPlayer
	     Element cPID = new Element("currentPlayer");
	     cPID.setText("0");
	     
	     //numberOfPlayers
	     Element noP = new Element("numberOfPlayers");
	     noP.setText("9");
	     
	     //Date
	     Element datee = new Element("date");
	     datee.setText(date);

	     doc.getRootElement().addContent(cPID);
	     doc.getRootElement().addContent(noP);
	     doc.getRootElement().addContent(datee);
	     
	     //playerProcess
	     for(int i=0; i<3; i++){
		     Element player = new Element("player");
		     
		     Element elt = new Element("id");
		     elt.setText(Integer.toString(i));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("name");
		     elt.setText(Integer.toString(i));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("isPlaying");
		     elt.setText(Integer.toString(i));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("money");
		     elt.setText(Integer.toString(i));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("countJail");
		     elt.setText(Integer.toString(i));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("row");
		     elt.setText(Integer.toString(i));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("position");
		     elt.setText(Integer.toString(i));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("reverse");
		     elt.setText(Integer.toString(i));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("numbers");
		     elt.setText("Properties-Cards-Properties-Houses-Hotels-Skyscrapers-CabStand-TransitStation");
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("colorProperties");
		     elt.setText("0-3-3-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0");
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("allPropertiesNames");
		     elt.setText("universidad");
		     player.addContent(elt);
		     elt = null;
		     
		     
		     //Properties
		     Element props = new Element("properties");   
		     
		     
		     //For loop Begin
		     	Element propelt = new Element("property");
			     	Element element = new Element("ownerID");
			     	element.setText("0");
			     	propelt.addContent(element);
			     	element = new Element("price");
			     	element.setText("260");
			     	propelt.addContent(element);
			     	element = new Element("originalRent");
			     	element.setText("26");
			     	propelt.addContent(element);
			     	element = new Element("rent");
			     	element.setText("52");
			     	propelt.addContent(element);
			     	element = new Element("isMortgaged");
			     	element.setText("true");
			     	propelt.addContent(element);
			     	element = new Element("house");
			     	element.setText("1");
			     	propelt.addContent(element);
			     	element = new Element("hotel");
			     	element.setText("1");
			     	propelt.addContent(element);
			     	element = new Element("skyscraper");
			     	element.setText("0");
			     	propelt.addContent(element);
			     	element = new Element("level");
			     	element.setText("13");
			     	propelt.addContent(element);
				props.addContent(propelt);
				
			//For Loop End
				     
			 player.addContent(props);
		     props = null;
		     
		     

		     //Trains
		     Element trains = new Element("transits");   
		     
		     
		     //For loop Begin
		     	Element trainelt = new Element("train");
			     	element = new Element("ownerID");
			     	element.setText("0");
			     	trainelt.addContent(element);
			     	element = new Element("price");
			     	element.setText("0");
			     	trainelt.addContent(element);
			     	element = new Element("rent");
			     	element.setText("0");
			     	trainelt.addContent(element);
			     	element = new Element("originalRent");
			     	element.setText("0");
			     	trainelt.addContent(element);
			     	element = new Element("trainDepot");
			     	element.setText("0");
			     	trainelt.addContent(element);
			     	element = new Element("trainDepotPrice");
			     	element.setText("0");
			     	trainelt.addContent(element);
			     	element = new Element("isMortgaged");
			     	element.setText("false");
			     	trainelt.addContent(element);
				trains.addContent(trainelt);
				
			//For Loop End
				     
			 player.addContent(trains);
			 trains = null;
		     
		     

		     //Cabs
		     Element cabs = new Element("cabs");   
		     		     
		     //For loop Begin
		     	Element cabelt = new Element("cab");
			     	element = new Element("ownerID");
			     	element.setText("0");
			     	cabelt.addContent(element);
			     	element = new Element("price");
			     	element.setText("0");
			     	cabelt.addContent(element);
			     	element = new Element("rent");
			     	element.setText("0");
			     	cabelt.addContent(element);
			     	element = new Element("originalRent");
			     	element.setText("0");
			     	cabelt.addContent(element);
			     	element = new Element("cabStand");
			     	element.setText("0");
			     	cabelt.addContent(element);
			     	element = new Element("cabStandPrice");
			     	element.setText("0");
			     	cabelt.addContent(element);
			     	element = new Element("isMortgaged");
			     	element.setText("0");
			     	cabelt.addContent(element);
				cabs.addContent(cabelt);
				
			//For Loop End
				     
			 player.addContent(cabs);
			 cabs = null;
		     
		     

		     //Utilities
		     Element utilities = new Element("utilities");   
		     		     
		     //For loop Begin
		     	Element utilityelt = new Element("utility");
			     	element = new Element("ownerID");
			     	element.setText("0");
			     	utilityelt.addContent(element);
			     	element = new Element("price");
			     	element.setText("0");
			     	utilityelt.addContent(element);
			     	element = new Element("rent");
			     	element.setText("0");
			     	utilityelt.addContent(element);
			     	element = new Element("isMortgaged");
			     	element.setText("0");
			     	utilityelt.addContent(element);
			     	utilities.addContent(utilityelt);
				
			//For Loop End
				     
			 player.addContent(utilities);
			 cabs = null;
		     
		     

		     //FreeProperties
		     Element freeProperties = new Element("freeProperties");   
		     		     
		     //For loop Begin
		     	Element freePropertyID = new Element("ID");
		     		freePropertyID.setText("0");
			     	freeProperties.addContent(freePropertyID);
				
			//For Loop End
				     
			 player.addContent(freeProperties);
			 freeProperties = null;
		     
		     

		     //MortgagedProperties
		     Element mortgagedProperties = new Element("mortgagedProperties");   
		     		     
		     //For loop Begin
		     	Element mPropertyID = new Element("ID");
		     	mPropertyID.setText("0");
		     	mortgagedProperties.addContent(mPropertyID);
				
			//For Loop End
				     
			 player.addContent(mortgagedProperties);
			 mortgagedProperties = null;
		     
		     

		     //Cards
		     Element cards = new Element("cards");   
		     		     
		     //For loop Begin
		     	Element cardelt = new Element("ID");
		     	cardelt.setText("0");
			    cards.addContent(cardelt);
				
			//For Loop End
				     
			 player.addContent(cards);
			 cabs = null;
		     
		     

		     //Bank
		     Element bank = new Element("bank");   
		     
		     	Element bankelt = new Element("money");
		     	bankelt.setText("10000000");
			    bank.addContent(bankelt);
				     
			 player.addContent(bank);
			 bank = null;
		     

		     doc.getRootElement().addContent(player);
	     }
	
	     int saveNumber=0;
		try{
			String lines[];
			BufferedReader rd = new BufferedReader(new FileReader("./saved_games/info.txt"));
			lines =  readLineArray(rd);
			
			saveNumber = Integer.parseInt(lines[2]);
			
			lines[saveNumber+3] = name + " - " + date;
			lines[2] = Integer.toString((saveNumber+1)%13);
			System.out.println(lines[saveNumber+3]);
			System.out.println(lines[2]);
			
			rd.close();	
			
			FileWriter writer = new FileWriter("./saved_games/info.txt");
			for(int i=0; i<lines.length;i++){
				if(i!=0)
					writer.write("\r\n");
				writer.write(lines[i]);}
            writer.close();
		}
		
		catch(IOException ex){
			System.out.println("There is an error with the file." + ""+ex);
		}
     
     
	     
	     
	
	     XMLOutputter xmlOutput = new XMLOutputter();
	
	     // display ml
	     xmlOutput.setFormat(Format.getPrettyFormat());
	     //xmlOutput.output(doc, System.out); 
	     xmlOutput.output(doc, new FileWriter("./saved_games/"+Integer.toString(saveNumber)+".xml"));
	      }catch(IOException e){
	         e.printStackTrace();
	      }		
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