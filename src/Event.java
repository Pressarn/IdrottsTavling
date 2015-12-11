import java.util.ArrayList;
import java.util.Collections;

public class Event {
	
	private String name;
	private int attemptsAllowed;
	private boolean biggerBetter;

	//private ArrayList<Participant> participants = new ArrayList<>();
	private ArrayList<Result> results = new ArrayList<>();
	
	private Medal goldMedal;
	private Medal silverMedal;
	private Medal bronzeMedal;
	
	public Event(String name, int attemptsAllowed, boolean biggerBetter) {
		
		this.name = name;
		this.attemptsAllowed = attemptsAllowed;
		this.biggerBetter = biggerBetter;
		
		goldMedal = new Medal("Gold", null, this);
		silverMedal = new Medal("Silver", null, this);
		bronzeMedal = new Medal("Bronze", null, this);
		
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
		
		for(int i = 0; i < results.size(); i++) {
			
			if(results.get(i).getAchievee() == participant) {
				
				results.remove(results.get(i));
				
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
		
		if(results.size() > 0) {
			Participant goldMedalWinner = results.get(0).getAchievee();
			Team goldMedalWinnersTeam = goldMedalWinner.getTeam();
				
				if(goldMedal.getOwner() == null) {
					
					goldMedalWinnersTeam.assignGoldMedal(goldMedal);
					
				} else {
					
					System.out.println("Trying to remove medal from " + goldMedal.getOwner());
					goldMedal.getOwner().removeGoldMedal(goldMedal);
					goldMedalWinnersTeam.assignGoldMedal(goldMedal);
					goldMedal.setOwner(goldMedalWinnersTeam);
					
				}
				
				System.out.println("Assigning gold medal to " + goldMedalWinnersTeam.getName());
		}
		
		if(results.size() > 1) {
			Participant silverMedalWinner = results.get(1).getAchievee();
			Team silverMedalWinnersTeam = silverMedalWinner.getTeam();
			
			if(silverMedal.getOwner() == null) {
				
				silverMedalWinnersTeam.assignSilverMedal(silverMedal);
				
			} else {
				
				System.out.println("Trying to remove medal from " + silverMedal.getOwner());
				silverMedal.getOwner().removeSilverMedal(silverMedal);
				silverMedalWinnersTeam.assignSilverMedal(silverMedal);
				silverMedal.setOwner(silverMedalWinnersTeam);
				
			}
			
			System.out.println("Assigning silver medal to " + silverMedalWinnersTeam.getName());
		}
		
		if(results.size() > 2) {
			Participant bronzeMedalWinner = results.get(2).getAchievee();
			Team bronzeMedalWinnersTeam = bronzeMedalWinner.getTeam();
			
			if(bronzeMedal.getOwner() == null) {
				
				bronzeMedalWinnersTeam.assignBronzeMedal(bronzeMedal);
				
			} else {
				
				System.out.println("Trying to remove medal from " + bronzeMedal.getOwner());
				bronzeMedal.getOwner().removeBronzeMedal(bronzeMedal);
				bronzeMedalWinnersTeam.assignBronzeMedal(bronzeMedal);
				bronzeMedal.setOwner(bronzeMedalWinnersTeam);
				
			}
			
			System.out.println("Assigning bronze medal to " + bronzeMedalWinnersTeam.getName());
		}
		
	}
	
	public void listResults() {
		
		System.out.println(name + ":");
		
		Collections.sort(results);
		
		for(int i = 0; i < results.size(); i++) {
			
			Result result = results.get(i);
			
			System.out.println(result.getResult() + ", " + result.getAchievee().getFullName());
			System.out.println(result.getAchievee().getTeam().getName());
			
		}
		
	}
	
	public String toString() {
		
		return String.format("Name: %s, Attempts allowed: %d, Bigger better: %s", name, attemptsAllowed, biggerBetter);
		
	}
}