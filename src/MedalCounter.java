import java.util.ArrayList;
import java.util.HashMap;

public class MedalCounter {

	private HashMap<String, Integer> medals = new HashMap<>();
	
	public MedalCounter() {
		
		medals.put("Gold medals", 0);
		medals.put("Silver medals", 0);
		medals.put("Bronze medals", 0);
		
	}
	
	public HashMap<String, Integer> getMedals() {
		
		return medals;
		
	}
	
	/*Anledning till att det finns tre funktioner och inte en som tar valören som parameter är att
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
	
	/* 
	 * Refaktorera, ta reda på varför switch-statementet inte fungerar, kan det vara något med Integer?
	 * Man kan testa att inte returnera HashMap utan skriva tre separata metoder.
	 */
	
	public HashMap<String, Integer> incrementMedalsBasedOnResults(ArrayList<Result> results) {
		
		resetMedals();
		
		for(Result result : results) {
			
			Medal medal = result.getMedal();
			
			if(medal == Medal.GOLD) {
				
				incrementGoldMedals(1);
				
			} else if(medal == Medal.SILVER) {
			
				incrementSilverMedals(1);
				
			} else if(medal == Medal.BRONZE) {
			
				incrementBronzeMedals(1);
				
			}
			
		}
		
//		switch(medal) {
//		
//			case GOLD:
//				medals.put("Gold medals", medals.get("Gold medals") + 1);
//				break;
//			case SILVER:
//				medals.put("Silver medals", medals.get("Silver medals") + 1);
//				break;
//			case BRONZE:
//				medals.put("Bronze medals", medals.get("Bronze medals") + 1);
//				break;
//		}
		return medals;
		
	}
	
	public void incrementMedalsBasedOnMembers(ArrayList<Participant> members) {
		
		resetMedals();

		for(Participant member : members) {

			try {

				HashMap<String, Integer> membersMedals = member.getNumberOfMedals();

				incrementGoldMedals(membersMedals.get("Gold medals"));
				incrementSilverMedals(membersMedals.get("Silver medals"));
				incrementBronzeMedals(membersMedals.get("Bronze medals"));

			} catch(NullPointerException e) {

				continue;

			}

		}
		
	}
	
	public void resetMedals() {
		
		medals.put("Gold medals", 0);
		medals.put("Silver medals", 0);
		medals.put("Bronze medals", 0);
		
	}
}
