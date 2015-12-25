package tests;

import static org.junit.Assert.*;
import domain.Player;

import org.junit.Test;

import domain.CardChance;
public class CardChanceTest {

	@Test
	public void doActionTest() {
		
		CardChance c = new CardChance(16, "Test", "Test", null);
		Player p = new Player("Test",3200,0,0,0,null);
		
		c.doAction(p);
		assertTrue("doAction is true",p.money == 3350);
		
	}

}
