package tests;


import static org.junit.Assert.*;

import org.junit.Test;

import domain.Board;
import domain.Square;


public class BoardTest {
	//////////////////////////////////////
	//Tested Methods
	//////////////////////////////////////
	//Board Constructor
	//getNumberOfSameColor()
	//getSquareFromBoard()
	//getSquareWithRowAndPosition()
	//getOtherProperties()
	//payToPool()
	//getCurrentPlayer()
	//setCurrentPlayer()
	//nextPlayer()
	//getHalfPool()
	//getNumberOfPlayers()
	
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
	public void testGetNumberOfSameColor() {
		Board test = new Board(4);
		assertTrue("getNumberOfSameColor() works correctly",test.repOk() && test.getNumberOfSameColor(0)==3);
	}
	
	@Test
	public void testGetSquareFromBoard(){
		Board test = new Board(4);
		Square s = test.getSquareFromBoard(5);
		// id = 5
		//row =0
		//pos = 5
		assertTrue("getSquareFromBoardTest() works correctly",test.repOk() && s.id==5 && s.row==0 &&  s.position==5);		
	}
	
	@Test 
	public void testGetSquareWithRowAndPosition(){
		Board test = new Board(4);
		Square s = test.getSquareWithRowAndPosition(1, 12);
		// id = 36
		//row = 1
		//pos = 12
		assertTrue("id is true",test.repOk() && s.id==36 && s.row==1 && s.position==12);
		
	}
	
	@Test
	public void testGetOtherProperties(){
		Board test = new Board(4);
		// color id = 19
		// There are 3 properties. id of them(116,117,119)		
		assertTrue("getOtherProperties() works correctly",test.repOk() && test.getOtherProperties(19)[0]==116 &&  test.getOtherProperties(19)[1]==117 &&  test.getOtherProperties(19)[2]==119);
	}
	
	@Test
	public void testPayToPool(){
		Board test = new Board(4);	
		int pool = test.pool;		
		test.payToPool(100);	
		assertTrue("payToPool() works correctly",test.repOk() && pool == (test.pool-100));
	}
	
	@Test
	public void testGetCurrentPlayer() {
		Board b= new Board (10);
		assertTrue("getCurrentPlayer() works",b.repOk() && b.getCurrentPlayer().id==0);
	}
	
	@Test
	public void testSetCurrentPlayer() {
		Board b= new Board (10);
		b.setCurrentPlayer(b.players.get(8));
		assertTrue("getCurrentPlayer() works",b.repOk() && b.getCurrentPlayer().id==8);
	}
	
	@Test
	public void testNextPlayer() {
		Board b= new Board (10);
		b.nextPlayer();
		assertTrue("nextPlayer() works",b.repOk() && b.getCurrentPlayer().id==1);
	}

	@Test
	public void testGetHalfPool() {
		Board b= new Board (10);
		b.pool = 1300;
		b.getHalfPool(b.players.get(8));
		assertTrue("testGetHalfPool() works",b.repOk() && b.players.get(8).money==3850);
	}

	@Test
	public void testGetNumberOfPlayers() {
		Board b= new Board (10);
		assertTrue("getNumberOfPlayers() works",b.repOk() && b.getNumberOfPlayers()==10);
	}

	
	
	
	
	
}
