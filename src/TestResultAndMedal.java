import static org.junit.Assert.*;
import org.junit.Test;

public class TestResultAndMedal {

	private IdrottsTävling idrottsTävling = new IdrottsTävling();
	
	@Test
	public void testAddingOneMedal() {
		
		idrottsTävling.addParticipant("Ada", "Lovelace", "Computing");
		idrottsTävling.addEvent("Long Jump", 3, true);
		
		idrottsTävling.addResult(100, "Long Jump", 6.7);
		
		assertEquals(6.7, idrottsTävling.getResultsByParticipant(100).get(0).getResult(), 0);
		assertEquals(6.7, idrottsTävling.getResultsByEvent("Long Jump").get(0).getResult(), 0);
		
		assertEquals("Computing\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).getMedals());
		
	}
	
	@Test
	public void testAddingTwoMedals() {
		
		testAddingOneMedal();
		
		idrottsTävling.addParticipant("Arthur", "Dent", "Sf");
		
		idrottsTävling.addResult(101, "Long Jump", 6.8);
		
		assertEquals(6.8, idrottsTävling.getResultsByParticipant(101).get(0).getResult(), 0);
		
		assertEquals(6.8, idrottsTävling.getResultsByEvent("Long Jump").get(0).getResult(), 0);
		assertEquals(6.7, idrottsTävling.getResultsByEvent("Long Jump").get(1).getResult(), 0);
		
		assertEquals("Sf\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).getMedals());
		assertEquals("Computing\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).getMedals());
		
	}
	
	@Test
	public void testAddingThreeMedals() {
		
		testAddingTwoMedals();
		
		idrottsTävling.addParticipant("Jöns", "Jönsson", "Historien");
		idrottsTävling.addResult(102, "Long Jump", 6.9);
		
		assertEquals(6.9, idrottsTävling.getResultsByParticipant(102).get(0).getResult(), 0);
		
		assertEquals(6.9, idrottsTävling.getResultsByEvent("Long Jump").get(0).getResult(), 0);
		assertEquals(6.8, idrottsTävling.getResultsByEvent("Long Jump").get(1).getResult(), 0);
		assertEquals(6.7, idrottsTävling.getResultsByEvent("Long Jump").get(2).getResult(), 0);
		
		assertEquals("Historien\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).getMedals());
		assertEquals("Sf\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).getMedals());
		assertEquals("Computing\nGold medals: 0, Silver medals: 0, Bronze medals: 1\n",
				idrottsTävling.getResultsSortedByTeam().get(2).getMedals());
		
	}
	
	@Test
	public void testAddingFourMedals() {
		
		testAddingThreeMedals();
		
		idrottsTävling.addParticipant("Barack", "Obama", "Presidents");
		idrottsTävling.addResult(103, "Long Jump", 7.0);
		
		assertEquals(7.0, idrottsTävling.getResultsByParticipant(103).get(0).getResult(), 0);
		
		assertEquals(7.0, idrottsTävling.getResultsByEvent("Long Jump").get(0).getResult(), 0);
		assertEquals(6.9, idrottsTävling.getResultsByEvent("Long Jump").get(1).getResult(), 0);
		assertEquals(6.8, idrottsTävling.getResultsByEvent("Long Jump").get(2).getResult(), 0);
		assertEquals(6.7, idrottsTävling.getResultsByEvent("Long Jump").get(3).getResult(), 0);
		
		assertEquals("Presidents\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).getMedals());
		assertEquals("Historien\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).getMedals());
		assertEquals("Sf\nGold medals: 0, Silver medals: 0, Bronze medals: 1\n",
				idrottsTävling.getResultsSortedByTeam().get(2).getMedals());
		assertEquals("Computing\nGold medals: 0, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(3).getMedals());
		
	}
	
	@Test
	public void testAddingSeveralMedalsToEachTeam() {
		
		testAddingFourMedals();
		
		idrottsTävling.addEvent("Marathon", 1, false);
		
		idrottsTävling.addResult(100, "Marathon", 4.56);
		idrottsTävling.addResult(101, "Marathon", 4.55);
		idrottsTävling.addResult(102, "Marathon", 4.54);
		idrottsTävling.addResult(103, "Marathon", 4.53);
		
		assertEquals(4.56, idrottsTävling.getResultsByParticipant(100).get(1).getResult(), 0);
		assertEquals(4.55, idrottsTävling.getResultsByParticipant(101).get(1).getResult(), 0);
		assertEquals(4.54, idrottsTävling.getResultsByParticipant(102).get(1).getResult(), 0);
		assertEquals(4.53, idrottsTävling.getResultsByParticipant(103).get(1).getResult(), 0);
		
		assertEquals(4.53, idrottsTävling.getResultsByEvent("Marathon").get(0).getResult(), 0);
		assertEquals(4.54, idrottsTävling.getResultsByEvent("Marathon").get(1).getResult(), 0);
		assertEquals(4.55, idrottsTävling.getResultsByEvent("Marathon").get(2).getResult(), 0);
		assertEquals(4.56, idrottsTävling.getResultsByEvent("Marathon").get(3).getResult(), 0);
		
		assertEquals("Presidents\nGold medals: 2, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).getMedals());
		assertEquals("Historien\nGold medals: 0, Silver medals: 2, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).getMedals());
		assertEquals("Sf\nGold medals: 0, Silver medals: 0, Bronze medals: 2\n",
				idrottsTävling.getResultsSortedByTeam().get(2).getMedals());
		assertEquals("Computing\nGold medals: 0, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(3).getMedals());
		
	}
	
	@Test
	public void testAddingEqualResults() {
		
		idrottsTävling.addParticipant("Ada", "Lovelace", "Computing");
		idrottsTävling.addParticipant("Barack", "Obama", "Presidents");
		
		idrottsTävling.addEvent("Long Jump", 3, true);
		
		idrottsTävling.addResult(100, "Long Jump", 7.0);
		idrottsTävling.addResult(101, "Long Jump", 7.0);
		
		assertEquals("Computing\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).getMedals());
		assertEquals("Presidents\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).getMedals());
		
	}
	
	@Test
	public void testAddingSilverMedalierAfterAddingTwoGoldMedaliers() {
		
		testAddingEqualResults();
		
		idrottsTävling.addParticipant("Arthur", "Dent", "Sf");
		
		idrottsTävling.addResult(102, "Long Jump", 6.9);
		
		assertEquals("Computing\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).getMedals());
		assertEquals("Presidents\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).getMedals());
		assertEquals("Sf\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(2).getMedals());
		
	}
	
	@Test
	public void testAddingBronzeMedalierAfterAddingTwoGoldMedaliersAndOneSilverMedalier() {
		
		testAddingSilverMedalierAfterAddingTwoGoldMedaliers();
		
		idrottsTävling.addParticipant("Jöns", "Jönsson", "Historien");
		
		idrottsTävling.addResult(103, "Long Jump", 6.8);
		
		assertEquals("Computing\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).getMedals());
		assertEquals("Presidents\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).getMedals());
		assertEquals("Sf\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(2).getMedals());
		assertEquals("Historien\nGold medals: 0, Silver medals: 0, Bronze medals: 1\n",
				idrottsTävling.getResultsSortedByTeam().get(3).getMedals());
		
	}
	
	@Test
	public void testAddingNewWinnerAfterAddingTwoEqualResults() {
		
		testAddingEqualResults();
		
		idrottsTävling.addParticipant("Arthur", "Dent", "Sf");
		
		idrottsTävling.addResult(102, "Long Jump", 7.1);
		
		assertEquals("Sf\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).getMedals());
		assertEquals("Computing\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).getMedals());
		assertEquals("Presidents\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(2).getMedals());
		
	}
	
	@Test
	public void testListingResultsForOneParticipant() {
		
		idrottsTävling.addParticipant("Ada", "Lovelace", "Computing");
		
		idrottsTävling.addEvent("Long Jump", 3, true);
		idrottsTävling.addEvent("Marathon", 1, false);
		idrottsTävling.addEvent("Javelin", 3, true);
		idrottsTävling.addEvent("100 meters", 1, false);
		
		idrottsTävling.addResult(100, "Long Jump", 6.7);
		idrottsTävling.addResult(100, "Marathon", 2.46);
		idrottsTävling.addResult(100, "Javelin", 40.5);
		idrottsTävling.addResult(100, "100 meters", 15.0);
		
		assertEquals("Event:\n100 meters\nResult: 15.0",
				idrottsTävling.getResultsByParticipant(100).get(0).toString());
		
		assertEquals("Event:\nJavelin\nResult: 40.5", 
				idrottsTävling.getResultsByParticipant(100).get(1).toString());
		
		assertEquals("Event:\nLong Jump\nResult: 6.7", 
					idrottsTävling.getResultsByParticipant(100).get(2).toString());
		
		assertEquals("Event:\nMarathon\nResult: 2.46", 
				idrottsTävling.getResultsByParticipant(100).get(3).toString());
		
	}

}
