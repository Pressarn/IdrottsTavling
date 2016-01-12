import java.util.ArrayList;
import java.util.Collections;

public class IdrottsTavling {

	private ArrayList<Event> events = new ArrayList<>();
	private ArrayList<Team> teams = new ArrayList<>();
	
	private int participantCounter = 100;	
	
	public int getParticipantCounter() {
		
		return participantCounter;
		
	}
	
	public void addEvent(String eventName, int attemptsAllowed, boolean biggerBetter) {
			
		events.add(new Event(eventName, attemptsAllowed, biggerBetter));
		
	}
	
	public Event getEvent(String eventName) {
		
		for(Event event : events) {
			
			if(event.getName().equals(eventName)) {
				
				return event;
				
			}
			
		}
		return null;
		
	}
	
	public Team getTeam(String teamName) {
	
		for(Team team : teams) {
			
			if(team.getName().equals(teamName)) {
				
				return team;
				
			}
			
		}
		return null;
		
	}
	
	private void createTeam(String teamName) {
		
		teams.add(new Team(teamName));
		
	}
	
	public void addParticipant(String firstName, String lastName, String teamName) {
		
		Team team = getTeam(teamName);
		
		if(team == null) {
			createTeam(teamName);
			team = getTeam(teamName);
		}
		
		team.addMember(new Participant(firstName, lastName, team, participantCounter));
		
		participantCounter++;
		
	}
	
	public Participant getParticipant(int startNumber) {

		for(Team team : teams) {
			
			for(Participant member : team.getMembers()) {
			
				if(member.getStartNumber() == startNumber) {
				
					return member;
				
				}
				
			}
			
		}
		return null;
		
	}
	
	public void removeParticipant(Participant participant) {
		
		for(Event e : events) {
				
			e.removeParticipantsResults(participant);
				
		}
		
		Team participantsTeam = participant.getTeam();
		participantsTeam.removeMember(participant);
		
		if(participantsTeam.hasNoMembers()) {

			teams.remove(participantsTeam);
		
		}
		
	}
	
	public boolean checkNumberOfAttempts(Participant achievee, Event eventAchievedIn) {
		
		return achievee.hasReachedMaximumNumberOfAttempts(eventAchievedIn) ? false : true;
		
	}
	
	public void addResult(Participant achievee, Event eventAchievedIn, double result) {
			
		achievee.addResult(result, eventAchievedIn);
		
	}
	
	public ArrayList<Result> getResultsByParticipant(Participant participant) {
		
		return participant.getResultsSortedByName();
		
		
	}
	
	public ArrayList<Result> getResultsByEvent(String eventName) {
		
		Event event = getEvent(eventName);
		return event.getSortedResults();
		
	}
	
	public ArrayList<Result>getResultsByParticipantAndEvent(Participant achievee, Event eventAchievedIn) {
		
		ArrayList<Result> resultsForEvent = new ArrayList<>();
		
		ArrayList<Result> allResultsForParticipant = getResultsByParticipant(achievee);
		
		for(Result result : allResultsForParticipant) {
			
			if(result.getNameOfEventAchievedIn().equals(eventAchievedIn.getName())) {
				
				resultsForEvent.add(result);
				
			}
			
		}
		return resultsForEvent;
		
	}
	
	private void assignMedals() {
		
		for(Event event: events) {
			
			event.assignMedals();
			
		}
		
	}
	
	private void countMedals() {
		
		for(Team team : teams) {
			
			team.calculateMedals();
			
		}
		
	}
	
	public ArrayList<Team> getResultsSortedByTeam() {
		
		assignMedals();
		countMedals();
		
		Collections.sort(teams);
		
		return teams;
		
	}
	
	public ArrayList<Event> getEvents() {
		
		return events;
		
	}
	
	public ArrayList<Team> getTeams() {
		
		return teams;
		
	}
	
	public ArrayList<Participant> getMembersOfTeam(String teamName) {
		
		Team team = getTeam(teamName);

		return team.getMembers();
		
	}
	
	public void reinitialize() {
		
		teams.clear();
		events.clear();
		participantCounter = 100;
		
	}
	
}
