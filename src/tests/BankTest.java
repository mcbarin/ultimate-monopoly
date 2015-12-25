package tests;
import static org.junit.Assert.*;
import domain.Board;
import domain.SquareProperty;

import org.junit.Test;

public class BankTest {

	Board board = new Board(2);
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	

	
	@Test
	public void findHighestRentPropertyTest() {
		Board board = new Board(4);
		SquareProperty outer = (SquareProperty)board.getSquareFromBoard(84);
		SquareProperty inner = (SquareProperty)board.getSquareFromBoard(53);
		outer.rent = 3000;
		inner.rent = 4000;
		
		int[] a = board.bank.findHighestRentProperty(3);
		int[] b = board.bank.findHighestRentProperty(4);
		assertTrue("findHighestRentProperty odd case is true",outer.position == a[1] && outer.row == a[0]);
		//assertTrue("findHighestRentProperty even case is true",inner.position == a[1] && inner.row == b[0]);
			
	}

	/*@Test
	public void auctionTest() {
		Board board = new Board(4);
		SquareProperty meditAve = (SquareProperty)board.getSquareFromBoard(25);
		int[] bids = {50, 40, 60, 80};
		board.bank.auction(meditAve, bids); 
	
		assertTrue("auction is true",meditAve.owner.id == board.players.get(3).id);
		
	}*/
	
}