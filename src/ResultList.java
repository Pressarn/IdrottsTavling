import java.util.ArrayList;

public class ResultList {

	private ArrayList<Result> results = new ArrayList<>();
	
	private void addResult(double actualResult, Participant achievee, Event achievedIn) {
		
		results.add(new Result(actualResult, achievee, achievedIn));
		
	}
	
}
