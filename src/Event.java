import java.util.ArrayList;
import java.util.Collections;

public class Event {
	
	private String name;
	private int attemptsAllowed;
	private boolean biggerBetter;

	private ArrayList<Result> results = new ArrayList<>();
	
	public Event(String name, int attemptsAllowed, boolean biggerBetter) {
		
		this.name = name;
		this.attemptsAllowed = attemptsAllowed;
		this.biggerBetter = biggerBetter;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public int getAttemptsAllowed() {
		
		return attemptsAllowed;
		
	}
	
	public boolean getBiggerBetter() {
		
		return biggerBetter;
		
	}
	
	public void addResult(Result result) {
		
		results.add(result);
		
	}
	
	public void removeParticipantsResults(Participant participant) {
		
		for(Result result : results) {
			
			if(result.getAchievee() == participant) {
				
				results.remove(result);
				
			}
			
		}
		
	}
	
	//Refaktorera
	public void assignMedals() {
		
		/*
		 * 
		 * Den här funktionen tar ännu inte hänsyn till likaplaceringar.
		 * 
		 * 
		 */
		
		ArrayList<Result> goldMedalResults = new ArrayList<>();
		ArrayList<Result> silverMedalResults = new ArrayList<>();
		ArrayList<Result> bronzeMedalResults = new ArrayList<>();
		
		Collections.sort(results);
		
		for(Result result : results) {
			
			result.setMedal(null);
			
			double theActualResult = result.getResult();
			
			if(theActualResult == results.get(0).getResult()) {
				
				goldMedalResults.add(result);
				
			} else if(theActualResult == results.get(1).getResult()) {
				
				silverMedalResults.add(result);
				
			} else if(theActualResult == results.get(2).getResult()) {
				
				bronzeMedalResults.add(result);
				
			}
			
		}
		
		for(Result goldMedalResult : goldMedalResults) {
		
			goldMedalResult.setMedal(Medal.GOLD);
			
		}
		
		for(Result silverMedalResult : silverMedalResults) {
			
			silverMedalResult.setMedal(Medal.SILVER);
			
		}

		for(Result bronzeMedalResult : bronzeMedalResults) {
	
			bronzeMedalResult.setMedal(Medal.BRONZE);
	
		}
		
		/*if(results.size() > 0) {
			
			results.get(0).setMedal(Medal.GOLD);
			
		}
		
		if(results.size() > 1) {
			
			results.get(1).setMedal(Medal.SILVER);
			
		}
		
		if(results.size() > 2) {
			
			results.get(2).setMedal(Medal.BRONZE);
			
		}*/
		
	}
	
	public ArrayList<Result> getSortedResults() {
		
		Collections.sort(results);
		
		return results;
		
	}
	
	public String toString() {
		
		return String.format("Name: %s, Attempts allowed: %d, Bigger better: %s", name, attemptsAllowed, biggerBetter);
		
	}
}