public class MedalCounter {

	private int[] medals = new int[3];
	
	public int[] getMedals() {
		
		return medals;
		
	}
	
	/*Anledningen till att det finns tre funktioner och inte en som tar valören som parameter är att
	 * det bara finns tre tillåtna värden och det kan bli fel om man skulle börja hitta på andra benämningar.
	 * På det här sättet blir det garanterat rätt.
	 */
	
	public Integer getGoldMedals() {
		
		return medals[0];
		
	}
	
	public Integer getSilverMedals() {
		
		return medals[1];
		
	}

	public Integer getBronzeMedals() {
	
		return medals[2];
	
	}
	
	public void incrementGoldMedals(int amount) {
		
		medals[0] += amount;
		
	}
	
	public void incrementSilverMedals(int amount) {
		
		medals[1] += amount;
		
	}
	
	public void incrementBronzeMedals(int amount) {
		
		medals[2] += amount;
		
	}
	
	public void incrementMedals(int numberOfGoldMedals, int numberOfSilverMedals, int numberOfBronzeMedals) {
		
		incrementGoldMedals(numberOfGoldMedals);
		incrementSilverMedals(numberOfSilverMedals);
		incrementBronzeMedals(numberOfBronzeMedals);
		
	}
	
	public void resetMedals() {
		
		medals[0] = 0;
		medals[1] = 0;
		medals[2] = 0;
		
	}
}
