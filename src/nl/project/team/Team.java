package nl.project.team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import nl.project.user.User;

@Entity
public class Team {
	
	@Column(name="id")
	private Long id;
	
	private String name;
	private String sport;
	private User coach;
	
	
/*	private User manager;*/

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
	
	@OneToOne
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
 	*/
	
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
