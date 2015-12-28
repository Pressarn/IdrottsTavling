import java.util.ArrayList;
import java.util.HashMap;

public class Team implements Comparable<Team> {

  private String name;
  
  private ArrayList<Participant> members = new ArrayList<>();
  
  private int numberOfGoldMedals;
  private int numberOfSilverMedals;
  private int numberOfBronzeMedals;
  
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
  
  //Refaktorera
  public void calculateMedals() {
	  
	  numberOfGoldMedals = 0;
	  numberOfSilverMedals = 0;
	  numberOfBronzeMedals = 0;
	  
	  for(Participant member : members) {
		  
		  try {
			  
			  HashMap<String, Integer> membersMedals = member.getNumberOfMedals();
			  
			  numberOfGoldMedals += membersMedals.get("Gold medals"); 
			  numberOfSilverMedals += membersMedals.get("Silver medals");
			  numberOfBronzeMedals += membersMedals.get("Bronze medals");
			  
		  } catch(NullPointerException e) {
			  
			  continue;
			  
		  }
		  
	  }
	  
  }
  
  public String getMedals() {
	  
	  return String.format(
			  "%s\nGold medals: %s, Silver medals: %s, Bronze medals: %s\n",
			  name, 
			  numberOfGoldMedals, 
			  numberOfSilverMedals, 
			  numberOfBronzeMedals
			  );
	  
  }
  
  //Refaktorera, dela upp i flera delmetoder
  public int compareTo(Team anotherTeam) {
	  
	  if(this.numberOfGoldMedals > anotherTeam.numberOfGoldMedals) {

		  return -1;

	  } else if (this.numberOfGoldMedals == anotherTeam.numberOfGoldMedals) {

		  if(this.numberOfSilverMedals > anotherTeam.numberOfSilverMedals) {

			  return -1;

		  } else if(this.numberOfSilverMedals == anotherTeam.numberOfSilverMedals) {

			  if(this.numberOfBronzeMedals > anotherTeam.numberOfBronzeMedals) {

				  return -1;

			  } else if(this.numberOfBronzeMedals == anotherTeam.numberOfBronzeMedals) {

				  return this.name.compareTo(anotherTeam.name);

			  }

		  }

	  } 
	  
	  return 1;
  }
  
  public String toString() {
	  
	  return String.format("%s", name);
	  
  }

}