/**
 * 
 * Simon Olofsson - siol0547
 * 
 */

public class MedalCounter {

	private int[] medals = new int[3];
	
	public int[] getMedals() {
		
		return medals;
		
	}
	
	public int getGoldMedals() {
		
		return medals[0];
		
	}
	
	public int getSilverMedals() {
		
		return medals[1];
		
	}

	public int getBronzeMedals() {
	
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
