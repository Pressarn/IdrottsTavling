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
		
		Collections.sort(results);
		
		for(Result result : results) {
			
			result.setMedal(null);
			
		}
		
		if(results.size() > 0) {
			
			results.get(0).setMedal(new Medal("Gold"));
			
		}
		
		if(results.size() > 1) {
			
			results.get(1).setMedal(new Medal("Silver"));
			
		}
		
		if(results.size() > 2) {
			
			results.get(2).setMedal(new Medal("Bronze"));
			
		}
		
	}
	
	public ArrayList<Result> getSortedResults() {
		
		Collections.sort(results);
		
		return results;
		
	}
	
	public String toString() {
		
		return String.format("Name: %s, Attempts allowed: %d, Bigger better: %s", name, attemptsAllowed, biggerBetter);
		
	}
}