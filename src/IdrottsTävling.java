import java.util.ArrayList;
import java.util.Collections;

public class IdrottsTävling {

	private ArrayList<Event> events = new ArrayList<>();
	private ArrayList<Team> teams = new ArrayList<>();
	//private ArrayList<Participant> participants = new ArrayList<>();
	//private ArrayList<Result> results = new ArrayList<>();
	
	private int participantCounter = 100;	
	
	public boolean addEvent(String eventName, int attemptsAllowed, boolean biggerBetter) {
			
		if(getEvent(eventName) == null) {
			
			events.add(new Event(eventName, attemptsAllowed, biggerBetter));
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	/*
	 * Fundera över om normalizeEvent verkligen ska ligga här, den gör att allt som kommer 
	 * hit matchar det som finns lagrat vilket iofs är bra, men då kanske ett anrop till normalizeEvent
	 * inte behövs i readEventName()?
	 * 
	 */
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
	
	public void removeParticipant(int startNumber) {
			
		Participant participant = getParticipant(startNumber);
		
		for(Event e : events) {
				
			e.removeParticipantsResults(participant);
				
		}
		
		Team participantsTeam = participant.getTeam();
		participantsTeam.removeMember(participant);
		
		if(participantsTeam.hasNoMembers()) {

			teams.remove(participantsTeam);
		
		}
		
	}
	
	/*
	 * Fundera �ver den h�r. �ndrade ju fr�n att lagra hos b�de participant och event till att bara skapa ett resultat och
	 * sk�ta associationerna i konstruktorn.
	 * Dock t�nkte jag ju inte �ver att jag bara instantierade ett nytt resultat utan att lagra det n�gonstans.
	 * Fixade det genom att introducera en resultatarraylist i programmet, men var det verkligen bra? �r det inte b�ttre att
	 * bara spara resultatet hos participant och event?
	 */
	//Refaktorera, beh�ver resultat l�ggas till p� tv� st�llen? Samt Result och theActualResult? Fult. Fundera ocks� �ver om inte if/else ska komma i omv�nd ordning.
	public boolean addResult(int startNumber, String eventName, double result) {
		
		Participant achievee = getParticipant(startNumber);
		Event eventAchievedIn = getEvent(eventName);
		
		if(achievee.hasReachedMaximumNumberOfAttempts(eventAchievedIn)) {

			return false;
			
		} else {
			
			achievee.addResult(new Result(result, achievee, eventAchievedIn));
			return true;
			
		}
		
	}
	
	public ArrayList<Result> getResultsByParticipant(int startNumber) {
		
		Participant participant = getParticipant(startNumber);
		
		return participant.getResultsSortedByName();
		
	}
	
	public ArrayList<Result> getResultsByEvent(String eventName) {
		
		Event event = getEvent(eventName);
		return event.getSortedResults();
		
	}
	
	public ArrayList<Team> getResultsSortedByTeam() {
		
		for(Event event: events) {
			
			event.assignMedals();
			
		}
		
		for(Team team : teams) {
			
			team.calculateMedals();
			
		}
		
		Collections.sort(teams);
		
		return teams;
		
	}
	
	public ArrayList<Event> getEvents() {
		
		return events;
		
	}
	
	public void listTeams() {
		
		for(int i = 0; i < teams.size(); i++) {
			
			System.out.println(teams.get(i));
			
		}
		
	}
	
	public ArrayList<Participant> getMembersOfTeam(String teamName) {
		
		Team team = getTeam(teamName);

		return team.getMembers();
		
	}
	
}
