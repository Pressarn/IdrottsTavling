import java.util.ArrayList;
import java.util.HashMap;

public class Team implements Comparable<Team> {

  private String name;
  
  private ArrayList<Participant> members = new ArrayList<>();
  
//  private int numberOfGoldMedals;
//  private int numberOfSilverMedals;
//  private int numberOfBronzeMedals;
  
  private HashMap<String, Integer> medals = new HashMap<>();
  
  public Team(String name) {
	  
	  this.name = name;
	  
	  medals.put("Gold medals", 0);
	  medals.put("Silver medals", 0);
	  medals.put("Bronze medals", 0);
	  
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
	  
	  medals.put("Gold medals", 0);
	  medals.put("Silver medals", 0);
	  medals.put("Bronze medals", 0);
	  
	  for(Participant member : members) {
		  
		  try {
			  
			  HashMap<String, Integer> membersMedals = member.getNumberOfMedals();
			  
			  medals.put("Gold medals", medals.get("Gold medals") + membersMedals.get("Gold medals"));
			  medals.put("Silver medals", medals.get("Silver medals") + membersMedals.get("Silver medals"));
			  medals.put("Bronze medals", medals.get("Bronze medals") + membersMedals.get("Bronze medals"));
			  
		  } catch(NullPointerException e) {
			  
			  continue;
			  
		  }
		  
	  }
	  
  }
  
  public HashMap<String, Integer> getMedals() {
	  
	  return medals;
	  
  }
  
  //Refaktorera, dela upp i flera delmetoder
  public int compareTo(Team anotherTeam) {
	  
	  int goldMedals = medals.get("Gold medals");
	  int silverMedals = medals.get("Silver medals");
	  int bronzeMedals = medals.get("Bronze medals");
	  
	  int anotherTeamsGoldMedals = anotherTeam.medals.get("Gold medals");
	  int anotherTeamsSilverMedals = anotherTeam.medals.get("Silver medals");
	  int anotherTeamsBronzeMedals = anotherTeam.medals.get("Bronze medals");
	  
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
			  medals.get("Gold medals"), 
			  medals.get("Silver medals"), 
			  medals.get("Bronze medals")
			  );
	  
  }
  
  public String toString() {
	  
	 return String.format("%s", name);
	 
	  
  }

}