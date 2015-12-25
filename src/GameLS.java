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
	     cPID.setText(Integer.toString(board.currentPlayer.id));
	     
	     //numberOfPlayers
	     Element noP = new Element("numberOfPlayers");
	     noP.setText(Integer.toString(board.getNumberOfPlayers()));
	     
	     //Date
	     Element datee = new Element("date");
	     datee.setText(date);
	     
	     

	     //Bank
	     Element bank = new Element("bank");   
	     
	     	Element bankelt = new Element("money");
	     	bankelt.setText("10000000");
		    bank.addContent(bankelt);
			     
		 doc.getRootElement().addContent(bank);
		 bank = null;
	     

	     doc.getRootElement().addContent(cPID);
	     doc.getRootElement().addContent(noP);
	     doc.getRootElement().addContent(datee);
	     
	     //playerProcess
	     for(Player p: board.players){
		     Element player = new Element("player");
		     
		     Element elt = new Element("id");
		     elt.setText(Integer.toString(p.id));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("name");
		     elt.setText(p.name);
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("isPlaying");
		     elt.setText(Boolean.toString(p.isPlaying));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("money");
		     elt.setText(Integer.toString(p.money));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("countJail");
		     elt.setText(Integer.toString(p.countJail));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("row");
		     elt.setText(Integer.toString(p.row));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("position");
		     elt.setText(Integer.toString(p.position));
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("reverse");
		     elt.setText(Boolean.toString(p.reverse));
		     player.addContent(elt);
		     elt = null;
		    
		     elt = new Element("numbers");
		     elt.setText(Integer.toString(p.numberOfProperties)+"-"+Integer.toString(p.numberOfCards)+"-"+Integer.toString(p.numberOfHouses)+"-"+Integer.toString(p.numberOfHotels)+"-"+Integer.toString(p.numberOfSkyscrapers)+"-"+Integer.toString(p.numberOfCabStand)+"-"+Integer.toString(p.numberOfTransitStation));
		     //Properties-Cards-Houses-Hotels-Skyscrapers-CabStand-TransitStation
		     player.addContent(elt);
		     elt = null;
		     
		     String cl = Integer.toString(p.colorProperties[0]);
		     for(int i=1; i<20; i++){
		    	 cl = cl+"-"+Integer.toString(p.colorProperties[0]);
		     }

		     elt = new Element("colorProperties");
		     elt.setText(cl);
		     player.addContent(elt);
		     elt = null;
		     
		     elt = new Element("allPropertiesNames");
		     elt.setText(p.allPropertiesNames);
		     player.addContent(elt);
		     elt = null;
		     
		     
		     //Properties
		     Element props = new Element("properties");   
		     
		     Element element;
		     //For loop Begin
		     for(SquareProperty sq : p.properties){
		     	Element propelt = new Element("property");
			     	element = new Element("ownerID");
			     	element.setText(Integer.toString(sq.owner.id));
			     	propelt.addContent(element);
			     	element = new Element("price");
			     	element.setText(Integer.toString(sq.price));
			     	propelt.addContent(element);
			     	element = new Element("originalRent");
			     	element.setText(Integer.toString(sq.originalRent));
			     	propelt.addContent(element);
			     	element = new Element("rent");
			     	element.setText(Integer.toString(sq.rent));
			     	propelt.addContent(element);
			     	element = new Element("isMortgaged");
			     	element.setText(Boolean.toString(sq.isMortgaged));
			     	propelt.addContent(element);
			     	element = new Element("house");
			     	element.setText(Integer.toString(sq.house));
			     	propelt.addContent(element);
			     	element = new Element("hotel");
			     	element.setText(Integer.toString(sq.hotel));
			     	propelt.addContent(element);
			     	element = new Element("skyscraper");
			     	element.setText(Integer.toString(sq.skyscraper));
			     	propelt.addContent(element);
			     	element = new Element("level");
			     	element.setText(Integer.toString(sq.level));
			     	propelt.addContent(element);
				props.addContent(propelt);
		     }
			//For Loop End
				     
			 player.addContent(props);
		     props = null;
		     
		     

		     //Trains
		     Element trains = new Element("transits");   
		     
		     
		     //For loop Begin
		     for(SquareTransit tr : p.trains){
		     	Element trainelt = new Element("train");
			     	element = new Element("ownerID");
			     	element.setText(Integer.toString(tr.owner.id));
			     	trainelt.addContent(element);
			     	element = new Element("price");
			     	element.setText(Integer.toString(tr.price));
			     	trainelt.addContent(element);
			     	element = new Element("rent");
			     	element.setText(Integer.toString(tr.rent));
			     	trainelt.addContent(element);
			     	element = new Element("originalRent");
			     	element.setText(Integer.toString(tr.originalRent));
			     	trainelt.addContent(element);
			     	element = new Element("trainDepot");
			     	element.setText(Integer.toString(tr.trainDepot));
			     	trainelt.addContent(element);
			     	element = new Element("trainDepotPrice");
			     	element.setText(Integer.toString(tr.trainDepotPrice));
			     	trainelt.addContent(element);
			     	element = new Element("isMortgaged");
			     	element.setText(Boolean.toString(tr.isMortgaged));
			     	trainelt.addContent(element);
				trains.addContent(trainelt);
		     }
			//For Loop End
				     
			 player.addContent(trains);
			 trains = null;
		     
		     

		     //Cabs
		     Element cabs = new Element("cabs");   
		     		     
		     //For loop Begin
		     for(SquareCabCompany cb : p.cabs){
		     	Element cabelt = new Element("cab");
			     	element = new Element("ownerID");
			     	element.setText(Integer.toString(cb.owner.id));
			     	cabelt.addContent(element);
			     	element = new Element("price");
			     	element.setText(Integer.toString(cb.price));
			     	cabelt.addContent(element);
			     	element = new Element("rent");
			     	element.setText(Integer.toString(cb.rent));
			     	cabelt.addContent(element);
			     	element = new Element("originalRent");
			     	element.setText(Integer.toString(cb.originalRent));
			     	cabelt.addContent(element);
			     	element = new Element("cabStand");
			     	element.setText(Integer.toString(cb.cabStand));
			     	cabelt.addContent(element);
			     	element = new Element("cabStandPrice");
			     	element.setText(Integer.toString(cb.cabStandPrice));
			     	cabelt.addContent(element);
			     	element = new Element("isMortgaged");
			     	element.setText(Boolean.toString(cb.isMortgaged));
			     	cabelt.addContent(element);
				cabs.addContent(cabelt);
		     }
				
			//For Loop End
				     
			 player.addContent(cabs);
			 cabs = null;
		     
		     

		     //Utilities
		     Element utilities = new Element("utilities");   
		     		     
		     //For loop Begin
		     for(SquareUtility ut : p.utilities){
		     	Element utilityelt = new Element("utility");
			     	element = new Element("ownerID");
			     	element.setText(Integer.toString(ut.owner.id));
			     	utilityelt.addContent(element);
			     	element = new Element("price");
			     	element.setText(Integer.toString(ut.price));
			     	utilityelt.addContent(element);
			     	element = new Element("rent");
			     	element.setText(Integer.toString(ut.rent));
			     	utilityelt.addContent(element);
			     	element = new Element("isMortgaged");
			     	element.setText(Boolean.toString(ut.isMortgaged));
			     	utilityelt.addContent(element);
			     	utilities.addContent(utilityelt);
		     }
			//For Loop End
				     
			 player.addContent(utilities);
			 cabs = null;
		     
		     

		     //FreeProperties
		     Element freeProperties = new Element("freeProperties");   
		     		     
		     //For loop Begin
		     for(Square fp : p.freeProperties){
		     	Element freePropertyID = new Element("ID");
		     		freePropertyID.setText(Integer.toString(fp.id));
			     	freeProperties.addContent(freePropertyID);
		     }
			//For Loop End
				     
			 player.addContent(freeProperties);
			 freeProperties = null;
		     
		     

		     //MortgagedProperties
		     Element mortgagedProperties = new Element("mortgagedProperties");   
		     		     
		     //For loop Begin
		     for(Square mp : p.mortgagedProperties){
		     	Element mPropertyID = new Element("ID");
		     	mPropertyID.setText(Integer.toString(mp.id));
		     	mortgagedProperties.addContent(mPropertyID);
		     }
			//For Loop End
				     
			 player.addContent(mortgagedProperties);
			 mortgagedProperties = null;
		     
		     

		     //Cards
		     Element cards = new Element("cards");   
		     		     
		     //For loop Begin
		     for(Card crd : p.cards){
		     	Element cardelt = new Element("ID");
		     	cardelt.setText(Integer.toString(crd.number));
			    cards.addContent(cardelt);
		     }
			//For Loop End
				     
			 player.addContent(cards);
			 cabs = null;
		     
		     


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