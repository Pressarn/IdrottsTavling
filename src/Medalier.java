import java.util.HashMap;

public abstract class Medalier {

	private HashMap<String, Integer> medals = new HashMap<>();
	
	public Medalier() {
		
		medals.put("Gold medals", 0);
		medals.put("Silver medals", 0);
		medals.put("Bronze medals", 0);
		
	}
	
	public HashMap<String, Integer> getMedals() {
		
		return medals;
		
	}
	
	/*Anledningen till att det finns tre funktioner och inte en som tar valören som parameter är att
	 * det bara finns tre tillåtna värden och det kan bli fel om man skulle börja hitta på andra benämningar.
	 * På det här sättet blir det garanterat rätt.
	 */
	
	public Integer getGoldMedals() {
		
		return medals.get("Gold medals");
		
	}
	
	public Integer getSilverMedals() {
		
		return medals.get("Silver medals");
		
	}

	public Integer getBronzeMedals() {
	
		return medals.get("Bronze medals");
	
	}
	
	public void incrementGoldMedals(int amount) {
		
		medals.put("Gold medals", getGoldMedals() + amount);
		
	}
	
	public void incrementSilverMedals(int amount) {
		
		medals.put("Silver medals", getSilverMedals() + amount);
		
	}
	
	public void incrementBronzeMedals(int amount) {
		
		medals.put("Bronze medals", getBronzeMedals() + amount);
		
	}
	
	public void incrementMedals(int numberOfGoldMedals, int numberOfSilverMedals, int numberOfBronzeMedals) {
		
		incrementGoldMedals(numberOfGoldMedals);
		incrementSilverMedals(numberOfSilverMedals);
		incrementBronzeMedals(numberOfBronzeMedals);
		
	}
	
	public void resetMedals() {
		
		medals.put("Gold medals", 0);
		medals.put("Silver medals", 0);
		medals.put("Bronze medals", 0);
		
	}
}
