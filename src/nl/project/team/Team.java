package nl.project.team;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import nl.project.event.DefaultEvent;
import nl.project.user.User;

@Entity
public class Team {
	
	@Column(name="id")
	private Long id;
	
	private String name;
	private List<User> members; 
	private List<DefaultEvent> events;

	//private User manager;
	private User coach;
	private String sport;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany()
	@JoinColumn(name="team_id")
	public List<DefaultEvent> getDefaultEvents() {
		return events;
	}
	
	public void setEvents(List<DefaultEvent> events) {
		this.events = events;
	}
	
	@ManyToMany
	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	@OneToOne
	public User getCoach() {
		return coach;
	}

	public void setCoach(User coach) {
		this.coach = coach;
	}

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
	
/*	@Override
	public String toString() {
		return "Team " + this.name;
		
	}*/
}
