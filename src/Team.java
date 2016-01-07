import java.util.ArrayList;
import java.util.HashMap;

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

			try {

				HashMap<String, Integer> membersMedals = member.calculateMedals();

				incrementGoldMedals(membersMedals.get("Gold medals"));
				incrementSilverMedals(membersMedals.get("Silver medals"));
				incrementBronzeMedals(membersMedals.get("Bronze medals"));

			} catch(NullPointerException e) {

				continue;

			}

		}
	  
  }
  
  public HashMap<String, Integer> getMedals() {
	  
	  return getMedals();
	  
  }
  
  //Refaktorera, dela upp i flera delmetoder
  public int compareTo(Team anotherTeam) {
	  
	  int goldMedals = getGoldMedals();
	  int silverMedals = getSilverMedals();
	  int bronzeMedals = getBronzeMedals();
	  
	  int anotherTeamsGoldMedals = anotherTeam.getGoldMedals();
	  int anotherTeamsSilverMedals = anotherTeam.getSilverMedals();
	  int anotherTeamsBronzeMedals = anotherTeam.getBronzeMedals();
	  
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
			  getGoldMedals(), 
			  getSilverMedals(), 
			  getBronzeMedals()
			  );
	  
  }
  
  public String toString() {
	  
	 return String.format("%s", name);
	 
	  
  }

}