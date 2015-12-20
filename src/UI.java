import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class UI {
	
	private static final int MESSAGE_BOX_WIDTH = 60;
	
	Scanner scanner = new Scanner(System.in);
	IdrottsTävling idrottsTävling = new IdrottsTävling();
	
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
		eventName = normalizeString(eventName);
		
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
	private int readStartNumber() {
		
		int startNumber = readInt("Participant's start number: ");
		
		return startNumber;
		
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
	
	/*
	 * Refaktorera, man ska inte behöva skriva in alla värden innan man får reda på om eventet redan finns.
	 * idrottsTävling.addEvent() bör heller inte sköta output.
	 * 
	 */
	
	private void addEvent() {
		
		String eventName = readEventName();		
		int attemptsAllowed = readAttemptsAllowed();
		boolean biggerBetter = readBiggerBetter();
		
		idrottsTävling.addEvent(eventName, attemptsAllowed, biggerBetter);
		
	}
	
	/*
	 * Fundera över om normalizeEvent verkligen ska ligga här, den gör att allt som kommer 
	 * hit matchar det som finns lagrat vilket iofs är bra, men då kanske ett anrop till normalizeEvent
	 * inte behövs i readEventName()?
	 * 
	 */
	private Event getEvent(String eventName) {
		
		eventName = normalizeString(eventName);
		
		return idrottsTävling.getEvent(eventName);
		
	}
	
	
	private Team getTeam(String teamName) {
		
		return idrottsTävling.getTeam(teamName);
		
	}
	
	private void addParticipant() {
		
		String firstName = readName("First name: ");
		String lastName = readName("Last Name: ");
		String teamName = readName("Team name: ");
		
		idrottsTävling.addParticipant(firstName, lastName, teamName);
		
		System.out.println("Participant added");
		
	}
	
	
	private void removeParticipant() {
			
		int startNumber = readStartNumber();
		
		try {
			
			idrottsTävling.removeParticipant(startNumber);
			
		} catch(NullPointerException e) {
			
			System.out.println("Couldn't find a participant with that startnumber");
			return;
			
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
		
		int achieveeStartNumber = readStartNumber();
		
		String eventAchievedIn = readName("Event name: ");
		
		double theActualResult = readDouble("Result: ");
		
		while(theActualResult < 0) {
			
			System.out.println("Invalid result. Please enter a value greater than or equal to 0.");
			theActualResult = readDouble("Result: ");
		}
		
		idrottsTävling.addResult(achieveeStartNumber, eventAchievedIn, theActualResult);
		
		System.out.println("Result added.");
		
	}
	
	
	private void listResultsByParticipant() {
		
		int startNumber = readStartNumber();
		
		idrottsTävling.listResultsByParticipant(startNumber);
		
	}
	
	
	private void listResultsByEvent(String eventName) {
		
		eventName = normalizeString(eventName);
		idrottsTävling.listResultsByEvent(eventName);
		
	}
	
	
	private void listResultsByTeam() {
		
		idrottsTävling.listResultsByTeam();
		
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

	
	
	private void listMembersOfTeam() {
		
		String teamName = readString("Team name: ");
		idrottsTävling.listMembersOfTeam(teamName);
		
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
				idrottsTävling.listParticipants();
				break;
			case "list events":
				idrottsTävling.listEvents();
				break;
			case "list teams":
				idrottsTävling.listTeams();
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
		
		UI ui = new UI();
		
		ui.initialize();
		ui.run();

	}

}
