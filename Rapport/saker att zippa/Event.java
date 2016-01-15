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
	
	private Result resolveEqualAchievees(Result existingResult, Result newResult) {
		
		if(newResult.getResult() > existingResult.getResult()) {
			
			existingResult.setMedal(null);
			results.remove(existingResult);
			
			return newResult;
			
		}
		return null;
		
	}
	
	private Result determineResultToBeAdded(Result newResult) {
		
		for(Result existingResult : results) {
			
			if(existingResult.getAchievee() == newResult.getAchievee()) {
				
				return resolveEqualAchievees(existingResult, newResult);
				
			}
			
		}
		return newResult;
		
	}
	
	public void addResult(Result result) {
		
		Result resultToBeAdded = determineResultToBeAdded(result);

		if((resultToBeAdded != null)) {

			results.add(resultToBeAdded);

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
		
	}
	
	public void assignMedals() {
		
		if(results.size() == 0) {
			
			return;
			
		}
		
		Collections.sort(results);
		
		int placementCounter = 1;
		
		for(int i = 0; i < results.size(); i++) {
			
			Result result = results.get(i);
			Result lastResult = i == 0 ? null : results.get(i - 1);
			
			boolean thereIsAPreviousResult = lastResult != null;
			boolean currentResultIsNotEqualToLastResult = thereIsAPreviousResult && (result.getResult() != lastResult.getResult());
			
			if(currentResultIsNotEqualToLastResult) {
				
				placementCounter = i + 1;
				
			}
			
			result.setPlacement(placementCounter);
				
			switch(placementCounter) {

			case(1):
				result.setMedal(Medal.GOLD);
			break;
			case(2):
				result.setMedal(Medal.SILVER);
			break;
			case(3):
				result.setMedal(Medal.BRONZE);
			break;
			default:
				result.setMedal(null);
	
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