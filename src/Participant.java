import java.util.ArrayList;
import java.util.Collections;

public class Participant extends Medalier {

	private String firstName;
	private String lastName;
	private int startNumber;

	private Team myTeam;
	private ArrayList<Result> results = new ArrayList<>();
	
	public Participant(String firstName, String lastName, Team team, int startNumber) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.startNumber = startNumber;
		myTeam = team;
		
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
	
	public String getTeamName() {
		
		return myTeam.getName();
		
	}
	
	public void addResult(double result, Event eventAchievedIn) {
			
		results.add(new Result(result, this, eventAchievedIn));
		
	}
	
	public boolean hasReachedMaximumNumberOfAttempts(Event event) {
		
		int numberOfResultsForEvent = 0;
		
		for(Result result : results) {
			
			if(result.getNameOfEventAchievedIn().equals(event.getName())) {
				
				numberOfResultsForEvent++;
				
			}
			
		}
		
		return numberOfResultsForEvent >= event.getAttemptsAllowed() ? true : false;
	}
	
	public ArrayList<Result> getResultsSortedByName() {
		
		//Överlagrar den vanliga sorteringsordningen hos Result.
		Collections.sort(
				results, 
				(Result result1, Result result2) -> 
					result1.getNameOfEventAchievedIn().compareTo(result2.getNameOfEventAchievedIn())
		);
		
		return results;
		
	}
	
	public void calculateMedals() {
		
		/* 
		 * Refaktorera, ta reda på varför switch-statementet inte fungerar, kan det vara något med Integer?
		 * Man kan testa att inte returnera HashMap utan skriva tre separata metoder.
		 */

		resetMedals();
		
		for(Result result : results) {

			Medal medal = result.getMedal();
			
			if(medal == null) {
				
				continue;
				
			}
			
			if(medal == Medal.GOLD) {

				incrementGoldMedals(1);

			} else if(medal == Medal.SILVER) {

				incrementSilverMedals(1);

			} else if(medal == Medal.BRONZE) {

				incrementBronzeMedals(1);

			}

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