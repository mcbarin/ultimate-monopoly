package tests;


import static org.junit.Assert.*;
import domain.Player;
import domain.Board;

import org.junit.Test;

public class PlayerTest {

	//////////////////////////////////////
	//Tested Methods
	//////////////////////////////////////
	//Player Constructions
	//addCardDebug()
	//addPropertyDebug()
	//deleteProperty()
	//addMoney()
	//setPosition()
	

	
	@Test
	public void testNegativeIDPlayer() {
		Player p = new Player("Test",3200,-13,0,0,null);
		assertFalse("board with zero player repOK is true",p.repOk());
	}
	
	
	
	@Test
	public void testAddCardDebug() {
		Board b = new Board(2);
		Player p = b.getCurrentPlayer();
		p.addCardDebug(16);
		assertTrue("addCardDebug() works",p.repOk() && p.hasCardWithId(16));
	}
	
	@Test
	public void testAddPropertyDebug(){
		Board b = new Board(2);
		Player p = b.getCurrentPlayer();
		p.addPropertyDebug(1, 3);
		assertTrue("property is added successfully",p.repOk() && p.properties.get(0).id == 1 &&  p.properties.get(0).house ==3);
	}

	@Test
	public void testDeleteProperty(){
		Board b = new Board(2);
		Player p = b.getCurrentPlayer();
		p.addPropertyDebug(1, 3);
		int o = p.numberOfProperties;
		p.deleteProperty(p.properties.get(0));
		assertTrue("deleteProperty() works",p.repOk() && o-1==p.numberOfProperties);
	}

	@Test
	public void testAddMoney(){
		Board b = new Board(2);
		Player p = b.getCurrentPlayer();
		p.addMoney(13);
		assertTrue("addMoney() works",p.repOk() && p.money==3213);
	}


	@Test
	public void testSetPosition(){
		Board b = new Board(2);
		Player p = b.getCurrentPlayer();
		p.setPosition(13);
		assertTrue("addMoney() works",p.repOk() && p.row==1 && p.position==13);
	}
	
	

}
