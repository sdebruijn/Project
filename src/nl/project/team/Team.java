package nl.project.team;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import nl.project.user.UserOld;

@Entity
public class Team {
	
	private Long teamId;
	
	private String name;
	private List<UserOld> members; // TODO: List or Set?
	private UserOld manager;
	private UserOld coach;

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Team (){
	}
	
	public Team (Long teamId){
		this.teamId = teamId;
	}
	
	public	Team (Long teamId, String name, UserOld manager){
		this.teamId = teamId;
		this.name = name;
		this.manager = manager;
	}

	public Team(Long teamId, String name, List<UserOld> members, UserOld manager) {
		this.teamId = teamId;
		this.name = name;
		this.members = members;
		this.manager = manager;
	}

	public UserOld getCoach() {
		return coach;
	}

	public void setCoach(UserOld coach) {
		this.coach = coach;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public UserOld getManager() {
		return this.manager;
	}
	
	public void setManager(UserOld manager) {
		if (manager != null){
			this.manager = manager;
		}
		else {
			throw new NullPointerException("Er is geen manager meegegeven");
		}
	}

	public List<UserOld> getMembers() {
		return new ArrayList<>(members);
	}

	public boolean addMember(UserOld member) {
		if (member == null) { 
			return false;
		}
		
		if (!members.contains(member)) {
			return members.add(member);
		}
		
		return false;
	}
	
	public boolean removeMember(UserOld member) {
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
