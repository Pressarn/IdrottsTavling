import java.util.Scanner;

public class InputHandler {
	
	private Scanner scanner = new Scanner(System.in);

	public int readInt(String leadText) {

		System.out.print(leadText);
		int number = scanner.nextInt();

		scanner.nextLine();

		return number;

	}
	
	public int readAttemptsAllowed() {
		
		int attemptsAllowed = readInt("Attempts allowed: ");
		
		while(attemptsAllowed <= 0) {
			
			attemptsAllowed = readInt("Invalid number of attempts. Please enter an integer greater than zero: ");
		}
		
		return attemptsAllowed;
		
	}
	
	public int readStartNumber() {
		
		int startNumber = readInt("Participant's start number: ");
		
		return startNumber;
		
	}

	public double readDouble(String leadText) {

		System.out.print(leadText);
		double number = scanner.nextDouble();

		scanner.nextLine();

		return number;

	}

	public String readString(String leadText) {

		System.out.print(leadText);
		return scanner.nextLine();

	}
	
	public String readName(String leadText) {
		
		String name = readString(leadText);
		
		while(name.trim().length() == 0) {
			
			System.out.println("Names can't be empty!");
			name = readString(leadText);
			
		}
		
		name = normalizeString(name);
		
		return name;
		
	}
	
	public String readEventName() {
		
		String eventName = readName("Event name: ");
		eventName = normalizeString(eventName);
		
		return eventName;
		
	}
	
	/*
	 * Frågan är om det där variabelnamnet verkligen är så bra...
	 * Samt, ska inte try-statementet bara omfatta substring-raden? Det är ju den som throwar
	 * exception om man inte skriver in någonting.
	 * 
	 */
	public String normalizeString(String stringToBeNormalized) {
		
		stringToBeNormalized = stringToBeNormalized.trim();
		stringToBeNormalized = stringToBeNormalized.toLowerCase();
		
		try {
			
			String upperCaseFirstLetter = stringToBeNormalized.substring(0, 1).toUpperCase();
			String stringToBeNormalizedWithoutFirstLetter = stringToBeNormalized.substring(1);
			
			String normalizedString = upperCaseFirstLetter + stringToBeNormalizedWithoutFirstLetter;
			
			return normalizedString;
			
		} catch(IndexOutOfBoundsException e) {
			
			return null;
			
		}
		
	}
	
	public boolean readBiggerBetter() {
		
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
	
}
