package tests;
import static org.junit.Assert.*;


import domain.Board;
import domain.Player;
import domain.SquareProperty;

import org.junit.Test;

public class SellBuyPropertyTest {

	//////////////////////////////////////
	//Tested Methods
	//////////////////////////////////////
	//buyProperty()
	//sellProperty()

	
	@Test
	public void testBuyProperty() {
		Board board = new Board(4);
		Player p = board.getCurrentPlayer();
		SquareProperty po = (SquareProperty)board.getSquareFromBoard(84);
		po.buyProperty(p);
		assertTrue("findHighestRentProperty() works",board.repOk() && p.repOk() && po.getName().equals(p.properties.get(0).getName()));

			
	}

	
	@Test
	public void testSellProperty() {
		Board board = new Board(4);
		Player p = board.getCurrentPlayer();
		SquareProperty po = (SquareProperty)board.getSquareFromBoard(84);
		po.buyProperty(p);
		int as = p.properties.size();
		int asi = po.owner.id;
		po.sellProperty(p);
		int sa = p.properties.size();
		assertTrue("findHighestRentProperty() works",board.repOk() && p.repOk() && as==(sa+1) && po.owner==null && asi==p.id);

			
	}
	
}