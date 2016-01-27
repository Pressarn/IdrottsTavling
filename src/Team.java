import java.util.ArrayList;

/**
 * 
 * Simon Olofsson - siol0547
 * 
 */

public class Team implements Comparable<Team> {

  private String name;
  
  private ArrayList<Participant> members = new ArrayList<>();
  private MedalCounter medalCounter = new MedalCounter();
  
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
  
  public Integer getGoldMedals() {
	  
	  return medalCounter.getGoldMedals();
	  
  }
  
  public Integer getSilverMedals() {
	  
	  return medalCounter.getSilverMedals();
	  
  }
  
  public Integer getBronzeMedals() {
	  
	  return medalCounter.getBronzeMedals();
	  
  }
  
  public void calculateMedals() {
	  
		medalCounter.resetMedals();

		for(Participant member : members) {

			member.calculateMedals();

			medalCounter.incrementMedals(member.getGoldMedals(), 
										 member.getSilverMedals(), 
										 member.getBronzeMedals());

		}
	  
  }
  
  public int compareTo(Team anotherTeam) {
	  
	  if(medalCounter.getGoldMedals() > anotherTeam.medalCounter.getGoldMedals()) {

		  return -1;

	  } else if (medalCounter.getGoldMedals() == anotherTeam.medalCounter.getGoldMedals()) {

		  if(medalCounter.getSilverMedals() > anotherTeam.medalCounter.getSilverMedals()) {

			  return -1;

		  } else if(medalCounter.getSilverMedals() == anotherTeam.medalCounter.getSilverMedals()) {

			  if(medalCounter.getBronzeMedals() > anotherTeam.medalCounter.getBronzeMedals()) {

				  return -1;

			  } else if(medalCounter.getBronzeMedals() == anotherTeam.medalCounter.getBronzeMedals()) {

				  return this.name.compareTo(anotherTeam.name);

			  }

		  }

	  } 
	  
	  return 1;
  }
  
  public String debugString() {
	  
	  return String.format("%s\nGold medals: %s, Silver medals: %s, Bronze medals: %s\n",
			  name, 
			  medalCounter.getGoldMedals(), 
			  medalCounter.getSilverMedals(), 
			  medalCounter.getBronzeMedals()
			  );
	  
  }
  
  public String toString() {
	  
	 return String.format("%s", name);
	 
	  
  }

}