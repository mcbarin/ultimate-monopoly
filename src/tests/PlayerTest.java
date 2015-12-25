package tests;


import static org.junit.Assert.*;
import domain.Player;

import org.junit.Test;

public class PlayerTest {

	Player p = new Player("Test",3200,0,0,0,null);

	@Test
	public void addCardTest() {
		p = new Player("Test",3200,0,0,0,null);
		p.addCardDebug(16);
		assertTrue(p.hasCardWithId(16));
	}
	
	@Test
	public void addPropertyDebugTest(){
		p = new Player("Test",3200,0,0,0,null);
		p.addPropertyDebug(1, 3);
		assertTrue("property is added successfully",p.properties.get(0).id == 1);
		assertTrue("house is added", p.properties.get(0).house ==3);
	}
	
	@Test
	public void updateRentPricesTest(){
		p = new Player("Test",3200,0,0,0,null);
		int rentBefore,rentAfter;
		p.addPropertyDebug(5, 0);
		p.addPropertyDebug(6, 0);
		p.addPropertyDebug(7, 0);
		rentBefore = p.properties.get(0).rent;
		p.updateRentPrices(p.properties.get(0).color);
		rentAfter=p.properties.get(0).rent;
		assertTrue("Rent price is updated",rentBefore*3 == rentAfter);
	}
	
	

}
