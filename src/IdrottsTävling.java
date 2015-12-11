import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class IdrottsTävling {
	
	private static final int MESSAGE_BOX_WIDTH = 60;
	
	private ArrayList<Event> events = new ArrayList<>();
	private ArrayList<Team> teams = new ArrayList<>();
	private ArrayList<Participant> participants = new ArrayList<>();
	private ArrayList<Result> results = new ArrayList<>();
	
	Scanner scanner = new Scanner(System.in);
	
	int participantCounter = 100;
	
	private int readInt(String leadText) {

		System.out.print(leadText);
		int number = scanner.nextInt();

		scanner.nextLine();

		return number;

	}
	
	private int readAttemptsAllowed() {
		
		int attemptsAllowed = readInt("Attempts allowed: ");
		
		while(attemptsAllowed <= 0) {
			
			attemptsAllowed = readInt("Invalid number of attempts. Please enter an integer greater than zero: ");
		}
		
		return attemptsAllowed;
		
	}

	private double readDouble(String leadText) {

		System.out.print(leadText);
		double number = scanner.nextDouble();

		scanner.nextLine();

		return number;

	}

	private String readString(String leadText) {

		System.out.print(leadText);
		return scanner.nextLine();

	}
	
	//Frågan är om det där variabelnamnet verkligen är så bra...
	private String normalizeString(String stringToBeNormalized) {
		
		stringToBeNormalized = stringToBeNormalized.trim();
		stringToBeNormalized = stringToBeNormalized.toLowerCase();
		
		String upperCaseFirstLetter = stringToBeNormalized.substring(0, 1).toUpperCase();
		String stringToBeNormalizedWithoutFirstLetter = stringToBeNormalized.substring(1);
		
		String normalizedString = upperCaseFirstLetter + stringToBeNormalizedWithoutFirstLetter;
		
		return normalizedString;
	}
	
	private String readName(String leadText) {
		
		String name = readString(leadText);
		
		while(name.trim().length() == 0) {
			
			System.out.println("Names can't be empty!");
			name = readString(leadText);
			
		}
		
		name = normalizeString(name);
		
		return name;
		
	}
	
	private String readEventName() {
		
		String eventName = readName("Event name: ");
		
		return eventName;
		
	}
	
	
	private Event readEvent() {
		
		String eventName = readString("Name of the event: ");
		Event event = getEvent(eventName);
		
		return event;
		
	}
	
	private boolean readBiggerBetter() {
		
		String biggerBetter = readString("Bigger better? (Y/N): ").toLowerCase();
				
		while(true) {
			switch(biggerBetter) {

			case "y":
			case "yes":
				return true;
			case "n":
			case "no":
				return false;
			default:
				biggerBetter = readString("Valid answers are either \"y\" or \"n\". Bigger better?: ").toLowerCase();
				
			}
		}
	}
	
	
	/*
	 * Den här borde throwa exception om den inte hittar en Participant.
	 * Skulle göra det möjligt att ta bort massa fula if(participant==null){return;} statements i kod längre ner 
	 * 
	 */
	private Participant readStartNumber() {
		
		int startNumber = readInt("Participant's start number: ");
		
		Participant participant = getParticipant(startNumber);
		
		return participant;
		
	}
	
	
	private void initialize() {
		
		/*addParticipant("John", "Lennon", "Brynäs");
		addParticipant("Ringo", "Starr", "Luleå");
		addParticipant("Paul", "McCartney", "Brynäs");
		
		Event e1 = new Event("Höjdhopp", 3, true);
		Event e2 = new Event("Kulstötning", 3, false);
		Event e3 = new Event("Spjut", 3, true);
		events.add(e1);
		events.add(e2);
		events.add(e3);
		
		addResult(100, "Höjdhopp", 2.0);
		addResult(100, "Höjdhopp", 1.5);
		addResult(100, "Höjdhopp", 1.8);
		addResult(101, "Höjdhopp", 1.0);
		addResult(102, "Höjdhopp", 0.5);
		addResult(100, "Kulstötning", 10.4);
		addResult(101, "Kulstötning", 10.1);
		addResult(100, "Spjut", 6.0);
		addResult(101, "Spjut", 7.0);
		
		e1.assignMedals();
		e2.assignMedals();
		e3.assignMedals();*/
		
//		addParticipant("Ada", "Lovelace", "Computing");
//		events.add(new Event("Long jump", 3, true));
//		addResult(100, "Long jump", 6.7);
		
	}
	
	
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
	
	
	private void addEvent() {
		
		String eventName = readEventName();
		
		if(getEvent(eventName) == null) {
			
			int attemptsAllowed = readAttemptsAllowed();
			boolean biggerBetter = readBiggerBetter();
			
			events.add(new Event(eventName, attemptsAllowed, biggerBetter));
			System.out.println(eventName + " added");
			
		} else {
			
			System.out.println("An event with that name already exists.");
			
		}
		
	}
	
	
	/*
	 * Fundera över om normalizeEvent verkligen ska ligga här, den gör att allt som kommer 
	 * hit matchar det som finns lagrat vilket iofs är bra, men då kanske ett anrop till normalizeEvent
	 * inte behövs i readEventName()?
	 * 
	 */
	private Event getEvent(String eventName) {
		
		eventName = normalizeString(eventName);
		
		for(Event event : events) {
			
			if(event.getName().equals(eventName)) {
				
				return event;
				
			}
			
		}
		return null;
		
	}
	
	
	private Team getTeam(String teamName) {
		
		for(int i = 0; i < teams.size(); i++) {
			
			if(teams.get(i).getName().equals(teamName)) {
				
				return teams.get(i);
				
			}
			
		}
		return null;
		
	}
	
	
	private void createTeam(String teamName) {
		
		teams.add(new Team(teamName));
		
	}
	
	private void addParticipant() {
		
		String firstName = readName("First name: ");
		String lastName = readName("Last Name: ");
		String teamName = readName("Team name: ");
		
		Team team = getTeam(teamName);
		
		if(team == null) {
			createTeam(teamName);
			team = getTeam(teamName);
		}
		
		Participant participant = new Participant(firstName, lastName, team, participantCounter) ;
		participants.add(participant);
		
		participantCounter++;
		
		System.out.println("Participant added");
		
	}
	
	
	private void addParticipant(String firstName, String lastName, String teamName) {
		
		Team team = getTeam(teamName);
		
		if(team == null) {
			createTeam(teamName);
			team = getTeam(teamName);
		}
		
		Participant participant = new Participant(firstName, lastName, team, participantCounter) ;
		participants.add(participant);
		team.addMember(participant);
		
		participantCounter++;
		
		System.out.println("Participant added");
		
	}
	
	
	private Participant getParticipant(int startNumber) {

		for(int i = 0; i < participants.size(); i++) {
			
			if(participants.get(i).getStartNumber() == startNumber) {
				
				return participants.get(i);
				
			}
				
		}
		return null;
		
	}
	
	
	private void removeParticipant() {
			
		Participant participant = readStartNumber();
		
		if(participant == null) {
			
			System.out.println("Couldn't find a participant with that start number.");
			return;
			
		}
		
		for(Event e : events) {
			
			e.removeParticipantsResults(participant);
			
		}
		
		Team participantsTeam = participant.getTeam();
		participantsTeam.removeMember(participant);
		
		participants.remove(participant);
		
		System.out.println("Participant removed.");
		
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
	//Refaktorera, behöver resultat läggas till på två ställen? Samt Result och theActualResult? Fult.
	private void addResult() {
		
		Participant achievee = readStartNumber();
		
		if(achievee == null) {
			
			System.out.println("Couldn't find a participant with that start number.");
			return;
			
		}
		
		Event eventAchievedIn = readEvent();
		
		if(eventAchievedIn == null) {
			
			System.out.println("Couldn't find an event with that name");
			return;
			
		}
		
		if(achievee.hasReachedMaximumNumberOfAttempts(eventAchievedIn)) {
			
			System.out.println(
					"The participant has already reached the maximum number of attempts allowed for that event.");
			return;
			
		}
		
		double theActualResult = readDouble("Result: ");
		
		while(theActualResult < 0) {
			
			System.out.println("Invalid result. Please enter a value greater than or equal to 0.");
			theActualResult = readDouble("Result: ");
		}
		
		results.add(new Result(theActualResult, achievee, eventAchievedIn));
		
		System.out.println("Result added.");
		
	}
	
	
	private void addResult(int startNumber, String eventName, double theActualResult) {
		
		Participant achievee = getParticipant(startNumber);
		Event eventAchievedIn = getEvent(eventName);
		
		Result result = new Result(theActualResult, achievee, eventAchievedIn);
		achievee.addResult(result);
		eventAchievedIn.addResult(result);
		System.out.println("Result added.");
		
	}
	
	
	private void listResultsByParticipant() {
		
		Participant participant = readStartNumber();
		
		if(participant == null) {
			
			System.out.println("Couldn't find a participant with that start number.");
			return;
			
		}
		
		participant.listResultsSortedByName();
		
	}
	
	
	private void listResultsByEvent(String eventName) {
		
		Event event = getEvent(eventName);
		event.listResults();
		
	}
	
	
	private void listResultsByTeam() {
		
		for(Event event: events) {
			
			event.assignMedals();
			
		}
		
		Collections.sort(teams);
		
		for(Team team : teams) {
			
			System.out.println(team.getMedals());
			
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
	
	
	private void listParticipants() {
		
		for(int i = 0; i < participants.size(); i++) {
				
				System.out.println(participants.get(i));
			
		}
		
	}
	
	
	private void listEvents() {
		
		for(int i = 0; i < events.size(); i++) {
			
			System.out.println(events.get(i));
		
		}
		
	}
	
	
	private void listTeams() {
		
		for(int i = 0; i < teams.size(); i++) {
			
			System.out.println(teams.get(i));
			
		}
		
	}
	
	
	private void listMembersOfTeam() {
		
		String teamName = readString("Team name: ");
		Team team = getTeam(teamName);

		team.listMembers();
		
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
			case "print menu":
				printMenu();
				break;
			case "list participants":
				listParticipants();
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
			
			String command = readString("What would you like to do?\n> ");
			handleCommand(command); //den här bör returnera boolean för att avsluta programmet
			
		}
		
	}
	
	public static void main(String[] args) {
		
		IdrottsTävling idrottsTävling = new IdrottsTävling();
		
		idrottsTävling.initialize();
		idrottsTävling.run();

	}

}
