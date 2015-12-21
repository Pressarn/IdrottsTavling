import java.util.ArrayList;

public class Team implements Comparable<Team> {

  private String name;
  ArrayList<Medal> goldMedals = new ArrayList<>();
  ArrayList<Medal> silverMedals = new ArrayList<>();
  ArrayList<Medal >bronzeMedals = new ArrayList<>();
  
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
  
  public ArrayList<Participant> getMembers() {
	  
	  return members;
	  
  }
  
  public boolean hasNoMembers() {
	  
	  return members.isEmpty();
	  
  }
  
  public void assignGoldMedal(Medal goldMedal) {
	  
	  goldMedals.add(goldMedal);
	  goldMedal.setOwner(this);
	  
  }
  
  public void removeGoldMedal(Medal goldMedal) {
	  
	goldMedals.remove(goldMedal);  
	
  }
  
  public void assignSilverMedal(Medal silverMedal) {
	  
	  silverMedals.add(silverMedal);
	  silverMedal.setOwner(this);
	  
  }
  
  public void removeSilverMedal(Medal silverMedal) {
	  
	  silverMedals.remove(silverMedal);
	  
  }

  public void assignBronzeMedal(Medal bronzeMedal) {
	  
	  bronzeMedals.add(bronzeMedal);
	  bronzeMedal.setOwner(this);
	  
  }
  
  public void removeBronzeMedal(Medal bronzeMedal) {
	  
	  bronzeMedals.remove(bronzeMedal);
	  
  }
  
  public boolean hasNotEarnedGoldMedalInEvent(Event eventAchievedIn) {
	  
	  for(Medal medal : goldMedals) {
		  
		  if(medal.getEarnedIn() == eventAchievedIn) {
			  
			  return false;
			  
		  }
		  
	  }
	  return true;
	  
  }
  
  public boolean hasNotEarnedSilverMedalInEvent(Event eventAchievedIn) {
	  
	  for(Medal medal : silverMedals) {
		  
		  if(medal.getEarnedIn() == eventAchievedIn) {
			  
			  return false;
			  
		  }
		  
	  }
	  return true;
	  
  }
  
  public boolean hasNotEarnedBronzeMedalInEvent(Event eventAchievedIn) {
	  
	  for(Medal medal : bronzeMedals) {
		  
		  if(medal.getEarnedIn() == eventAchievedIn) {
			  
			  return false;
			  
		  }
		  
	  }
	  return true;
	  
}
  
  public String getMedals() {
	  
	  return String.format(
			  "%s\nGold medals: %s, Silver medals: %s, Bronze medals: %s\n",
			  name, 
			  goldMedals.size(), 
			  silverMedals.size(), 
			  bronzeMedals.size()
			  );
	  
  }
  
  //Refaktorera, dela upp i flera delmetoder
  public int compareTo(Team anotherTeam) {
	  
	  if(this.goldMedals.size() > anotherTeam.goldMedals.size()) {

		  return -1;

	  } else if (this.goldMedals.size() == anotherTeam.goldMedals.size()) {

		  if(this.silverMedals.size() > anotherTeam.silverMedals.size()) {

			  return -1;

		  } else if(this.silverMedals.size() == anotherTeam.silverMedals.size()) {

			  if(this.bronzeMedals.size() > anotherTeam.bronzeMedals.size()) {

				  return -1;

			  } else if(this.bronzeMedals.size() == anotherTeam.bronzeMedals.size()) {

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