import java.text.DecimalFormat;

public class Result implements Comparable<Result>{

	private double result;

	private Participant achievee;
	private Event achievedIn;
	private Medal medal;

	public Result(double result, Participant achievee, Event achievedIn) {
		
		this.result = result;
		this.achievee = achievee;
		this.achievedIn = achievedIn;
		
		achievedIn.addResult(this);
		
	}
	
	public Event getAchievedIn() {
		
		return achievedIn;
		
	}
	
	public Participant getAchievee() {
		
		return achievee;
		
	}
	
	public double getResult() {
		
		return result;
		
	}
	
	public void setMedal(Medal medal) {
		
		this.medal = medal;
		//System.out.println(result + " resulted in " + this.medal + " in " + achievedIn + "\n" + achievee.getName());
	}
	
	public Medal getMedal() {
		
		return medal;
		
	}
	
	public String toString() {
		
		//DecimalFormat df = new DecimalFormat("0.0");
		
		return String.format("Event:\n%s\nResult: %s", achievedIn.getName(), result);
		
	}
	
	public int compareTo(Result anotherResult) {
		
		if(achievedIn.getBiggerBetter()){
			
			if(this.result > anotherResult.result) {

				return -1;

			} else if (this.result < anotherResult.result){

				return 1;

			}
		} else {
			
			if(this.result > anotherResult.result) {

				return 1;

			} else if (this.result < anotherResult.result){

				return -1;

			}
			
		}
		return 0;
		
	}

}