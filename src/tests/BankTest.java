package tests;
import static org.junit.Assert.*;


import domain.Board;
import domain.Player;
import domain.SquareCabCompany;
import domain.SquareProperty;
import domain.Bank;

import org.junit.Test;

public class BankTest {

	//////////////////////////////////////
	//Tested Methods
	//////////////////////////////////////
	//findHighestRentProperty()
	//auction()
	//thereIsUnownedProperty()
	//getUnownedProperties()
	//checkShareOfStocks()
	//buyStock()
	//auctionStock()
	//getIdByStockName()

	
	@Test
	public void testFindHighestRentProperty() {
		Board board = new Board(4);
		SquareProperty outer = (SquareProperty)board.getSquareFromBoard(84);
		SquareProperty inner = (SquareProperty)board.getSquareFromBoard(53);
		outer.rent = 3000;
		inner.rent = 4000;
		Bank ba = board.bank;
		
		int[] a = ba.findHighestRentProperty(3);
		int[] b = ba.findHighestRentProperty(4);
		assertTrue("findHighestRentProperty() works",ba.repOk() && (outer.position == a[1] && outer.row == a[0]) && (inner.position == b[1] && inner.row == b[0]));

			
	}

	@Test
	public void testAuction() {
		Board board = new Board(4);
		Bank ba = board.bank;
		SquareProperty meditAve = (SquareProperty)board.getSquareFromBoard(25);
		int[] bids = {50, 40, 60, 80};
		ba.auction(meditAve, bids); 
	
		assertTrue("auction() works",ba.repOk() && meditAve.owner.id == board.players.get(3).id);
		
	}
	@Test
	public void testAuctionCab() {
		Board board = new Board(4);
		Bank ba = board.bank;
		SquareCabCompany cab = (SquareCabCompany)board.getSquareFromBoard(70);  //price=300
		int[] bids = {50, 8000, 160, 180};
		ba.auction(cab, bids); 
	
		assertTrue("auction() works",ba.repOk() && cab.owner.id == board.players.get(3).id);
		
	}
	
	
	@Test
	public void testThereIsUnownedProperty(){
		Board board = new Board(4);
		Bank ba = board.bank;
		assertTrue("thereIsUnownedProperty() works",ba.repOk() && ba.thereIsUnownedProperty());
		
	}
	
	@Test
	public void testGetUnownedProperties(){
		Board board = new Board(4);
		Bank ba = board.bank;
		assertTrue("getUnownedProperties() works",ba.repOk() && ba.getUnownedProperties().size()==64);
		
	}
	
	@Test
	public void testCheckShareOfStocks(){
		Board board = new Board(4);
		Bank ba = board.bank;
		assertTrue("getUnownedProperties() works",ba.repOk() && ba.checkShareOfStocks().size()==6);
		
	}
	
	@Test
	public void testBuyStock(){
		Board board = new Board(4);
		Bank ba = board.bank;
		Player p = board.getCurrentPlayer();
		ba.buyStock("General Radio", p);
		ba.buyStock("General Radio", p);
		assertTrue("buyStock() works",ba.repOk() && p.repOk() && p.shares[0]==2);
		
	}
	
	@Test
	public void testAuctionStock(){
		Board board = new Board(4);
		Bank ba = board.bank;
		int[] bids = {100,1300,600,640};
		ba.auctionStock("General Radio", bids);
		Player p = board.players.get(1);
		assertTrue("auctionStock() works",ba.repOk() && p.repOk() && p.shares[0]==1);
		
	}
	
	@Test
	public void testGetIdByStockName(){
		Board board = new Board(4);
		Bank ba = board.bank;
		assertTrue("GetIdByStockName() works",ba.repOk() && ba.getIdByStockName("United Railways")==1);
		
	}
	
}