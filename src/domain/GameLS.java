package domain;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class GameLS {
	
     SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy H:mm:ss");
     String date = sdf.format(new Date());
     Board board;
   
   
    public static void main(String[] args) {GameLS a = new GameLS(); Board b = a.loadGame(11); System.out.println(b.totalPlayer);}
    public GameLS(){}
    public void setBoard(Board b){this.board = b;}
    
    

    
    public Board loadGame(int index){

    	

    	  // reading can be done using any of the two 'DOM' or 'SAX' parser
    	  // we have used saxBuilder object here
    	  // please note that this saxBuilder is not internal sax from jdk
    	     SAXBuilder saxBuilder = new SAXBuilder();
    	     
    	     // obtain file object 
    	     File file = new File("./saved_games/"+Integer.toString(index)+".xml");

    	  try {
    	   // converted file to document object
    	   Document document = saxBuilder.build(file);
    	   
    	   // get root node from xml
    	   Element rootNode = document.getRootElement();
    	   
    	   // got all xml elements into a list
    	   
    	   board = new Board(Integer.parseInt(rootNode.getChildText("numberOfPlayers")));
    	   board.currentPlayer = board.players.get(Integer.parseInt(rootNode.getChildText("currentPlayer")));
    	   
    	   Element bankl = rootNode.getChild("bank");
    	   List<Element> comp = bankl.getChildren("company");
    	   
    	   for(int i=0; i<board.bank.companies.size(); i++){
    		   board.bank.companies.get(i).parValue = Integer.parseInt(comp.get(i).getChildText("parValue"));
    		   board.bank.companies.get(i).share = Integer.parseInt(comp.get(i).getChildText("share"));
    		   
    	   }
    	  


    	   List<Element> playerList = rootNode.getChildren("player");
    	      
    	      
    	      // simple iteration to see the result on console
    	      for(int i=0;i<=playerList.size()-1;i++){
    	    	  Player p = board.players.get(i);
    	    	  Element element = playerList.get(i);
    	    	  p.name = element.getChildText("name");
    	    	  p.isPlaying = Boolean.parseBoolean(element.getChildText("isPlaying"));
    	    	  p.money = Integer.parseInt(element.getChildText("money"));
    	    	  p.countJail = Integer.parseInt(element.getChildText("countJail"));
    	    	  p.row = Integer.parseInt(element.getChildText("row"));
    	    	  p.position = Integer.parseInt(element.getChildText("position"));
    	    	  p.reverse = Boolean.parseBoolean(element.getChildText("reverse"));
    	    	  
    	    	  
    	    	  String shrs[] = element.getChildText("shares").split("-");
    	    	  for(int j = 0; j<6; j++){
    	    		  p.shares[j] = Integer.parseInt(shrs[j]);
    	    	  }
    	    	  
    	    	  String nums[] = element.getChildText("numbers").split("-");
    			  p.numberOfProperties = Integer.parseInt(nums[0]);
    			  p.numberOfCards = Integer.parseInt(nums[1]);
    			  p.numberOfHouses = Integer.parseInt(nums[2]);
    			  p.numberOfHotels = Integer.parseInt(nums[3]);
    			  p.numberOfSkyscrapers = Integer.parseInt(nums[4]);
    			  p.numberOfCabStand = Integer.parseInt(nums[5]);
    			  p.numberOfTransitStation = Integer.parseInt(nums[6]);
    			  

    	    	  String clrprp[] = element.getChildText("colorProperties").split("-");
    	    	  for(int j = 0; j<20; j++){
    	    		  p.colorProperties[j] = Integer.parseInt(clrprp[j]);
    	    	  }

    	    	  p.allPropertiesNames = element.getChildText("allPropertiesNames");
    	    	  
    	    	  Element props = element.getChild("properties");
    	    	  List<Element> proplist = props.getChildren("property");
    	    	  for(Element prp : proplist){
    	    		  SquareProperty pr = (SquareProperty)board.getSquareFromBoard(Integer.parseInt(prp.getChildText("ID")));
    	    		  pr.owner = board.players.get(Integer.parseInt(prp.getChildText("ownerID")));
    	    		  pr.price = Integer.parseInt(prp.getChildText("price"));
    	    		  pr.originalRent = Integer.parseInt(prp.getChildText("originalRent"));
    	    		  pr.rent = Integer.parseInt(prp.getChildText("rent"));
    	    		  pr.isMortgaged = Boolean.parseBoolean(prp.getChildText("isMortgaged"));
    	    		  pr.house = Integer.parseInt(prp.getChildText("house"));
    	    		  pr.hotel = Integer.parseInt(prp.getChildText("hotel"));
    	    		  pr.skyscraper = Integer.parseInt(prp.getChildText("skyscraper"));
    	    		  pr.level = Integer.parseInt(prp.getChildText("level"));
    	    		  
    	    		  p.properties.add(pr);
    	    		  
    	    	  }
    	    	  Element transits = element.getChild("transits");
    	    	  List<Element> trainlist = transits.getChildren("train");
    	    	  for(Element trn : trainlist){
    	    		  SquareTransit tr = (SquareTransit)board.getSquareFromBoard(Integer.parseInt(trn.getChildText("ID")));
    	    		  tr.owner = board.players.get(Integer.parseInt(trn.getChildText("ownerID")));
    	    		  tr.price = Integer.parseInt(trn.getChildText("price"));
    	    		  tr.originalRent = Integer.parseInt(trn.getChildText("originalRent"));
    	    		  tr.rent = Integer.parseInt(trn.getChildText("rent"));
    	    		  tr.isMortgaged = Boolean.parseBoolean(trn.getChildText("isMortgaged"));
    	    		  tr.trainDepot = Integer.parseInt(trn.getChildText("trainDepot"));
    	    		  tr.trainDepotPrice = Integer.parseInt(trn.getChildText("trainDepotPrice"));

    	    		  p.trains.add(tr);
    	    		  
    	    	  }
    	    	  
    	    	  
    	    	  Element cabs = element.getChild("cabs");
    	    	  List<Element> cablist = cabs.getChildren("cab");
    	    	  for(Element cab : cablist){
    	    		  SquareCabCompany cb = (SquareCabCompany)board.getSquareFromBoard(Integer.parseInt(cab.getChildText("ID")));
    	    		  cb.owner = board.players.get(Integer.parseInt(cab.getChildText("ownerID")));
    	    		  cb.price = Integer.parseInt(cab.getChildText("price"));
    	    		  cb.originalRent = Integer.parseInt(cab.getChildText("originalRent"));
    	    		  cb.rent = Integer.parseInt(cab.getChildText("rent"));
    	    		  cb.isMortgaged = Boolean.parseBoolean(cab.getChildText("isMortgaged"));
    	    		  cb.cabStand = Integer.parseInt(cab.getChildText("cabStand"));
    	    		  cb.cabStandPrice = Integer.parseInt(cab.getChildText("cabStandPrice"));

    	    		  p.cabs.add(cb);
    	    		  
    	    	  }
    	    	  
    	    	  Element utilities = element.getChild("utilities");
    	    	  List<Element> utilitylist = utilities.getChildren("utility");
    	    	  for(Element uti : utilitylist){
    	    		  SquareUtility ut = (SquareUtility)board.getSquareFromBoard(Integer.parseInt(uti.getChildText("ID")));
    	    		  ut.owner = board.players.get(Integer.parseInt(uti.getChildText("ownerID")));
    	    		  ut.price = Integer.parseInt(uti.getChildText("price"));
    	    		  ut.rent = Integer.parseInt(uti.getChildText("rent"));
    	    		  ut.isMortgaged = Boolean.parseBoolean(uti.getChildText("isMortgaged"));

    	    		  p.utilities.add(ut);
    	    		  
    	    	  }
    	    	  
    	    	  Element cards = element.getChild("cards");
    	    	  List<Element> cardlist = cards.getChildren("ID");
    	    	  for(Element crdid : cardlist){
    	    		  p.addCardDebug(Integer.parseInt(crdid.getText()));
    	    		  
    	    	  }
	    		  //System.out.println(board.players.get(0).utilities.get(0).name);
	    		  
	    		  

    	    	  p.setFreeProperties();
	    		  
	    		  

	    		  //System.out.println(board.players.get(0).freeProperties.get(8).id);
    	       
	    		  //System.out.println("Test: "+element.getChildText("name"));
    	      }
    	     
    	  } catch (JDOMException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
    	  } catch (IOException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
    	  }  
    	
    	return board;
    	
    }
    
    
    
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
	     Element company;

	     for(Company c: board.bank.companies){
		     company = new Element("company");
		     
		     Element celt = new Element("name");
		     celt.setText(c.name);
		     company.addContent(celt);
		     
		     celt = new Element("id");
		     celt.setText(Integer.toString(c.id));
		     company.addContent(celt);
		     
		     celt = new Element("parValue");
		     celt.setText(Integer.toString(c.parValue));
		     company.addContent(celt);
		     
		     celt = new Element("share");
		     celt.setText(Integer.toString(c.share));
		     company.addContent(celt);
			 bank.addContent(company);}
	     
			     
		 doc.getRootElement().addContent(bank);
		 bank = null;
	     

	     doc.getRootElement().addContent(cPID);
	     doc.getRootElement().addContent(noP);
	     doc.getRootElement().addContent(datee);
	     
	     //playerProcess
	     for(Player p: board.players){
	    	 p.setFreeProperties();
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
		     
		     elt = new Element("shares");
		     elt.setText(Integer.toString(p.shares[0])+"-"+Integer.toString(p.shares[1])+"-"+Integer.toString(p.shares[2])+"-"+Integer.toString(p.shares[3])+"-"+Integer.toString(p.shares[4])+"-"+Integer.toString(p.shares[5]));
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
			     	element = new Element("ID");
			     	element.setText(Integer.toString(sq.id));
			     	propelt.addContent(element);
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
			     	element = new Element("ID");
			     	element.setText(Integer.toString(tr.id));
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
			     	element = new Element("ID");
			     	element.setText(Integer.toString(cb.id));
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
			     	element = new Element("ID");
			     	element.setText(Integer.toString(ut.id));
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