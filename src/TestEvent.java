import static org.junit.Assert.*;

import org.junit.Test;

public class TestEvent {

	IdrottsTavling idrottsTävling = new IdrottsTavling();
	
	@Test
	public void testAddingOneEvent() {
		
		idrottsTävling.addEvent("Long Jump", 3, true);
		
		assertEquals("Long Jump", (idrottsTävling.getEvent("Long Jump").getName()));
		
	}

}
