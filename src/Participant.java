import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Participant {

	private String firstName;
	private String lastName;
	private int startNumber;

	private Team myTeam;
	private ArrayList<Result> results = new ArrayList<>();
	//public ArrayList<Event> events;
	
	public Participant(String firstName, String lastName, Team team, int startNumber) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		myTeam = team;
		this.startNumber = startNumber;
		
		team.addMember(this);
		
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
		
		for(int i = 0; i < results.size(); i++) {
			
			if(results.get(i).getAchievedIn().getName().equals(event.getName())) {
				
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
	public void listResultsSortedByName() {
		
		if(results.size() == 0) {
			
			System.out.println(firstName + " " + lastName + " has no registered results");
			
		}
		
		//Överlagrar den vanliga sorteringsordningen hos Result.
		Collections.sort(results, new Comparator<Result>() {
			
			public int compare(Result result1, Result result2) {
				
				String result1EventName = result1.getAchievedIn().getName();
				String result2EventName = result2.getAchievedIn().getName();
				
				return result1EventName.compareTo(result2EventName);
				
			}
			
		});
		
		for(Result result : results) {
			
			System.out.println(result);
			
		}
		
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