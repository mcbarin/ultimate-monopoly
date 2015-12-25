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
	
	
	public static void main(String[] args) {
		BoardTest bt = new BoardTest();
		bt.testNullBoard();
	}
}
