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
		
	}
	
	public Medal getMedal() {
		
		return medal;
		
	}
	
	public String toString() {
		
		return String.format("Event:\n%s\nResult: %f", achievedIn, result);
		
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