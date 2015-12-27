package tests;

import static org.junit.Assert.*;
import domain.Board;
import domain.CardCommunity;
import domain.Player;

import org.junit.Test;

public class CardCommunityTest {

	//////////////////////////////////////
	//Tested Methods
	//////////////////////////////////////
	//doAction() for Community Card 36
	//doAction() for Community Card 37
	//doAction() for Community Card 39
	//doAction() for Community Card 43
	//applyCard40()

	@Test
		public void testDoAction36() {
			Board b = new Board(2);
			CardCommunity c = new CardCommunity(36, "Test", "Test", b);
			Player p = b.getCurrentPlayer();
			c.doAction(p);
			assertTrue("doAction() for Community Card 36 works",c.repOk() && p.repOk() && p.money == 3300);
			
		}

	@Test
		public void testDoAction37() {
			Board b = new Board(2);
			CardCommunity c = new CardCommunity(37, "Test", "Test", b);
			Player p = b.getCurrentPlayer();
			c.doAction(p);
			assertTrue("doAction() for Community Card 37 works",c.repOk() && p.repOk() && p.money == 3210 && p.row==2 && p.position==51);
			
		}

	@Test
		public void testDoAction39() {
			Board b = new Board(2);
			CardCommunity c = new CardCommunity(39, "Test", "Test", b);
			Player p = b.getCurrentPlayer();
			c.doAction(p);
			assertTrue("doAction() for Community Card 39 works",c.repOk() && p.repOk() && p.money == 3150);
			
		}

	@Test
		public void testDoAction43() {
			Board b = new Board(2);
			CardCommunity c = new CardCommunity(43, "Test", "Test", b);
			Player p = b.getCurrentPlayer();
			p.addPropertyDebug(5, 0);
			c.doAction(p);
			assertTrue("doAction() for Community Card 43 works",c.repOk() && p.repOk() && p.money == 3160);
			
		}

	@Test
		public void testApplyCard40() {
			Board b = new Board(2);
			CardCommunity c = new CardCommunity(40, "Test", "Test", b);
			Player p = b.getCurrentPlayer();
			p.addPropertyDebug(5, 0);
			c.applyCard40(false, p);
			assertTrue("applyCard40() works",c.repOk() && p.repOk() && p.row == 1 && p.position == 10);
			
		}

	

}
