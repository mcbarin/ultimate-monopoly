package tests;


import static org.junit.Assert.*;

import org.junit.Test;

import domain.Board;
import domain.Square;


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
	
	@Test
	public void getNumberOfSameColorTest() {
		Board test = new Board(4);
		assertTrue("getNumberOfSameColor() works correctly",test.getNumberOfSameColor(0)==3);
	}
	
	@Test
	public void getSquareFromBoardTest(){
		Board test = new Board(4);
		Square s = test.getSquareFromBoard(5);
		// id = 5
		//row =0
		//pos = 5
		assertTrue("getSquareFromBoardTest() works correctly", s.id==5 && s.row==0 &&  s.position==5);		
	}
	
	@Test 
	public void getSquareWithRowAndPositionTest(){
		Board test = new Board(4);
		Square s = test.getSquareWithRowAndPosition(1, 12);
		// id = 36
		//row = 1
		//pos = 12
		assertTrue("id is true", s.id==36 && s.row==1 && s.position==12);
		
	}
	
	@Test
	public void getOtherPropertiesTest(){
		Board test = new Board(4);
		// color id = 19
		// There are 3 properties. id of them(116,117,119)		
		assertTrue("getOtherProperties() works correctly", test.getOtherProperties(19)[0]==116 &&  test.getOtherProperties(19)[1]==117 &&  test.getOtherProperties(19)[2]==119);
	}
	
	@Test
	public void payToPoolTest(){
		Board test = new Board(4);	
		int pool = test.pool;		
		test.payToPool(100);	
		assertTrue("payToPool() works correctly",pool == (test.pool-100));
	}

	
	
	
	
	
}
