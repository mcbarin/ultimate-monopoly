package tests;

import static org.junit.Assert.*;
import domain.Player;
import domain.Board;
import domain.SquareCabCompany;
import domain.SquareProperty;

import org.junit.Test;

import domain.CardChance;
public class CardChanceTest {

	//////////////////////////////////////
	//Tested Methods
	//////////////////////////////////////
	//doAction() for Chance Card 2
	//doAction() for Chance Card 3
	//doAction() for Chance Card 10
	//applyCard31()
	//applyCard18()
	
	
	@Test
	public void testDoAction2() {
		Board b = new Board(2);
		CardChance c = new CardChance(2, "Test", "Test", b);
		Player p = b.getCurrentPlayer();
		p.row = 1;
		p.position = 22;
		
		c.doAction(p);
		assertTrue("doAction is true for Chance Card Number 2",c.repOk() && p.position == 28);
		
	}
	@Test
	public void testDoAction3() {
		Board b = new Board(2);
		CardChance c = new CardChance(3, "Test", "Test", b);
		Player p = b.getCurrentPlayer();
		
		c.doAction(p);
		assertTrue("doAction is true for Chance Card Number 3",c.repOk() && p.position == 10 && p.row==1);
		
	}
	@Test
	public void testDoAction10() {
		Board b = new Board(2);
		CardChance c = new CardChance(10, "Test", "Test", b);
		Player p = b.getCurrentPlayer();
		
		c.doAction(p);
		assertTrue("doAction is true for Chance Card Number 10",c.repOk() && p.money == 3050);
		
	}
	@Test
	public void testApplyCard18() {
		Board b = new Board(2);
		CardChance c = new CardChance(18, "Test", "Test", b);
		Player p = b.getCurrentPlayer();
		p.addProperty((SquareProperty)b.getSquareFromBoard(7));
		
		c.applyCard18((SquareProperty)b.getSquareFromBoard(7));
		assertTrue("testApplyCard18() works",c.repOk() && ((SquareProperty)b.getSquareFromBoard(7)).owner==null);
		
	}

	@Test
	public void testApplyCard31() {
		Board b = new Board(2);
		CardChance c = new CardChance(31, "Test", "Test", b);
		Player p = b.getCurrentPlayer();
		
		c.applyCard31(b.getSquareWithRowAndPosition(2, 22), p);
		assertTrue("ApplyCard31() works",c.repOk() && ((SquareCabCompany)b.getSquareWithRowAndPosition(2, 22)).owner.id == p.id);
		
	}

}
