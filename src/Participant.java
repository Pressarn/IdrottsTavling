import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * Simon Olofsson - siol0547
 * 
 */

public class Participant {

	private String firstName;
	private String lastName;
	private int startNumber;

	private Team myTeam;
	private ArrayList<Result> results = new ArrayList<>();
	private MedalCounter medalCounter = new MedalCounter();
	
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
	
	public Integer getGoldMedals() {

		return medalCounter.getGoldMedals();

	}
	  
	public Integer getSilverMedals() {

		return medalCounter.getSilverMedals();

	}

	public Integer getBronzeMedals() {

		return medalCounter.getBronzeMedals();

	}
	
	public void calculateMedals() {

		medalCounter.resetMedals();
		
		for(Result result : results) {

			Medal medal = result.getMedal();
			
			if(medal == null) {
				
				continue;
				
			}
			
			if(medal == Medal.GOLD) {

				medalCounter.incrementGoldMedals(1);

			} else if(medal == Medal.SILVER) {

				medalCounter.incrementSilverMedals(1);

			} else if(medal == Medal.BRONZE) {

				medalCounter.incrementBronzeMedals(1);

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