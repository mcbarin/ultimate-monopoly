package tests;

import static org.junit.Assert.*;
import domain.CardCommunity;
import domain.Player;

import org.junit.Test;

public class CardCommunityTest {

	@Test
		public void doActionTest() {
			
			CardCommunity c = new CardCommunity(36, "Test", "Test", null);
			Player p = new Player("Test",3200,0,0,0,null);
			c.doAction(p);
			assertTrue("doAction is true",p.money == 3300);
			
		}
	

}
