import java.util.Scanner;

public class InputHandler {
	
	private Scanner scanner = new Scanner(System.in);

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
	
	
}
