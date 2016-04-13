package nl.project.team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Team {
	
	@Column(name="id")
	private Long id;
	
	private String name;
	private String sport;
	
//	@OneToMany
//	private List<User> members;
//	
/*	private List<User> members; // TODO: List or Set?
	private User manager;
	private User coach;*/

/*	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}
*/
	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	public void setId(Long teamId) {
		this.id = teamId;
	}

	public Team (){
	}
	
	public Team (Long id){
		this.id = id;
	}
	
/*	public	Team (Long id, String name, User manager){
		this.teamId = teamId;
		this.name = name;
		this.manager = manager;
	}

	public Team(Long id, String name, List<User> members, User manager) {
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
	}*/
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
/*	
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
	}*/
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Team)) {
			return false;
		}
		
		Team other = (Team) obj;
		return this.id.equals(other.id) && this.name.equals(other.name);
	}
	
	@Override
	public String toString() {
		return "Team " + this.name;
		
	}
}
