import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Participant {

	private String firstName;
	private String lastName;
	private int startNumber;

	private Team myTeam;
	private ArrayList<Result> results = new ArrayList<>();
	private HashMap<String, Integer> medals = new HashMap<>();
	
	public Participant(String firstName, String lastName, Team team, int startNumber) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		myTeam = team;
		this.startNumber = startNumber;
		
		medals.put("Gold medals", 0);
		medals.put("Silver medals", 0);
		medals.put("Bronze medals", 0);
		
	}
	
	public void setName(String firstName, String lastName) {
	}
	
	public String getName() {
		
		return firstName;
		
	}
	
	public String getFullName() {
		
		return String.format("%s %s", firstName, lastName);
		
	}
	
	public int getStartNumber() {
		
		return startNumber;
		
	}
	
	public Team getTeam() {
		
		return myTeam;
		
	}
	
	public void addResult(Result result) {
			
		results.add(result);
		
	}
	
	//Refaktorera
	public boolean hasReachedMaximumNumberOfAttempts(Event event) {
		
		int numberOfResultsForEvent = 0;
		
		for(Result result : results) {
			
			if(result.getAchievedIn().getName().equals(event.getName())) {
				
				numberOfResultsForEvent++;
				
			}
			
		}
		
		if(numberOfResultsForEvent >= event.getAttemptsAllowed()) {
			
			return true;
			
		} else {
		
			return false;
			
		}
	}
	
	//Refaktorera, kolla in om lambda-uttryck kan vara en bättre lösning än att skicka in Comparator här
	public ArrayList<Result> getResultsSortedByName() {
		
		//Överlagrar den vanliga sorteringsordningen hos Result.
		Collections.sort(results, new Comparator<Result>() {
			
			public int compare(Result result1, Result result2) {
				
				String result1EventName = result1.getAchievedIn().getName();
				String result2EventName = result2.getAchievedIn().getName();
				
				return result1EventName.compareTo(result2EventName);
				
			}
			
		});
		
		return results;
		
	}
	
	/* 
	 * Refaktorera, ta reda på varför switch-statementet inte fungerar, kan det vara något med Integer?
	 * Man kan testa att inte returnera HashMap utan skriva tre separata metoder.
	 * Anledningen till att Participant använder en HashMap och att Team använder tre separata
	 * variabler för medaljerna är att Participant behöver returnera medaljerna till Team, och det blir smidigast
	 * att göra det med en funktion istället för tre. Team behöver inte returnera värderna utan behöver endast kunna
	 * sorteras enligt dem, varför det är lättare att använda tre olika variabler för medaljerna där.
	 */
	public HashMap<String, Integer> getNumberOfMedals() {
		
		medals.put("Gold medals", 0);
		medals.put("Silver medals", 0);
		medals.put("Bronze medals", 0);
		
		for(Result result : results) {
			
			Medal medal = result.getMedal();
			
			if(medal == Medal.GOLD) {
				
				medals.put("Gold medals", medals.get("Gold medals") + 1);
				
			} else if(medal == Medal.SILVER) {
			
				medals.put("Silver medals", medals.get("Silver medals") + 1);
				
			} else if(medal == Medal.BRONZE) {
			
				medals.put("Bronze medals", medals.get("Bronze medals") + 1);
				
			}
			
//			switch(medal) {
//			
//				case GOLD:
//					medals.put("Gold medals", medals.get("Gold medals") + 1);
//					break;
//				case SILVER:
//					medals.put("Silver medals", medals.get("Silver medals") + 1);
//					break;
//				case BRONZE:
//					medals.put("Bronze medals", medals.get("Bronze medals") + 1);
//					break;
//			}
			
		}
		return medals;
		
	}
	
	public String toString() {
		
		return String.format(
				"First name: %s\nLast Name: %s\nTeam Name: %s\nStart Number: %d",
				firstName, 
				lastName, 
				myTeam, 
				startNumber);
		
	}

}