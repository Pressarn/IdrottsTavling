import static org.junit.Assert.*;

import org.junit.Test;

public class TestEvent {

	IdrottsTavling idrottsTävling = new IdrottsTavling();
	
	@Test
	public void testAddingOneEvent() {
		
		idrottsTävling.addEvent("Long Jump", 3, true);
		
		assertEquals("Long Jump", (idrottsTävling.getEvent("Long Jump").getName()));
		
	}
	
//	@Test (expected = IndexOutOfBoundsException.class)
//	public void testAssigningMedalsWithNoRegisteredResults() {
//		
//		idrottsTävling.addEvent("Long jump", 3, true);
//		
//		idrottsTävling.getResultsSortedByTeam();
//		
//	}
//	
//	@Test (expected = IndexOutOfBoundsException.class)
//	public void testAssigningMedalsWithResultsRegisteredInOneEvent() {
//		
//		idrottsTävling.addEvent("Long Jump", 3, true);
//		idrottsTävling.addEvent("Marathon", 1, false);
//		
//		idrottsTävling.addParticipant("Ada", "Lovelace", "Computing");
//		idrottsTävling.addResult(100, "Long Jump", 6.7);
//		
//		idrottsTävling.getResultsSortedByTeam();
//		
//	}

}
