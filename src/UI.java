import java.util.ArrayList;

public class UI {
	
	private static final int MESSAGE_BOX_WIDTH = 60;
	
	private InputHandler inputHandler = new InputHandler();
	private IdrottsTävling idrottsTävling = new IdrottsTävling();

	private void printMenu() {
		
		String[] commands = {"add event", 
							 "add participant", 
							 "remove participant",
							 "add result",
							 "participant",
							 "teams",
							 "TEAMNAME",
							 "message MESSAGE",
							 "print menu"};
		
		System.out.println("Menu:");
		for(String command : commands) {
			
			System.out.println("* " + command);
			
		}
		
	}
	
	/*
	 * Refaktorera, man ska inte behöva skriva in alla värden innan man får reda på om eventet redan finns.
	 * 
	 */

	private void addEvent() {
		
		String eventName = inputHandler.readEventName();
		
		if(idrottsTävling.getEvent(eventName) == null) {
			
			int attemptsAllowed = inputHandler.readAttemptsAllowed();
			boolean biggerBetter = inputHandler.readBiggerBetter();

			idrottsTävling.addEvent(eventName, attemptsAllowed, biggerBetter);

			System.out.println(eventName + " added");
			
		} else {
			
			System.out.println(eventName + " has already been added");
			
		}
		
	}
	
	/*
	 * Fundera över om normalizeEvent verkligen ska ligga här, den gör att allt som kommer 
	 * hit matchar det som finns lagrat vilket iofs är bra, men då kanske ett anrop till normalizeEvent
	 * inte behövs i readEventName()?
	 * 
	 */
	private Event getEvent(String eventName) {
		
		eventName = inputHandler.normalizeString(eventName);
		
		return idrottsTävling.getEvent(eventName);
		
	}
	
	private void addParticipant() {
		
		String firstName = inputHandler.readName("First name: ");
		String lastName = inputHandler.readName("Last Name: ");
		String teamName = inputHandler.readName("Team name: ");
		
		int startNumber = idrottsTävling.getParticipantCounter();
		
		idrottsTävling.addParticipant(firstName, lastName, teamName);
		
		System.out.printf("%s %s from %s with number %d added\n",
							firstName,
							lastName,
							teamName,
							startNumber);
		
	}
	
	private void removeParticipant() {
			
		int startNumber = inputHandler.readStartNumber();
		
		try {
			
			idrottsTävling.removeParticipant(startNumber);
			
		} catch(NullPointerException e) {
			
			System.out.println("Couldn't find a participant with that startnumber");
			
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
		
		int achieveeStartNumber = inputHandler.readStartNumber();
		String eventAchievedIn = inputHandler.readEventName();
		double theActualResult = inputHandler.readDouble("Result: ");
		
		while(theActualResult < 0) {
			
			System.out.println("Invalid result. Please enter a value greater than or equal to 0.");
			theActualResult = inputHandler.readDouble("Result: ");
		}

		
		if(idrottsTävling.addResult(achieveeStartNumber, eventAchievedIn, theActualResult)) {
			
			System.out.println("Result added.");
			
		} else {
			
			System.out.println(
					"The participant has already reached the maximum number of attempts allowed for that event.");
			
		}
		
	}
	
	private void listResultsByParticipant() {
		
		int startNumber = inputHandler.readStartNumber();
		
		try {
			ArrayList<Result> participantsResults = idrottsTävling.getResultsByParticipant(startNumber);
			
			if(participantsResults.size() == 0) {
				
				System.out.println("That participant has no results");
				
			} else {
			
				for(Result result : participantsResults) {
				
					System.out.println(result);
					
				}
				
			}
			
		} catch(NullPointerException e) {
			
			System.out.println("There are no registered participants.");;
			
		}
		
	}
	
	private void listResultsByEvent(String eventName) {
		
		eventName = inputHandler.normalizeString(eventName);
		ArrayList<Result> eventsResults = idrottsTävling.getResultsByEvent(eventName);
		
		System.out.println(eventName + ":");
		
		for(Result result : eventsResults) {
			
			System.out.println(result.getResult() + ", " + result.getAchievee().getFullName());
			System.out.println(result.getAchievee().getTeam().getName());
			
		}
		
	}
	
	//Refaktorera
	private void listResultsByTeam() {
		
		try {
			
			ArrayList<Team> teams = idrottsTävling.getResultsSortedByTeam();
			
			if(teams.size() == 0) {
				
				System.out.println("There are no registered teams.");
				
			} else {
			
				for(Team team : teams) {

					System.out.println(team.getMedals());

				}
				
			}
			
		} catch(IndexOutOfBoundsException e) {
			
			System.out.println("There are no teams with registered results.");
			
		}
		
	}
	
	private void printMessage(String message) {
		
		int numberOfRows = 3;
		
		message = message.replaceFirst("message", "");
		message = message.trim();
		message = message.toUpperCase();
		
		if(message.length() > 56) {
				
			message = message.substring(0, 55) + "\n" + message.substring(message.length() - (message.length() - 54), message.length() - 1);
			numberOfRows++;
			
		}
		
		int rowForDisplayingMessage = 2;
		
		for(int row = 1; row <= numberOfRows; row++) {
			
			for(int column = 1; column <= MESSAGE_BOX_WIDTH; column++) {
				
				if(column == 1 || column == MESSAGE_BOX_WIDTH) {
					
					System.out.print(" ");
					
				} else if((row != rowForDisplayingMessage)) {
					
					System.out.print("*");
					
				} else if(row == rowForDisplayingMessage) {
					
					System.out.print(message);
					break;
					
				}
				else {
					
					System.out.print(" ");
					
				}
					
				
			}
			System.out.println();
			
		}
		
	}
	
	private void listEvents() {
		
		ArrayList<Event> events = idrottsTävling.getEvents();
		
		for(Event event : events) {
			
			System.out.println(event);
			
		}
		
	}
	
	private void listTeams() {
		
		ArrayList<Team> teams = idrottsTävling.getTeams();
		
		for(Team team : teams) {
			
			System.out.println(team);
			
		}
		
	}

	private void listMembersOfTeam() {
		
		String teamName = inputHandler.readString("Team name: ");
		ArrayList<Participant> teamsMembers = idrottsTävling.getMembersOfTeam(teamName);
		
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
				idrottsTävling.reinitialize();
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
