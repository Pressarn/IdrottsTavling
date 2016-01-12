import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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
	
	private Result resolveEqualResults(Result existingResult, Result newResult) {
		
		if(newResult.getResult() > existingResult.getResult()) {
			
			results.remove(existingResult);
			return newResult;
			
		}
		return null;
		
	}
	
	private Result determineResultToBeAdded(Result newResult) {
		
		for(Result existingResult : results) {
			
			if(existingResult.getAchievee() == newResult.getAchievee()) {
				
				return resolveEqualResults(existingResult, newResult);
				
			}
			
		}
		return newResult;
		
	}
	
	public void addResult(Result result) {
		
		if(!(result.getAchievee().hasReachedMaximumNumberOfAttempts(this))) {
		
			Result resultToBeAdded = determineResultToBeAdded(result);

			if((resultToBeAdded != null)) {

				results.add(resultToBeAdded);

			}
		}
	}
	
	public void removeParticipantsResults(Participant participant) {
		
		Iterator<Result> iterator = results.iterator();
		
		while(iterator.hasNext()) {
			
			Result result = iterator.next();
			
			if(result.getAchievee() == participant) {
				
				iterator.remove();
				
			}
			
		}
		
//		for(Result result : results) {
//			
//			if(result.getAchievee() == participant) {
//				
//				results.remove(result);
//				
//			}
//			
//		}
		
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
		
		if(results.size() == 0) {
			
			return;
			
		}
		
		Collections.sort(results);
		
		Result goldBenchmark = results.get(0);
		Result silverBenchmark = null;
		Result bronzeBenchmark = null;
		
		for(Result result : results) {
			
			result.setMedal(null);
			
			double theActualResult = result.getResult();
			
			if(theActualResult == goldBenchmark.getResult()) {
				
				result.setMedal(Medal.GOLD);
				
			} else {
				
				if(silverBenchmark == null) {
					
					silverBenchmark = result;
					
				}
					
				if(theActualResult == silverBenchmark.getResult()) {
					
					result.setMedal(Medal.SILVER);
					
				} else {
					
					if(bronzeBenchmark == null) {
						
						bronzeBenchmark = result;
						
					}
					
					if(theActualResult == bronzeBenchmark.getResult()) {
						
						result.setMedal(Medal.BRONZE);
						
					}
					
				}
				
			}
			
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