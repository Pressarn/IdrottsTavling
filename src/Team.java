import java.util.ArrayList;

public class Team extends Medalier implements Comparable<Team> {

  private String name;
  
  private ArrayList<Participant> members = new ArrayList<>();
  
  public Team(String name) {
	  
	  this.name = name;
	  
  }
  
  public String getName() {
	  
	  return name;
	  
  }
  
  public void addMember(Participant member) {
	  
	  members.add(member);
	  
  }
  
  public void removeMember(Participant member) {
	  
	  members.remove(member);
	  
  }
  
  public void removeAllMembers() {
	  
	  members.removeAll(members);
	  
  }
  
  public ArrayList<Participant> getMembers() {
	  
	  return members;
	  
  }
  
  public boolean hasNoMembers() {
	  
	  return members.isEmpty();
	  
  }
  
  public void calculateMedals() {
	  
		resetMedals();

		for(Participant member : members) {

			member.calculateMedals();

			incrementMedals(member.getGoldMedals(), 
							member.getSilverMedals(), 
							member.getBronzeMedals());

		}
	  
  }
  
  //Refaktorera, dela upp i flera delmetoder
  public int compareTo(Team anotherTeam) {
	  
	  if(getGoldMedals() > anotherTeam.getGoldMedals()) {

		  return -1;

	  } else if (getGoldMedals() == anotherTeam.getGoldMedals()) {

		  if(getSilverMedals() > anotherTeam.getSilverMedals()) {

			  return -1;

		  } else if(getSilverMedals() == anotherTeam.getSilverMedals()) {

			  if(getBronzeMedals() > anotherTeam.getBronzeMedals()) {

				  return -1;

			  } else if(getBronzeMedals() == anotherTeam.getBronzeMedals()) {

				  return this.name.compareTo(anotherTeam.name);

			  }

		  }

	  } 
	  
	  return 1;
  }
  
  public String debugString() {
	  
	  return String.format("%s\nGold medals: %s, Silver medals: %s, Bronze medals: %s\n",
			  name, 
			  getGoldMedals(), 
			  getSilverMedals(), 
			  getBronzeMedals()
			  );
	  
  }
  
  public String toString() {
	  
	 return String.format("%s", name);
	 
	  
  }

}