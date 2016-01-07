import java.util.ArrayList;
import java.util.HashMap;

public class Team implements Comparable<Team> {

  private String name;
  
  private ArrayList<Participant> members = new ArrayList<>();
  //private HashMap<String, Integer> medals = new HashMap<>();
  private MedalCounter medalCounter = new MedalCounter();
  
  public Team(String name) {
	  
	  this.name = name;
	  
//	  medals.put("Gold medals", 0);
//	  medals.put("Silver medals", 0);
//	  medals.put("Bronze medals", 0);
	  
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
	  
	  medalCounter.incrementMedalsBasedOnMembers(members);
	  
  }
  
  public HashMap<String, Integer> getMedals() {
	  
	  return medalCounter.getMedals();
	  
  }
  
  //Refaktorera, dela upp i flera delmetoder
  public int compareTo(Team anotherTeam) {
	  
	  int goldMedals = medalCounter.getGoldMedals();
	  int silverMedals = medalCounter.getSilverMedals();
	  int bronzeMedals = medalCounter.getBronzeMedals();
	  
	  int anotherTeamsGoldMedals = anotherTeam.medalCounter.getGoldMedals();
	  int anotherTeamsSilverMedals = anotherTeam.medalCounter.getSilverMedals();
	  int anotherTeamsBronzeMedals = anotherTeam.medalCounter.getBronzeMedals();
	  
	  if(goldMedals > anotherTeamsGoldMedals) {

		  return -1;

	  } else if (goldMedals == anotherTeamsGoldMedals) {

		  if(silverMedals > anotherTeamsSilverMedals) {

			  return -1;

		  } else if(silverMedals == anotherTeamsSilverMedals) {

			  if(bronzeMedals > anotherTeamsBronzeMedals) {

				  return -1;

			  } else if(bronzeMedals == anotherTeamsBronzeMedals) {

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