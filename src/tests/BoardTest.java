package tests;


import static org.junit.Assert.*;

import org.junit.Test;

import domain.Board;


public class BoardTest {

	@Test(expected = NullPointerException.class)
	public void testNullBoard() {
		Board b=null;
		b.repOk();
	}
	
	@Test
	public void testZeroBoard() {
		Board b= new Board (0);
		assertTrue("board with zero player repOK is true",b.repOk());
	}
	
	@Test
	public void testNormalBoard() {
		Board b= new Board (10);
		assertTrue("board with normal number of player repOK is true",b.repOk());
	}
	
	@Test
	public void testExtremeBoard() {
		Board b= new Board (13);
		assertTrue("board with extreme number of player repOK is true",b.repOk());
	}
	
	
	public static void main(String[] args) {
		BoardTest bt = new BoardTest();
		bt.testZeroBoard();
	}
}
