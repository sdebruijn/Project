package nl.project.team;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import nl.project.user.User;

@Entity
public class Team {
	
	@Column(name="id")
	private Long id;
	
	private String name;
	private List<User> members; 
	//private List<Event> events;

	@NotNull
	private User manager;
	private User coach;
	private String sport;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/*
	@ManyToMany(mappedBy="teams")
	public List<Event> getEvents() {
		return events;
	}
	
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	*/
	
	@OneToMany(mappedBy="team",
			cascade={CascadeType.PERSIST, CascadeType.MERGE}
			)
	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="coach_id")
	public User getCoach() {
		return coach;
	}

	public void setCoach(User coach) {
		this.coach = coach;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager_id")
	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
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

	public void addMember(User u){
		if (this.members == null) {
			this.members = new ArrayList<User>();
		}
		this.members.add(u);
	}
	
	public void removeMember(User u){
		members.remove(u);
	}
	
	public void removeAllMembers(){
		members.clear();
	}
	
	/*
	public void addEvent(Event u){
		this.events.add(u);
	}
	
	public void sortEvents(){
		Collections.sort(events, new Comparator<Event>() {
		    @Override
		    public int compare(Event r1, Event r2) {
		    	System.out.println(r1.getTitle() + " " + r2.getTitle());
		        return r1.getDate().compareTo(r2.getDate());
		    }
		});
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
