import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	Board test = new Board(4);

	@Test
	public void getNumberOfSameColorTest() {
		Board test = new Board(4);
		assertTrue("It works",test.getNumberOfSameColor(0)==3);
	}
	
	@Test
	public void getSquareFromBoardTest(){
		Square s = test.getSquareFromBoard(5);
		// id = 5
		//row =0
		//pos = 5
		assertTrue("id is true", s.id==5);
		assertTrue("row is true", s.row==0);
		assertTrue("position is true", s.position==5);
		
	}
	
	@Test 
	public void getSquareWithRowAndPositionTest(){
		test = new Board(4);
		Square s = test.getSquareWithRowAndPosition(1, 12);
		// id = 36
		//row = 1
		//pos = 12
		assertTrue("id is true", s.id==36);
		assertTrue("row is true", s.row==1);
		assertTrue("position is true", s.position==12);
		
	}
	
	@Test
	public void getOtherPropertiesTest(){
		test = new Board(4);
		
		// color id = 19
		// There are 3 properties. id of them(116,117,119)
		
		assertTrue("getOtherProperties works", test.getOtherProperties(19)[0]==116);
		assertTrue("getOtherProperties works", test.getOtherProperties(19)[1]==117);
		assertTrue("getOtherProperties works", test.getOtherProperties(19)[2]==119);

	}
	
	@Test
	public void payToPoolTest(){
		test = new Board(4);
		
		int pool = test.pool;
		
		test.payToPool(100);
		
		assertTrue("Pay to pool works",pool == (test.pool-100));
	}

}
