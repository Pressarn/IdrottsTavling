import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class TestParticipant {
	
	IdrottsTavling idrottsTävling = new IdrottsTavling();
	
	@Test
	public void testAddingOneParticipant() {
		
		int startNumber = idrottsTävling.getParticipantCounter();
		
		assertEquals(null, idrottsTävling.getParticipant(startNumber));
		
		idrottsTävling.addParticipant("Ada", "Lovelace", "Computing");
		
		int nextStartNumber = idrottsTävling.getParticipantCounter();
		
		assertEquals("Ada Lovelace", idrottsTävling.getParticipant(startNumber).getFullName());
		assertThat(nextStartNumber, not(startNumber));
	}
	
	@Test
	public void testAddingDuplicateParticipant() {
		
		testAddingOneParticipant();
		
		int startNumber = idrottsTävling.getParticipantCounter();
		idrottsTävling.addParticipant("Ada", "Lovelace", "Computing");
		
		assertEquals("Ada Lovelace", idrottsTävling.getParticipant(startNumber - 1).getFullName());
		assertEquals("Ada Lovelace", idrottsTävling.getParticipant(startNumber).getFullName());
		
	}
	
	@Test
	public void testRemovingParticipant() {
		
		testAddingOneParticipant();
		
		idrottsTävling.removeParticipant(idrottsTävling.getParticipant(100));
		
		assertEquals(null, idrottsTävling.getParticipant(100));
		
	}

}
