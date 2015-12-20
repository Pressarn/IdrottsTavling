import java.util.ArrayList;
import java.util.Collections;

public class IdrottsTävling {

	private ArrayList<Event> events = new ArrayList<>();
	private ArrayList<Team> teams = new ArrayList<>();
	private ArrayList<Participant> participants = new ArrayList<>();
	private ArrayList<Result> results = new ArrayList<>();
	
	private int participantCounter = 100;	
	
	//Refaktorera, bör inte sköta output.
	public void addEvent(String eventName, int attemptsAllowed, boolean biggerBetter) {
			
		if(getEvent(eventName) == null) {
			
			events.add(new Event(eventName, attemptsAllowed, biggerBetter));
			System.out.println(eventName + " added");
			
		} else {
			
			System.out.println("An event with that name already exists");
			
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
		
		Participant participant = new Participant(firstName, lastName, team, participantCounter) ;
		participants.add(participant);
		
		participantCounter++;
		
	}
	
	public Participant getParticipant(int startNumber) {

		for(Participant participant : participants) {
			
			if(participant.getStartNumber() == startNumber) {
				
				return participant;
				
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
		
		participants.remove(participant);
		
		if(participantsTeam.hasNoMembers()) {

			teams.remove(participantsTeam);
		
		}
		
	}
	
	/*
	 * Fundera över den här. Ändrade ju från att lagra hos både participant och event till att bara skapa ett resultat och
	 * sköta associationerna i konstruktorn.
	 * Dock tänkte jag ju inte över att jag bara instantierade ett nytt resultat utan att lagra det någonstans.
	 * Fixade det genom att introducera en resultatarraylist i programmet, men var det verkligen bra? Är det inte bättre att
	 * bara spara resultatet hos participant och event?
	 */
	//Refaktorera, behöver resultat läggas till på två ställen? Samt Result och theActualResult? Fult. Fundera också över om inte if/else ska komma i omvänd ordning.
	public boolean addResult(int startNumber, String eventName, double result) {
		
		Participant achievee = getParticipant(startNumber);
		Event eventAchievedIn = getEvent(eventName);
		
		if(achievee.hasReachedMaximumNumberOfAttempts(eventAchievedIn)) {

			return false;
			
		} else {
			
			results.add(new Result(result, achievee, eventAchievedIn));
			return true;
			
		}
		
	}
	
	public ArrayList<Result> getResultsByParticipant(int startNumber) {
		
		Participant participant = getParticipant(startNumber);
		
		return participant.getResultsSortedByName();
		
	}
	
	public ArrayList<Result> listResultsByEvent(String eventName) {
		
		Event event = getEvent(eventName);
		return event.getSortedResults();
		
	}
	
	public void listResultsByTeam() {
		
		for(Event event: events) {
			
			event.assignMedals();
			
		}
		
		Collections.sort(teams);
		
		for(Team team : teams) {
			
			System.out.println(team.getMedals());
			
		}
		
	}
	
	public void listParticipants() {
		
		for(int i = 0; i < participants.size(); i++) {
				
				System.out.println(participants.get(i));
			
		}
		
	}
	
	public void listEvents() {
		
		for(int i = 0; i < events.size(); i++) {
			
			System.out.println(events.get(i));
		
		}
		
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
