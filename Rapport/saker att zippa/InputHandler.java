import java.util.Scanner;

public class InputHandler {
	
	private int lastInt;
	private String lastString;
	
	private Scanner scanner = new Scanner(System.in);

	//Refaktorera, allting bör egentligen normaliseras, typ. Iaf trimmas.
	
	public int getLastInt() {
		
		return lastInt;
		
	}
	
	public String getLastString() {
		
		return lastString;
		
	}
	
	public int readInt(String leadText) {

		System.out.print(leadText);
		int number = scanner.nextInt();
		lastInt = number;

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
	
	//Refaktorera, bör man inte ha en allmän loopfunktion som kan återanvändas?
	public double readResult() {
		
		double result = readDouble("Result: ");
		
		while(result < 0) {
			
			System.out.println("Invalid result. Please enter a value greater than or equal to 0.");
			result = readDouble("Result: ");
			
		}
		
		return result;
		
	}

	public String readString(String leadText) {

		System.out.print(leadText);
		
		String text = scanner.nextLine();
		lastString = text;
		
		return text;

	}
	
	public String readName(String leadText) {
		
		String name = readString(leadText);
		
		while(name.trim().length() == 0) {
			
			System.out.println("Names can't be empty!");
			name = readString(leadText);
			
		}
		
		name = normalizeString(name);
		
		lastString = name;
		
		return name;
		
	}
	
	//Refaktorera
	public String readEventName() {
		
		String eventName = readName("Event name: ");
		
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
