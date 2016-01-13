import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class UI {
	
	private static final int MESSAGE_BOX_WIDTH = 60;
	
	private InputHandler inputHandler = new InputHandler();
	private IdrottsTavling idrottsTavling = new IdrottsTavling();

	private void printMenu() {
		
		String[] commands = {"add event", 
							 "add participant", 
							 "remove participant",
							 "add result",
							 "participant",
							 "teams",
							 "EVENTNAME",
							 "reinitialize",
							 "message MESSAGE",
							 "print menu"};
		
		System.out.println("Menu:");
		for(String command : commands) {
			
			System.out.println("* " + command);
			
		}
		
	}
	
	private Participant getParticipant() {
		
		int startNumber = inputHandler.readStartNumber();
		
		return idrottsTavling.getParticipant(startNumber);
		
	}
	
	private void addEvent() {
		
		Event event = getEvent();
		String eventName = inputHandler.getLastString();
		
		if(event == null) {
			
			int attemptsAllowed = inputHandler.readAttemptsAllowed();
			boolean biggerBetter = inputHandler.readBiggerBetter();

			idrottsTavling.addEvent(eventName, attemptsAllowed, biggerBetter);

			System.out.println(eventName + " added");
			
		} else {
			
			System.out.println(eventName + " has already been added");
			
		}
		
	}
	
	private Event getEvent() {
		
		String eventName = inputHandler.readEventName();
		return getEvent(eventName);
		
	}
	
	/*
	 * Fundera över om normalizeEvent verkligen ska ligga här, den gör att allt som kommer 
	 * hit matchar det som finns lagrat vilket iofs är bra, men då kanske ett anrop till normalizeEvent
	 * inte behövs i readEventName()?
	 * 
	 */
	private Event getEvent(String eventName) {
		
		eventName = inputHandler.normalizeString(eventName);
		
		return idrottsTavling.getEvent(eventName);
		
	}
	
	private void addParticipant() {
		
		String firstName = inputHandler.readName("First name: ");
		String lastName = inputHandler.readName("Last Name: ");
		String teamName = inputHandler.readName("Team name: ");
		
		int startNumber = idrottsTavling.getParticipantCounter();
		
		idrottsTavling.addParticipant(firstName, lastName, teamName);
		
		System.out.printf("%s %s from %s with number %d added\n",
							firstName,
							lastName,
							teamName,
							startNumber);
		
	}
	
	private void removeParticipant() {
		
		try {
			
			Participant participantToBeRemoved = getParticipant();
			idrottsTavling.removeParticipant(participantToBeRemoved);
			
			System.out.printf("%s from %s with number %d removed\n",
								participantToBeRemoved.getFullName(),
								participantToBeRemoved.getTeamName(),
								participantToBeRemoved.getStartNumber());
			
		} catch(NullPointerException e) {
			
			System.out.println("No participant with number " + inputHandler.getLastInt() + ".");
			
		}
		
	}
	
	/*
	 * Fundera över den här. Ändrade ju från att lagra hos både participant och event till att bara skapa ett resultat och
	 * sköta associationerna i konstruktorn.
	 * Dock tänkte jag ju inte över att jag bara instantierade ett nytt resultat utan att lagra det någonstans.
	 * Fixade det genom att introducera en resultatarraylist i programmet, men var det verkligen bra? Är det inte bättre att
	 * bara spara resultatet hos participant och event?
	 */
	//Refaktorera, behöver resultat läggas till på två ställen? Samt Result och theActualResult? Fult. Frågan är om inte storlekschecken ska ske redan
	// hos participanten
	private void addResult() {
		
		Participant achievee = getParticipant();
		
		if(achievee == null) {
			
			System.out.println("No participant with number " + inputHandler.getLastInt() + ".");
			return;
			
		}
		
		Event eventAchievedIn = getEvent();
		
		if(eventAchievedIn == null) {
			
			System.out.println(inputHandler.getLastString() + " is not a registered event.");
			return;
			
		}

		if(idrottsTavling.checkNumberOfAttempts(achievee, eventAchievedIn)) {

			double theActualResult = inputHandler.readResult();
			idrottsTavling.addResult(achievee, eventAchievedIn, theActualResult);

			listResultsByParticipantAndEvent(achievee, eventAchievedIn);

		} else {

			System.out.println(
					"The participant has already reached the maximum number of attempts allowed for that event.");

		}
		
	}
	
	private void listResultsByParticipantAndEvent(Participant achievee, Event eventAchievedIn) {
		
		try {
			
			ArrayList<Result> results = idrottsTavling.getResultsByParticipantAndEvent(achievee, eventAchievedIn);
			
			System.out.printf("Results for %s from %s in %s: ", 
							  achievee.getFullName(),
							  achievee.getTeamName(),
							  eventAchievedIn.getName());
			
			for(Result result : results) {
				
				System.out.print(result + ", ");
				
			}
				
			System.out.println();
			
		} catch(NullPointerException e) {
				
				System.out.println("No participant with number " + achievee.getStartNumber() + ".");
				
		}
		
	}
	
	private void listResultsByParticipant() {
		
		Participant achievee = getParticipant();
		
		if(achievee == null) {
			
			System.out.println("No participant with number " + inputHandler.getLastInt() + ".");
			return;
			
		}
		
		ArrayList<Event> allEvents = idrottsTavling.getEvents();
		
		if(allEvents.size() == 0) {
			
			System.out.println("There are no events registered.");
			return;
			
		}
		
		for(Event event : allEvents) {
			
			listResultsByParticipantAndEvent(achievee, event);
			
		}
		
	}
	
	private void listResultsByEvent(String eventName) {
		
		eventName = inputHandler.normalizeString(eventName);
		ArrayList<Result> eventsResults = idrottsTavling.getResultsByEvent(eventName);
		
		System.out.println("Results for " + eventName + ":");
		
		for(Result result : eventsResults) {
			
			System.out.println(result.getPlacement() + " " + result.getResult() + ", " + result.getAchievee().getFullName());
			
		}
		
	}
	
	//Refaktorera
	private void listResultsByTeam() {
			
		ArrayList<Team> teams = idrottsTavling.getResultsSortedByTeam();

		if(teams.size() == 0) {

			System.out.println("There are no registered teams.");

		} else {
			
			for(Team team : teams) {
				
				System.out.printf(
						  "%s\nGold medals: %s, Silver medals: %s, Bronze medals: %s\n",
						  team.getName(), 
						  team.getGoldMedals(), 
						  team.getSilverMedals(), 
						  team.getBronzeMedals()
						  );

			}

		}
		
	}
	
	private void printRowOfStars() {
		
		System.out.println();
		
		for(int i = 0; i < MESSAGE_BOX_WIDTH; i++) {
			
			System.out.print("*");
			
		}
		
		System.out.println();
		
	}
	
	//Refaktorera
	private void printMessage(String message) {
		
		message = message.replaceFirst("message", "");
		message = message.trim();
		message = message.toUpperCase();
		
		printRowOfStars();
		
		for(int i = 0; i < message.length(); i++) {
			
			boolean messageSpansSeveralLines = message.length() > 55 ? true : false;
			
			boolean indexIsEvenlyDivisibleBy56 = i % 56 == 0 ? true : false;
			boolean indexIsNotAtEndOfMessage = i < message.length() ? true : false;
			boolean indexIsAtEndOfLine = indexIsEvenlyDivisibleBy56 && indexIsNotAtEndOfMessage;
			
			int numberOfConstantWhiteSpacesAndStars = 4;
			int charactersLeftUntilEndOfBox = (MESSAGE_BOX_WIDTH - i) - numberOfConstantWhiteSpacesAndStars;
			
			if(i == 0) {
				
				System.out.print("* ");
				
			} else if(indexIsAtEndOfLine) {
			
				for(int n = 0; n < charactersLeftUntilEndOfBox; n++) {
					
					System.out.print(" ");
					
				}
				System.out.print(" *\n* ");
			}
			
			System.out.print(message.substring(i, i + 1));
			
			if(i == message.length() - 1) {
				
				if(messageSpansSeveralLines) {
				
					for(int n = 0; n < charactersLeftUntilEndOfBox + 56; n++) {
					
						System.out.print(" ");
					
					}
					
					System.out.print("*");
					
				} else {
					
					for(int n = 0; n < charactersLeftUntilEndOfBox; n++) {
						
						System.out.print(" ");
					
					}
					
					System.out.print("*");
					
				}
				
			}
			
		}
		
		printRowOfStars();
		
	}

	
	private void listEvents() {
		
		ArrayList<Event> events = idrottsTavling.getEvents();
		
		for(Event event : events) {
			
			System.out.println(event);
			
		}
		
	}
	
	private void listTeams() {
		
		ArrayList<Team> teams = idrottsTavling.getTeams();
		
		for(Team team : teams) {
			
			System.out.println(team);
			
		}
		
	}

	private void listMembersOfTeam() {
		
		String teamName = inputHandler.readString("Team name: ");
		ArrayList<Participant> teamsMembers = idrottsTavling.getMembersOfTeam(teamName);
		
		System.out.println("Members of team " + teamName + ":");
		
		for(Participant member : teamsMembers) {
			
			System.out.println(member);
			
		}
		
	}
	
	//Refaktorera, använd array för kommandon plus for loop
	private void handleCommand(String command) {
		
		if(getEvent(command) != null) {
			
			listResultsByEvent(command);
			
		} else if(command.matches("message.+")) {
			
			printMessage(command);
			
		} else {
		
			switch(command.toLowerCase()) {

			case "add event":
				addEvent();
				break;
			case "add participant":
				addParticipant();
				break;
			case "remove participant":
				removeParticipant();
				break;
			case "add result":
				addResult();
				break;
			case "participant":
				listResultsByParticipant();
				break;
			case "teams":
				listResultsByTeam();
				break;
			case "reinitialize":
				idrottsTavling.reinitialize();
				break;
			case "print menu":
				printMenu();
				break;
			case "list events":
				listEvents();
				break;
			case "list teams":
				listTeams();
				break;
			case "list members of team":
				listMembersOfTeam();
				break;
			case "exit":
				exit();
				break;
			default:
				System.out.println("Unknown command.");

			}
			
		}
		
	}
	
	private void exit() {
		
		System.out.println("Terminating");
		System.exit(0);
		
	}
	
	private void run() {
		
		System.out.println("Welcome!");
		
		printMenu();
		
		while(true) {
			
			String command = inputHandler.readString("What would you like to do?\n> ");
			handleCommand(command); //den här bör returnera boolean för att avsluta programmet
			
		}
		
	}
	
	public static void main(String[] args) {
		
		UI ui = new UI();
		
		ui.run();

	}

}
