
public class Medal {

	private String type;
	
	private Team owner;
	private Event earnedIn;
	
	public Medal(String type, Team owner, Event earnedIn) {
		
		this.type = type;
		this.owner = owner;
		this.earnedIn = earnedIn;
		
	}
	
	public String getType() {
		
		return type;
		
	}
	
	public void setOwner(Team owner) {
		
		this.owner = owner;
		
	}
	
	public Team getOwner() {
		
		return owner;
		
	}
	
	public Event getEarnedIn() {
		
		return earnedIn;
		
	}
	
}
