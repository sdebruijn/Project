package nl.project.team;

import java.util.List;

import nl.project.user.User;

public class Team {
	private final String teamId; // or int?
	private String name;
	private List<User> members; // TODO: List or Set?
	private User manager;

	public Team(String teamId, String name, List<User> members, User manager) {
		this.teamId = teamId;
		this.name = name;
		this.members = members;
		this.manager = manager;
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
