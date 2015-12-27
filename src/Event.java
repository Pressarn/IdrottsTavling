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
		 * Anledningen till att jag använder objekt som benchmarks och inte doublevärden
		 * är att det är möjligt att registrera ett resultat som är 0.0. Därför kan jag inte
		 * använda if(benchmark == 0.0) { benchmark = theActualResult } som check för att sätta
		 * ett nytt benchmark.
		 * 
		 * 
		 */
		
		ArrayList<Result> goldMedalResults = new ArrayList<>();
		ArrayList<Result> silverMedalResults = new ArrayList<>();
		ArrayList<Result> bronzeMedalResults = new ArrayList<>();
		
		Collections.sort(results);
		
		Result goldBenchmark = results.get(0);
		Result silverBenchmark = null;
		Result bronzeBenchmark = null;
		
		for(Result result : results) {
			
			result.setMedal(null);
			
			double theActualResult = result.getResult();
			
			if(theActualResult == goldBenchmark.getResult()) {
				
				goldMedalResults.add(result);
				
				
			} else {
				
				if(silverBenchmark == null) {
					
					silverBenchmark = result;
					
				}
					
				if(theActualResult == silverBenchmark.getResult()) {
					
					silverMedalResults.add(result);
					
				} else {
					
					if(bronzeBenchmark == null) {
						
						bronzeBenchmark = result;
						
					}
					
					if(theActualResult == bronzeBenchmark.getResult()) {
						
						bronzeMedalResults.add(result);
						
					}
					
				}
				
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
		
	}
	
	public ArrayList<Result> getSortedResults() {
		
		Collections.sort(results);
		
		return results;
		
	}
	
	public String toString() {
		
		return String.format("Name: %s, Attempts allowed: %d, Bigger better: %s", name, attemptsAllowed, biggerBetter);
		
	}
}