package nl.project.user;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import nl.project.event.Event;
import nl.project.team.Team;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"name", "surname"})) 
public class User {
	/*** Fields ***/
	private Long id;
	
	@NotBlank
	@Size(min=2,max=45)
	private String name;
	
	@NotNull
	@Size(min=2,max=45)
	private String surname;
	
	private List<Team> teams;
	private List<Event> eventsPresent;
	private List<Event> eventsAbsent;
	
	/*** Constructors ***/	
	public User(){}
	
	public User(String name, String surname) {
		this.setName(name);
		this.setSurname(surname);
	}
	
	public User (Long id, String name, String surname){
		this(name, surname);
		this.id = id;
	}
	
	/*** Getters and Setters ***/
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName (){
		return name;
	}
	
	public void setName(String name){
		name = name.trim();
		if (name == null){
			throw new NullPointerException();
		}
		else if (name.isEmpty()){
			throw new IllegalArgumentException();
		}
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		surname = surname.trim();
		if (surname == null){
			throw new NullPointerException();
		}
		else if (surname.isEmpty()){
			throw new IllegalArgumentException();
		}
		this.surname = surname;
	}

	@ManyToMany(mappedBy="members", fetch = FetchType.EAGER)
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	@ManyToMany(mappedBy="present", fetch = FetchType.EAGER)
	public List<Event> getEventsPresent() {
		return eventsPresent;
	}

	public void setEventsPresent(List<Event> eventsPresent) {
		this.eventsPresent = eventsPresent;
	}

	@ManyToMany(mappedBy="absent", fetch = FetchType.EAGER)
	public List<Event> getEventsAbsent() {
		return eventsAbsent;
	}

	public void setEventsAbsent(List<Event> eventsAbsent) {
		this.eventsAbsent = eventsAbsent;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) { 
			return true;
		}
		if (!(o instanceof User)){
			return false;
		}
		User u = (User)o;
		
		if ( this.name.equals(u.name) && 
			 this.surname.equals(u.surname) ) { 
				return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "User: " + name + " " + surname;
	}
}
