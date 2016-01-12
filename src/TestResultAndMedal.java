import static org.junit.Assert.*;
import org.junit.Test;

public class TestResultAndMedal {

	private IdrottsTavling idrottsTävling = new IdrottsTavling();
	
	Participant adaLovelace = null;
	Participant arthurDent = null;
	Participant jönsJönsson = null;
	Participant barackObama = null;
	
	Event longJump = null;
	Event marathon = null;
	Event javelin = null;
	Event hundredMeters = null;
	
	public void reinitialize() {
		
		idrottsTävling.reinitialize();
		
		adaLovelace = null;
		arthurDent = null;
		jönsJönsson = null;
		barackObama = null;
		
		longJump = null;
		marathon = null;
		javelin = null;
		hundredMeters = null;
		
	}
	
	@Test
	public void testAddingOneMedal() {
		
		idrottsTävling.addParticipant("Ada", "Lovelace", "Computing");
		idrottsTävling.addEvent("Long Jump", 3, true);
		
		adaLovelace = idrottsTävling.getParticipant(100);
		longJump = idrottsTävling.getEvent("Long Jump");
		
		idrottsTävling.addResult(adaLovelace, idrottsTävling.getEvent("Long Jump"), 6.7);
		
		assertEquals(6.7, idrottsTävling.getResultsByParticipant(adaLovelace).get(0).getResult(), 0);
		assertEquals(6.7, idrottsTävling.getResultsByEvent("Long Jump").get(0).getResult(), 0);
		
		assertEquals("Computing\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).debugString());
		
	}
	
	@Test
	public void testAddingTwoMedals() {
		
		testAddingOneMedal();
		
		idrottsTävling.addParticipant("Arthur", "Dent", "Sf");
		arthurDent = idrottsTävling.getParticipant(101);
		
		idrottsTävling.addResult(arthurDent, longJump, 6.8);
		
		assertEquals(6.8, idrottsTävling.getResultsByParticipant(arthurDent).get(0).getResult(), 0);
		
		assertEquals(6.8, idrottsTävling.getResultsByEvent("Long Jump").get(0).getResult(), 0);
		assertEquals(6.7, idrottsTävling.getResultsByEvent("Long Jump").get(1).getResult(), 0);
		
		assertEquals("Sf\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).debugString());
		assertEquals("Computing\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).debugString());
		
	}
	
	@Test
	public void testAddingThreeMedals() {
		
		testAddingTwoMedals();
		
		idrottsTävling.addParticipant("Jöns", "Jönsson", "Historien");
		jönsJönsson = idrottsTävling.getParticipant(102);
		
		idrottsTävling.addResult(jönsJönsson, longJump, 6.9);
		
		assertEquals(6.9, idrottsTävling.getResultsByParticipant(jönsJönsson).get(0).getResult(), 0);
		
		assertEquals(6.9, idrottsTävling.getResultsByEvent("Long Jump").get(0).getResult(), 0);
		assertEquals(6.8, idrottsTävling.getResultsByEvent("Long Jump").get(1).getResult(), 0);
		assertEquals(6.7, idrottsTävling.getResultsByEvent("Long Jump").get(2).getResult(), 0);
		
		assertEquals("Historien\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).debugString());
		assertEquals("Sf\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).debugString());
		assertEquals("Computing\nGold medals: 0, Silver medals: 0, Bronze medals: 1\n",
				idrottsTävling.getResultsSortedByTeam().get(2).debugString());
		
	}
	
	@Test
	public void testAddingFourMedals() {
		
		testAddingThreeMedals();
		
		idrottsTävling.addParticipant("Barack", "Obama", "Presidents");
		barackObama = idrottsTävling.getParticipant(103);
		
		idrottsTävling.addResult(barackObama, longJump, 7.0);
		
		assertEquals(7.0, idrottsTävling.getResultsByParticipant(barackObama).get(0).getResult(), 0);
		
		assertEquals(7.0, idrottsTävling.getResultsByEvent("Long Jump").get(0).getResult(), 0);
		assertEquals(6.9, idrottsTävling.getResultsByEvent("Long Jump").get(1).getResult(), 0);
		assertEquals(6.8, idrottsTävling.getResultsByEvent("Long Jump").get(2).getResult(), 0);
		assertEquals(6.7, idrottsTävling.getResultsByEvent("Long Jump").get(3).getResult(), 0);
		
		assertEquals("Presidents\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).debugString());
		assertEquals("Historien\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).debugString());
		assertEquals("Sf\nGold medals: 0, Silver medals: 0, Bronze medals: 1\n",
				idrottsTävling.getResultsSortedByTeam().get(2).debugString());
		assertEquals("Computing\nGold medals: 0, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(3).debugString());
		
	}
	
	@Test
	public void testAddingSeveralMedalsToEachTeam() {
		
		testAddingFourMedals();
		
		idrottsTävling.addEvent("Marathon", 1, false);
		marathon = idrottsTävling.getEvent("Marathon");
		
		idrottsTävling.addResult(adaLovelace, marathon, 4.56);
		idrottsTävling.addResult(arthurDent, marathon, 4.55);
		idrottsTävling.addResult(jönsJönsson, marathon, 4.54);
		idrottsTävling.addResult(barackObama, marathon, 4.53);
		
		assertEquals(4.56, idrottsTävling.getResultsByParticipant(adaLovelace).get(1).getResult(), 0);
		assertEquals(4.55, idrottsTävling.getResultsByParticipant(arthurDent).get(1).getResult(), 0);
		assertEquals(4.54, idrottsTävling.getResultsByParticipant(jönsJönsson).get(1).getResult(), 0);
		assertEquals(4.53, idrottsTävling.getResultsByParticipant(barackObama).get(1).getResult(), 0);
		
		assertEquals(4.53, idrottsTävling.getResultsByEvent("Marathon").get(0).getResult(), 0);
		assertEquals(4.54, idrottsTävling.getResultsByEvent("Marathon").get(1).getResult(), 0);
		assertEquals(4.55, idrottsTävling.getResultsByEvent("Marathon").get(2).getResult(), 0);
		assertEquals(4.56, idrottsTävling.getResultsByEvent("Marathon").get(3).getResult(), 0);
		
		assertEquals("Presidents\nGold medals: 2, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).debugString());
		assertEquals("Historien\nGold medals: 0, Silver medals: 2, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).debugString());
		assertEquals("Sf\nGold medals: 0, Silver medals: 0, Bronze medals: 2\n",
				idrottsTävling.getResultsSortedByTeam().get(2).debugString());
		assertEquals("Computing\nGold medals: 0, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(3).debugString());
		
	}
	
	@Test
	public void testAddingEqualResults() {
		
		reinitialize();
		
		idrottsTävling.addParticipant("Ada", "Lovelace", "Computing");
		idrottsTävling.addParticipant("Barack", "Obama", "Presidents");
		
		adaLovelace = idrottsTävling.getParticipant(100);
		barackObama = idrottsTävling.getParticipant(101);
		
		idrottsTävling.addEvent("Long Jump", 3, true);
		longJump = idrottsTävling.getEvent("Long Jump");
		
		idrottsTävling.addResult(adaLovelace, longJump, 7.0);
		idrottsTävling.addResult(barackObama, longJump, 7.0);
		
		assertEquals("Computing\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).debugString());
		assertEquals("Presidents\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).debugString());
		
	}
	
	@Test
	public void testAddingSilverMedalierAfterAddingTwoGoldMedaliers() {
		
		testAddingEqualResults();
		
		idrottsTävling.addParticipant("Arthur", "Dent", "Sf");
		arthurDent = idrottsTävling.getParticipant(102);
		
		idrottsTävling.addResult(arthurDent, longJump, 6.9);
		
		assertEquals("Computing\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).debugString());
		assertEquals("Presidents\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).debugString());
		assertEquals("Sf\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(2).debugString());
		
	}
	
	@Test
	public void testAddingBronzeMedalierAfterAddingTwoGoldMedaliersAndOneSilverMedalier() {
		
		testAddingSilverMedalierAfterAddingTwoGoldMedaliers();
		
		idrottsTävling.addParticipant("Jöns", "Jönsson", "Historien");
		jönsJönsson = idrottsTävling.getParticipant(103);
		
		idrottsTävling.addResult(jönsJönsson, longJump, 6.8);
		
		assertEquals("Computing\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).debugString());
		assertEquals("Presidents\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).debugString());
		assertEquals("Sf\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(2).debugString());
		assertEquals("Historien\nGold medals: 0, Silver medals: 0, Bronze medals: 1\n",
				idrottsTävling.getResultsSortedByTeam().get(3).debugString());
		
	}
	
	@Test
	public void testAddingNewWinnerAfterAddingTwoEqualResults() {
		
		testAddingEqualResults();
		
		idrottsTävling.addParticipant("Arthur", "Dent", "Sf");
		arthurDent = idrottsTävling.getParticipant(102);
		
		idrottsTävling.addResult(arthurDent, longJump, 7.1);
		
		assertEquals("Sf\nGold medals: 1, Silver medals: 0, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(0).debugString());
		assertEquals("Computing\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(1).debugString());
		assertEquals("Presidents\nGold medals: 0, Silver medals: 1, Bronze medals: 0\n",
				idrottsTävling.getResultsSortedByTeam().get(2).debugString());
		
	}
	
	@Test
	public void testListingResultsForOneParticipant() {
		
		reinitialize();
		
		idrottsTävling.addParticipant("Ada", "Lovelace", "Computing");
		adaLovelace = idrottsTävling.getParticipant(100);
		
		idrottsTävling.addEvent("Long Jump", 3, true);
		idrottsTävling.addEvent("Marathon", 1, false);
		idrottsTävling.addEvent("Javelin", 3, true);
		idrottsTävling.addEvent("100 meters", 1, false);
		
		longJump = idrottsTävling.getEvent("Long Jump");
		marathon = idrottsTävling.getEvent("Marathon");
		javelin = idrottsTävling.getEvent("Javelin");
		hundredMeters = idrottsTävling.getEvent("100 meters");
		
		idrottsTävling.addResult(adaLovelace, longJump, 6.7);
		idrottsTävling.addResult(adaLovelace, marathon, 2.46);
		idrottsTävling.addResult(adaLovelace, javelin, 40.5);
		idrottsTävling.addResult(adaLovelace, hundredMeters, 15.0);
		
		assertEquals("15.0",
				idrottsTävling.getResultsByParticipant(adaLovelace).get(0).toString());
		
		assertEquals("40.5", 
				idrottsTävling.getResultsByParticipant(adaLovelace).get(1).toString());
		
		assertEquals("6.7", 
					idrottsTävling.getResultsByParticipant(adaLovelace).get(2).toString());
		
		assertEquals("2.46", 
				idrottsTävling.getResultsByParticipant(adaLovelace).get(3).toString());
		
	}
	
	@Test
	public void testMaximumNumberOfAttempts() {
		
		reinitialize();
		
		idrottsTävling.addParticipant("Ada", "Lovelace", "Computing");
		adaLovelace = idrottsTävling.getParticipant(100);
		
		idrottsTävling.addEvent("Long Jump", 3, true);
		longJump = idrottsTävling.getEvent("Long Jump");
		
		idrottsTävling.addResult(adaLovelace, longJump, 6.7);
		idrottsTävling.addResult(adaLovelace, longJump, 6.8);
		
//		Participant adaLovelace = idrottsTävling.getParticipant(100);
//		Event longJump = idrottsTävling.getEvent("Long Jump");
		
		assertEquals(false, adaLovelace.hasReachedMaximumNumberOfAttempts(longJump));
		
		idrottsTävling.addResult(adaLovelace, longJump, 6.9);
		
		assertEquals(true, adaLovelace.hasReachedMaximumNumberOfAttempts(longJump));
		
		idrottsTävling.addResult(adaLovelace,  longJump, 7.0);
		
		assertEquals(1, longJump.getSortedResults().size());
		assertEquals(4, adaLovelace.getResultsSortedByName().size());
		assertEquals(6.9, longJump.getSortedResults().get(0).getResult(), 0);
	}
	
	@Test
	public void testCalculatingMedalsWithNoRegisteredResults() {
		
		
		
	}

}
