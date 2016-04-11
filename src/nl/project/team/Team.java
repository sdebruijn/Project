package nl.project.team;

import java.util.ArrayList;
import java.util.List;

import nl.project.user.User;

public class Team {
	private final String teamId; // or int?
	private String name;
	private List<User> members; // TODO: List or Set?
	private User manager;
	private User coach;

	public Team (){
		teamId = "";
	}
	
	public Team (String teamId){
		this.teamId = teamId;
	}
	
	public	Team (String teamId, String name, User manager){
		this.teamId = teamId;
		this.name = name;
		this.manager = manager;
	}

	public Team(String teamId, String name, List<User> members, User manager) {
		this.teamId = teamId;
		this.name = name;
		this.members = members;
		this.manager = manager;
	}

	public User getCoach() {
		return coach;
	}

	public void setCoach(User coach) {
		this.coach = coach;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return this.teamId;
	}
	
	public User getManager() {
		return this.manager;
	}
	
	public void setManager(User manager) {
		if (manager != null){
			this.manager = manager;
		}
		else {
			throw new NullPointerException("Er is geen manager meegegeven");
		}
	}

	public String getTeamId() {
		return teamId;
	}

	public List<User> getMembers() {
		return new ArrayList<>(members);
	}

	public boolean addMember(User member) {
		if (member == null) { 
			return false;
		}
		
		if (!members.contains(member)) {
			return members.add(member);
		}
		
		return false;
	}
	
	public boolean removeMember(User member) {
		return members.remove(member);
	}
	
	public void clearMembers(){
		members.clear();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Team)) {
			return false;
		}
		
		Team other = (Team) obj;
		return this.teamId.equals(other.teamId) && this.name.equals(other.name);
	}
	
	@Override
	public String toString() {
		return "Team " + this.name + " (manager: " + this.manager.getName() + ")";
		
	}
}
